/*
 
 Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu)
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
var process=process||{env:{NODE_ENV:"development"}};BsF={};BsF.ajax={};BsF.onCompleteCallback={};BsF.onErrorCallback={};BsF.onSuccessCallback={};BsF.blockBlockUI=false;
BsF.ajax.onevent=function(data){if(data.status==="begin"){if($.blockUI&&$.blockUI!=null&&!BsF.blockBlockUI){var message=$.blockUI.defaults.message;$.blockUI()}}else if(data.status==="complete"){var cid=data.source.id.replace(/[^a-zA-Z0-9]+/g,"_");if(data.responseText!=null&&data.responseText.indexOf("<error><error-name>")>=0){var f=BsF.onErrorCallback[cid];if(f&&f!=null&&typeof f!="undefined")f()}else{var f=BsF.onSuccessCallback[cid];if(f&&f!=null&&typeof f!="undefined")f()}if($.blockUI&&$.blockUI!=
null)$.unblockUI()}if(data.status=="success"){var cid=data.source.id.replace(/[^a-zA-Z0-9]+/g,"_");var f=BsF.onCompleteCallback[cid];if(f&&f!=null&&typeof f!="undefined")f()}if(data.status=="error"){var cid=data.source.id.replace(/[^a-zA-Z0-9]+/g,"_");var f=BsF.onErrorCallback[cid];if(f&&f!=null&&typeof f!="undefined")f()}};BsF.ajax.cb=function(o,e,r,f){BsF.ajax.callAjax(o,e,r,"@all",f,null)};
BsF.ajax.callAjax=function(source,event,update,execute,oncomplete,onerror,onsuccess,eventType,delay,parameters){var argn=arguments.length;if(source.id==null||source.id=="")source=source.parentNode;var oid=source.id;var cid=oid.replace(/[^a-zA-Z0-9]+/g,"_");var opts={};if(eventType)opts.params={"BsFEvent":eventType};update=BsF.ajax.resolveJQuery(update);if(update&&update!=null)opts.render=update;execute=BsF.ajax.resolveJQuery(execute);if(execute&&execute!=null)opts.execute=execute;opts[oid]=oid;if(oncomplete&&
oncomplete!=null)BsF.onCompleteCallback[cid]=oncomplete;else BsF.onCompleteCallback[cid]=null;if(onerror&&onerror!=null)BsF.onErrorCallback[cid]=onerror;else BsF.onErrorCallback[cid]=null;if(onsuccess&&onsuccess!=null)BsF.onSuccessCallback[cid]=onsuccess;else BsF.onSuccessCallback[cid]=null;opts.onevent=BsF.ajax.onevent;if(!!delay)opts.delay=delay;if(parameters)for(var propertyName in parameters){var p=parameters[propertyName];if(typeof p==="object")if(p.constructor.name!="Array"||p.length>1)p=JSON.stringify(p).replace(/"/g,
"'");opts[propertyName]=p}BsF.blockBlockUI=opts["blockui.disabled"]==="true";jsf.ajax.request(source,event,opts);return false};
BsF.ajax.resolveJQuery=function(update){if(typeof update=="undefined")return"";if(update==null)return"";var newUpdate="";var parts=update.split(" ");for(i=0;i<parts.length;i++){part=parts[i];if(part.indexOf("@(")==0&&part.lastIndexOf(")")==part.length-1){var jqueryexp=part.substring(2,part.length-1);var jQueryObjects=$(jqueryexp);if(jQueryObjects)jQueryObjects.each(function(index,element){newUpdate+=element.id+" "})}else newUpdate+=part+" "}return newUpdate.trim()};
BsF.ajax.paginate=function(o,e,v,c,r){var opts={};opts.execute="@this";opts.render=r;opts[c]=v;jsf.ajax.request(c,e,opts);return false};
if($.datepicker){var generateHTML_orig=$.datepicker._generateHTML;$.datepicker._generateHTML=function(){var ret=generateHTML_orig.apply(this,arguments);ret=ret.replace(/<span\s+class='ui-icon\s+ui-icon-circle-triangle-w'\s*>[^<]+<\/span>/,'<span class="glyphicon glyphicon-chevron-left"></span>');ret=ret.replace(/<span\s+class='ui-icon\s+ui-icon-circle-triangle-e'\s*>[^<]+<\/span>/,'<span class="glyphicon glyphicon-chevron-right"></span>');return ret}}
function jq(myid){return"#"+myid.replace(/(:|\.|\[|\]|,)/g,"\\$1")}BsF.substringMatcher=function(strs){return function findMatches(q,cb){var matches,substringRegex;matches=[];substrRegex=new RegExp(q,"i");$.each(strs,function(i,str){if(substrRegex.test(str))matches.push(str)});cb(matches)}};
BsF.addParametersToForm=function apf(currentForm,pvp){var parametersAddedTemporarily=new Array;currentForm.parametersAddedTemporarily=parametersAddedTemporarily;var i=0;for(var k in pvp)if(pvp.hasOwnProperty(k)){var p=document.createElement("input");p.type="hidden";p.name=k;p.value=pvp[k];currentForm.appendChild(p);parametersAddedTemporarily[i++]=p}};
BsF.submitForm=function jsfcljs(currentFormId,parameters){var currentForm=document.getElementById(currentFormId);BsF.addParametersToForm(currentForm,parameters);if(currentForm.onsubmit){var result=currentForm.onsubmit();if(typeof result=="undefined"||result)currentForm.submit()}else currentForm.submit();BsF.removeTemporalParametersFromForm(currentForm)};
BsF.removeTemporalParametersFromForm=function removeTemporalParametersFromForm(currentForm){var parametersAddedTemporarily=currentForm.parametersAddedTemporarily;if(parametersAddedTemporarily!==null)for(var i=0;i<parametersAddedTemporarily.length;i++)currentForm.removeChild(parametersAddedTemporarily[i])};