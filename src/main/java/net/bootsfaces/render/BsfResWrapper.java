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

import javax.faces.application.Resource;
import javax.faces.application.ResourceWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author thecoder4.eu
 */
public class BsfResWrapper extends ResourceWrapper {

	Resource resource;

	public BsfResWrapper(Resource r) {
		this.resource=r;
	}

	@Override
	public String getRequestPath() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String mapping = externalContext.getRequestServletPath();

		if (externalContext.getRequestPathInfo() == null) {
			mapping = mapping.substring(mapping.lastIndexOf('.'));
		}

		String path = super.getRequestPath();

		if (mapping.charAt(0) == '/') {
			return path.replaceFirst(mapping, "");
		}
		else if (path.contains("?")) {
			return path.replace(mapping + "?", "?");
		}
		else {
			return path.substring(0, path.length() - mapping.length());
		}
	}

	@Override // Necessary because this is missing in ResourceWrapper (will be fixed in JSF 2.2).
	public String getResourceName() {
		return resource.getResourceName();
	}

	@Override // Necessary because this is missing in ResourceWrapper (will be fixed in JSF 2.2).
	public String getLibraryName() {
		return resource.getLibraryName();
	}

	@Override // Necessary because this is missing in ResourceWrapper (will be fixed in JSF 2.2).
	public String getContentType() {
		return resource.getContentType();
	}

	@Override
	public Resource getWrapped() {
		return resource;
	}

}
