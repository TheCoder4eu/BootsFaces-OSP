Image.prototype.load = function( url, onLoadStartCallback, onProgressCallback, onLoadEndCallback ) {
    var thisImg = this,
        xmlHTTP = new XMLHttpRequest();

    thisImg.completedPercentage = 0;

    xmlHTTP.open( 'GET', url , true );
    xmlHTTP.responseType = 'arraybuffer';

    xmlHTTP.onload = function( e ) {
        var h = xmlHTTP.getAllResponseHeaders(),
            m = h.match( /^Content-Type\:\s*(.*?)$/mi ),
            mimeType = m[ 1 ] || 'image/png';

        var blob = new Blob( [ this.response ], { type: mimeType } );
        thisImg.src = window.URL.createObjectURL( blob );
    };

    xmlHTTP.onprogress = function( e ) {
        if ( e.lengthComputable )
            thisImg.completedPercentage = parseInt( ( e.loaded / e.total ) * 100 );
        if ( onProgressCallback ) onProgressCallback( thisImg );
    };

    xmlHTTP.onloadstart = function() {
        thisImg.completedPercentage = 0;
        if ( onLoadStartCallback ) onLoadStartCallback( thisImg );
    };

    xmlHTTP.onloadend = function() {
        thisImg.completedPercentage = 100;
        if ( onLoadEndCallback ) onLoadEndCallback( thisImg );
    }

    xmlHTTP.send();
};