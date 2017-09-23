/*
 
 Copyright 2014-2017 Riccardo Massera (TheCoder4.Eu)
 BootsFaces JS
 author: TheCoder4.eu

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
*/
BsF={ajax:{},onCompleteCallback:{},onErrorCallback:{},onSuccessCallback:{}};
BsF.ajax.onevent=function(a){if("complete"===a.status){var b=a.source.id.replace(/[^a-zA-Z0-9]+/g,"_");(b=null!=a.responseText&&0<=a.responseText.indexOf("<error><error-name>")?BsF.onErrorCallback[b]:BsF.onSuccessCallback[b])&&null!=b&&"undefined"!=typeof b&&b();$.blockUI&&null!=$.blockUI&&$.unblockUI()}"success"==a.status&&(b=a.source.id.replace(/[^a-zA-Z0-9]+/g,"_"),(b=BsF.onCompleteCallback[b])&&null!=b&&"undefined"!=typeof b&&b());"error"==a.status&&(b=a.source.id.replace(/[^a-zA-Z0-9]+/g,"_"),
(b=BsF.onErrorCallback[b])&&null!=b&&"undefined"!=typeof b&&b())};BsF.ajax.cb=function(a,b,c,d){BsF.ajax.callAjax(a,b,c,"@all",d,null)};
BsF.ajax.callAjax=function(a,b,c,d,e,f,h,n,k){if(null==a.id||""==a.id)a=a.parentNode;var l=a.id,m=l.replace(/[^a-zA-Z0-9]+/g,"_"),g={};n&&(g.params="BsFEvent="+n);(c=BsF.ajax.resolveJQuery(c))&&null!=c&&(g.render=c);(d=BsF.ajax.resolveJQuery(d))&&null!=d&&(g.execute=d);g[l]=l;BsF.onCompleteCallback[m]=e&&null!=e?e:null;BsF.onErrorCallback[m]=f&&null!=f?f:null;BsF.onSuccessCallback[m]=h&&null!=h?h:null;g.onevent=BsF.ajax.onevent;if(k)for(var p in k)c=k[p],"object"===typeof c&&("Array"!=c.constructor.name||
1<c.length)&&(c=JSON.stringify(c).replace(/\"/g,"'")),g[p]=c;jsf.ajax.request(a,b,g);a="true"===g["blockui.disabled"];$.blockUI&&null!=$.blockUI&&!a&&$.blockUI();return!1};BsF.ajax.resolveJQuery=function(a){if("undefined"==typeof a||null==a)return"";var b="";a=a.split(" ");for(i=0;i<a.length;i++)if(part=a[i],0==part.indexOf("@(")&&part.lastIndexOf(")")==part.length-1){var c=part.substring(2,part.length-1);(c=$(c))&&c.each(function(a,c){b+=" "+c.id})}else b+=part+" ";return b.trim()};
BsF.ajax.paginate=function(a,b,c,d,e){a={execute:"@this"};a.render=e;a[d]=c;jsf.ajax.request(d,b,a);return!1};if($.datepicker){var generateHTML_orig=$.datepicker._generateHTML;$.datepicker._generateHTML=function(){var a=generateHTML_orig.apply(this,arguments),a=a.replace(/<span\s+class='ui-icon\s+ui-icon-circle-triangle-w'\s*>[^<]+<\/span>/,'<span class="glyphicon glyphicon-chevron-left"></span>');return a=a.replace(/<span\s+class='ui-icon\s+ui-icon-circle-triangle-e'\s*>[^<]+<\/span>/,'<span class="glyphicon glyphicon-chevron-right"></span>')}}
function jq(a){return"#"+a.replace(/(:|\.|\[|\]|,)/g,"\\$1")}BsF.substringMatcher=function(a){return function(b,c){var d;d=[];substrRegex=new RegExp(b,"i");$.each(a,function(a,b){substrRegex.test(b)&&d.push(b)});c(d)}};BsF.addParametersToForm=function(a,b){var c=[];a.parametersAddedTemporarily=c;var d=0,e;for(e in b)if(b.hasOwnProperty(e)){var f=document.createElement("input");f.type="hidden";f.name=e;f.value=b[e];a.appendChild(f);c[d++]=f}};
BsF.submitForm=function(a,b){a=document.getElementById(a);BsF.addParametersToForm(a,b);a.onsubmit?(b=a.onsubmit(),("undefined"==typeof b||b)&&a.submit()):a.submit();BsF.removeTemporalParametersFromForm(a)};BsF.removeTemporalParametersFromForm=function(a){var b=a.parametersAddedTemporarily;if(null!==b)for(var c=0;c<b.length;c++)a.removeChild(b[c])};