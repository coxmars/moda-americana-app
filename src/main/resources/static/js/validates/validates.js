// Get form
const form = document.getElementById('form');

// Add listener for submit
if (form != null) {
	form.addEventListener('submit', (event) => {

		// Get elements by ID
		const nombre = document.getElementById('volunteer-name').value.trim();
		const email = document.getElementById('volunteer-email').value.trim();
		const fullname = document.getElementById('volunteer-fullname').value.trim();
		const phone = document.getElementById('volunteer-phone').value.trim();
		const message = document.getElementById('volunteer-message').value.trim();

		// Verify if the fields not empty
		if (nombre === '' || email === '' || fullname === '' || phone === '' || message === '') {
			// Show and hide the error
			required.classList.remove('d-none')
			setTimeout(function() {
				required.classList.add('d-none')
			}, 5000);
			event.preventDefault();
		}


	});
} 