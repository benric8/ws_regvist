$(document).ready(function(){
		
	$('[data-toggle="popover"]').popover();
	
	$('.anioCopyright').html(new Date().getFullYear());
	
	$('#menu__icon').click(function(e){
		e.preventDefault();
		$('body').toggleClass('with--sidebar');
	});
    
    $('#site-cache').click(function(e){
    	$('body').removeClass('with--sidebar');
    });
    
    $('form').attr('autocomplete', 'off');
   
    //Disable cut copy paste
    /* $('body').on('cut copy paste', function (e) {
        e.preventDefault();
        return false; 
    });*/
   
    //Disable mouse right click
    /* $('body').on('contextmenu', function(e){
    	e.preventDefault();
        return false;
    });*/
    
});

function start() {
    PF('dlgVarBlock').show();
}
 
function stop() {
    PF('dlgVarBlock').hide();
}

