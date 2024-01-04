/*
 ========================================================================
 Bootstrap: transition.js v3.4.1
 https://getbootstrap.com/docs/3.4/javascript/#transitions
 ========================================================================
 Copyright 2011-2019 Twitter, Inc.
 Licensed under MIT (https://github.com/twbs/bootstrap/blob/master/LICENSE)
 ======================================================================== */
var process=process||{env:{NODE_ENV:"development"}};
+function(a){function e(){var b=document.createElement("bootstrap"),c={WebkitTransition:"webkitTransitionEnd",MozTransition:"transitionend",OTransition:"oTransitionEnd otransitionend",transition:"transitionend"},d;for(d in c)if(void 0!==b.style[d])return{end:c[d]};return!1}a.fn.emulateTransitionEnd=function(b){var c=!1,d=this;a(this).one("bsTransitionEnd",function(){c=!0});setTimeout(function(){c||a(d).trigger(a.support.transition.end)},b);return this};a(function(){a.support.transition=e();a.support.transition&&
(a.event.special.bsTransitionEnd={bindType:a.support.transition.end,delegateType:a.support.transition.end,handle:function(b){if(a(b.target).is(this))return b.handleObj.handler.apply(this,arguments)}})})}(jQuery);