/*

 Copyright 2014 Riccardo Massera (TheCoder4.Eu)
 BootsFaces JS
 author: TheonSuccessCallbackCoder4.eu
*/
BsF={ajax:{},onCompleteCallback:{},onErrorCallback:{},onSuccessCallback:{}};
BsF.ajax.onevent=function(a){if("complete"===a.status){var b=a.source.id.replace(/[^a-zA-Z0-9]+/g,"_");(b=null!=a.responseText&&0<=a.responseText.indexOf("<error><error-name>")?BsF.onErrorCallback[b]:BsF.onSuccessCallback[b])&&null!=b&&"undefined"!=typeof b&&b();$.blockUI&&null!=$.blockUI&&$.unblockUI()}"success"==a.status&&(b=a.source.id.replace(/[^a-zA-Z0-9]+/g,"_"),(b=BsF.onCompleteCallback[b])&&null!=b&&"undefined"!=typeof b&&b());"error"==a.status&&(b=a.source.id.replace(/[^a-zA-Z0-9]+/g,"_"),
(b=BsF.onErrorCallback[b])&&null!=b&&"undefined"!=typeof b&&b())};BsF.ajax.cb=function(a,b,c,d){BsF.ajax.callAjax(a,b,c,"@all",d,null)};
BsF.ajax.callAjax=function(a,b,c,d,f,g,h,m){var k=a.id,l=k.replace(/[^a-zA-Z0-9]+/g,"_"),e={};m&&(e.params="BsFEvent="+m);(c=BsF.ajax.resolveJQuery(c))&&null!=c&&(e.render=c);(d=BsF.ajax.resolveJQuery(d))&&null!=d&&(e.execute=d);e[k]=k;BsF.onCompleteCallback[l]=f&&null!=f?f:null;BsF.onErrorCallback[l]=g&&null!=g?g:null;BsF.onSuccessCallback[l]=h&&null!=h?h:null;e.onevent=BsF.ajax.onevent;jsf.ajax.request(a,b,e);$.blockUI&&null!=$.blockUI&&$.blockUI();return!1};
BsF.ajax.resolveJQuery=function(a){if("undefined"==typeof a||null==a)return"";var b="";a=a.split(" ");for(i=0;i<a.length;i++)if(part=a[i],0==part.indexOf("@(")&&part.lastIndexOf(")")==part.length-1){var c=part.substring(2,part.length-1);(c=$(c))&&c.each(function(a,c){b+=" "+c.id})}else b+=part+" ";return b.trim()};BsF.ajax.paginate=function(a,b,c,d,f){a={execute:"@this"};a.render=f;a[d]=c;jsf.ajax.request(d,b,a);return!1};
if($.datepicker){var generateHTML_orig=$.datepicker._generateHTML;$.datepicker._generateHTML=function(){var a=generateHTML_orig.apply(this,arguments),a=a.replace(/<span\s+class='ui-icon\s+ui-icon-circle-triangle-w'\s*>[^<]+<\/span>/,'<span class="glyphicon glyphicon-chevron-left"></span>');return a=a.replace(/<span\s+class='ui-icon\s+ui-icon-circle-triangle-e'\s*>[^<]+<\/span>/,'<span class="glyphicon glyphicon-chevron-right"></span>')}}
function jq(a){return"#"+a.replace(/(:|\.|\[|\]|,)/g,"\\$1")}function treeDataMapper(a){return a&&"undefined"!==a?a.nodeInternalId+"|#*#|"+a.text+"|#*#|"+a.state.checked+"|#*#|"+a.state.disabled+"|#*#|"+a.state.expanded+"|#*#|"+a.state.selected:""};

BsF.substringMatcher = function(strs) {
	  return function findMatches(q, cb) {
	    var matches, substringRegex;

	    // an array that will be populated with substring matches
	    matches = [];

	    // regex used to determine if a string contains the substring `q`
	    substrRegex = new RegExp(q, 'i');

	    // iterate through the pool of strings and for any string that
	    // contains the substring `q`, add it to the `matches` array
	    $.each(strs, function(i, str) {
	      if (substrRegex.test(str)) {
	        matches.push(str);
	      }
	    });

	    cb(matches);
	  };
	};
