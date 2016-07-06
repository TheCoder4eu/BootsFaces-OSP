/**
 *  Copyright 2014-2016 Riccardo Massera (TheCoder4.Eu)
 *  
 *  This file is part of BootsFaces.
 *  
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
 */
package net.bootsfaces.render;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Map.Entry;

import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.application.ResourceHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 * Also see the discussion at http://stackoverflow.com/questions/14963756/prevent-suffix-from-being-added-to-resources-when-page-loads.
 * @since 0.3.8
 */
public class UnmappedResourceHandler extends ResourceHandlerWrapper {

    private ResourceHandler wrapped;

    public UnmappedResourceHandler(ResourceHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public Resource createResource(final String resourceName, final String libraryName) {
        final Resource resource = super.createResource(resourceName, libraryName);

        if (resource == null) {
            return null;
        }
        return new BsfResWrapper(resource);
        
    }

    @Override
    public boolean isResourceRequest(FacesContext context) {
        return ResourceHandler.RESOURCE_IDENTIFIER.equals(context.getExternalContext().getRequestServletPath());
    }

    @Override
    public void handleResourceRequest(FacesContext context) throws IOException {
        ExternalContext externalContext = context.getExternalContext();
        String resourceName = externalContext.getRequestPathInfo();
        String libraryName = externalContext.getRequestParameterMap().get("ln");
        Resource resource = context.getApplication().getResourceHandler().createResource(resourceName, libraryName);

        if (resource == null) {
            super.handleResourceRequest(context);
            return;
        }

        if (!resource.userAgentNeedsUpdate(context)) {
            externalContext.setResponseStatus(HttpServletResponse.SC_NOT_MODIFIED);
            return;
        }

        externalContext.setResponseContentType(resource.getContentType());

        for (Entry<String, String> header : resource.getResponseHeaders().entrySet()) {
            externalContext.setResponseHeader(header.getKey(), header.getValue());
        }

        ReadableByteChannel input = null;
        WritableByteChannel output = null;

        try {
            input = Channels.newChannel(resource.getInputStream());
            output = Channels.newChannel(externalContext.getResponseOutputStream());

            for (ByteBuffer buffer = ByteBuffer.allocateDirect(10240); input.read(buffer) != -1; buffer.clear()) {
                output.write((ByteBuffer) buffer.flip());
            }
        }
        finally {
            if (output != null) try { output.close(); } catch (IOException ignore) {}
            if (input != null) try { input.close(); } catch (IOException ignore) {}
        }
    }

    @Override
    public ResourceHandler getWrapped() {
        return wrapped;
    }

}
