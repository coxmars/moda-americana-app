// Wait for load all document
$(document).ready(function() {

	// Get elements by ID
	let total = document.getElementById('total').textContent
	let totalModal = document.getElementById('totalModal')
	let finish = document.getElementById('finish')
	let vueltoF = document.getElementById('vuelto')
	let kind = document.getElementById('kind')
	let clienteSelect = document.getElementById('clientS')
	let dolar = document.getElementById('dolar').textContent
	let euro = document.getElementById('euro').textContent

	// Disable the button
	finish.disabled = true;

	kind.addEventListener("change", function() {

		let vuelto = document.getElementById('vuel')
		const pago = document.getElementById('pago');
		pago.classList.add('d-none');

		if (kind.value === "") {
			finish.disabled = true;
		} else {
			switch (kind.value) {
				case "Efectivo":
					pago.classList.remove('d-none')
					pago.classList.add('d-block')

					vuelto.classList.remove('d-none')
					vuelto.classList.add('d-block')
					break;
				case "Tarjeta":
				case "Sinpe":
					pago.classList.add('d-none')
					break;
			}

			finish.disabled = false;
		}
	});

	// Wait for a click in finish button
	finish.addEventListener('click', () => {

		// Currency


		// Add total
		let client = document.getElementById('client').value
		totalModal.innerHTML = t

		// Add vuelto
		let vuelto = client - t
		vueltoF.innerHTML = vuelto

		// Add client
		let cliente = document.getElementById('cli')
		cliente.innerHTML = clienteSelect.value


		// Add type
		let type = document.getElementById('typeP')
		type.innerHTML = kind.value

	});

});

var t = 0;

const addDataTable = () => {
	// Obtener referencia a los elementos de entrada
	const input1 = document.getElementById("montoAdd");
	const input2 = document.getElementById("cantidadAdd");
	const input3 = document.getElementById("typeAdd");

	// Obtener referencia al formulario y tabla
	const form = document.getElementById("formAdd");
	const table = document.getElementById("tableAdd");

	// Crear una nueva fila de tabla con los valores capturados en los inputs
	const newRow = document.createElement("tr");
	const cell1 = document.createElement("td");
	const cell2 = document.createElement("td");
	const cell3 = document.createElement("td");
	const cell4 = document.createElement("td");
	const cell5 = document.createElement("td");

	cell1.textContent = input3.value;
	cell2.textContent = input1.value;
	cell3.textContent = input2.value;
	cell4.textContent = input2.value * input1.value;

	newRow.appendChild(cell1);
	newRow.appendChild(cell2);
	newRow.appendChild(cell3);
	newRow.appendChild(cell4);
	newRow.appendChild(cell5);
	table.appendChild(newRow);

	// Crear los botones de editar y borrar
	const deleteButton = document.createElement("button");
	deleteButton.classList.add("btn", "btn-outline-danger", "w-100");
	deleteButton.innerHTML = '<i class="fa-solid fa-trash"></i>';

	// Añadir los botones a la última celda de la fila
	cell5.appendChild(deleteButton);

	// Sumar el total de la fila a la variable total
	t += input1.value * input2.value;

	// Añadir el evento de borrar al botón correspondiente
	deleteButton.addEventListener("click", () => {
		// Restar el monto del total
		const rowTotal = cell4.textContent;
		t -= rowTotal;

		// Eliminar la fila de la tabla
		newRow.remove();
	});

	// Limpiar los inputs
	form.reset();
};




// Get element by ID
let buttonAdd = document.getElementById('btnAdd')

// Listener to click
buttonAdd.addEventListener('click', function(event) {

	// Prevenir que se envíe el formulario
	event.preventDefault();

	// Obtener referencia a los elementos de entrada
	const input1 = document.getElementById('montoAdd');
	const input2 = document.getElementById('cantidadAdd');
	const input3 = document.getElementById('typeAdd');

	// Verify if anything is null
	if (input1.value != "" && input2.value != "" && input3.value != "") {

		// Call the function
		addDataTable()
	}
})

const fact = () => {
	const contenedorElementos = document.getElementById('contentAdd');

	// Listen for changes in the table
	const observer = new MutationObserver(function(mutations) {
		mutations.forEach(function(mutation) {
			if (mutation.type === 'childList') {
				// Update the UI when a row is deleted
				contenedorElementos.innerHTML = '';
				const filasTabla = document.querySelectorAll('#tableAdd tr:not(:first-child)');
				filasTabla.forEach(fila => {
					const nombreVenta = fila.children[0].textContent;
					const cantidad = fila.children[1].textContent;
					const monto = fila.children[2].textContent;
					const total = fila.children[3].textContent;

					const nuevoElemento = document.createElement('div');
					nuevoElemento.classList.add('card', 'cardElemente', 'cardBody');

					const nombreVentaElemento = document.createElement('h5', 'mt-3');
					nombreVentaElemento.classList.add('nombreVenta');
					nombreVentaElemento.textContent = nombreVenta;

					const cantidadElemento = document.createElement('span');
					cantidadElemento.classList.add('font-weight-normal', 'mb-2');
					cantidadElemento.textContent = cantidad;

					const montoElemento = document.createElement('span');
					montoElemento.classList.add('font-weight-normal', 'mb-2');
					montoElemento.textContent = monto;

					const textoCantidad = document.createTextNode('- Cantidad: ');
					const textoMonto = document.createTextNode(' - Monto: ');

					nuevoElemento.appendChild(nombreVentaElemento);
					nuevoElemento.appendChild(document.createElement('hr'));
					nuevoElemento.appendChild(textoCantidad);
					nuevoElemento.appendChild(cantidadElemento);
					nuevoElemento.appendChild(textoMonto);
					nuevoElemento.appendChild(montoElemento);
					contenedorElementos.appendChild(nuevoElemento);
				});
			}
		});
	});

	// Start observing the table
	observer.observe(document.querySelector('#tableAdd'), { childList: true });
}


// Actualiza la hora cada segundo
setInterval(() => {
	fact();
	let markup = document.getElementById('total')
	markup.innerHTML = t
}, 500);


// Last Step
let formSale = document.getElementById('formSale')

// Listener to click
formSale.addEventListener('submit', function(event) {

	event.preventDefault(); // Prevenir el envío por defecto del formulario

	let clienteSelect = document.getElementById('clientS')

	// Add data to inputs
	let saleFinish = document.getElementById('saleFinish')
	saleFinish.value = t

	let clientFinish = document.getElementById('clientFinish')
	clientFinish.value = clienteSelect.value

	let kindFinish = document.getElementById('kindFinish')
	kindFinish.value = kind.value





	var formData = new FormData(formSale);
	var values = {};
	formData.forEach(function(value, key) {
		values[key] = value;
	});
	console.log(values); // Muestra los valores en la consola


})








