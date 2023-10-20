/*
 ========================================================================
 Bootstrap: button.js v3.4.1
 https://getbootstrap.com/docs/3.4/javascript/#buttons
 ========================================================================
 Copyright 2011-2019 Twitter, Inc.
 Licensed under MIT (https://github.com/twbs/bootstrap/blob/master/LICENSE)
 ======================================================================== */
var process=process||{env:{NODE_ENV:"development"}};
+function(d){function g(a){return this.each(function(){var b=d(this),c=b.data("bs.button"),f="object"==typeof a&&a;c||b.data("bs.button",c=new e(this,f));"toggle"==a?c.toggle():a&&c.setState(a)})}var e=function(a,b){this.$element=d(a);this.options=d.extend({},e.DEFAULTS,b);this.isLoading=!1};e.VERSION="3.4.1";e.DEFAULTS={loadingText:"loading..."};e.prototype.setState=function(a){var b=this.$element,c=b.is("input")?"val":"html",f=b.data();a+="Text";null==f.resetText&&b.data("resetText",b[c]());setTimeout(d.proxy(function(){b[c](null==
f[a]?this.options[a]:f[a]);"loadingText"==a?(this.isLoading=!0,b.addClass("disabled").attr("disabled","disabled").prop("disabled",!0)):this.isLoading&&(this.isLoading=!1,b.removeClass("disabled").removeAttr("disabled").prop("disabled",!1))},this),0)};e.prototype.toggle=function(){var a=!0,b=this.$element.closest('[data-toggle="buttons"]');if(b.length){var c=this.$element.find("input");"radio"==c.prop("type")?(c.prop("checked")&&(a=!1),b.find(".active").removeClass("active"),this.$element.addClass("active")):
"checkbox"==c.prop("type")&&(c.prop("checked")!==this.$element.hasClass("active")&&(a=!1),this.$element.toggleClass("active"));c.prop("checked",this.$element.hasClass("active"));a&&c.trigger("change")}else this.$element.attr("aria-pressed",!this.$element.hasClass("active")),this.$element.toggleClass("active")};var h=d.fn.button;d.fn.button=g;d.fn.button.Constructor=e;d.fn.button.noConflict=function(){d.fn.button=h;return this};d(document).on("click.bs.button.data-api",'[data-toggle^="button"]',function(a){var b=
d(a.target).closest(".btn");g.call(b,"toggle");d(a.target).is('input[type="radio"], input[type="checkbox"]')||(a.preventDefault(),b.is("input,button")?b.trigger("focus"):b.find("input:visible,button:visible").first().trigger("focus"))}).on("focus.bs.button.data-api blur.bs.button.data-api",'[data-toggle^="button"]',function(a){d(a.target).closest(".btn").toggleClass("focus",/^focus(in)?$/.test(a.type))})}(jQuery);