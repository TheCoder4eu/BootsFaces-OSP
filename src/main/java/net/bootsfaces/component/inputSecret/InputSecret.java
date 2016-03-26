/**
 *  Copyright 2014-2016 Stephan Rauh (http://www.beyondjava.net).
 *  
 *  This file is part of BootsFaces.
 *  
 *  BootsFaces is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  BootsFaces is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with BootsFaces. If not, see <http://www.gnu.org/licenses/>.
 */

package net.bootsfaces.component.inputSecret;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;

import net.bootsfaces.C;
import net.bootsfaces.component.inputText.InputText;

/**
 *
 * @author Stephan Rauh, http://www.beyondjava.net
 */

@ResourceDependencies({
    @ResourceDependency(library="bsf", name="css/core.css", target="head"),
    @ResourceDependency(library="bsf", name="css/bsf.css", target="head"),
	@ResourceDependency(library = "javax.faces", name = "jsf.js", target = "head"),
	@ResourceDependency(library = "bsf", name = "js/bsf.js", target = "head"),
    @ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head")
})
@FacesComponent("net.bootsfaces.component.inputSecret.InputSecret")
public class InputSecret extends InputText {
}
