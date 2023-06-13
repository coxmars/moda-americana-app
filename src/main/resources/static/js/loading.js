//  ? WAIT UNTIL DOCUMENT IS READY
window.onload = function () {
    $('#onload').fadeOut();
    $('body').removeClass('hidden');
    let nav = document.getElementById('nav')
    // Verify if this id is null
	if (nav != null) {
		nav.classList.remove('hiddenNav')
    	nav.classList.add('Nav')
	} 
}