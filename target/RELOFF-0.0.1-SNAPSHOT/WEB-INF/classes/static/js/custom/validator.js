$(document).ready(function () {
    
});

$(".numbers").on('keypress', function(e) {
	  var theEvent = e || window.event;
	  var key = theEvent.keyCode || theEvent.which;
	  key = String.fromCharCode( key );
	  var regex = /^[0-9]|\+$/;
	  if( !regex.test(key) ) {
	    theEvent.returnValue = false;
	    if(theEvent.preventDefault) theEvent.preventDefault();
	  }
});

$(".letters").on('keypress', function(e) {
	  var theEvent = e || window.event;
	  var key = theEvent.keyCode || theEvent.which;
	  key = String.fromCharCode( key );
	  var regex = /^[a-zA-Z\s]+$/;
	  if( !regex.test(key) ) {
	    theEvent.returnValue = false;
	    if(theEvent.preventDefault) theEvent.preventDefault();
	  }
});

$("#fade").fadeOut(5000);
$("#fadeError").fadeOut(5000);







