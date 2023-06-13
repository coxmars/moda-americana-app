// Get the sales of current day
function obtenerVentasDiaActual(ventas) {
	const fechaActual = new Date();
	const diaActual = fechaActual.toLocaleString('default', { day: '2-digit' });
	const mesActual = fechaActual.toLocaleString('default', { month: '2-digit' });
	const anioActual = fechaActual.getFullYear();
	const key = `${anioActual}-${mesActual}-${diaActual}`;
	let ventasDiaActual = 0;

	ventas.forEach(venta => {
		const fechaVenta = new Date(venta.fecha + 'T00:00:00');
		const dia = fechaVenta.toLocaleString('default', { day: '2-digit' });
		const mes = fechaVenta.toLocaleString('default', { month: '2-digit' });
		const anio = fechaVenta.getFullYear();
		const ventaDiaMesAnio = `${anio}-${mes}-${dia}`;

		if (ventaDiaMesAnio === key) {
			ventasDiaActual += venta.cantidad;
		}
	});

	return ventasDiaActual.toLocaleString('es-AR');
}

// Get the sales of current month
function obtenerVentasMesActual(ventas) {
	const fechaActual = new Date();
	const mesActual = fechaActual.toLocaleString('default', { month: '2-digit' });
	const anioActual = fechaActual.getFullYear();
	const key = `${anioActual}-${mesActual}`;
	let ventasMesActual = 0;

	ventas.forEach(venta => {
		const fechaVenta = new Date(venta.fecha + 'T00:00:00');
		const mes = fechaVenta.toLocaleString('default', { month: '2-digit' });
		const anio = fechaVenta.getFullYear();
		const ventaMesAnio = `${anio}-${mes}`;

		if (ventaMesAnio === key) {
			ventasMesActual += venta.cantidad;
		}
	});

	return ventasMesActual.toLocaleString('de-DE');
}

// Get the sales for the current first fortnight 
function obtenerVentasPrimeraQuincena(ventas) {
  const fechaActual = new Date();
  const mesActual = fechaActual.toLocaleString('default', { month: '2-digit' });
  const anioActual = fechaActual.getFullYear();
  const primerDiaMes = new Date(anioActual, mesActual - 1, 1);
  const decimoquintoDiaMes = new Date(anioActual, mesActual - 1, 15);
  let ventasPrimeraQuincena = 0;

  ventas.forEach(venta => {
    const fechaVenta = new Date(venta.fecha + 'T00:00:00');
    if (fechaVenta >= primerDiaMes && fechaVenta <= decimoquintoDiaMes) {
      ventasPrimeraQuincena += venta.cantidad;
    }
  });

  return ventasPrimeraQuincena.toLocaleString('es-AR');
}

// Get the sales for the current second fortnight
function obtenerVentasSegundaQuincena(ventas) {
  const fechaActual = new Date();
  const mesActual = fechaActual.toLocaleString('default', { month: '2-digit' });
  const anioActual = fechaActual.getFullYear();
  const decimosextoDiaMes = new Date(anioActual, mesActual - 1, 16);
  const ultimoDiaMes = new Date(anioActual, mesActual, 0);
  let ventasSegundaQuincena = 0;

  ventas.forEach(venta => {
    const fechaVenta = new Date(venta.fecha + 'T00:00:00');
    if (fechaVenta >= decimosextoDiaMes && fechaVenta <= ultimoDiaMes) {
      ventasSegundaQuincena += venta.cantidad;
    }
  });

  return ventasSegundaQuincena.toLocaleString('es-AR');
}

// Get markups by ID
let month_sales = document.getElementById('month_sales')
let day_sales = document.getElementById('day_sales')
let first_fortnight_sales = document.getElementById('first_fortnight')
let second_fortnight_sales = document.getElementById('second_fortnight')

// Get data of functions
let sales_month = obtenerVentasMesActual(ventas)
let sales_day = obtenerVentasDiaActual(ventas)
let sales_first_fortnight = obtenerVentasPrimeraQuincena(ventas)
let sales_second_fortnight = obtenerVentasSegundaQuincena(ventas)


// Verify if the ids is null
if (month_sales != null && day_sales != null && first_fortnight_sales != null && second_fortnight_sales != null) {

	// Inner the data in html
	month_sales.innerHTML = sales_month
	day_sales.innerHTML = sales_day
	first_fortnight_sales.innerHTML = sales_first_fortnight
	second_fortnight_sales.innerHTML = sales_second_fortnight
}

