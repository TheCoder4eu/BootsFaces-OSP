/*
 ========================================================================
 Bootstrap: alert.js v3.3.7
 http://getbootstrap.com/javascript/#alerts
 ========================================================================
 Copyright 2011-2016 Twitter, Inc.
 Licensed under MIT (https://github.com/twbs/bootstrap/blob/master/LICENSE)
 ======================================================================== */
+function(a){var c=function(b){a(b).on("click",'[data-dismiss="alert"]',this.close)};c.VERSION="3.3.7";c.TRANSITION_DURATION=150;c.prototype.close=function(b){function f(){d.detach().trigger("closed.bs.alert").remove()}var e=a(this),g=e.attr("data-target");g||(g=(g=e.attr("href"))&&g.replace(/.*(?=#[^\s]*$)/,""));var d=a("#"===g?[]:g);b&&b.preventDefault();d.length||(d=e.closest(".alert"));d.trigger(b=a.Event("close.bs.alert"));b.isDefaultPrevented()||(d.removeClass("in"),a.support.transition&&d.hasClass("fade")?
d.one("bsTransitionEnd",f).emulateTransitionEnd(c.TRANSITION_DURATION):f())};var h=a.fn.alert;a.fn.alert=function(b){return this.each(function(){var f=a(this),e=f.data("bs.alert");e||f.data("bs.alert",e=new c(this));"string"==typeof b&&e[b].call(f)})};a.fn.alert.Constructor=c;a.fn.alert.noConflict=function(){a.fn.alert=h;return this};a(document).on("click.bs.alert.data-api",'[data-dismiss="alert"]',c.prototype.close)}(jQuery);