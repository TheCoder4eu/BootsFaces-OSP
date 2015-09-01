/*! Bootstrap v3.3.5 (http://getbootstrap.com) | Copyright 2011-2015 Twitter, Inc. | Licensed under MIT */
+function(e){var d='[data-dismiss="alert"]';var b=function(f){e(f).on("click",d,this.close)};b.VERSION="3.3.5";b.TRANSITION_DURATION=150;b.prototype.close=function(j){var i=e(this);var g=i.attr("data-target");if(!g){g=i.attr("href");g=g&&g.replace(/.*(?=#[^\s]*$)/,"")}var h=e(g);if(j){j.preventDefault()}if(!h.length){h=i.closest(".alert")}h.trigger(j=e.Event("close.bs.alert"));if(j.isDefaultPrevented()){return}h.removeClass("in");function f(){h.detach().trigger("closed.bs.alert").remove()}e.support.transition&&h.hasClass("fade")?h.one("bsTransitionEnd",f).emulateTransitionEnd(b.TRANSITION_DURATION):f()};function c(f){return this.each(function(){var h=e(this);var g=h.data("bs.alert");if(!g){h.data("bs.alert",(g=new b(this)))}if(typeof f=="string"){g[f].call(h)}})}var a=e.fn.alert;e.fn.alert=c;e.fn.alert.Constructor=b;e.fn.alert.noConflict=function(){e.fn.alert=a;return this};e(document).on("click.bs.alert.data-api",d,b.prototype.close)}(jQuery);;/* ========================================================================
 * bootstrap-switch - v3.3.2
 * http://www.bootstrap-switch.org
 * ========================================================================
 * Copyright 2012-2013 Mattia Larentis
 *
 * ========================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ========================================================================
 */

(function() {
  var slice = [].slice;

  (function($, window) {
    "use strict";
    var BootstrapSwitch;
    BootstrapSwitch = (function() {
      function BootstrapSwitch(element, options) {
        if (options == null) {
          options = {};
        }
        this.$element = $(element);
        this.options = $.extend({}, $.fn.bootstrapSwitch.defaults, {
          state: this.$element.is(":checked"),
          size: this.$element.data("size"),
          animate: this.$element.data("animate"),
          disabled: this.$element.is(":disabled"),
          readonly: this.$element.is("[readonly]"),
          indeterminate: this.$element.data("indeterminate"),
          inverse: this.$element.data("inverse"),
          radioAllOff: this.$element.data("radio-all-off"),
          onColor: this.$element.data("on-color"),
          offColor: this.$element.data("off-color"),
          onText: this.$element.data("on-text"),
          offText: this.$element.data("off-text"),
          labelText: this.$element.data("label-text"),
          handleWidth: this.$element.data("handle-width"),
          labelWidth: this.$element.data("label-width"),
          baseClass: this.$element.data("base-class"),
          wrapperClass: this.$element.data("wrapper-class")
        }, options);
        this.prevOptions = {};
        this.$wrapper = $("<div>", {
          "class": (function(_this) {
            return function() {
              var classes;
              classes = ["" + _this.options.baseClass].concat(_this._getClasses(_this.options.wrapperClass));
              classes.push(_this.options.state ? _this.options.baseClass + "-on" : _this.options.baseClass + "-off");
              if (_this.options.size != null) {
                classes.push(_this.options.baseClass + "-" + _this.options.size);
              }
              if (_this.options.disabled) {
                classes.push(_this.options.baseClass + "-disabled");
              }
              if (_this.options.readonly) {
                classes.push(_this.options.baseClass + "-readonly");
              }
              if (_this.options.indeterminate) {
                classes.push(_this.options.baseClass + "-indeterminate");
              }
              if (_this.options.inverse) {
                classes.push(_this.options.baseClass + "-inverse");
              }
              if (_this.$element.attr("id")) {
                classes.push(_this.options.baseClass + "-id-" + (_this.$element.attr("id")));
              }
              return classes.join(" ");
            };
          })(this)()
        });
        this.$container = $("<div>", {
          "class": this.options.baseClass + "-container"
        });
        this.$on = $("<span>", {
          html: this.options.onText,
          "class": this.options.baseClass + "-handle-on " + this.options.baseClass + "-" + this.options.onColor
        });
        this.$off = $("<span>", {
          html: this.options.offText,
          "class": this.options.baseClass + "-handle-off " + this.options.baseClass + "-" + this.options.offColor
        });
        this.$label = $("<span>", {
          html: this.options.labelText,
          "class": this.options.baseClass + "-label"
        });
        this.$element.on("init.bootstrapSwitch", (function(_this) {
          return function() {
            return _this.options.onInit.apply(element, arguments);
          };
        })(this));
        this.$element.on("switchChange.bootstrapSwitch", (function(_this) {
          return function(e) {
            if (false === _this.options.onSwitchChange.apply(element, arguments)) {
              if (_this.$element.is(":radio")) {
                return $("[name='" + (_this.$element.attr('name')) + "']").trigger("previousState.bootstrapSwitch", true);
              } else {
                return _this.$element.trigger("previousState.bootstrapSwitch", true);
              }
            }
          };
        })(this));
        this.$container = this.$element.wrap(this.$container).parent();
        this.$wrapper = this.$container.wrap(this.$wrapper).parent();
        this.$element.before(this.options.inverse ? this.$off : this.$on).before(this.$label).before(this.options.inverse ? this.$on : this.$off);
        if (this.options.indeterminate) {
          this.$element.prop("indeterminate", true);
        }
        this._init();
        this._elementHandlers();
        this._handleHandlers();
        this._labelHandlers();
        this._formHandler();
        this._externalLabelHandler();
        this.$element.trigger("init.bootstrapSwitch", this.options.state);
      }

      BootstrapSwitch.prototype._constructor = BootstrapSwitch;

      BootstrapSwitch.prototype.setPrevOptions = function() {
        return this.prevOptions = $.extend(true, {}, this.options);
      };

      BootstrapSwitch.prototype.state = function(value, skip) {
        if (typeof value === "undefined") {
          return this.options.state;
        }
        if (this.options.disabled || this.options.readonly) {
          return this.$element;
        }
        if (this.options.state && !this.options.radioAllOff && this.$element.is(":radio")) {
          return this.$element;
        }
        if (this.$element.is(":radio")) {
          $("[name='" + (this.$element.attr('name')) + "']").trigger("setPreviousOptions.bootstrapSwitch");
        } else {
          this.$element.trigger("setPreviousOptions.bootstrapSwitch");
        }
        if (this.options.indeterminate) {
          this.indeterminate(false);
        }
        value = !!value;
        this.$element.prop("checked", value).trigger("change.bootstrapSwitch", skip);
        return this.$element;
      };

      BootstrapSwitch.prototype.toggleState = function(skip) {
        if (this.options.disabled || this.options.readonly) {
          return this.$element;
        }
        if (this.options.indeterminate) {
          this.indeterminate(false);
          return this.state(true);
        } else {
          return this.$element.prop("checked", !this.options.state).trigger("change.bootstrapSwitch", skip);
        }
      };

      BootstrapSwitch.prototype.size = function(value) {
        if (typeof value === "undefined") {
          return this.options.size;
        }
        if (this.options.size != null) {
          this.$wrapper.removeClass(this.options.baseClass + "-" + this.options.size);
        }
        if (value) {
          this.$wrapper.addClass(this.options.baseClass + "-" + value);
        }
        this._width();
        this._containerPosition();
        this.options.size = value;
        return this.$element;
      };

      BootstrapSwitch.prototype.animate = function(value) {
        if (typeof value === "undefined") {
          return this.options.animate;
        }
        value = !!value;
        if (value === this.options.animate) {
          return this.$element;
        }
        return this.toggleAnimate();
      };

      BootstrapSwitch.prototype.toggleAnimate = function() {
        this.options.animate = !this.options.animate;
        this.$wrapper.toggleClass(this.options.baseClass + "-animate");
        return this.$element;
      };

      BootstrapSwitch.prototype.disabled = function(value) {
        if (typeof value === "undefined") {
          return this.options.disabled;
        }
        value = !!value;
        if (value === this.options.disabled) {
          return this.$element;
        }
        return this.toggleDisabled();
      };

      BootstrapSwitch.prototype.toggleDisabled = function() {
        this.options.disabled = !this.options.disabled;
        this.$element.prop("disabled", this.options.disabled);
        this.$wrapper.toggleClass(this.options.baseClass + "-disabled");
        return this.$element;
      };

      BootstrapSwitch.prototype.readonly = function(value) {
        if (typeof value === "undefined") {
          return this.options.readonly;
        }
        value = !!value;
        if (value === this.options.readonly) {
          return this.$element;
        }
        return this.toggleReadonly();
      };

      BootstrapSwitch.prototype.toggleReadonly = function() {
        this.options.readonly = !this.options.readonly;
        this.$element.prop("readonly", this.options.readonly);
        this.$wrapper.toggleClass(this.options.baseClass + "-readonly");
        return this.$element;
      };

      BootstrapSwitch.prototype.indeterminate = function(value) {
        if (typeof value === "undefined") {
          return this.options.indeterminate;
        }
        value = !!value;
        if (value === this.options.indeterminate) {
          return this.$element;
        }
        return this.toggleIndeterminate();
      };

      BootstrapSwitch.prototype.toggleIndeterminate = function() {
        this.options.indeterminate = !this.options.indeterminate;
        this.$element.prop("indeterminate", this.options.indeterminate);
        this.$wrapper.toggleClass(this.options.baseClass + "-indeterminate");
        this._containerPosition();
        return this.$element;
      };

      BootstrapSwitch.prototype.inverse = function(value) {
        if (typeof value === "undefined") {
          return this.options.inverse;
        }
        value = !!value;
        if (value === this.options.inverse) {
          return this.$element;
        }
        return this.toggleInverse();
      };

      BootstrapSwitch.prototype.toggleInverse = function() {
        var $off, $on;
        this.$wrapper.toggleClass(this.options.baseClass + "-inverse");
        $on = this.$on.clone(true);
        $off = this.$off.clone(true);
        this.$on.replaceWith($off);
        this.$off.replaceWith($on);
        this.$on = $off;
        this.$off = $on;
        this.options.inverse = !this.options.inverse;
        return this.$element;
      };

      BootstrapSwitch.prototype.onColor = function(value) {
        var color;
        color = this.options.onColor;
        if (typeof value === "undefined") {
          return color;
        }
        if (color != null) {
          this.$on.removeClass(this.options.baseClass + "-" + color);
        }
        this.$on.addClass(this.options.baseClass + "-" + value);
        this.options.onColor = value;
        return this.$element;
      };

      BootstrapSwitch.prototype.offColor = function(value) {
        var color;
        color = this.options.offColor;
        if (typeof value === "undefined") {
          return color;
        }
        if (color != null) {
          this.$off.removeClass(this.options.baseClass + "-" + color);
        }
        this.$off.addClass(this.options.baseClass + "-" + value);
        this.options.offColor = value;
        return this.$element;
      };

      BootstrapSwitch.prototype.onText = function(value) {
        if (typeof value === "undefined") {
          return this.options.onText;
        }
        this.$on.html(value);
        this._width();
        this._containerPosition();
        this.options.onText = value;
        return this.$element;
      };

      BootstrapSwitch.prototype.offText = function(value) {
        if (typeof value === "undefined") {
          return this.options.offText;
        }
        this.$off.html(value);
        this._width();
        this._containerPosition();
        this.options.offText = value;
        return this.$element;
      };

      BootstrapSwitch.prototype.labelText = function(value) {
        if (typeof value === "undefined") {
          return this.options.labelText;
        }
        this.$label.html(value);
        this._width();
        this.options.labelText = value;
        return this.$element;
      };

      BootstrapSwitch.prototype.handleWidth = function(value) {
        if (typeof value === "undefined") {
          return this.options.handleWidth;
        }
        this.options.handleWidth = value;
        this._width();
        this._containerPosition();
        return this.$element;
      };

      BootstrapSwitch.prototype.labelWidth = function(value) {
        if (typeof value === "undefined") {
          return this.options.labelWidth;
        }
        this.options.labelWidth = value;
        this._width();
        this._containerPosition();
        return this.$element;
      };

      BootstrapSwitch.prototype.baseClass = function(value) {
        return this.options.baseClass;
      };

      BootstrapSwitch.prototype.wrapperClass = function(value) {
        if (typeof value === "undefined") {
          return this.options.wrapperClass;
        }
        if (!value) {
          value = $.fn.bootstrapSwitch.defaults.wrapperClass;
        }
        this.$wrapper.removeClass(this._getClasses(this.options.wrapperClass).join(" "));
        this.$wrapper.addClass(this._getClasses(value).join(" "));
        this.options.wrapperClass = value;
        return this.$element;
      };

      BootstrapSwitch.prototype.radioAllOff = function(value) {
        if (typeof value === "undefined") {
          return this.options.radioAllOff;
        }
        value = !!value;
        if (value === this.options.radioAllOff) {
          return this.$element;
        }
        this.options.radioAllOff = value;
        return this.$element;
      };

      BootstrapSwitch.prototype.onInit = function(value) {
        if (typeof value === "undefined") {
          return this.options.onInit;
        }
        if (!value) {
          value = $.fn.bootstrapSwitch.defaults.onInit;
        }
        this.options.onInit = value;
        return this.$element;
      };

      BootstrapSwitch.prototype.onSwitchChange = function(value) {
        if (typeof value === "undefined") {
          return this.options.onSwitchChange;
        }
        if (!value) {
          value = $.fn.bootstrapSwitch.defaults.onSwitchChange;
        }
        this.options.onSwitchChange = value;
        return this.$element;
      };

      BootstrapSwitch.prototype.destroy = function() {
        var $form;
        $form = this.$element.closest("form");
        if ($form.length) {
          $form.off("reset.bootstrapSwitch").removeData("bootstrap-switch");
        }
        this.$container.children().not(this.$element).remove();
        this.$element.unwrap().unwrap().off(".bootstrapSwitch").removeData("bootstrap-switch");
        return this.$element;
      };

      BootstrapSwitch.prototype._width = function() {
        var $handles, handleWidth;
        $handles = this.$on.add(this.$off);
        $handles.add(this.$label).css("width", "");
        handleWidth = this.options.handleWidth === "auto" ? Math.max(this.$on.width(), this.$off.width()) : this.options.handleWidth;
        $handles.width(handleWidth);
        this.$label.width((function(_this) {
          return function(index, width) {
            if (_this.options.labelWidth !== "auto") {
              return _this.options.labelWidth;
            }
            if (width < handleWidth) {
              return handleWidth;
            } else {
              return width;
            }
          };
        })(this));
        this._handleWidth = this.$on.outerWidth();
        this._labelWidth = this.$label.outerWidth();
        this.$container.width((this._handleWidth * 2) + this._labelWidth);
        return this.$wrapper.width(this._handleWidth + this._labelWidth);
      };

      BootstrapSwitch.prototype._containerPosition = function(state, callback) {
        if (state == null) {
          state = this.options.state;
        }
        this.$container.css("margin-left", (function(_this) {
          return function() {
            var values;
            values = [0, "-" + _this._handleWidth + "px"];
            if (_this.options.indeterminate) {
              return "-" + (_this._handleWidth / 2) + "px";
            }
            if (state) {
              if (_this.options.inverse) {
                return values[1];
              } else {
                return values[0];
              }
            } else {
              if (_this.options.inverse) {
                return values[0];
              } else {
                return values[1];
              }
            }
          };
        })(this));
        if (!callback) {
          return;
        }
        return setTimeout(function() {
          return callback();
        }, 50);
      };

      BootstrapSwitch.prototype._init = function() {
        var init, initInterval;
        init = (function(_this) {
          return function() {
            _this.setPrevOptions();
            _this._width();
            return _this._containerPosition(null, function() {
              if (_this.options.animate) {
                return _this.$wrapper.addClass(_this.options.baseClass + "-animate");
              }
            });
          };
        })(this);
        if (this.$wrapper.is(":visible")) {
          return init();
        }
        return initInterval = window.setInterval((function(_this) {
          return function() {
            if (_this.$wrapper.is(":visible")) {
              init();
              return window.clearInterval(initInterval);
            }
          };
        })(this), 50);
      };

      BootstrapSwitch.prototype._elementHandlers = function() {
        return this.$element.on({
          "setPreviousOptions.bootstrapSwitch": (function(_this) {
            return function(e) {
              return _this.setPrevOptions();
            };
          })(this),
          "previousState.bootstrapSwitch": (function(_this) {
            return function(e) {
              _this.options = _this.prevOptions;
              if (_this.options.indeterminate) {
                _this.$wrapper.addClass(_this.options.baseClass + "-indeterminate");
              }
              return _this.$element.prop("checked", _this.options.state).trigger("change.bootstrapSwitch", true);
            };
          })(this),
          "change.bootstrapSwitch": (function(_this) {
            return function(e, skip) {
              var state;
              e.preventDefault();
              e.stopImmediatePropagation();
              state = _this.$element.is(":checked");
              _this._containerPosition(state);
              if (state === _this.options.state) {
                return;
              }
              _this.options.state = state;
              _this.$wrapper.toggleClass(_this.options.baseClass + "-off").toggleClass(_this.options.baseClass + "-on");
              if (!skip) {
                if (_this.$element.is(":radio")) {
                  $("[name='" + (_this.$element.attr('name')) + "']").not(_this.$element).prop("checked", false).trigger("change.bootstrapSwitch", true);
                }
                return _this.$element.trigger("switchChange.bootstrapSwitch", [state]);
              }
            };
          })(this),
          "focus.bootstrapSwitch": (function(_this) {
            return function(e) {
              e.preventDefault();
              return _this.$wrapper.addClass(_this.options.baseClass + "-focused");
            };
          })(this),
          "blur.bootstrapSwitch": (function(_this) {
            return function(e) {
              e.preventDefault();
              return _this.$wrapper.removeClass(_this.options.baseClass + "-focused");
            };
          })(this),
          "keydown.bootstrapSwitch": (function(_this) {
            return function(e) {
              if (!e.which || _this.options.disabled || _this.options.readonly) {
                return;
              }
              switch (e.which) {
                case 37:
                  e.preventDefault();
                  e.stopImmediatePropagation();
                  return _this.state(false);
                case 39:
                  e.preventDefault();
                  e.stopImmediatePropagation();
                  return _this.state(true);
              }
            };
          })(this)
        });
      };

      BootstrapSwitch.prototype._handleHandlers = function() {
        this.$on.on("click.bootstrapSwitch", (function(_this) {
          return function(event) {
            event.preventDefault();
            event.stopPropagation();
            _this.state(false);
            return _this.$element.trigger("focus.bootstrapSwitch");
          };
        })(this));
        return this.$off.on("click.bootstrapSwitch", (function(_this) {
          return function(event) {
            event.preventDefault();
            event.stopPropagation();
            _this.state(true);
            return _this.$element.trigger("focus.bootstrapSwitch");
          };
        })(this));
      };

      BootstrapSwitch.prototype._labelHandlers = function() {
        return this.$label.on({
          "click": function(e) {
            return e.stopPropagation();
          },
          "mousedown.bootstrapSwitch touchstart.bootstrapSwitch": (function(_this) {
            return function(e) {
              if (_this._dragStart || _this.options.disabled || _this.options.readonly) {
                return;
              }
              e.preventDefault();
              e.stopPropagation();
              _this._dragStart = (e.pageX || e.originalEvent.touches[0].pageX) - parseInt(_this.$container.css("margin-left"), 10);
              if (_this.options.animate) {
                _this.$wrapper.removeClass(_this.options.baseClass + "-animate");
              }
              return _this.$element.trigger("focus.bootstrapSwitch");
            };
          })(this),
          "mousemove.bootstrapSwitch touchmove.bootstrapSwitch": (function(_this) {
            return function(e) {
              var difference;
              if (_this._dragStart == null) {
                return;
              }
              e.preventDefault();
              difference = (e.pageX || e.originalEvent.touches[0].pageX) - _this._dragStart;
              if (difference < -_this._handleWidth || difference > 0) {
                return;
              }
              _this._dragEnd = difference;
              return _this.$container.css("margin-left", _this._dragEnd + "px");
            };
          })(this),
          "mouseup.bootstrapSwitch touchend.bootstrapSwitch": (function(_this) {
            return function(e) {
              var state;
              if (!_this._dragStart) {
                return;
              }
              e.preventDefault();
              if (_this.options.animate) {
                _this.$wrapper.addClass(_this.options.baseClass + "-animate");
              }
              if (_this._dragEnd) {
                state = _this._dragEnd > -(_this._handleWidth / 2);
                _this._dragEnd = false;
                _this.state(_this.options.inverse ? !state : state);
              } else {
                _this.state(!_this.options.state);
              }
              return _this._dragStart = false;
            };
          })(this),
          "mouseleave.bootstrapSwitch": (function(_this) {
            return function(e) {
              return _this.$label.trigger("mouseup.bootstrapSwitch");
            };
          })(this)
        });
      };

      BootstrapSwitch.prototype._externalLabelHandler = function() {
        var $externalLabel;
        $externalLabel = this.$element.closest("label");
        return $externalLabel.on("click", (function(_this) {
          return function(event) {
            event.preventDefault();
            event.stopImmediatePropagation();
            if (event.target === $externalLabel[0]) {
              return _this.toggleState();
            }
          };
        })(this));
      };

      BootstrapSwitch.prototype._formHandler = function() {
        var $form;
        $form = this.$element.closest("form");
        if ($form.data("bootstrap-switch")) {
          return;
        }
        return $form.on("reset.bootstrapSwitch", function() {
          return window.setTimeout(function() {
            return $form.find("input").filter(function() {
              return $(this).data("bootstrap-switch");
            }).each(function() {
              return $(this).bootstrapSwitch("state", this.checked);
            });
          }, 1);
        }).data("bootstrap-switch", true);
      };

      BootstrapSwitch.prototype._getClasses = function(classes) {
        var c, cls, i, len;
        if (!$.isArray(classes)) {
          return [this.options.baseClass + "-" + classes];
        }
        cls = [];
        for (i = 0, len = classes.length; i < len; i++) {
          c = classes[i];
          cls.push(this.options.baseClass + "-" + c);
        }
        return cls;
      };

      return BootstrapSwitch;

    })();
    $.fn.bootstrapSwitch = function() {
      var args, option, ret;
      option = arguments[0], args = 2 <= arguments.length ? slice.call(arguments, 1) : [];
      ret = this;
      this.each(function() {
        var $this, data;
        $this = $(this);
        data = $this.data("bootstrap-switch");
        if (!data) {
          $this.data("bootstrap-switch", data = new BootstrapSwitch(this, option));
        }
        if (typeof option === "string") {
          return ret = data[option].apply(data, args);
        }
      });
      return ret;
    };
    $.fn.bootstrapSwitch.Constructor = BootstrapSwitch;
    return $.fn.bootstrapSwitch.defaults = {
      state: true,
      size: null,
      animate: true,
      disabled: false,
      readonly: false,
      indeterminate: false,
      inverse: false,
      radioAllOff: false,
      onColor: "primary",
      offColor: "default",
      onText: "ON",
      offText: "OFF",
      labelText: "&nbsp;",
      handleWidth: "auto",
      labelWidth: "auto",
      baseClass: "bootstrap-switch",
      wrapperClass: "wrapper",
      onInit: function() {},
      onSwitchChange: function() {}
    };
  })(window.jQuery, window);

}).call(this);
;/**
 * Bootstrap Multiselect (https://github.com/davidstutz/bootstrap-multiselect)
 * 
 * Apache License, Version 2.0:
 * Copyright (c) 2012 - 2015 David Stutz
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a
 * copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 * 
 * BSD 3-Clause License:
 * Copyright (c) 2012 - 2015 David Stutz
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *    - Redistributions of source code must retain the above copyright notice,
 *      this list of conditions and the following disclaimer.
 *    - Redistributions in binary form must reproduce the above copyright notice,
 *      this list of conditions and the following disclaimer in the documentation
 *      and/or other materials provided with the distribution.
 *    - Neither the name of David Stutz nor the names of its contributors may be
 *      used to endorse or promote products derived from this software without
 *      specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
!function ($) {
    "use strict";// jshint ;_;

    if (typeof ko !== 'undefined' && ko.bindingHandlers && !ko.bindingHandlers.multiselect) {
        ko.bindingHandlers.multiselect = {
            after: ['options', 'value', 'selectedOptions'],

            init: function(element, valueAccessor, allBindings, viewModel, bindingContext) {
                var $element = $(element);
                var config = ko.toJS(valueAccessor());

                $element.multiselect(config);

                if (allBindings.has('options')) {
                    var options = allBindings.get('options');
                    if (ko.isObservable(options)) {
                        ko.computed({
                            read: function() {
                                options();
                                setTimeout(function() {
                                    var ms = $element.data('multiselect');
                                    if (ms)
                                        ms.updateOriginalOptions();//Not sure how beneficial this is.
                                    $element.multiselect('rebuild');
                                }, 1);
                            },
                            disposeWhenNodeIsRemoved: element
                        });
                    }
                }

                //value and selectedOptions are two-way, so these will be triggered even by our own actions.
                //It needs some way to tell if they are triggered because of us or because of outside change.
                //It doesn't loop but it's a waste of processing.
                if (allBindings.has('value')) {
                    var value = allBindings.get('value');
                    if (ko.isObservable(value)) {
                        ko.computed({
                            read: function() {
                                value();
                                setTimeout(function() {
                                    $element.multiselect('refresh');
                                }, 1);
                            },
                            disposeWhenNodeIsRemoved: element
                        }).extend({ rateLimit: 100, notifyWhenChangesStop: true });
                    }
                }

                //Switched from arrayChange subscription to general subscription using 'refresh'.
                //Not sure performance is any better using 'select' and 'deselect'.
                if (allBindings.has('selectedOptions')) {
                    var selectedOptions = allBindings.get('selectedOptions');
                    if (ko.isObservable(selectedOptions)) {
                        ko.computed({
                            read: function() {
                                selectedOptions();
                                setTimeout(function() {
                                    $element.multiselect('refresh');
                                }, 1);
                            },
                            disposeWhenNodeIsRemoved: element
                        }).extend({ rateLimit: 100, notifyWhenChangesStop: true });
                    }
                }

                ko.utils.domNodeDisposal.addDisposeCallback(element, function() {
                    $element.multiselect('destroy');
                });
            },

            update: function(element, valueAccessor, allBindings, viewModel, bindingContext) {
                var $element = $(element);
                var config = ko.toJS(valueAccessor());

                $element.multiselect('setOptions', config);
                $element.multiselect('rebuild');
            }
        };
    }

    function forEach(array, callback) {
        for (var index = 0; index < array.length; ++index) {
            callback(array[index], index);
        }
    }

    /**
     * Constructor to create a new multiselect using the given select.
     *
     * @param {jQuery} select
     * @param {Object} options
     * @returns {Multiselect}
     */
    function Multiselect(select, options) {

        this.$select = $(select);
        
        // Placeholder via data attributes
        if (this.$select.attr("data-placeholder")) {
            options.nonSelectedText = this.$select.data("placeholder");
        }
        
        this.options = this.mergeOptions($.extend({}, options, this.$select.data()));

        // Initialization.
        // We have to clone to create a new reference.
        this.originalOptions = this.$select.clone()[0].options;
        this.query = '';
        this.searchTimeout = null;
        this.lastToggledInput = null

        this.options.multiple = this.$select.attr('multiple') === "multiple";
        this.options.onChange = $.proxy(this.options.onChange, this);
        this.options.onDropdownShow = $.proxy(this.options.onDropdownShow, this);
        this.options.onDropdownHide = $.proxy(this.options.onDropdownHide, this);
        this.options.onDropdownShown = $.proxy(this.options.onDropdownShown, this);
        this.options.onDropdownHidden = $.proxy(this.options.onDropdownHidden, this);
        
        // Build select all if enabled.
        this.buildContainer();
        this.buildButton();
        this.buildDropdown();
        this.buildSelectAll();
        this.buildDropdownOptions();
        this.buildFilter();

        this.updateButtonText();
        this.updateSelectAll();

        if (this.options.disableIfEmpty && $('option', this.$select).length <= 0) {
            this.disable();
        }
        
        this.$select.hide().after(this.$container);
    };

    Multiselect.prototype = {

        defaults: {
            /**
             * Default text function will either print 'None selected' in case no
             * option is selected or a list of the selected options up to a length
             * of 3 selected options.
             * 
             * @param {jQuery} options
             * @param {jQuery} select
             * @returns {String}
             */
            buttonText: function(options, select) {
                if (options.length === 0) {
                    return this.nonSelectedText;
                }
                else if (this.allSelectedText 
                            && options.length === $('option', $(select)).length 
                            && $('option', $(select)).length !== 1 
                            && this.multiple) {

                    if (this.selectAllNumber) {
                        return this.allSelectedText + ' (' + options.length + ')';
                    }
                    else {
                        return this.allSelectedText;
                    }
                }
                else if (options.length > this.numberDisplayed) {
                    return options.length + ' ' + this.nSelectedText;
                }
                else {
                    var selected = '';
                    var delimiter = this.delimiterText;
                    
                    options.each(function() {
                        var label = ($(this).attr('label') !== undefined) ? $(this).attr('label') : $(this).text();
                        selected += label + delimiter;
                    });
                    
                    return selected.substr(0, selected.length - 2);
                }
            },
            /**
             * Updates the title of the button similar to the buttonText function.
             * 
             * @param {jQuery} options
             * @param {jQuery} select
             * @returns {@exp;selected@call;substr}
             */
            buttonTitle: function(options, select) {
                if (options.length === 0) {
                    return this.nonSelectedText;
                }
                else {
                    var selected = '';
                    var delimiter = this.delimiterText;
                    
                    options.each(function () {
                        var label = ($(this).attr('label') !== undefined) ? $(this).attr('label') : $(this).text();
                        selected += label + delimiter;
                    });
                    return selected.substr(0, selected.length - 2);
                }
            },
            /**
             * Create a label.
             *
             * @param {jQuery} element
             * @returns {String}
             */
            optionLabel: function(element){
                return $(element).attr('label') || $(element).text();
            },
            /**
             * Triggered on change of the multiselect.
             * 
             * Not triggered when selecting/deselecting options manually.
             * 
             * @param {jQuery} option
             * @param {Boolean} checked
             */
            onChange : function(option, checked) {

            },
            /**
             * Triggered when the dropdown is shown.
             *
             * @param {jQuery} event
             */
            onDropdownShow: function(event) {

            },
            /**
             * Triggered when the dropdown is hidden.
             *
             * @param {jQuery} event
             */
            onDropdownHide: function(event) {

            },
            /**
             * Triggered after the dropdown is shown.
             * 
             * @param {jQuery} event
             */
            onDropdownShown: function(event) {
                
            },
            /**
             * Triggered after the dropdown is hidden.
             * 
             * @param {jQuery} event
             */
            onDropdownHidden: function(event) {
                
            },
            /**
             * Triggered on select all.
             */
            onSelectAll: function() {
                
            },
            enableHTML: false,
            buttonClass: 'btn btn-default',
            inheritClass: false,
            buttonWidth: 'auto',
            buttonContainer: '<div class="btn-group" />',
            dropRight: false,
            selectedClass: 'active',
            // Maximum height of the dropdown menu.
            // If maximum height is exceeded a scrollbar will be displayed.
            maxHeight: false,
            checkboxName: false,
            includeSelectAllOption: false,
            includeSelectAllIfMoreThan: 0,
            selectAllText: ' Select all',
            selectAllValue: 'multiselect-all',
            selectAllName: false,
            selectAllNumber: true,
            enableFiltering: false,
            enableCaseInsensitiveFiltering: false,
            enableClickableOptGroups: false,
            filterPlaceholder: 'Search',
            // possible options: 'text', 'value', 'both'
            filterBehavior: 'text',
            includeFilterClearBtn: true,
            preventInputChangeEvent: false,
            nonSelectedText: 'None selected',
            nSelectedText: 'selected',
            allSelectedText: 'All selected',
            numberDisplayed: 3,
            disableIfEmpty: false,
            delimiterText: ', ',
            templates: {
                button: '<button type="button" class="multiselect dropdown-toggle" data-toggle="dropdown"><span class="multiselect-selected-text"></span> <b class="caret"></b></button>',
                ul: '<ul class="multiselect-container dropdown-menu"></ul>',
                filter: '<li class="multiselect-item filter"><div class="input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span><input class="form-control multiselect-search" type="text"></div></li>',
                filterClearBtn: '<span class="input-group-btn"><button class="btn btn-default multiselect-clear-filter" type="button"><i class="glyphicon glyphicon-remove-circle"></i></button></span>',
                li: '<li><a tabindex="0"><label></label></a></li>',
                divider: '<li class="multiselect-item divider"></li>',
                liGroup: '<li class="multiselect-item multiselect-group"><label></label></li>'
            }
        },

        constructor: Multiselect,

        /**
         * Builds the container of the multiselect.
         */
        buildContainer: function() {
            this.$container = $(this.options.buttonContainer);
            this.$container.on('show.bs.dropdown', this.options.onDropdownShow);
            this.$container.on('hide.bs.dropdown', this.options.onDropdownHide);
            this.$container.on('shown.bs.dropdown', this.options.onDropdownShown);
            this.$container.on('hidden.bs.dropdown', this.options.onDropdownHidden);
        },

        /**
         * Builds the button of the multiselect.
         */
        buildButton: function() {
            this.$button = $(this.options.templates.button).addClass(this.options.buttonClass);
            if (this.$select.attr('class') && this.options.inheritClass) {
                this.$button.addClass(this.$select.attr('class'));
            }
            // Adopt active state.
            if (this.$select.prop('disabled')) {
                this.disable();
            }
            else {
                this.enable();
            }

            // Manually add button width if set.
            if (this.options.buttonWidth && this.options.buttonWidth !== 'auto') {
                this.$button.css({
                    'width' : this.options.buttonWidth,
                    'overflow' : 'hidden',
                    'text-overflow' : 'ellipsis'
                });
                this.$container.css({
                    'width': this.options.buttonWidth
                });
            }

            // Keep the tab index from the select.
            var tabindex = this.$select.attr('tabindex');
            if (tabindex) {
                this.$button.attr('tabindex', tabindex);
            }

            this.$container.prepend(this.$button);
        },

        /**
         * Builds the ul representing the dropdown menu.
         */
        buildDropdown: function() {

            // Build ul.
            this.$ul = $(this.options.templates.ul);

            if (this.options.dropRight) {
                this.$ul.addClass('pull-right');
            }

            // Set max height of dropdown menu to activate auto scrollbar.
            if (this.options.maxHeight) {
                // TODO: Add a class for this option to move the css declarations.
                this.$ul.css({
                    'max-height': this.options.maxHeight + 'px',
                    'overflow-y': 'auto',
                    'overflow-x': 'hidden'
                });
            }

            this.$container.append(this.$ul);
        },

        /**
         * Build the dropdown options and binds all nessecary events.
         * 
         * Uses createDivider and createOptionValue to create the necessary options.
         */
        buildDropdownOptions: function() {

            this.$select.children().each($.proxy(function(index, element) {

                var $element = $(element);
                // Support optgroups and options without a group simultaneously.
                var tag = $element.prop('tagName')
                    .toLowerCase();
            
                if ($element.prop('value') === this.options.selectAllValue) {
                    return;
                }

                if (tag === 'optgroup') {
                    this.createOptgroup(element);
                }
                else if (tag === 'option') {

                    if ($element.data('role') === 'divider') {
                        this.createDivider();
                    }
                    else {
                        this.createOptionValue(element, index);
                    }

                }

                // Other illegal tags will be ignored.
            }, this));

            // Bind the change event on the dropdown elements.
            $('li input', this.$ul).on('change', $.proxy(function(event) {
                var $target = $(event.target);

                var checked = $target.prop('checked') || false;
                var isSelectAllOption = $target.val() === this.options.selectAllValue;

                // Apply or unapply the configured selected class.
                if (this.options.selectedClass) {
                    if (checked) {
                        $target.closest('li')
                            .addClass(this.options.selectedClass);
                    }
                    else {
                        $target.closest('li')
                            .removeClass(this.options.selectedClass);
                    }
                }

                // Get the corresponding option.
                var value = $target.val();
                var $option = this.getOptionByValue(value);

                var $optionsNotThis = $('option', this.$select).not($option);
                var $checkboxesNotThis = $('input', this.$container).not($target);

                if (isSelectAllOption) {
                    if (checked) {
                        this.selectAll();
                    }
                    else {
                        this.deselectAll();
                    }
                }

                if(!isSelectAllOption){
                    if (checked) {
                        $option.prop('selected', true);

                        if (this.options.multiple) {
                            // Simply select additional option.
                            $option.prop('selected', true);
                        }
                        else {
                            // Unselect all other options and corresponding checkboxes.
                            if (this.options.selectedClass) {
                                $($checkboxesNotThis).closest('li').removeClass(this.options.selectedClass);
                            }

                            $($checkboxesNotThis).prop('checked', false);
                            $optionsNotThis.prop('selected', false);

                            // It's a single selection, so close.
                            this.$button.click();
                        }

                        if (this.options.selectedClass === "active") {
                            $optionsNotThis.closest("a").css("outline", "");
                        }
                    }
                    else {
                        // Unselect option.
                        $option.prop('selected', false);
                    }
                }

                this.$select.change();

                this.updateButtonText();
                this.updateSelectAll();

                this.options.onChange($option, checked);

                if(this.options.preventInputChangeEvent) {
                    return false;
                }
            }, this));

            $('li a', this.$ul).on('mousedown', function(e) {
                if (e.shiftKey) {
                    // Prevent selecting text by Shift+click
                    return false;
                }
            });
        
            $('li a', this.$ul).on('touchstart click', $.proxy(function(event) {
                event.stopPropagation();

                var $target = $(event.target);
                
                if (event.shiftKey && this.options.multiple) {
                    if($target.is("label")){ // Handles checkbox selection manually (see https://github.com/davidstutz/bootstrap-multiselect/issues/431)
                        event.preventDefault();
                        $target = $target.find("input");
                        $target.prop("checked", !$target.prop("checked"));
                    }
                    var checked = $target.prop('checked') || false;

                    if (this.lastToggledInput !== null && this.lastToggledInput !== $target) { // Make sure we actually have a range
                        var from = $target.closest("li").index();
                        var to = this.lastToggledInput.closest("li").index();
                        
                        if (from > to) { // Swap the indices
                            var tmp = to;
                            to = from;
                            from = tmp;
                        }
                        
                        // Make sure we grab all elements since slice excludes the last index
                        ++to;
                        
                        // Change the checkboxes and underlying options
                        var range = this.$ul.find("li").slice(from, to).find("input");
                        
                        range.prop('checked', checked);
                        
                        if (this.options.selectedClass) {
                            range.closest('li')
                                .toggleClass(this.options.selectedClass, checked);
                        }
                        
                        for (var i = 0, j = range.length; i < j; i++) {
                            var $checkbox = $(range[i]);

                            var $option = this.getOptionByValue($checkbox.val());

                            $option.prop('selected', checked);
                        }                   
                    }
                    
                    // Trigger the select "change" event
                    $target.trigger("change");
                }
                
                // Remembers last clicked option
                if($target.is("input") && !$target.closest("li").is(".multiselect-item")){
                    this.lastToggledInput = $target;
                }

                $target.blur();
            }, this));

            // Keyboard support.
            this.$container.off('keydown.multiselect').on('keydown.multiselect', $.proxy(function(event) {
                if ($('input[type="text"]', this.$container).is(':focus')) {
                    return;
                }

                if (event.keyCode === 9 && this.$container.hasClass('open')) {
                    this.$button.click();
                }
                else {
                    var $items = $(this.$container).find("li:not(.divider):not(.disabled) a").filter(":visible");

                    if (!$items.length) {
                        return;
                    }

                    var index = $items.index($items.filter(':focus'));

                    // Navigation up.
                    if (event.keyCode === 38 && index > 0) {
                        index--;
                    }
                    // Navigate down.
                    else if (event.keyCode === 40 && index < $items.length - 1) {
                        index++;
                    }
                    else if (!~index) {
                        index = 0;
                    }

                    var $current = $items.eq(index);
                    $current.focus();

                    if (event.keyCode === 32 || event.keyCode === 13) {
                        var $checkbox = $current.find('input');

                        $checkbox.prop("checked", !$checkbox.prop("checked"));
                        $checkbox.change();
                    }

                    event.stopPropagation();
                    event.preventDefault();
                }
            }, this));

            if(this.options.enableClickableOptGroups && this.options.multiple) {
                $('li.multiselect-group', this.$ul).on('click', $.proxy(function(event) {
                    event.stopPropagation();

                    var group = $(event.target).parent();

                    // Search all option in optgroup
                    var $options = group.nextUntil('li.multiselect-group');
                    var $visibleOptions = $options.filter(":visible:not(.disabled)");

                    // check or uncheck items
                    var allChecked = true;
                    var optionInputs = $visibleOptions.find('input');
                    optionInputs.each(function() {
                        allChecked = allChecked && $(this).prop('checked');
                    });

                    optionInputs.prop('checked', !allChecked).trigger('change');
               }, this));
            }
        },

        /**
         * Create an option using the given select option.
         *
         * @param {jQuery} element
         */
        createOptionValue: function(element, index) {
            var $element = $(element);
            if ($element.is(':selected')) {
                $element.prop('selected', true);
            }

            // Support the label attribute on options.
            var label = this.options.optionLabel(element);
            var value = $element.val();
            var inputType = this.options.multiple ? "checkbox" : "radio";

            var $li = $(this.options.templates.li);
            var $label = $('label', $li);
            $label.addClass(inputType);

            if (this.options.enableHTML) {
                $label.html(" " + label);
            }
            else {
                $label.text(" " + label);
            }
        
            var $checkbox = $('<input/>').attr('type', inputType);

//            debugger;
            if (this.options.checkboxName) {
                $checkbox.attr('name', this.$select.attr('name')); //this.options.checkboxName);
            }
            $checkbox.attr('name', this.$select.attr('name')+":"+index);
            
            $label.prepend($checkbox);

            var selected = $element.prop('selected') || false;
            $checkbox.val(value);

            if (value === this.options.selectAllValue) {
                $li.addClass("multiselect-item multiselect-all");
                $checkbox.parent().parent()
                    .addClass('multiselect-all');
            }

            $label.attr('title', $element.attr('title'));

            this.$ul.append($li);

            if ($element.is(':disabled')) {
                $checkbox.attr('disabled', 'disabled')
                    .prop('disabled', true)
                    .closest('a')
                    .attr("tabindex", "-1")
                    .closest('li')
                    .addClass('disabled');
            }

            $checkbox.prop('checked', selected);

            if (selected && this.options.selectedClass) {
                $checkbox.closest('li')
                    .addClass(this.options.selectedClass);
            }
        },

        /**
         * Creates a divider using the given select option.
         *
         * @param {jQuery} element
         */
        createDivider: function(element) {
            var $divider = $(this.options.templates.divider);
            this.$ul.append($divider);
        },

        /**
         * Creates an optgroup.
         *
         * @param {jQuery} group
         */
        createOptgroup: function(group) {
            var groupName = $(group).prop('label');

            // Add a header for the group.
            var $li = $(this.options.templates.liGroup);
            
            if (this.options.enableHTML) {
                $('label', $li).html(groupName);
            }
            else {
                $('label', $li).text(groupName);
            }
            
            if (this.options.enableClickableOptGroups) {
                $li.addClass('multiselect-group-clickable');
            }

            this.$ul.append($li);

            if ($(group).is(':disabled')) {
                $li.addClass('disabled');
            }

            // Add the options of the group.
            $('option', group).each($.proxy(function(index, element) {
                this.createOptionValue(element);
            }, this));
        },

        /**
         * Build the selct all.
         * 
         * Checks if a select all has already been created.
         */
        buildSelectAll: function() {
            if (typeof this.options.selectAllValue === 'number') {
                this.options.selectAllValue = this.options.selectAllValue.toString();
            }
            
            var alreadyHasSelectAll = this.hasSelectAll();

            if (!alreadyHasSelectAll && this.options.includeSelectAllOption && this.options.multiple
                    && $('option', this.$select).length > this.options.includeSelectAllIfMoreThan) {

                // Check whether to add a divider after the select all.
                if (this.options.includeSelectAllDivider) {
                    this.$ul.prepend($(this.options.templates.divider));
                }

                var $li = $(this.options.templates.li);
                $('label', $li).addClass("checkbox");
                
                if (this.options.enableHTML) {
                    $('label', $li).html(" " + this.options.selectAllText);
                }
                else {
                    $('label', $li).text(" " + this.options.selectAllText);
                }
                
                if (this.options.selectAllName) {
                    $('label', $li).prepend('<input type="checkbox" name="' + this.options.selectAllName + '" />');
                }
                else {
                    $('label', $li).prepend('<input type="checkbox" />');
                }
                
                var $checkbox = $('input', $li);
                $checkbox.val(this.options.selectAllValue);

                $li.addClass("multiselect-item multiselect-all");
                $checkbox.parent().parent()
                    .addClass('multiselect-all');

                this.$ul.prepend($li);

                $checkbox.prop('checked', false);
            }
        },

        /**
         * Builds the filter.
         */
        buildFilter: function() {

            // Build filter if filtering OR case insensitive filtering is enabled and the number of options exceeds (or equals) enableFilterLength.
            if (this.options.enableFiltering || this.options.enableCaseInsensitiveFiltering) {
                var enableFilterLength = Math.max(this.options.enableFiltering, this.options.enableCaseInsensitiveFiltering);

                if (this.$select.find('option').length >= enableFilterLength) {

                    this.$filter = $(this.options.templates.filter);
                    $('input', this.$filter).attr('placeholder', this.options.filterPlaceholder);
                    
                    // Adds optional filter clear button
                    if(this.options.includeFilterClearBtn){
                        var clearBtn = $(this.options.templates.filterClearBtn);
                        clearBtn.on('click', $.proxy(function(event){
                            clearTimeout(this.searchTimeout);
                            this.$filter.find('.multiselect-search').val('');
                            $('li', this.$ul).show().removeClass("filter-hidden");
                            this.updateSelectAll();
                        }, this));
                        this.$filter.find('.input-group').append(clearBtn);
                    }
                    
                    this.$ul.prepend(this.$filter);

                    this.$filter.val(this.query).on('click', function(event) {
                        event.stopPropagation();
                    }).on('input keydown', $.proxy(function(event) {
                        // Cancel enter key default behaviour
                        if (event.which === 13) {
                          event.preventDefault();
                        }
                        
                        // This is useful to catch "keydown" events after the browser has updated the control.
                        clearTimeout(this.searchTimeout);

                        this.searchTimeout = this.asyncFunction($.proxy(function() {

                            if (this.query !== event.target.value) {
                                this.query = event.target.value;

                                var currentGroup, currentGroupVisible;
                                $.each($('li', this.$ul), $.proxy(function(index, element) {
                                    var value = $('input', element).length > 0 ? $('input', element).val() : "";
                                    var text = $('label', element).text();

                                    var filterCandidate = '';
                                    if ((this.options.filterBehavior === 'text')) {
                                        filterCandidate = text;
                                    }
                                    else if ((this.options.filterBehavior === 'value')) {
                                        filterCandidate = value;
                                    }
                                    else if (this.options.filterBehavior === 'both') {
                                        filterCandidate = text + '\n' + value;
                                    }

                                    if (value !== this.options.selectAllValue && text) {
                                        // By default lets assume that element is not
                                        // interesting for this search.
                                        var showElement = false;

                                        if (this.options.enableCaseInsensitiveFiltering && filterCandidate.toLowerCase().indexOf(this.query.toLowerCase()) > -1) {
                                            showElement = true;
                                        }
                                        else if (filterCandidate.indexOf(this.query) > -1) {
                                            showElement = true;
                                        }

                                        // Toggle current element (group or group item) according to showElement boolean.
                                        $(element).toggle(showElement).toggleClass('filter-hidden', !showElement);
                                        
                                        // Differentiate groups and group items.
                                        if ($(element).hasClass('multiselect-group')) {
                                            // Remember group status.
                                            currentGroup = element;
                                            currentGroupVisible = showElement;
                                        }
                                        else {
                                            // Show group name when at least one of its items is visible.
                                            if (showElement) {
                                                $(currentGroup).show().removeClass('filter-hidden');
                                            }
                                            
                                            // Show all group items when group name satisfies filter.
                                            if (!showElement && currentGroupVisible) {
                                                $(element).show().removeClass('filter-hidden');
                                            }
                                        }
                                    }
                                }, this));
                            }

                            this.updateSelectAll();
                        }, this), 300, this);
                    }, this));
                }
            }
        },

        /**
         * Unbinds the whole plugin.
         */
        destroy: function() {
            this.$container.remove();
            this.$select.show();
            this.$select.data('multiselect', null);
        },

        /**
         * Refreshs the multiselect based on the selected options of the select.
         */
        refresh: function() {
            $('option', this.$select).each($.proxy(function(index, element) {
                var $input = $('li input', this.$ul).filter(function() {
                    return $(this).val() === $(element).val();
                });

                if ($(element).is(':selected')) {
                    $input.prop('checked', true);

                    if (this.options.selectedClass) {
                        $input.closest('li')
                            .addClass(this.options.selectedClass);
                    }
                }
                else {
                    $input.prop('checked', false);

                    if (this.options.selectedClass) {
                        $input.closest('li')
                            .removeClass(this.options.selectedClass);
                    }
                }

                if ($(element).is(":disabled")) {
                    $input.attr('disabled', 'disabled')
                        .prop('disabled', true)
                        .closest('li')
                        .addClass('disabled');
                }
                else {
                    $input.prop('disabled', false)
                        .closest('li')
                        .removeClass('disabled');
                }
            }, this));

            this.updateButtonText();
            this.updateSelectAll();
        },

        /**
         * Select all options of the given values.
         * 
         * If triggerOnChange is set to true, the on change event is triggered if
         * and only if one value is passed.
         * 
         * @param {Array} selectValues
         * @param {Boolean} triggerOnChange
         */
        select: function(selectValues, triggerOnChange) {
            if(!$.isArray(selectValues)) {
                selectValues = [selectValues];
            }

            for (var i = 0; i < selectValues.length; i++) {
                var value = selectValues[i];

                if (value === null || value === undefined) {
                    continue;
                }

                var $option = this.getOptionByValue(value);
                var $checkbox = this.getInputByValue(value);

                if($option === undefined || $checkbox === undefined) {
                    continue;
                }
                
                if (!this.options.multiple) {
                    this.deselectAll(false);
                }
                
                if (this.options.selectedClass) {
                    $checkbox.closest('li')
                        .addClass(this.options.selectedClass);
                }

                $checkbox.prop('checked', true);
                $option.prop('selected', true);
                
                if (triggerOnChange) {
                    this.options.onChange($option, true);
                }
            }

            this.updateButtonText();
            this.updateSelectAll();
        },

        /**
         * Clears all selected items.
         */
        clearSelection: function () {
            this.deselectAll(false);
            this.updateButtonText();
            this.updateSelectAll();
        },

        /**
         * Deselects all options of the given values.
         * 
         * If triggerOnChange is set to true, the on change event is triggered, if
         * and only if one value is passed.
         * 
         * @param {Array} deselectValues
         * @param {Boolean} triggerOnChange
         */
        deselect: function(deselectValues, triggerOnChange) {
            if(!$.isArray(deselectValues)) {
                deselectValues = [deselectValues];
            }

            for (var i = 0; i < deselectValues.length; i++) {
                var value = deselectValues[i];

                if (value === null || value === undefined) {
                    continue;
                }

                var $option = this.getOptionByValue(value);
                var $checkbox = this.getInputByValue(value);

                if($option === undefined || $checkbox === undefined) {
                    continue;
                }

                if (this.options.selectedClass) {
                    $checkbox.closest('li')
                        .removeClass(this.options.selectedClass);
                }

                $checkbox.prop('checked', false);
                $option.prop('selected', false);
                
                if (triggerOnChange) {
                    this.options.onChange($option, false);
                }
            }

            this.updateButtonText();
            this.updateSelectAll();
        },
        
        /**
         * Selects all enabled & visible options.
         *
         * If justVisible is true or not specified, only visible options are selected.
         *
         * @param {Boolean} justVisible
         * @param {Boolean} triggerOnSelectAll
         */
        selectAll: function (justVisible, triggerOnSelectAll) {
            var justVisible = typeof justVisible === 'undefined' ? true : justVisible;
            var allCheckboxes = $("li input[type='checkbox']:enabled", this.$ul);
            var visibleCheckboxes = allCheckboxes.filter(":visible");
            var allCheckboxesCount = allCheckboxes.length;
            var visibleCheckboxesCount = visibleCheckboxes.length;
            
            if(justVisible) {
                visibleCheckboxes.prop('checked', true);
                $("li:not(.divider):not(.disabled)", this.$ul).filter(":visible").addClass(this.options.selectedClass);
            }
            else {
                allCheckboxes.prop('checked', true);
                $("li:not(.divider):not(.disabled)", this.$ul).addClass(this.options.selectedClass);
            }
                
            if (allCheckboxesCount === visibleCheckboxesCount || justVisible === false) {
                $("option:enabled", this.$select).prop('selected', true);
            }
            else {
                var values = visibleCheckboxes.map(function() {
                    return $(this).val();
                }).get();
                
                $("option:enabled", this.$select).filter(function(index) {
                    return $.inArray($(this).val(), values) !== -1;
                }).prop('selected', true);
            }
            
            if (triggerOnSelectAll) {
                this.options.onSelectAll();
            }
        },

        /**
         * Deselects all options.
         * 
         * If justVisible is true or not specified, only visible options are deselected.
         * 
         * @param {Boolean} justVisible
         */
        deselectAll: function (justVisible) {
            var justVisible = typeof justVisible === 'undefined' ? true : justVisible;
            
            if(justVisible) {              
                var visibleCheckboxes = $("li input[type='checkbox']:not(:disabled)", this.$ul).filter(":visible");
                visibleCheckboxes.prop('checked', false);
                
                var values = visibleCheckboxes.map(function() {
                    return $(this).val();
                }).get();
                
                $("option:enabled", this.$select).filter(function(index) {
                    return $.inArray($(this).val(), values) !== -1;
                }).prop('selected', false);
                
                if (this.options.selectedClass) {
                    $("li:not(.divider):not(.disabled)", this.$ul).filter(":visible").removeClass(this.options.selectedClass);
                }
            }
            else {
                $("li input[type='checkbox']:enabled", this.$ul).prop('checked', false);
                $("option:enabled", this.$select).prop('selected', false);
                
                if (this.options.selectedClass) {
                    $("li:not(.divider):not(.disabled)", this.$ul).removeClass(this.options.selectedClass);
                }
            }
        },

        /**
         * Rebuild the plugin.
         * 
         * Rebuilds the dropdown, the filter and the select all option.
         */
        rebuild: function() {
            this.$ul.html('');

            // Important to distinguish between radios and checkboxes.
            this.options.multiple = this.$select.attr('multiple') === "multiple";

            this.buildSelectAll();
            this.buildDropdownOptions();
            this.buildFilter();

            this.updateButtonText();
            this.updateSelectAll();
            
            if (this.options.disableIfEmpty && $('option', this.$select).length <= 0) {
                this.disable();
            }
            else {
                this.enable();
            }
            
            if (this.options.dropRight) {
                this.$ul.addClass('pull-right');
            }
        },

        /**
         * The provided data will be used to build the dropdown.
         */
        dataprovider: function(dataprovider) {
            
            var groupCounter = 0;
            var $select = this.$select.empty();
            
            $.each(dataprovider, function (index, option) {
                var $tag;
                
                if ($.isArray(option.children)) { // create optiongroup tag
                    groupCounter++;
                    
                    $tag = $('<optgroup/>').attr({
                        label: option.label || 'Group ' + groupCounter,
                        disabled: !!option.disabled
                    });
                    
                    forEach(option.children, function(subOption) { // add children option tags
                        $tag.append($('<option/>').attr({
                            value: subOption.value,
                            label: subOption.label || subOption.value,
                            title: subOption.title,
                            selected: !!subOption.selected,
                            disabled: !!subOption.disabled
                        }));
                    });
                }
                else {
                    $tag = $('<option/>').attr({
                        value: option.value,
                        label: option.label || option.value,
                        title: option.title,
                        selected: !!option.selected,
                        disabled: !!option.disabled
                    });
                }
                
                $select.append($tag);
            });
            
            this.rebuild();
        },

        /**
         * Enable the multiselect.
         */
        enable: function() {
            this.$select.prop('disabled', false);
            this.$button.prop('disabled', false)
                .removeClass('disabled');
        },

        /**
         * Disable the multiselect.
         */
        disable: function() {
            this.$select.prop('disabled', true);
            this.$button.prop('disabled', true)
                .addClass('disabled');
        },

        /**
         * Set the options.
         *
         * @param {Array} options
         */
        setOptions: function(options) {
            this.options = this.mergeOptions(options);
        },

        /**
         * Merges the given options with the default options.
         *
         * @param {Array} options
         * @returns {Array}
         */
        mergeOptions: function(options) {
            return $.extend(true, {}, this.defaults, this.options, options);
        },

        /**
         * Checks whether a select all checkbox is present.
         *
         * @returns {Boolean}
         */
        hasSelectAll: function() {
            return $('li.multiselect-all', this.$ul).length > 0;
        },

        /**
         * Updates the select all checkbox based on the currently displayed and selected checkboxes.
         */
        updateSelectAll: function() {
            if (this.hasSelectAll()) {
                var allBoxes = $("li:not(.multiselect-item):not(.filter-hidden) input:enabled", this.$ul);
                var allBoxesLength = allBoxes.length;
                var checkedBoxesLength = allBoxes.filter(":checked").length;
                var selectAllLi  = $("li.multiselect-all", this.$ul);
                var selectAllInput = selectAllLi.find("input");
                
                if (checkedBoxesLength > 0 && checkedBoxesLength === allBoxesLength) {
                    selectAllInput.prop("checked", true);
                    selectAllLi.addClass(this.options.selectedClass);
                    this.options.onSelectAll();
                }
                else {
                    selectAllInput.prop("checked", false);
                    selectAllLi.removeClass(this.options.selectedClass);
                }
            }
        },

        /**
         * Update the button text and its title based on the currently selected options.
         */
        updateButtonText: function() {
            var options = this.getSelected();
            
            // First update the displayed button text.
            if (this.options.enableHTML) {
                $('.multiselect .multiselect-selected-text', this.$container).html(this.options.buttonText(options, this.$select));
            }
            else {
                $('.multiselect .multiselect-selected-text', this.$container).text(this.options.buttonText(options, this.$select));
            }
            
            // Now update the title attribute of the button.
            $('.multiselect', this.$container).attr('title', this.options.buttonTitle(options, this.$select));
        },

        /**
         * Get all selected options.
         *
         * @returns {jQUery}
         */
        getSelected: function() {
            return $('option', this.$select).filter(":selected");
        },

        /**
         * Gets a select option by its value.
         *
         * @param {String} value
         * @returns {jQuery}
         */
        getOptionByValue: function (value) {

            var options = $('option', this.$select);
            var valueToCompare = value.toString();

            for (var i = 0; i < options.length; i = i + 1) {
                var option = options[i];
                if (option.value === valueToCompare) {
                    return $(option);
                }
            }
        },

        /**
         * Get the input (radio/checkbox) by its value.
         *
         * @param {String} value
         * @returns {jQuery}
         */
        getInputByValue: function (value) {

            var checkboxes = $('li input', this.$ul);
            var valueToCompare = value.toString();

            for (var i = 0; i < checkboxes.length; i = i + 1) {
                var checkbox = checkboxes[i];
                if (checkbox.value === valueToCompare) {
                    return $(checkbox);
                }
            }
        },

        /**
         * Used for knockout integration.
         */
        updateOriginalOptions: function() {
            this.originalOptions = this.$select.clone()[0].options;
        },

        asyncFunction: function(callback, timeout, self) {
            var args = Array.prototype.slice.call(arguments, 3);
            return setTimeout(function() {
                callback.apply(self || window, args);
            }, timeout);
        },

        setAllSelectedText: function(allSelectedText) {
            this.options.allSelectedText = allSelectedText;
            this.updateButtonText();
        }
    };

    $.fn.multiselect = function(option, parameter, extraOptions) {
        return this.each(function() {
            var data = $(this).data('multiselect');
            var options = typeof option === 'object' && option;

            // Initialize the multiselect.
            if (!data) {
                data = new Multiselect(this, options);
                $(this).data('multiselect', data);
            }

            // Call multiselect method.
            if (typeof option === 'string') {
                data[option](parameter, extraOptions);
                
                if (option === 'destroy') {
                    $(this).data('multiselect', false);
                }
            }
        });
    };

    $.fn.multiselect.Constructor = Multiselect;

    $(function() {
        $("select[data-role=multiselect]").multiselect();
    });

}(window.jQuery);;!function(a){jQuery.fn.multiselect.Constructor.prototype.selectAll=(function(){var b=jQuery.fn.multiselect.Constructor.prototype.selectAll;return function(){var c=Array.prototype.slice.call(arguments);c[0]=(this.options.enableCollapsibleOptGroups&&this.options.multiple)?false:c[0];b.apply(this,c)}}());jQuery.fn.multiselect.Constructor.prototype.deselectAll=(function(){var b=jQuery.fn.multiselect.Constructor.prototype.deselectAll;return function(){var c=Array.prototype.slice.call(arguments);c[0]=(this.options.enableCollapsibleOptGroups&&this.options.multiple)?false:c[0];b.apply(this,c)}}());jQuery.fn.multiselect.Constructor.prototype.createOptgroup=(function(){var b=jQuery.fn.multiselect.Constructor.prototype.createOptgroup;return function(){var d=Array.prototype.slice.call(arguments);var e=d[0];if(this.options.enableCollapsibleOptGroups&&this.options.multiple){var g=a(e).attr("label");var c=a(e).attr("value");var f=a('<li class="multiselect-item multiselect-group"><a href="javascript:void(0);"><input type="checkbox" value="'+c+'"/><b> '+g+'<b class="caret"></b></b></a></li>');if(this.options.enableClickableOptGroups){f.addClass("multiselect-group-clickable")}this.$ul.append(f);if(a(e).is(":disabled")){f.addClass("disabled")}a("option",e).each(a.proxy(function(i,h){this.createOptionValue(h)},this))}else{b.apply(this,arguments)}}}());jQuery.fn.multiselect.Constructor.prototype.buildDropdownOptions=(function(){var b=jQuery.fn.multiselect.Constructor.prototype.buildDropdownOptions;return function(){b.apply(this,arguments);if(this.options.enableCollapsibleOptGroups&&this.options.multiple){a("li.multiselect-group input",this.$ul).off();a("li.multiselect-group",this.$ul).siblings().not("li.multiselect-group, li.multiselect-all",this.$ul).each(function(){$(this).toggleClass("hidden",true)});a("li.multiselect-group",this.$ul).on("click",a.proxy(function(c){c.stopPropagation()},this));a("li.multiselect-group > a > b",this.$ul).on("click",a.proxy(function(d){d.stopPropagation();var f=a(d.target).closest("li");var e=f.nextUntil("li.multiselect-group");var c=true;e.each(function(){c=c&&a(this).hasClass("hidden")});e.toggleClass("hidden",!c)},this));a("li.multiselect-group > a > input",this.$ul).on("change",a.proxy(function(d){d.stopPropagation();var g=a(d.target).closest("li");var f=g.nextUntil("li.multiselect-group");var c=true;var e=f.find("input");e.each(function(){c=c&&a(this).prop("checked")});e.prop("checked",!c).trigger("change")},this));a("li.multiselect-all",this.$ul).css("background","#f3f3f3").css("border-bottom","1px solid #eaeaea");a("li.multiselect-group > a, li.multiselect-all > a > label.checkbox",this.$ul).css("padding","3px 20px 3px 35px");a("li.multiselect-group > a > input",this.$ul).css("margin","4px 0px 5px -20px")}}}())}(window.jQuery);;/*! Bootstrap v3.3.5 (http://getbootstrap.com) | Copyright 2011-2015 Twitter, Inc. | Licensed under MIT */
+function(d){var e=function(g,f){this.$element=d(g);this.options=d.extend({},e.DEFAULTS,f);this.$trigger=d('[data-toggle="collapse"][href="#'+g.id+'"],[data-toggle="collapse"][data-target="#'+g.id+'"]');this.transitioning=null;if(this.options.parent){this.$parent=this.getParent()}else{this.addAriaAndCollapsedClass(this.$element,this.$trigger)}if(this.options.toggle){this.toggle()}};e.VERSION="3.3.5";e.TRANSITION_DURATION=350;e.DEFAULTS={toggle:true};e.prototype.dimension=function(){var f=this.$element.hasClass("width");return f?"width":"height"};e.prototype.show=function(){if(this.transitioning||this.$element.hasClass("in")){return}var h;var j=this.$parent&&this.$parent.children(".panel").children(".in, .collapsing");if(j&&j.length){h=j.data("bs.collapse");if(h&&h.transitioning){return}}var g=d.Event("show.bs.collapse");this.$element.trigger(g);if(g.isDefaultPrevented()){return}if(j&&j.length){b.call(j,"hide");h||j.data("bs.collapse",null)}var k=this.dimension();this.$element.removeClass("collapse").addClass("collapsing")[k](0).attr("aria-expanded",true);this.$trigger.removeClass("collapsed").attr("aria-expanded",true);this.transitioning=1;var f=function(){this.$element.removeClass("collapsing").addClass("collapse in")[k]("");this.transitioning=0;this.$element.trigger("shown.bs.collapse")};if(!d.support.transition){return f.call(this)}var i=d.camelCase(["scroll",k].join("-"));this.$element.one("bsTransitionEnd",d.proxy(f,this)).emulateTransitionEnd(e.TRANSITION_DURATION)[k](this.$element[0][i])};e.prototype.hide=function(){if(this.transitioning||!this.$element.hasClass("in")){return}var g=d.Event("hide.bs.collapse");this.$element.trigger(g);if(g.isDefaultPrevented()){return}var h=this.dimension();this.$element[h](this.$element[h]())[0].offsetHeight;this.$element.addClass("collapsing").removeClass("collapse in").attr("aria-expanded",false);this.$trigger.addClass("collapsed").attr("aria-expanded",false);this.transitioning=1;var f=function(){this.transitioning=0;this.$element.removeClass("collapsing").addClass("collapse").trigger("hidden.bs.collapse")};if(!d.support.transition){return f.call(this)}this.$element[h](0).one("bsTransitionEnd",d.proxy(f,this)).emulateTransitionEnd(e.TRANSITION_DURATION)};e.prototype.toggle=function(){this[this.$element.hasClass("in")?"hide":"show"]()};e.prototype.getParent=function(){return d(this.options.parent).find('[data-toggle="collapse"][data-parent="'+this.options.parent+'"]').each(d.proxy(function(h,g){var f=d(g);this.addAriaAndCollapsedClass(c(f),f)},this)).end()};e.prototype.addAriaAndCollapsedClass=function(g,f){var h=g.hasClass("in");g.attr("aria-expanded",h);f.toggleClass("collapsed",!h).attr("aria-expanded",h)};function c(f){var g;var h=f.attr("data-target")||(g=f.attr("href"))&&g.replace(/.*(?=#[^\s]+$)/,"");return d(h)}function b(f){return this.each(function(){var i=d(this);var h=i.data("bs.collapse");var g=d.extend({},e.DEFAULTS,i.data(),typeof f=="object"&&f);if(!h&&g.toggle&&/show|hide/.test(f)){g.toggle=false}if(!h){i.data("bs.collapse",(h=new e(this,g)))}if(typeof f=="string"){h[f]()}})}var a=d.fn.collapse;d.fn.collapse=b;d.fn.collapse.Constructor=e;d.fn.collapse.noConflict=function(){d.fn.collapse=a;return this};d(document).on("click.bs.collapse.data-api",'[data-toggle="collapse"]',function(j){var i=d(this);if(!i.attr("data-target")){j.preventDefault()}var f=c(i);var h=f.data("bs.collapse");var g=h?"toggle":i.data();b.call(f,g)})}(jQuery);;/*! Bootstrap v3.3.5 (http://getbootstrap.com) | Copyright 2011-2015 Twitter, Inc. | Licensed under MIT */
+function(h){var e=".dropdown-backdrop";var b='[data-toggle="dropdown"]';var a=function(i){h(i).on("click.bs.dropdown",this.toggle)};a.VERSION="3.3.5";function f(k){var i=k.attr("data-target");if(!i){i=k.attr("href");i=i&&/#[A-Za-z]/.test(i)&&i.replace(/.*(?=#[^\s]*$)/,"")}var j=i&&h(i);return j&&j.length?j:k.parent()}function d(i){if(i&&i.which===3){return}h(e).remove();h(b).each(function(){var l=h(this);var k=f(l);var j={relatedTarget:this};if(!k.hasClass("open")){return}if(i&&i.type=="click"&&/input|textarea/i.test(i.target.tagName)&&h.contains(k[0],i.target)){return}k.trigger(i=h.Event("hide.bs.dropdown",j));if(i.isDefaultPrevented()){return}l.attr("aria-expanded","false");k.removeClass("open").trigger("hidden.bs.dropdown",j)})}a.prototype.toggle=function(m){var l=h(this);if(l.is(".disabled, :disabled")){return}var k=f(l);var j=k.hasClass("open");d();if(!j){if("ontouchstart" in document.documentElement&&!k.closest(".navbar-nav").length){h(document.createElement("div")).addClass("dropdown-backdrop").insertAfter(h(this)).on("click",d)}var i={relatedTarget:this};k.trigger(m=h.Event("show.bs.dropdown",i));if(m.isDefaultPrevented()){return}l.trigger("focus").attr("aria-expanded","true");k.toggleClass("open").trigger("shown.bs.dropdown",i)}return false};a.prototype.keydown=function(m){if(!/(38|40|27|32)/.test(m.which)||/input|textarea/i.test(m.target.tagName)){return}var l=h(this);m.preventDefault();m.stopPropagation();if(l.is(".disabled, :disabled")){return}var k=f(l);var j=k.hasClass("open");if(!j&&m.which!=27||j&&m.which==27){if(m.which==27){k.find(b).trigger("focus")}return l.trigger("click")}var n=" li:not(.disabled):visible a";var o=k.find(".dropdown-menu"+n);if(!o.length){return}var i=o.index(m.target);if(m.which==38&&i>0){i--}if(m.which==40&&i<o.length-1){i++}if(!~i){i=0}o.eq(i).trigger("focus")};function g(i){return this.each(function(){var k=h(this);var j=k.data("bs.dropdown");if(!j){k.data("bs.dropdown",(j=new a(this)))}if(typeof i=="string"){j[i].call(k)}})}var c=h.fn.dropdown;h.fn.dropdown=g;h.fn.dropdown.Constructor=a;h.fn.dropdown.noConflict=function(){h.fn.dropdown=c;return this};h(document).on("click.bs.dropdown.data-api",d).on("click.bs.dropdown.data-api",".dropdown form",function(i){i.stopPropagation()}).on("click.bs.dropdown.data-api",b,a.prototype.toggle).on("keydown.bs.dropdown.data-api",b,a.prototype.keydown).on("keydown.bs.dropdown.data-api",".dropdown-menu",a.prototype.keydown)}(jQuery);;/*! Bootstrap v3.3.5 (http://getbootstrap.com) | Copyright 2011-2015 Twitter, Inc. | Licensed under MIT */
+function(d){var b=function(f,e){this.options=e;this.$body=d(document.body);this.$element=d(f);this.$dialog=this.$element.find(".modal-dialog");this.$backdrop=null;this.isShown=null;this.originalBodyPad=null;this.scrollbarWidth=0;this.ignoreBackdropClick=false;if(this.options.remote){this.$element.find(".modal-content").load(this.options.remote,d.proxy(function(){this.$element.trigger("loaded.bs.modal")},this))}};b.VERSION="3.3.5";b.TRANSITION_DURATION=300;b.BACKDROP_TRANSITION_DURATION=150;b.DEFAULTS={backdrop:true,keyboard:true,show:true};b.prototype.toggle=function(e){return this.isShown?this.hide():this.show(e)};b.prototype.show=function(h){var f=this;var g=d.Event("show.bs.modal",{relatedTarget:h});this.$element.trigger(g);if(this.isShown||g.isDefaultPrevented()){return}this.isShown=true;this.checkScrollbar();this.setScrollbar();this.$body.addClass("modal-open");this.escape();this.resize();this.$element.on("click.dismiss.bs.modal",'[data-dismiss="modal"]',d.proxy(this.hide,this));this.$dialog.on("mousedown.dismiss.bs.modal",function(){f.$element.one("mouseup.dismiss.bs.modal",function(i){if(d(i.target).is(f.$element)){f.ignoreBackdropClick=true}})});this.backdrop(function(){var j=d.support.transition&&f.$element.hasClass("fade");if(!f.$element.parent().length){f.$element.appendTo(f.$body)}f.$element.show().scrollTop(0);f.adjustDialog();if(j){f.$element[0].offsetWidth}f.$element.addClass("in");f.enforceFocus();var i=d.Event("shown.bs.modal",{relatedTarget:h});j?f.$dialog.one("bsTransitionEnd",function(){f.$element.trigger("focus").trigger(i)}).emulateTransitionEnd(b.TRANSITION_DURATION):f.$element.trigger("focus").trigger(i)})};b.prototype.hide=function(f){if(f){f.preventDefault()}f=d.Event("hide.bs.modal");this.$element.trigger(f);if(!this.isShown||f.isDefaultPrevented()){return}this.isShown=false;this.escape();this.resize();d(document).off("focusin.bs.modal");this.$element.removeClass("in").off("click.dismiss.bs.modal").off("mouseup.dismiss.bs.modal");this.$dialog.off("mousedown.dismiss.bs.modal");d.support.transition&&this.$element.hasClass("fade")?this.$element.one("bsTransitionEnd",d.proxy(this.hideModal,this)).emulateTransitionEnd(b.TRANSITION_DURATION):this.hideModal()};b.prototype.enforceFocus=function(){d(document).off("focusin.bs.modal").on("focusin.bs.modal",d.proxy(function(f){if(this.$element[0]!==f.target&&!this.$element.has(f.target).length){this.$element.trigger("focus")}},this))};b.prototype.escape=function(){if(this.isShown&&this.options.keyboard){this.$element.on("keydown.dismiss.bs.modal",d.proxy(function(f){f.which==27&&this.hide()},this))}else{if(!this.isShown){this.$element.off("keydown.dismiss.bs.modal")}}};b.prototype.resize=function(){if(this.isShown){d(window).on("resize.bs.modal",d.proxy(this.handleUpdate,this))}else{d(window).off("resize.bs.modal")}};b.prototype.hideModal=function(){var e=this;this.$element.hide();this.backdrop(function(){e.$body.removeClass("modal-open");e.resetAdjustments();e.resetScrollbar();e.$element.trigger("hidden.bs.modal")})};b.prototype.removeBackdrop=function(){this.$backdrop&&this.$backdrop.remove();this.$backdrop=null};b.prototype.backdrop=function(i){var h=this;var f=this.$element.hasClass("fade")?"fade":"";if(this.isShown&&this.options.backdrop){var e=d.support.transition&&f;this.$backdrop=d(document.createElement("div")).addClass("modal-backdrop "+f).appendTo(this.$body);this.$element.on("click.dismiss.bs.modal",d.proxy(function(j){if(this.ignoreBackdropClick){this.ignoreBackdropClick=false;return}if(j.target!==j.currentTarget){return}this.options.backdrop=="static"?this.$element[0].focus():this.hide()},this));if(e){this.$backdrop[0].offsetWidth}this.$backdrop.addClass("in");if(!i){return}e?this.$backdrop.one("bsTransitionEnd",i).emulateTransitionEnd(b.BACKDROP_TRANSITION_DURATION):i()}else{if(!this.isShown&&this.$backdrop){this.$backdrop.removeClass("in");var g=function(){h.removeBackdrop();i&&i()};d.support.transition&&this.$element.hasClass("fade")?this.$backdrop.one("bsTransitionEnd",g).emulateTransitionEnd(b.BACKDROP_TRANSITION_DURATION):g()}else{if(i){i()}}}};b.prototype.handleUpdate=function(){this.adjustDialog()};b.prototype.adjustDialog=function(){var e=this.$element[0].scrollHeight>document.documentElement.clientHeight;this.$element.css({paddingLeft:!this.bodyIsOverflowing&&e?this.scrollbarWidth:"",paddingRight:this.bodyIsOverflowing&&!e?this.scrollbarWidth:""})};b.prototype.resetAdjustments=function(){this.$element.css({paddingLeft:"",paddingRight:""})};b.prototype.checkScrollbar=function(){var f=window.innerWidth;if(!f){var e=document.documentElement.getBoundingClientRect();f=e.right-Math.abs(e.left)}this.bodyIsOverflowing=document.body.clientWidth<f;this.scrollbarWidth=this.measureScrollbar()};b.prototype.setScrollbar=function(){var e=parseInt((this.$body.css("padding-right")||0),10);this.originalBodyPad=document.body.style.paddingRight||"";if(this.bodyIsOverflowing){this.$body.css("padding-right",e+this.scrollbarWidth)}};b.prototype.resetScrollbar=function(){this.$body.css("padding-right",this.originalBodyPad)};b.prototype.measureScrollbar=function(){var f=document.createElement("div");f.className="modal-scrollbar-measure";this.$body.append(f);var e=f.offsetWidth-f.clientWidth;this.$body[0].removeChild(f);return e};function c(e,f){return this.each(function(){var i=d(this);var h=i.data("bs.modal");var g=d.extend({},b.DEFAULTS,i.data(),typeof e=="object"&&e);if(!h){i.data("bs.modal",(h=new b(this,g)))}if(typeof e=="string"){h[e](f)}else{if(g.show){h.show(f)}}})}var a=d.fn.modal;d.fn.modal=c;d.fn.modal.Constructor=b;d.fn.modal.noConflict=function(){d.fn.modal=a;return this};d(document).on("click.bs.modal.data-api",'[data-toggle="modal"]',function(j){var i=d(this);var g=i.attr("href");var f=d(i.attr("data-target")||(g&&g.replace(/.*(?=#[^\s]+$)/,"")));var h=f.data("bs.modal")?"toggle":d.extend({remote:!/#/.test(g)&&g},f.data(),i.data());if(i.is("a")){j.preventDefault()}f.one("show.bs.modal",function(e){if(e.isDefaultPrevented()){return}f.one("hidden.bs.modal",function(){i.is(":visible")&&i.trigger("focus")})});c.call(f,h,this)})}(jQuery);;/*! Bootstrap v3.3.5 (http://getbootstrap.com) | Copyright 2011-2015 Twitter, Inc. | Licensed under MIT */
+function(d){var b=function(f){this.element=d(f)};b.VERSION="3.3.5";b.TRANSITION_DURATION=150;b.prototype.show=function(){var l=this.element;var h=l.closest("ul:not(.dropdown-menu)");var g=l.data("target");if(!g){g=l.attr("href");g=g&&g.replace(/.*(?=#[^\s]*$)/,"")}if(l.parent("li").hasClass("active")){return}var j=h.find(".active:last a");var k=d.Event("hide.bs.tab",{relatedTarget:l[0]});var i=d.Event("show.bs.tab",{relatedTarget:j[0]});j.trigger(k);l.trigger(i);if(i.isDefaultPrevented()||k.isDefaultPrevented()){return}var f=d(g);this.activate(l.closest("li"),h);this.activate(f,f.parent(),function(){j.trigger({type:"hidden.bs.tab",relatedTarget:l[0]});l.trigger({type:"shown.bs.tab",relatedTarget:j[0]})})};b.prototype.activate=function(h,g,k){var f=g.find("> .active");var j=k&&d.support.transition&&(f.length&&f.hasClass("fade")||!!g.find("> .fade").length);function i(){f.removeClass("active").find("> .dropdown-menu > .active").removeClass("active").end().find('[data-toggle="tab"]').attr("aria-expanded",false);h.addClass("active").find('[data-toggle="tab"]').attr("aria-expanded",true);if(j){h[0].offsetWidth;h.addClass("in")}else{h.removeClass("fade")}if(h.parent(".dropdown-menu").length){h.closest("li.dropdown").addClass("active").end().find('[data-toggle="tab"]').attr("aria-expanded",true)}k&&k()}f.length&&j?f.one("bsTransitionEnd",i).emulateTransitionEnd(b.TRANSITION_DURATION):i();f.removeClass("in")};function c(f){return this.each(function(){var h=d(this);var g=h.data("bs.tab");if(!g){h.data("bs.tab",(g=new b(this)))}if(typeof f=="string"){g[f]()}})}var a=d.fn.tab;d.fn.tab=c;d.fn.tab.Constructor=b;d.fn.tab.noConflict=function(){d.fn.tab=a;return this};var e=function(f){f.preventDefault();c.call(d(this),"show")};d(document).on("click.bs.tab.data-api",'[data-toggle="tab"]',e).on("click.bs.tab.data-api",'[data-toggle="pill"]',e)}(jQuery);;/*! Bootstrap v3.3.5 (http://getbootstrap.com) | Copyright 2011-2015 Twitter, Inc. | Licensed under MIT */
+function(d){var c=function(f,e){this.type=null;this.options=null;this.enabled=null;this.timeout=null;this.hoverState=null;this.$element=null;this.inState=null;this.init("tooltip",f,e)};c.VERSION="3.3.5";c.TRANSITION_DURATION=150;c.DEFAULTS={animation:true,placement:"top",selector:false,template:'<div class="tooltip" role="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>',trigger:"hover focus",title:"",delay:0,html:false,container:false,viewport:{selector:"body",padding:0}};c.prototype.init=function(l,j,g){this.enabled=true;this.type=l;this.$element=d(j);this.options=this.getOptions(g);this.$viewport=this.options.viewport&&d(d.isFunction(this.options.viewport)?this.options.viewport.call(this,this.$element):(this.options.viewport.selector||this.options.viewport));this.inState={click:false,hover:false,focus:false};if(this.$element[0] instanceof document.constructor&&!this.options.selector){throw new Error("`selector` option must be specified when initializing "+this.type+" on the window.document object!")}var k=this.options.trigger.split(" ");for(var h=k.length;h--;){var f=k[h];if(f=="click"){this.$element.on("click."+this.type,this.options.selector,d.proxy(this.toggle,this))}else{if(f!="manual"){var m=f=="hover"?"mouseenter":"focusin";var e=f=="hover"?"mouseleave":"focusout";this.$element.on(m+"."+this.type,this.options.selector,d.proxy(this.enter,this));this.$element.on(e+"."+this.type,this.options.selector,d.proxy(this.leave,this))}}}this.options.selector?(this._options=d.extend({},this.options,{trigger:"manual",selector:""})):this.fixTitle()};c.prototype.getDefaults=function(){return c.DEFAULTS};c.prototype.getOptions=function(e){e=d.extend({},this.getDefaults(),this.$element.data(),e);if(e.delay&&typeof e.delay=="number"){e.delay={show:e.delay,hide:e.delay}}return e};c.prototype.getDelegateOptions=function(){var e={};var f=this.getDefaults();this._options&&d.each(this._options,function(g,h){if(f[g]!=h){e[g]=h}});return e};c.prototype.enter=function(f){var e=f instanceof this.constructor?f:d(f.currentTarget).data("bs."+this.type);if(!e){e=new this.constructor(f.currentTarget,this.getDelegateOptions());d(f.currentTarget).data("bs."+this.type,e)}if(f instanceof d.Event){e.inState[f.type=="focusin"?"focus":"hover"]=true}if(e.tip().hasClass("in")||e.hoverState=="in"){e.hoverState="in";return}clearTimeout(e.timeout);e.hoverState="in";if(!e.options.delay||!e.options.delay.show){return e.show()}e.timeout=setTimeout(function(){if(e.hoverState=="in"){e.show()}},e.options.delay.show)};c.prototype.isInStateTrue=function(){for(var e in this.inState){if(this.inState[e]){return true}}return false};c.prototype.leave=function(f){var e=f instanceof this.constructor?f:d(f.currentTarget).data("bs."+this.type);if(!e){e=new this.constructor(f.currentTarget,this.getDelegateOptions());d(f.currentTarget).data("bs."+this.type,e)}if(f instanceof d.Event){e.inState[f.type=="focusout"?"focus":"hover"]=false}if(e.isInStateTrue()){return}clearTimeout(e.timeout);e.hoverState="out";if(!e.options.delay||!e.options.delay.hide){return e.hide()}e.timeout=setTimeout(function(){if(e.hoverState=="out"){e.hide()}},e.options.delay.hide)};c.prototype.show=function(){var o=d.Event("show.bs."+this.type);if(this.hasContent()&&this.enabled){this.$element.trigger(o);var p=d.contains(this.$element[0].ownerDocument.documentElement,this.$element[0]);if(o.isDefaultPrevented()||!p){return}var n=this;var l=this.tip();var h=this.getUID(this.type);this.setContent();l.attr("id",h);this.$element.attr("aria-describedby",h);if(this.options.animation){l.addClass("fade")}var k=typeof this.options.placement=="function"?this.options.placement.call(this,l[0],this.$element[0]):this.options.placement;var s=/\s?auto?\s?/i;var t=s.test(k);if(t){k=k.replace(s,"")||"top"}l.detach().css({top:0,left:0,display:"block"}).addClass(k).data("bs."+this.type,this);this.options.container?l.appendTo(this.options.container):l.insertAfter(this.$element);this.$element.trigger("inserted.bs."+this.type);var q=this.getPosition();var f=l[0].offsetWidth;var m=l[0].offsetHeight;if(t){var j=k;var r=this.getPosition(this.$viewport);k=k=="bottom"&&q.bottom+m>r.bottom?"top":k=="top"&&q.top-m<r.top?"bottom":k=="right"&&q.right+f>r.width?"left":k=="left"&&q.left-f<r.left?"right":k;l.removeClass(j).addClass(k)}var i=this.getCalculatedOffset(k,q,f,m);this.applyPlacement(i,k);var g=function(){var e=n.hoverState;n.$element.trigger("shown.bs."+n.type);n.hoverState=null;if(e=="out"){n.leave(n)}};d.support.transition&&this.$tip.hasClass("fade")?l.one("bsTransitionEnd",g).emulateTransitionEnd(c.TRANSITION_DURATION):g()}};c.prototype.applyPlacement=function(j,k){var l=this.tip();var g=l[0].offsetWidth;var q=l[0].offsetHeight;var f=parseInt(l.css("margin-top"),10);var i=parseInt(l.css("margin-left"),10);if(isNaN(f)){f=0}if(isNaN(i)){i=0}j.top+=f;j.left+=i;d.offset.setOffset(l[0],d.extend({using:function(r){l.css({top:Math.round(r.top),left:Math.round(r.left)})}},j),0);l.addClass("in");var e=l[0].offsetWidth;var m=l[0].offsetHeight;if(k=="top"&&m!=q){j.top=j.top+q-m}var p=this.getViewportAdjustedDelta(k,j,e,m);if(p.left){j.left+=p.left}else{j.top+=p.top}var n=/top|bottom/.test(k);var h=n?p.left*2-g+e:p.top*2-q+m;var o=n?"offsetWidth":"offsetHeight";l.offset(j);this.replaceArrow(h,l[0][o],n)};c.prototype.replaceArrow=function(g,e,f){this.arrow().css(f?"left":"top",50*(1-g/e)+"%").css(f?"top":"left","")};c.prototype.setContent=function(){var f=this.tip();var e=this.getTitle();f.find(".tooltip-inner")[this.options.html?"html":"text"](e);f.removeClass("fade in top bottom left right")};c.prototype.hide=function(j){var g=this;var i=d(this.$tip);var h=d.Event("hide.bs."+this.type);function f(){if(g.hoverState!="in"){i.detach()}g.$element.removeAttr("aria-describedby").trigger("hidden.bs."+g.type);j&&j()}this.$element.trigger(h);if(h.isDefaultPrevented()){return}i.removeClass("in");d.support.transition&&i.hasClass("fade")?i.one("bsTransitionEnd",f).emulateTransitionEnd(c.TRANSITION_DURATION):f();this.hoverState=null;return this};c.prototype.fixTitle=function(){var e=this.$element;if(e.attr("title")||typeof e.attr("data-original-title")!="string"){e.attr("data-original-title",e.attr("title")||"").attr("title","")}};c.prototype.hasContent=function(){return this.getTitle()};c.prototype.getPosition=function(g){g=g||this.$element;var i=g[0];var f=i.tagName=="BODY";var h=i.getBoundingClientRect();if(h.width==null){h=d.extend({},h,{width:h.right-h.left,height:h.bottom-h.top})}var k=f?{top:0,left:0}:g.offset();var e={scroll:f?document.documentElement.scrollTop||document.body.scrollTop:g.scrollTop()};var j=f?{width:d(window).width(),height:d(window).height()}:null;return d.extend({},h,e,j,k)};c.prototype.getCalculatedOffset=function(e,h,f,g){return e=="bottom"?{top:h.top+h.height,left:h.left+h.width/2-f/2}:e=="top"?{top:h.top-g,left:h.left+h.width/2-f/2}:e=="left"?{top:h.top+h.height/2-g/2,left:h.left-f}:{top:h.top+h.height/2-g/2,left:h.left+h.width}};c.prototype.getViewportAdjustedDelta=function(h,k,e,j){var m={top:0,left:0};if(!this.$viewport){return m}var g=this.options.viewport&&this.options.viewport.padding||0;var l=this.getPosition(this.$viewport);if(/right|left/.test(h)){var n=k.top-g-l.scroll;var i=k.top+g-l.scroll+j;if(n<l.top){m.top=l.top-n}else{if(i>l.top+l.height){m.top=l.top+l.height-i}}}else{var o=k.left-g;var f=k.left+g+e;if(o<l.left){m.left=l.left-o}else{if(f>l.right){m.left=l.left+l.width-f}}}return m};c.prototype.getTitle=function(){var g;var e=this.$element;var f=this.options;g=e.attr("data-original-title")||(typeof f.title=="function"?f.title.call(e[0]):f.title);return g};c.prototype.getUID=function(e){do{e+=~~(Math.random()*1000000)}while(document.getElementById(e));return e};c.prototype.tip=function(){if(!this.$tip){this.$tip=d(this.options.template);if(this.$tip.length!=1){throw new Error(this.type+" `template` option must consist of exactly 1 top-level element!")}}return this.$tip};c.prototype.arrow=function(){return(this.$arrow=this.$arrow||this.tip().find(".tooltip-arrow"))};c.prototype.enable=function(){this.enabled=true};c.prototype.disable=function(){this.enabled=false};c.prototype.toggleEnabled=function(){this.enabled=!this.enabled};c.prototype.toggle=function(g){var f=this;if(g){f=d(g.currentTarget).data("bs."+this.type);if(!f){f=new this.constructor(g.currentTarget,this.getDelegateOptions());d(g.currentTarget).data("bs."+this.type,f)}}if(g){f.inState.click=!f.inState.click;if(f.isInStateTrue()){f.enter(f)}else{f.leave(f)}}else{f.tip().hasClass("in")?f.leave(f):f.enter(f)}};c.prototype.destroy=function(){var e=this;clearTimeout(this.timeout);this.hide(function(){e.$element.off("."+e.type).removeData("bs."+e.type);if(e.$tip){e.$tip.detach()}e.$tip=null;e.$arrow=null;e.$viewport=null})};function b(e){return this.each(function(){var h=d(this);var g=h.data("bs.tooltip");var f=typeof e=="object"&&e;if(!g&&/destroy|hide/.test(e)){return}if(!g){h.data("bs.tooltip",(g=new c(this,f)))}if(typeof e=="string"){g[e]()}})}var a=d.fn.tooltip;d.fn.tooltip=b;d.fn.tooltip.Constructor=c;d.fn.tooltip.noConflict=function(){d.fn.tooltip=a;return this}}(jQuery);;/*!
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
            f();
        }
    };
BsF.ajax.cb=function(o,e,r,f) { //commandButton ajax helper (object, event, [render], [oncomplete])
    var argn=arguments.length;
    var oid=o.id;
    var cid=oid.replace(/[^a-zA-Z0-9]+/g,'_');
    var opts={};
    opts.execute='@all';
    opts[oid]=oid;
    if(argn==4) {
    BsF.callback[cid]=f;
    
    opts.render=r;
    opts.onevent=BsF.ajax.onevent;
    }
    if(argn==3) {
        if(BsF.isFunction(r)) {
            BsF.callback[cid]=r;
            opts.onevent=BsF.ajax.onevent;
        }
        else { opts.render=r; } //jsf.ajax.request(o,e, { execute: '@form', render: r }); }
    }
    
    jsf.ajax.request(o,e, opts);
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
