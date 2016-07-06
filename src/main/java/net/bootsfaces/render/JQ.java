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

import javax.faces.context.ResponseWriter;

/**
 * Class with jQuery specific encoding functions
 * @author thecoder4.eu
 */
public final class JQ {
    
    public static final String TEXT_JS="text/javascript";
    public static final String SCRIPT="script";
    
    public static final String START_F="$(function(){";
    public static final String END_F="});";
    public static final String CLOSE_F=");";
    public static final String JS_SUFFIX="_js";
    public static final String jQo ="$('";
    public static final String jQc ="')";
    public static final String DOT=".";
    
    public static final String CID="CID"; //Client ID
    public static final String WID="WID"; //Widget ID
    public static final String TID="TID"; //Toggler ID
    public static final String SEL="SEL"; //Selector
    //public static final String DP_START= "$('#WID').datepicker({";
    public static final String DP_START= "$(SEL).datepicker({";
    public static final String DP_SEL_DIV="'div[id=\"WID\"]'";
    public static final String DP_SEL_INPUT="'input[id=\"WID\"]'";
    //public static final String DP_START_DIV= "$('div[id=\"WID\"]').datepicker({";
    //public static final String DP_START_INPUT= "$('input[id=\"CID\"]').datepicker({";
    public static final String DP_REGION= "$(SEL).datepicker(\"option\",$.datepicker.regional[\"REGION\"]);";
    //public static final String DP_INPUT_REGION= "$('input[id=\"CID\"]').datepicker(\"option\",$.datepicker.regional[\"LANG\"]);";
    public static final String DP_SELECT="onSelect: function(dateText, inst) { $('input[id=\"CID\"]').val(dateText); }";
    public static final String DP_CLICK="$('span[id=\"TID\"]').click(function() {";
    public static final String DP_CLICK_BODY="var el=$('input[id=\"CID\"]');";
    public static final String DP_TOGGLER="if(el.attr('data-DPisVisible') == 'true') {el.attr('data-DPisVisible', 'false');el.datepicker('hide');}else{el.attr('data-DPisVisible', 'true');el.datepicker('show');}";
    
    //Because JQ1.8+ gives Error: Syntax error, unrecognized expression: unsupported pseudo: j_idt13
    //with #id0:id1 we use the [id=] selector
    public static final String SLIDER_START= "$('div[id=\"CID_slider\"]').slider({";
    public static final String SLIDER_SLIDE="slide: function( event, ui ) { $('input[name=\"CID\"]').val( ui.value ); BADGE}";
    public static final String SLIDER_SLIDE_BADGE="$('span[id=\"CID_badge\"]').text( ui.value ); ";
    public static final String SLIDER_UPDATE="$('input[name=\"CID\"]').val($('div[id=\"CID_slider\"]').slider('value'));";
    public static final String SLIDER_INPUT_KEYUP="$('input[name=\"CID\"]').keyup(function( event ) {$('div[id=\"CID_slider\"]').slider('value',$('input[name=\"CID\"]').val())});";
    
    //$('div[id="fslider:j_idt60_slider"] > .ui-slider-handle').addClass('ui-slider-handle-round');
    public static final String SLIDER_HANDLE_ROUND="$('div[id=\"CID_slider\"] > .ui-slider-handle').addClass('ui-slider-handle-round');";
    public static final String SLIDER_HANDLE_MD="$('div[id=\"CID_slider\"] > .ui-slider-handle').addClass('ui-slider-handle-md');";
    public static final String SLIDER_HANDLE_LG="$('div[id=\"CID_slider\"] > .ui-slider-handle').addClass('ui-slider-handle-lg');";
    
    public static final String MODAL= "$('#CID').modal({ show: false });";
    
    protected static final void startInlineF(ResponseWriter rw, String cId) throws IOException {
        rw.startElement(SCRIPT, null);
        rw.writeAttribute("id", cId.concat(JS_SUFFIX), null);
        rw.writeAttribute("type", TEXT_JS, null);
        rw.write(START_F);
    }
    protected static final void endInlineF(ResponseWriter rw) throws IOException {
        rw.write(END_F);
        rw.endElement(SCRIPT);
    }
    public static final void datePicker(ResponseWriter rw, String cId, String dpId, String opts, String region) throws IOException {
        startInlineF(rw, cId);
        StringBuilder sb = new StringBuilder(150); //optimize int
        
        String sel=DP_SEL_INPUT.replace(WID, dpId);
        if(region!=null) //set region defaults first, so they can be overriden by custom options
            sb.append(DP_REGION.replace(SEL, sel).replace("REGION", region));
        
        if(!cId.equals(dpId)) { //Inline
            sel=DP_SEL_DIV.replace(WID, dpId);
            sb.append(DP_START.replace(SEL, sel)).append(opts);
            //sb.append(DP_START_DIV.replace(WID, dpId)).append(opts);
            sb.append(DP_SELECT.replace(CID, cId));
        } else {
            sb.append(DP_START.replace(SEL, sel)).append(opts);
            //sb.append(DP_START_INPUT.replace(CID, dpId)).append(opts);
        }
        sb.append(END_F);
        rw.write(sb.toString());
        endInlineF(rw);
    }
    
    public static final void datePickerToggler(ResponseWriter rw, String cId, String tId) throws IOException {
        startInlineF(rw, tId);
        StringBuilder sb = new StringBuilder(260); //optimize int
        sb.append(DP_CLICK.replace(TID, tId)).append(DP_CLICK_BODY.replace(CID, cId))
                .append(DP_TOGGLER.concat(END_F));
        rw.write(sb.toString());
        endInlineF(rw);
    }
    
    public static void simpleSlider(ResponseWriter rw, String cId, String opts, boolean badge, String hsize, boolean hround) throws IOException {
        startInlineF(rw, cId);
        StringBuilder sb = new StringBuilder(150); //optimize int
        
        if(badge) {
            sb.append(
                    SLIDER_START.concat(opts)
                    .concat(SLIDER_SLIDE.replace("BADGE",SLIDER_SLIDE_BADGE.replace(CID, cId)))
                    .concat(END_F).replace(CID, cId)
            );
        } else { sb.append(SLIDER_START.concat(opts).concat(SLIDER_SLIDE.replace("BADGE","")).concat(END_F).replace(CID, cId)); }
        sb.append(SLIDER_UPDATE.replace(CID, cId));
        sb.append(SLIDER_INPUT_KEYUP.replace(CID, cId));
        
        if( (hsize!=null) && (hsize.equals("md")) ) { sb.append(SLIDER_HANDLE_MD.replace(CID, cId)); }
        if( (hsize!=null) && (hsize.equals("lg")) ) { sb.append(SLIDER_HANDLE_LG.replace(CID, cId)); }
        if(hround) { sb.append(SLIDER_HANDLE_ROUND.replace(CID, cId)); }
        
        rw.write(sb.toString());
        endInlineF(rw);
    }
    
    
    public static final String[] SLIDER_OPTS= {
        "max", "min", "orientation", "range", "step"
    };
    
    /*
     * Slider Events
     */
    public static final String SLIDE= "slide";
    
    /*
     * jQuery DatePicker Supported Options/Attributes
     */
    public static final String NUMOFMONTHS= "numberOfMonths"; //Int
    public static final String FIRSTDAY = "firstDay"; //Int - Set the first day of the week: Sunday is 0, Monday is 1, etc.
    public static final String SHOWBUTS = "showButtonPanel"; //Bool
    public static final String CHNGMONTH = "changeMonth"; //Bool
    public static final String CHNGYEAR = "changeYear"; //Bool
    public static final String SHOWWK = "showWeek"; //Bool
    public static final String DTFORMAT = "dateFormat";
    public static final String DTDISABLED = "disabled"; //Bool
    /*
     * Default: "c-10:c+10" 
     * The range of years displayed in the year drop-down:
     * either relative to today's year ("-nn:+nn"), relative to the currently selected year ("c-nn:c+nn"),
     * absolute ("nnnn:nnnn"), or combinations of these formats ("nnnn:-nn").
     * Note that this option only affects what appears in the drop-down, 
     * to restrict which dates may be selected use the minDate and/or maxDate options.
     */
    public static final String YRANGE = "yearRange";
    public static final String LOCALE = "locale";
    public static final String MINDATE = "minDate";
    public static final String MAXDATE = "maxDate";
    public static final String DEFAULTDT = "defaultDate";  //Set the date to highlight on first opening if the field is blank. (e.g. +7 +1m +3d ...)
    public static final String SHOWON="showOn";
    public static final String LANG= "lang";
    
    public static final String[] DP_OPTS= {
        NUMOFMONTHS, SHOWBUTS, CHNGMONTH, CHNGYEAR, DTFORMAT,
        YRANGE, LOCALE, MINDATE, MAXDATE, SHOWWK, DEFAULTDT, FIRSTDAY
    };

}
