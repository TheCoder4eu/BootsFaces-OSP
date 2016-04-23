package net.bootsfaces.component;

public enum ComponentsEnum {
	accordion("<b:accordion", "accordion"),
	alert("<b:alert", "alert"),
	badge("<b:badge", "badge"),
	button("<b:button", "button"),
	buttonGroup("<b:buttonGroup", "buttonGroup"),
	buttonToolbar("<b:buttonToolbar", "buttonToolbar"),
	canvas("<b:canvas", "canvas"),
	carousel("<b:carousel", "carousel"),
	carouselItem("<b:carouselItem", "carouselItem"),
	carouselCaption("<b:carouselCaption", "carouselCaption"),
	carouselControl("<b:carouselControl", "carouselControl"),
	colorPicker("<b:colorPicker", "colorPicker"),
	column("<b:column", "column"),
	commandButton("<b:commandButton", "commandButton"),
	container("<b:container", "container"),
	dataTable("<b:dataTable", "dataTable"),
	datepicker("<b:datepicker", "datepicker"),
	defaultCommand("<b:defaultCommand", "defaultCommand"),
	dropButton("<b:dropButton", "dropButton"),
	dropMenu("<b:dropMenu", "dropMenu"),
	fetchBeanInfos("<b:fetchBeanInfos", "fetchBeanInfos"),
	flyOutMenu("<b:flyOutMenu", "flyOutMenu"),
	focus("<b:focus", "focus"),
	growl("<b:growl", "growl"),
	gyroscope("<b:gyroscope", "gyroscope"),
	icon("<b:icon", "icon"),
	iconAwesome("<b:iconAwesome", "iconAwesome"),
	image("<b:image", "image"),
	inputSecret("<b:inputSecret", "inputSecret"),
	inputText("<b:inputText", "inputText"),
	inputTextarea("<b:inputTextarea", "inputTextarea"),
	internalFALink("<b:internalFALink", "internalFALink"),
	internalIE8CompatibilityLink("<b:internalIE8CompatibilityLink", "internalIE8CompatibilityLink"),
	jumbotron("<b:jumbotron", "jumbotron"),
	label("<b:label", "label"),
	listLinks("<b:listLinks", "listLinks"),
	message("<b:message", "message"),
	messages("<b:messages", "messages"),
	modal("<b:modal", "modal"),
	navBar("<b:navBar", "navBar"),
	navCommandLink("<b:navCommandLink", "navCommandLink"),
	navLink("<b:navLink", "navLink"),
	navbarLinks("<b:navbarLinks", "navbarLinks"),
	panel("<b:panel", "panel"),
	panelGrid("<b:panelGrid", "panelGrid"),
	poll("<b:poll", "poll"),
	progressBar("<b:progressBar", "progressBar"),
	row("<b:row", "row"),
	scrollUp("<b:scrollUp", "scrollUp"),
	scrollSpy("<b:scrollSpy", "scrollSpy"),
	selectBooleanCheckbox("<b:selectBooleanCheckbox", "selectBooleanCheckbox"),
	shake("<b:shake", "shake"),
	switchComponent("<b:switch", "switch"),
	selectMultiMenu("<b:selectMultiMenu", "selectMultiMenu"),
	selectOneMenu("<b:selectOneMenu", "selectOneMenu"),
	slider("<b:slider", "slider"),
	tab("<b:tab", "tab"),
	tabView("<b:tabView", "tabView"),
	tree("<b:tree", "tree"),
	thumbnail("<b:thumbnail", "thumbnail"),
	well("<b:well", "well")
    ;
    private String tag;

    private String tagname;

    ComponentsEnum(String tag, String tagname) {
        this.tag = tag;
        this.tagname = tagname;
    }

    public String tag() {
        return tag;
    }

    public String tagname() {
    	return tagname;
    }
}
