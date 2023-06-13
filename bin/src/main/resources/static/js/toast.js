// Wait for load all document
$(document).ready(function() {

	// Get element by ID
	let m = document.getElementById('m').textContent

	// Check if the element is null
	if (m != null) {

		// Success = Item was saved
		if (m == 'success') {
			$('#toastCreate').toast('show')

			// Error = Item not saved
		} else if (m == 'error') {
			$('#toastError').toast('show')
		}

		// Delete = Item was delited
		else if (m == 'delete') {
			$('#toastDelete').toast('show')
		}
		
		// Send = Item was been send
		else if (m == 'send') {
			$('#toastSend').toast('show')
		}
	}

});