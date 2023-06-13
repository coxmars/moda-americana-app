// Getting the current year and logging it to the console. 
var currentTime = new Date();

// Get types of the date
let year = currentTime.getFullYear();
let month = currentTime.getMonth() + 1;
let day = currentTime.getDate();
let mesActual = new Intl.DateTimeFormat('es-ES', { month: 'long' }).format(new Date());

// Get elemtns by ID
let rights = document.getElementById('rights');
let date = document.getElementById('date')
let dateD = document.getElementById('dateD')
let monthD = document.getElementById('month')
let info_month = document.getElementsByClassName('info_month')
let info_year = document.getElementsByClassName('info_year')

// Get the hour in real time
function actualizarHora() {

	// Get types of the date	
	let currentTime = new Date();
	let hours = currentTime.getHours();
	let minutes = currentTime.getMinutes();
	let seconds = currentTime.getSeconds();

	// Agrega un cero delante si el número es menor a 10
	hours = hours < 10 ? "0" + hours : hours;
	minutes = minutes < 10 ? "0" + minutes : minutes;
	seconds = seconds < 10 ? "0" + seconds : seconds;

	// Verify if this id is null
	if (rights != null) {

		// Get markup by ID
		let time = document.getElementById("time")

		// Actualiza el contenido del elemento HTML con la hora actual
		if (time != null) {
			time.innerHTML = hours + ":" + minutes + ":" + seconds;
		}
	}

}

// Actualiza la hora cada segundo
setInterval(actualizarHora, 1000);

// Verify if this id is null
if (rights != null) {

	// Inner the html in the markups
	rights.innerHTML = `${year}`;

}

// Verify if this id is null
if (date != null) {

	// Inner the html in the markups
	date.innerHTML = `${day}/${month}/${year}`;
}

if (dateD != null) {
	dateD.innerHTML = `${day}/${month}/${year}`;
}

// Verify if this id is null
if (monthD != null) {
	monthD.innerHTML = `${mesActual}`;
}

// Verify if this id is null
if (info_month.length > 0 && info_year.length > 0) {
	for (let i = 0; i < info_month.length; i++) {
		info_month[i].innerHTML = month;
	}
	for (let i = 0; i < info_year.length; i++) {
		info_year[i].innerHTML = year;
	}
}

