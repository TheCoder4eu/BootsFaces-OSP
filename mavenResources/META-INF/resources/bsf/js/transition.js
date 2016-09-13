/*
 ========================================================================
 Bootstrap: transition.js v3.3.7
 http://getbootstrap.com/javascript/#transitions
 ========================================================================
 Copyright 2011-2016 Twitter, Inc.
 Licensed under MIT (https://github.com/twbs/bootstrap/blob/master/LICENSE)
 ======================================================================== */
+function(a){function e(){var a=document.createElement("bootstrap"),b={WebkitTransition:"webkitTransitionEnd",MozTransition:"transitionend",OTransition:"oTransitionEnd otransitionend",transition:"transitionend"},c;for(c in b)if(void 0!==a.style[c])return{end:b[c]};return!1}a.fn.emulateTransitionEnd=function(d){var b=!1,c=this;a(this).one("bsTransitionEnd",function(){b=!0});setTimeout(function(){b||a(c).trigger(a.support.transition.end)},d);return this};a(function(){a.support.transition=e();a.support.transition&&
(a.event.special.bsTransitionEnd={bindType:a.support.transition.end,delegateType:a.support.transition.end,handle:function(d){if(a(d.target).is(this))return d.handleObj.handler.apply(this,arguments)}})})}(jQuery);