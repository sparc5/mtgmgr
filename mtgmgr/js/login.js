$(document).ready(function() {
	$.pseudoFocus();
	$.focusFirstFormField();
	// horizontally center login div
	$("body").layout({
		spacing_open:	0
	,	spacing_closed:	0
	,	minSize:		50
	,	resizable:		false
	,	closable:		false
	,	west__size:		"33%"
	,	west__minSize:	"33%"
	,	west__maxSize:	"33%"
	,	east__size:		"33%"
	,	east__minSize:	"33%"
	,	east__maxSize:	"33%"
	});
	// vertically center login div
	$("div#login").css({
		"marginTop":0.5*($(window).height()-$("div#login").height())+"px"
	});
});