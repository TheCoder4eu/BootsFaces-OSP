/* German initialisation for the jQuery UI date picker plugin. */
/* Written by Milian Wolff (mail@milianw.de). */
(function( factory ) {
	if ( typeof define === "function" && define.amd ) {

		// AMD. Register as an anonymous module.
		define([ "../datepicker" ], factory );
	} else {

		// Browser globals
		factory( jQuery.datepicker );
	}
}(function( datepicker ) {

datepicker.regional['hu'] = {
	closeText: 'Bezárás',
	prevText: '&#x3C;Vissza',
	nextText: 'Előre&#x3E;',
	currentText: 'Ma',
	monthNames: ['Január','Február','Március','Április','Május','Június',
	'Július','Augusztus','Szeptember','Október','November','December'],
	monthNamesShort: ['Jan','Feb','Már','Ápr','Máj','Jún',
	'Júl','Aug','Szept','Okt','Nov','Dec'],
	dayNames: ['Vasárnap','Hétfő','Kedd','Szerda','Csütörtök','Pétek','Szombat'],
	dayNamesShort: ['Va','Hé','Ke','Sze','Cs','Pé','Szo'],
	dayNamesMin: ['Va','Hé','Ke','Sze','Cs','Pé','Szo'],
	weekHeader: 'Hét',
	dateFormat: 'yyyy.mm.dd',
	firstDay: 1,
	isRTL: false,
	showMonthAfterYear: false,
	yearSuffix: ''};
datepicker.setDefaults(datepicker.regional['hu']);

return datepicker.regional['hu'];

}));
