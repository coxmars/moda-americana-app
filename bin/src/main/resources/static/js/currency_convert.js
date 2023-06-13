// Set the headers of the petition
var myHeaders = new Headers();
myHeaders.append("apikey", "eB6owUnnyYj512B0JqmWr5x1IBoSlIbH");

// Get the markups
let usd = document.getElementById('dolar')
let eur = document.getElementById('euro')

// Set options
var requestOptions = {
	method: 'GET',
	redirect: 'follow',
	headers: myHeaders
};

// Verify if this id is null
if (usd != null && eur != null) {

	// Set function of convert CRC to USD
	const currencyUSD = () => {
		fetch("https://api.apilayer.com/currency_data/convert?to=CRC&from=USD&amount=1", requestOptions)
			.then(response => response.json())
			.then(data => showData(data))
			.catch(error => console.log('error', error));

		// Show the data
		const showData = (data) => {
			let change = (data.result)

			usd.innerHTML = `${change}`
		}
	}

	// Set function of convert CRC to USD
	const currencyEUR = () => {
		fetch("https://api.apilayer.com/currency_data/convert?to=CRC&from=EUR&amount=1", requestOptions)
			.then(response => response.json())
			.then(data => showData(data))
			.catch(error => console.log('error', error));

		// Show the data
		const showData = (data) => {
			let change = (data.result)

			eur.innerHTML = `${change}`
		}
	}

	// Call the functions of the change currency
	document.addEventListener("DOMContentLoaded", () => {
		currencyUSD();
		currencyEUR();
	});

}