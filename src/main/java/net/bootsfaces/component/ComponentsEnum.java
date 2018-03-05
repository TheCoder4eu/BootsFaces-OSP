/**
 *  Copyright 2014 - 17 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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
package net.bootsfaces.component;

public enum ComponentsEnum {
	accordion("<b:accordion", "accordion", "net.bootsfaces.component.accordion.Accordion"),
	alert("<b:alert", "alert", "net.bootsfaces.component.alert.Alert"),
	badge("<b:badge", "badge", "net.bootsfaces.component.badge.Badge"),
	breadcrumbs("<b:breadcrumbs", "breadcrumbs", "net.bootsfaces.component.breadcrumbs.Breadcrumbs"),
	button("<b:button", "button", "net.bootsfaces.component.button.Button"),
	buttonGroup("<b:buttonGroup", "buttonGroup", "net.bootsfaces.component.buttonGroup.ButtonGroup"),
	buttonToolbar("<b:buttonToolbar", "buttonToolbar", "net.bootsfaces.component.buttonToolbar.ButtonToolbar"),
	canvas("<b:canvas", "canvas", "net.bootsfaces.component.canvas.Canvas"),
	carousel("<b:carousel", "carousel", "net.bootsfaces.component.carousel.Carousel"),
	carouselItem("<b:carouselItem", "carouselItem", "net.bootsfaces.component.carouselItem.CarouselItem"),
	carouselCaption("<b:carouselCaption", "carouselCaption",
			"net.bootsfaces.component.carouselCaption.CarouselCaption"),
	carouselControl("<b:carouselControl", "carouselControl",
			"net.bootsfaces.component.carouselControl.CarouselControl"),
	colorPicker("<b:colorPicker", "colorPicker", "net.bootsfaces.component.colorPicker.ColorPicker"),
	column("<b:column", "column", "net.bootsfaces.component.column.Column"),
	commandButton("<b:commandButton", "commandButton", "net.bootsfaces.component.commandButton.CommandButton"),
	commandLink("<b:commandLink", "commandLink", "net.bootsfaces.component.commandLink.CommandLink"),
	container("<b:container", "container", "net.bootsfaces.component.container.Container"),
	dataTable("<b:dataTable", "dataTable", "net.bootsfaces.component.dataTable.DataTable"),
	dataTableColumn("<b:dataTableColumn", "dataTableColumn",
			"net.bootsfaces.component.dataTableColumn.DataTableColumn"),
	datepicker("<b:datepicker", "datepicker", "net.bootsfaces.component.datepicker.Datepicker"),
	dateTimePicker("<b:dateTimePicker", "dateTimePicker", "net.bootsfaces.component.dateTimePicker.DateTimePicker"),
	defaultCommand("<b:defaultCommand", "defaultCommand", "net.bootsfaces.component.defaultCommand.DefaultCommand"),
	dropButton("<b:dropButton", "dropButton", "net.bootsfaces.component.dropButton.DropButton"),
	dropMenu("<b:dropMenu", "dropMenu", "net.bootsfaces.component.dropMenu.DropMenu"),
	fetchBeanInfos("<b:fetchBeanInfos", "fetchBeanInfos", "net.bootsfaces.component.fetchBeanInfos.FetchBeanInfos"),
	form("<b:form", "form", "net.bootsfaces.component.form.Form"),
	flyOutMenu("<b:flyOutMenu", "flyOutMenu", "net.bootsfaces.component.flyOutMenu.FlyOutMenu"),
	focus("<b:focus", "focus", "net.bootsfaces.component.focus.Focus"),
	fullCalendar("<b:fullCalendar", "fullCalendar", "net.bootsfaces.component.fullCalendar.FullCalendar"),
	growl("<b:growl", "growl", "net.bootsfaces.component.growl.Growl"),
	gyroscope("<b:gyroscope", "gyroscope", "net.bootsfaces.component.gyroscope.Gyroscope"),
	icon("<b:icon", "icon", "net.bootsfaces.component.icon.Icon"),
	iconAwesome("<b:iconAwesome", "iconAwesome", "net.bootsfaces.component.iconAwesome.IconAwesome"),
	image("<b:image", "image", "net.bootsfaces.component.image.Image"),
	inputSecret("<b:inputSecret", "inputSecret", "net.bootsfaces.component.inputSecret.InputSecret"),
	inputText("<b:inputText", "inputText", "net.bootsfaces.component.inputText.InputText"),
	inputTextarea("<b:inputTextarea", "inputTextarea", "net.bootsfaces.component.inputTextarea.InputTextarea"),
	internalFALink("<b:internalFALink", "internalFALink", "net.bootsfaces.component.internalFALink.InternalFALink"),
	internalIE8CompatibilityLink("<b:internalIE8CompatibilityLink", "internalIE8CompatibilityLink",
			"net.bootsfaces.component.internalIE8CompatibilityLink.InternalIE8CompatibilityLink"),
	internalJavaScriptResource("<b:internalJavaScriptResource", "internalJavaScriptResource",
			"net.bootsfaces.component.internalJavaScriptResource.InternalJavaScriptResource"),
	internalCssScriptResource("<b:internalCssScriptResource", "internalCssScriptResource",
			"net.bootsfaces.component.internalCssScriptResource.InternalCssScriptResource"),
	jumbotron("<b:jumbotron", "jumbotron", "net.bootsfaces.component.jumbotron.Jumbotron"),
	kebab("<b:kebab", "kebab", "net.bootsfaces.component.kebab.Kebab"),
	label("<b:label", "label", "net.bootsfaces.component.label.Label"),
	link("<b:link", "link", "net.bootsfaces.component.link.Link"),
	listLinks("<b:listLinks", "listLinks", "net.bootsfaces.component.listLinks.ListLinks"),
	message("<b:message", "message", "net.bootsfaces.component.message.Message"),
	messages("<b:messages", "messages", "net.bootsfaces.component.messages.Messages"),
	modal("<b:modal", "modal", "net.bootsfaces.component.modal.Modal"),
	navBar("<b:navBar", "navBar", "net.bootsfaces.component.navBar.NavBar"),
	navCommandLink("<b:navCommandLink", "navCommandLink", "net.bootsfaces.component.navCommandLink.NavCommandLink"),
	navLink("<b:navLink", "navLink", "net.bootsfaces.component.navLink.NavLink"),
	navbarLinks("<b:navbarLinks", "navbarLinks", "net.bootsfaces.component.navbarLinks.NavbarLinks"),
	panel("<b:panel", "panel", "net.bootsfaces.component.panel.Panel"),
	panelGrid("<b:panelGrid", "panelGrid", "net.bootsfaces.component.panelGrid.PanelGrid"),
	pillLinks("<b:pillLinks", "pillLinks", "net.bootsfaces.component.pillLinks.PillLinks"),
	poll("<b:poll", "poll", "net.bootsfaces.component.poll.Poll"),
	progressBar("<b:progressBar", "progressBar", "net.bootsfaces.component.progressBar.ProgressBar"),
	radiobutton("<b:radiobutton", "radiobutton", "net.bootsfaces.component.radiobutton.Radiobutton"),
	remoteCommand("<b:remoteCommand", "remoteCommand", "net.bootsfaces.component.remoteCommand.RemoteCommand"),
	row("<b:row", "row", "net.bootsfaces.component.row.Row"),
	scrollUp("<b:scrollUp", "scrollUp", "net.bootsfaces.component.scrollUp.ScrollUp"),
	scrollSpy("<b:scrollSpy", "scrollSpy", "net.bootsfaces.component.scrollSpy.ScrollSpy"),
	selectBooleanCheckbox("<b:selectBooleanCheckbox", "selectBooleanCheckbox",
			"net.bootsfaces.component.selectBooleanCheckbox.SelectBooleanCheckbox"),
	shake("<b:shake", "shake", "net.bootsfaces.component.shake.Shake"),
	spinner("<b:spinner", "spinner", "net.bootsfaces.component.spinner.Spinner"),
	switchComponent("<b:switch", "switch", "net.bootsfaces.component.switch.Switch"),
	selectMultiMenu("<b:selectMultiMenu", "selectMultiMenu",
			"net.bootsfaces.component.selectMultiMenu.SelectMultiMenu"),
	selectOneMenu("<b:selectOneMenu", "selectOneMenu", "net.bootsfaces.component.selectOneMenu.SelectOneMenu"),
	slider("<b:slider", "slider", "net.bootsfaces.component.slider.Slider"),
	slider2("<b:slider2", "slider2", "net.bootsfaces.component.slider2.Slider2"),
	socialShare("<b:socialShare", "socialShare", "net.bootsfaces.component.socialShare.SocialShare"),
	touchSpin("<b:touchSpin", "touchSpin", "net.bootsfaces.component.touchSpin.TouchSpin"),
	tab("<b:tab", "tab", "net.bootsfaces.component.tab.Tab"),
	tabLinks("<b:tabLinks", "tabLinks", "net.bootsfaces.component.tabLinks.TabLinks"),
	tabView("<b:tabView", "tabView", "net.bootsfaces.component.tabView.TabView"),
	tree("<b:tree", "tree", "net.bootsfaces.component.tree.Tree"),
	thumbnail("<b:thumbnail", "thumbnail", "net.bootsfaces.component.thumbnail.Thumbnail"),
	video("<b:video", "video", "net.bootsfaces.component.video.Video"),
	well("<b:well", "well", "net.bootsfaces.component.well.Well"),
	formGroup("<b:formGroup", "formGroup", "net.bootsfaces.component.formGroup.FormGroup");
	private String tag;

	private String tagname;

	private String classname;

	ComponentsEnum(String tag, String tagname, String classname) {
		this.tag = tag;
		this.tagname = tagname;
		this.classname = classname;
	}

	public String tag() {
		return tag;
	}

	public String tagname() {
		return tagname;
	}

	public String classname() {
		return classname;
	}
}
