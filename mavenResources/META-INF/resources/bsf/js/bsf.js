/*
 
 Copyright 2014 Riccardo Massera (TheCoder4.Eu)
 BootsFaces JS
 author: TheCoder4.eu
*/
BsF={ajax:{},callback:{},isFunction:function(a){var c={};return a&&"[object Function]"===c.toString.call(a)}};BsF.ajax.onevent=function(a){"success"==a.status&&(a=a.source.id.replace(/[^a-zA-Z0-9]+/g,"_"),a=BsF.callback[a],"undefined"!=typeof a&&a())};BsF.ajax.cb=function(a,c,b,e){BsF.ajax.callAjax(a,c,b,"@all",e)};
BsF.ajax.callAjax=function(a,c,b,e,f){var g=arguments.length,h=a.id,k=h.replace(/[^a-zA-Z0-9]+/g,"_"),d={};d.execute=e;d[h]=h;5==g&&(BsF.callback[k]=f,d.render=b,d.onevent=BsF.ajax.onevent);if(3==g||4==g)BsF.isFunction(b)?(BsF.callback[k]=b,d.onevent=BsF.ajax.onevent):d.render=b;jsf.ajax.request(a,c,d);return!1};BsF.ajax.paginate=function(a,c,b,e,f){a={execute:"@this"};a.render=f;a[e]=b;jsf.ajax.request(e,c,a);return!1};
if($.datepicker){var generateHTML_orig=$.datepicker._generateHTML;$.datepicker._generateHTML=function(){var a=generateHTML_orig.apply(this,arguments),a=a.replace(/<span\s+class='ui-icon\s+ui-icon-circle-triangle-w'\s*>[^<]+<\/span>/,'<span class="glyphicon glyphicon-chevron-left"></span>');return a=a.replace(/<span\s+class='ui-icon\s+ui-icon-circle-triangle-e'\s*>[^<]+<\/span>/,'<span class="glyphicon glyphicon-chevron-right"></span>')}};