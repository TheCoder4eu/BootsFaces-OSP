package net.bootsfaces.decorator;
/**
 *  (C) 2013-2015 Stephan Rauh http://www.beyondjava.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.faces.view.Location;
import javax.faces.view.facelets.TagAttribute;

/** Create a tag attribute by means of reflection (so that they work with both Mojarra and MyFaces). */
public class TagAttributeUtilities {
	public static TagAttribute createTagAttribute(Location location, String ns, String myLocalName, String qName,
			String value) {
		try {
			Class<?> implClass;
			if (Configuration.myFaces)
				implClass = Thread.currentThread().getContextClassLoader()
						.loadClass("org.apache.myfaces.view.facelets.tag.TagAttributeImpl");
			else
				implClass = Thread.currentThread().getContextClassLoader()
						.loadClass("com.sun.faces.facelets.tag.TagAttributeImpl");
			Constructor<?> constructor = implClass.getConstructor(Location.class, String.class, String.class,
					String.class, String.class);
			Object newTagAttribute = constructor.newInstance(location, ns, myLocalName, qName, value);
			return (TagAttribute) newTagAttribute;
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(
					"Couldn't create neither a Oracle Mojarra Tag attribute nor an Apache MyFaces TagAttribute", e);

		} catch (NoSuchMethodException e) {
			throw new IllegalArgumentException(
					"Couldn't create neither a Oracle Mojarra Tag attribute nor an Apache MyFaces TagAttribute", e);

		} catch (InvocationTargetException e) {
			throw new IllegalArgumentException(
					"Couldn't create neither a Oracle Mojarra Tag attribute nor an Apache MyFaces TagAttribute", e);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(
					"Couldn't create neither a Oracle Mojarra Tag attribute nor an Apache MyFaces TagAttribute", e);
		} catch (InstantiationException e) {
			throw new IllegalArgumentException(
					"Couldn't create neither a Oracle Mojarra Tag attribute nor an Apache MyFaces TagAttribute", e);
		}
	}
}
