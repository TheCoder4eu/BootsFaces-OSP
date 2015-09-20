/*!
 * Copyright 2014 Riccardo Massera (TheCoder4.Eu)
 * BootsFaces JS
 * author: TheCoder4.eu
 */

BsF={};
BsF.ajax={};
BsF.callback={};
BsF.isFunction=function(f) {
 var getType = {};
 return f && getType.toString.call(f) === '[object Function]';
}
BsF.ajax.onevent=function(d) {
    if(d.status == "success") {
            var cid=d.source.id.replace(/[^a-zA-Z0-9]+/g,'_');
            var f=BsF.callback[cid];
            if (typeof(f)!='undefined') {
            	f();
            }
        }
    };
BsF.ajax.cb=function(o,e,r,f) { //commandButton ajax helper (object, event, [render], [oncomplete])
	BsF.ajax.callAjax(o,e,r,"@all",f, null);
}

/**
 * Initiates an asynchronous AJAX request.
 * param eventType In the case of jQuery events, JSF sends the wrong event type to the backend. The real event is put in this parameter.
 */
BsF.ajax.callAjax=function(source,event,update,execute,oncomplete,eventType) { //commandButton ajax helper (object, event, [render], [oncomplete])	
    var argn=arguments.length;
    var oid=source.id;
    var cid=oid.replace(/[^a-zA-Z0-9]+/g,'_');
    var opts={};
    if (eventType) {
    	opts.params="BsFEvent="+eventType;
    }
    if (execute && execute != null) {
    	opts.execute=execute;
    }
    opts[oid]=oid;
    if(argn==5 && oncomplete!=null) {
	    BsF.callback[cid]=oncomplete;
	    
	    opts.render=update;
	    opts.onevent=BsF.ajax.onevent;
    }
    if(argn>=3) {
        if(BsF.isFunction(update)) {
            BsF.callback[cid]=update;
            opts.onevent=BsF.ajax.onevent;
        }
        else { opts.render=update; } //jsf.ajax.request(o,e, { execute: '@form', render: r }); }
    }
    
    jsf.ajax.request(source,event, opts);
    return false;
};

BsF.ajax.paginate=function(o,e,v,c,r) { //Paginator ajax helper (object, event, value, component, render)
    var opts={};
    opts.execute='@this';
    opts.render=r;
    opts[c]=v;
    jsf.ajax.request(c,e, opts);
    return false;
};

/* DatePicker Bootstrap Icon support */
if( $.datepicker ) {
    var generateHTML_orig = $.datepicker._generateHTML;

    $.datepicker._generateHTML = function() {
        var ret = generateHTML_orig.apply(this,arguments); //alert(ret);
        ret = ret.replace(/<span\s+class='ui-icon\s+ui-icon-circle-triangle-w'\s*>[^<]+<\/span>/, '<span class="glyphicon glyphicon-chevron-left"></span>');
        ret = ret.replace(/<span\s+class='ui-icon\s+ui-icon-circle-triangle-e'\s*>[^<]+<\/span>/, '<span class="glyphicon glyphicon-chevron-right"></span>');
        return ret;    
    };
}
