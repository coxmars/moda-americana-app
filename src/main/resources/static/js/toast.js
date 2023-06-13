// Wait for load all document
$(document).ready(function() {

	// Get element by ID
	let m = document.getElementById('m')

	// Check if the element is null
	if (m != null) {
		
		t = m.textContent

		// Success = Item was saved
		if (t == 'success') {
			$('#toastCreate').toast('show')

			// Error = Item not saved
		} else if (t == 'error') {
			$('#toastError').toast('show')
		}

		// Delete = Item was delited
		else if (t == 'delete') {
			$('#toastDelete').toast('show')
		}
		
		// Send = Item was been send
		else if (t == 'send') {
			$('#toastSend').toast('show')
		}
	}

});