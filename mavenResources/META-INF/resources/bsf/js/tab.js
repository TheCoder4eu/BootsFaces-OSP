/*
 ========================================================================
 Bootstrap: tab.js v3.4.1
 https://getbootstrap.com/docs/3.4/javascript/#tabs
 ========================================================================
 Copyright 2011-2019 Twitter, Inc.
 Licensed under MIT (https://github.com/twbs/bootstrap/blob/master/LICENSE)
 ======================================================================== */
var process=process||{env:{NODE_ENV:"development"}};
+function(c){function k(a){return this.each(function(){var e=c(this),b=e.data("bs.tab");b||e.data("bs.tab",b=new f(this));if("string"==typeof a)b[a]()})}var f=function(a){this.element=c(a)};f.VERSION="3.4.1";f.TRANSITION_DURATION=150;f.prototype.show=function(){var a=this.element,e=a.closest("ul:not(.dropdown-menu)"),b=a.data("target");b||(b=(b=a.attr("href"))&&b.replace(/.*(?=#[^\s]*$)/,""));if(!a.parent("li").hasClass("active")){var g=e.find(".active:last a"),d=c.Event("hide.bs.tab",{relatedTarget:a[0]}),
h=c.Event("show.bs.tab",{relatedTarget:g[0]});g.trigger(d);a.trigger(h);h.isDefaultPrevented()||d.isDefaultPrevented()||(b=c(document).find(b),this.activate(a.closest("li"),e),this.activate(b,b.parent(),function(){g.trigger({type:"hidden.bs.tab",relatedTarget:a[0]});a.trigger({type:"shown.bs.tab",relatedTarget:g[0]})}))}};f.prototype.activate=function(a,e,b){function g(){d.removeClass("active").find("> .dropdown-menu > .active").removeClass("active").end().find('[data-toggle="tab"]').attr("aria-expanded",
!1);a.addClass("active").find('[data-toggle="tab"]').attr("aria-expanded",!0);h?(a[0].offsetWidth,a.addClass("in")):a.removeClass("fade");a.parent(".dropdown-menu").length&&a.closest("li.dropdown").addClass("active").end().find('[data-toggle="tab"]').attr("aria-expanded",!0);b&&b()}var d=e.find("> .active"),h=b&&c.support.transition&&(d.length&&d.hasClass("fade")||!!e.find("> .fade").length);d.length&&h?d.one("bsTransitionEnd",g).emulateTransitionEnd(f.TRANSITION_DURATION):g();d.removeClass("in")};
var m=c.fn.tab;c.fn.tab=k;c.fn.tab.Constructor=f;c.fn.tab.noConflict=function(){c.fn.tab=m;return this};var l=function(a){a.preventDefault();k.call(c(this),"show")};c(document).on("click.bs.tab.data-api",'[data-toggle="tab"]',l).on("click.bs.tab.data-api",'[data-toggle="pill"]',l)}(jQuery);