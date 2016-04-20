##Build and general instructions
* Install the [Gradle Eclipse plugin](http://projects.eclipse.org/projects/tools.buildship)
* `git clone https://github.com/TheCoder4eu/BootsFaces-OSP.git`
* Import the Gradle project into Eclipse (don't forget to click "build model"). You may or may not include the subproject.
* refresh dependencies
* ignore the errors in the test folders
* run the Gradle build from the next section

*Note: You don't explicitly need the Gradle plugin to import the project, you can also build it manually via the included Gradle wrapper without having Gradle installed.*  

###Gradle
Run `./gradlew` (or `gradlew.bat` on Windows) in the project's root directory to trigger the full gradle build via the included [Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html).

If you want to do it manually, run the following tasks:

  * `clean` (optional on first run)
  * `buildResources` (inherited from the subproject `gradleResources`)
  * `assemble`

Now the lib folder contains a `BootsFaces-OSP-{VERSION_NUMBER}.jar` file.


####Maven
Run `mvn clean install` as usual to build the artifact and register it in your local Maven repository.

Maven will take the needed resources from `mavenResources`. You _could_ change the resources for local testing right in there, but _it's not recommended_, because those changes will be overwritten on the next [Gradle build](#gradle). If you want your changes to persist, they must be made in the Gradle resources.

####XText
To optimize the repetitive tasks of keeping several library artifacts up to date, we centralize component information in a domain-specific language. Therefore you can find the definitions of all components in the [`BootsFaces.jsfdsl`](https://github.com/TheCoder4eu/BootsFaces-OSP/blob/master/xtext/BootsFaces.jsfdsl) file.

This file is used by our [JSFLibraryGenerator](https://github.com/stephanrauh/JSFLibraryGenerator), which is available as an [eclipse plugin](https://github.com/stephanrauh/JSFLibraryGenerator/tree/master/plugins) and generates the needed Java classes, taglib and documentation files for the [documentation](https://github.com/TheCoder4eu/BootsFacesWeb).

*TODO:* document how to from https://github.com/TheCoder4eu/BootsFaces-OSP/pull/296  
*TODO:* document probably needed XText dependency


##Adding components 
###Adding a standard Bootstrap component
* examine the `build.gradle` file in the `gradleResources` folder
* if the JS file you need is excluded, remove it from the exclude list to activate it 
* add a `bs-(component).less` file to the `gradleResources/less` folder. Only `bs*.less` files are processed!
* put custom additions to the CSS file in `/gradleResources/less/bs-(component).less`
* put custom JS files into `gradleResources/js` and add them to the `build.gradle` file
* put custom CSS files into `gradleResources/css` and add them to the `build.gradle` file
* run the [Gradle build](#gradle)

* To add an arbitrary component:
  * install the [eclipse plugin](#xtext)
  * add the description of the component in `xtext/BootsFaces.jsfdsl`:
  
		  widget carouselItem implemented_by net.bootsfaces.component.Carousel
		       processes_input
		       extends UICommand
		       has_children
		       has_tooltip 
		  {
		    id                  inherited                                          "Unique identifier of the component in a namingContainer."
		    action              javax.el.MethodExpression inherited                "The button action, this can be method expression or a string outcome."
		    actionListener      javax.faces.event.ActionListener inherited         "A method expression that refers to a method with this signature: void methodName(Action-Event)."
		    ajax                Boolean                                            "Whether the Button submits the form with AJAX."
		    caption                                                                "Optional caption, which is embedded in an h3 tag. If you need more flexibility, add an carouselCaption child tag. If you don't need a caption, omit both."
		    onclick                                                                "The onclick attribute."
		    oncomplete                                                             "Javascript to be executed when ajax completes with success."
		    ondblclick                                                             "Client side callback to execute when input element is double clicked."
		    onmousedown                                                            "Client side callback to execute when a pointer input element is pressed down over input element."
		    onmousemove                                                            "Client side callback to execute when a pointer input element is moved within input element."
		    onmouseout                                                             "Client side callback to execute when a pointer input element is moved away from input element."
		    onmouseover                                                            "Client side callback to execute when a pointer input element is moved onto input element."
		    onmouseup                                                              "Client side callback to execute when a pointer input element is released over input element."
		    startAnimation      Boolean default "true"                             "tells Bootstrap to begin animating the carousel immediately when the page loads."
		    showControls        Boolean default "true"                             "adds 'left' and 'right' buttons that allows the user to go back and forth between the slides manually"
		    showIndicators      Boolean default "true"                             "The indicators are the little dots at the bottom of each slide (which indicates how many slides there are in the carousel, and which slide the user are currently viewing)."
		    style                                                                  "Inline style of the input element."
		    styleClass                                                             "Style class of this element."
		    rendered            Boolean inherited                                  "Boolean value to specify the rendering of the component, when set to false component will not be rendered."
		    tooltip                                                                "The text of the tooltip."
		    tooltipDelay        Integer                                            "The tooltip is shown and hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay)."
		    tooltipDelayHide    Integer                                            "The tooltip is hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay)."
		    tooltipDelayShow    Integer                                            "The tooltip is shown with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay)."
		    tooltipPosition                                                        "Where is the tooltip to be displayed? Possible values: \"top\", \"bottom\", \"right\", \"left\", \"auto\", \"auto top\", \"auto bottom\", \"auto right\" and \"auto left\". Default to \"bottom\"."
		   }

  * copy the generated folder to the `net.bootsfaces.component` source folder
  * copy the tag from the generated taglib file into the common taglib (`src/main/meta/META-INF/bootsfaces-b.taglib.xml`).
    After that, you can delete the taglib file from the component folder.
  * Move the two *.xhtml files into the BootsFaces web project. They are part of the documentation.
  * Correct the component file:
    * add the `@ResourceDependencies` annotation to the component
    * add tooltips and AJAX (see below)
    * run organize imports
  * delete the line that renders the `rendered` attribute in the renderer class
  * start implementing the renderer. The generators generate a default implementation which simply renders the tag and every attribute (as if it was valid HTML).
    
#### Activating AJAX
  * add an on(event) attribute for every JS event you want to deal with
  * the component should implement `ClientBehaviorHolder` and `IAJAXComponent`
  * add these to the list of JavaScript AJAX events (without the prefix "on") and two methods to the component above the properties attribute:
    	   
    	  	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(
			 Arrays.asList("blur", "change", "valueChange", "click", "dblclick", ...,
					"mousedown", "mousemove", "mouseout", "mouseover", "mouseup"));

			public Collection<String> getEventNames() {
				return EVENT_NAMES;
			}
		
			public String getDefaultEventName() {
				return "click";
			}
    
  * add `AJAXRenderer.generateBootsFacesAJAXAndJavaScript(context, tabView, writer);` to the encode method to generate the JS handlers
  * remove the generation of the on(event) attributes - they are already covered by `generateBootsFacesAJAXAndJavaScript()`.
  * also remove the auto-generated `action`, `actionListener` and `ajax` methods
  * add `new AJAXRenderer().decode(context, component);` to the renderer's decode method

####Activating tooltips
* add the property `has_tooltip` to the component description
* add `Tooltip.addResourceFiles();` to the component constructor
* correct `Tooltip.activateTooltips(context, component);` as last line of the renderer encode method
      (it's already there, but uses a deprecated API)
* insert `Tooltip.generateTooltip(context, component, rw);` after the line which renders the ID
* delete the auto-generated tooltip attributes - they are already covered by `Tooltip.generateTooltip(context, component, rw);`
  
  