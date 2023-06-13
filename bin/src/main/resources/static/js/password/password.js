// Validate Password
function validarPassword(password) {

	// Get elements by ID
	let length = document.getElementById('length')
	let uppercase = document.getElementById('uppercase')
	let lowercase = document.getElementById('lowercase')
	let number = document.getElementById('number')
	let especial = document.getElementById('especial')
	let incorrect = document.getElementById('coincide')

	// Get password
	let confirm = document.getElementById('word')
	if (confirm != null) {
		let passwordConfirm = confirm.value;	
	}

	let isValid = true;

	// Greater than 8 and less than 64
	if (password.length < 8 || password.length > 64) {
		// Show and hide the error
		length.classList.remove('d-none')
		setTimeout(function() {
			length.classList.add('d-none')
		}, 5000);
		isValid = false;
	}

	// Set Validates
	let hasUppercase = false;
	let hasLowercase = false;
	let hasNumber = false;
	let hasSpecialChar = false;
	let coincide = false;

	// Validate the conditions
	for (let i = 0; i < password.length; i++) {
		let charCode = password.charCodeAt(i);

		// Uppercase
		if (charCode >= 65 && charCode <= 90) {
			hasUppercase = true;

			// Lowercase
		} else if (charCode >= 97 && charCode <= 122) {
			hasLowercase = true;

			// Number
		} else if (charCode >= 48 && charCode <= 57) {
			hasNumber = true;

			// Especial
		} else {
			hasSpecialChar = true;
		}

		// Confirm the password
		if (incorrect != null && confirm != null) {

			if (password === passwordConfirm) {
				coincide = true
			}

		}
	}

	// Show the uppercase error 
	if (!hasUppercase) {
		// Show and hide the error
		uppercase.classList.remove('d-none')
		setTimeout(function() {
			uppercase.classList.add('d-none')
		}, 5000);
		isValid = false;
	}

	// Show the lowercase error 
	if (!hasLowercase) {
		// Show and hide the error
		lowercase.classList.remove('d-none')
		setTimeout(function() {
			lowercase.classList.add('d-none')
		}, 5000);
		isValid = false;
	}

	// Show number error
	if (!hasNumber) {
		// Show and hide the error
		number.classList.remove('d-none')
		setTimeout(function() {
			number.classList.add('d-none')
		}, 5000);
		isValid = false;
	}

	// Show especial error
	if (!hasSpecialChar) {
		// Show and hide the error
		especial.classList.remove('d-none')
		setTimeout(function() {
			especial.classList.add('d-none')
		}, 5000);
		isValid = false;
	}

	// Show and hide error
	if (incorrect != null && confirm != null) {
		if (!coincide) {
			// Show and hide the error
			incorrect.classList.remove('d-none')
			setTimeout(function() {
				incorrect.classList.add('d-none')
			}, 5000);
			isValid = false;
		}
	}

	// Return the state
	return isValid;
}

// Get the form
const formulario = document.querySelector('#form');

// Prevent the submit of the form
if (formulario != null) {
	formulario.addEventListener('submit', function(event) {

		// Get password
		let input = document.getElementById('pwd')

		let password = input.value;
		
		console.log(password)

		// Validate the return of the function
		if (!validarPassword(password)) {
			event.preventDefault();
		}
	});
}

// If the user wants see the password 
const boton = document.querySelector('#x');
const password = document.getElementById('pwd');

// Listener to click btn
if (boton != null) {
	boton.addEventListener('click', function() {

		// Get elements by id
		let no = document.getElementById('no')
		let yes = document.getElementById('yes')

		// Verify if the user wants see the password
		if (password.type === 'password') {
			password.type = 'text';
			no.classList.remove('d-none')
			yes.style.display = 'none'

			// Check if the user does not want to see the password
		} else {
			password.type = 'password';
			no.classList.add('d-none')
			yes.style.display = 'block'
		}
	});
}

// If the user wants see the password 
const word = document.getElementById('word');

// Listener to click btn
if (boton != null) {
	boton.addEventListener('click', function() {

		// Verify if the user wants see the password
		if (word.type === 'password') {
			word.type = 'text';

			// Check if the user does not want to see the password
		} else {
			word.type = 'password';
		}
	});
}

// Show Error Message in Login
const validate = () => {

	// Get the state of validation
	let url = window.location

	// Check if the validate is incorrect
	if (url.search == '?error') {
		incorrect.classList.remove('d-none')
		setTimeout(function() {
			incorrect.classList.add('d-none')
		}, 10000);
	}

}

// Call the function
validate()





