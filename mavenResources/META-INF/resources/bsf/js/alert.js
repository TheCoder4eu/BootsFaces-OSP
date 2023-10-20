/*
 ========================================================================
 Bootstrap: alert.js v3.4.1
 https://getbootstrap.com/docs/3.4/javascript/#alerts
 ========================================================================
 Copyright 2011-2019 Twitter, Inc.
 Licensed under MIT (https://github.com/twbs/bootstrap/blob/master/LICENSE)
 ======================================================================== */
var process=process||{env:{NODE_ENV:"development"}};
+function(a){var d=function(b){a(b).on("click",'[data-dismiss="alert"]',this.close)};d.VERSION="3.4.1";d.TRANSITION_DURATION=150;d.prototype.close=function(b){function g(){e.detach().trigger("closed.bs.alert").remove()}var f=a(this),c=f.attr("data-target");c||(c=(c=f.attr("href"))&&c.replace(/.*(?=#[^\s]*$)/,""));c="#"===c?[]:c;var e=a(document).find(c);b&&b.preventDefault();e.length||(e=f.closest(".alert"));e.trigger(b=a.Event("close.bs.alert"));b.isDefaultPrevented()||(e.removeClass("in"),a.support.transition&&
e.hasClass("fade")?e.one("bsTransitionEnd",g).emulateTransitionEnd(d.TRANSITION_DURATION):g())};var h=a.fn.alert;a.fn.alert=function(b){return this.each(function(){var g=a(this),f=g.data("bs.alert");f||g.data("bs.alert",f=new d(this));"string"==typeof b&&f[b].call(g)})};a.fn.alert.Constructor=d;a.fn.alert.noConflict=function(){a.fn.alert=h;return this};a(document).on("click.bs.alert.data-api",'[data-dismiss="alert"]',d.prototype.close)}(jQuery);