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
            // Remove your progress bar or whatever here. Load is done.

        var blob = new Blob( [ this.response ], { type: mimeType } );
        thisImg.src = window.URL.createObjectURL( blob );
    };

    xmlHTTP.onprogress = function( e ) {
        if ( e.lengthComputable )
            thisImg.completedPercentage = parseInt( ( e.loaded / e.total ) * 100 );
        // Update your progress bar here. Make sure to check if the progress value
        // has changed to avoid spamming the DOM.
        // Something like: 
        // if ( prevValue != thisImage completedPercentage ) display_progress();
        if ( onProgressCallback ) onProgressCallback( thisImg );
    };

    xmlHTTP.onloadstart = function() {
        // Display your progress bar here, starting at 0
        thisImg.completedPercentage = 0;
        if ( onLoadStartCallback ) onLoadStartCallback( thisImg );
    };

    xmlHTTP.onloadend = function() {
        // You can also remove your progress bar here, if you like.
        thisImg.completedPercentage = 100;
        if ( onLoadEndCallback ) onLoadEndCallback( thisImg );
    }

    xmlHTTP.send();
};