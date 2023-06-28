// BINNACLE
$(document).ready(function() {
	
	var now = new Date();
	var jsDate = now.getDate() + '/' + (now.getMonth() + 1) + '/' + now.getFullYear(); // Guardamos la fecha es para el PDF
	
	// Esta función se encarga de Agregar desde el inicio todos los productos/registros en la datatable
	tablaProductos = $('#dataTablePaca').DataTable({
		
		// Con esto acomodamos los elementos de la tabla, B=Botones, l=Cantidad resultados, f=filtro, tr= table, p=pagination
		dom: "<<'float-right mb-1'B>>" +
			"<'row'<'col-sm-12 col-md-6 mb-2'l><'col-sm-12 col-md-1 mb-2'f>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-12 col-md-5'i><'col-sm-12 col-md-7'p>>",
		"responsive": true,
		
		// Agregamos estilos a los botones, text=Nombre, titleAttr=Nombre al pasar cursor, className=Color Bootstrap
		buttons: [
			{
				extend: "excelHtml5", filename: `TablaPacas-${jsDate}`, text: "Excel", titleAttr: "Exportar Excel", className: "btn btn-success px-3 mr-2 rounded",
				messageTop: `Registro de Pacas`,
				exportOptions: {
					columns: [1, 2, 3, 4, 5, 6]
				}, excelStyles: {                // Add an excelStyles definition
					template: "green_medium",  // Apply the 'blue_medium' template
				}
			},
			{
				extend: "pdfHtml5", filename: `TablaPacas-${jsDate}`, text: "PDF", titleAttr: "Exportar PDF", className: "btn btn-danger px-3 mr-2 rounded",
				exportOptions: {
					columns: [1, 2, 3, 4, 5, 6]
				}, // Determinamos las columnas que queremos exportar en pdf
				// Esta función nos sirve para personalizar todo el pdf
				customize: function(doc) {
					// Esto sirve para centrar los datos en las columnas del pdf
					doc.styles.tableBodyEven.alignment = 'center';
					doc.styles.tableBodyOdd.alignment = 'center';
					// Esto cambia el color del header del pdf
					for (var row = 0; row < doc.content[1].table.headerRows; row++) {
						var header = doc.content[1].table.body[row];
						for (var col = 0; col < header.length; col++) {
							header[col].fillColor = '#6799A3';
						}
					}
					// Lo siguiente se tomó de aquí: https://www.web-dev-qa-db-esp.com/es/jquery/exportacion-de-tablas-de-datos-pdf-con-100-de-ancho/823889082/
					doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
					doc.content[1].margin = [0, 0, 0, 0] // Esto centra la tabla en el pdf
					//Remueve el titulo creado por dataTables
					doc.content.splice(0, 1);
					doc.pageMargins = [10, 70, 10, 40]; // 20,60,20,30
					doc.defaultStyle.fontSize = 9;
					doc.styles.tableHeader.fontSize = 10;
					doc.styles.tableFooter.fontSize = 8;
					// Personalizar header del pdf
					doc['header'] = (function() {
						return {
							columns: [
								{ alignment: 'center', italics: false, text: 'Registro de Pacas', color: "black", fontSize: 18, margin: [0, 10] },
							],
							margin: 20
						}
					});
					// Personalizar footer del pdf
					doc['footer'] = (function(page, pages) {
						return {
							columns: [
								{ alignment: 'left', text: 'Moda Americana | ' + jsDate.toString(), color: "gray" },
								{ alignment: 'right', text: 'Página ' + page.toString() + ' de ' + pages.toString(), color: "gray" }
							],
							margin: 20
						}
					});
				}
			},
			
			{
				extend: "print", filename: "Pacas", text: "Imprimir", titleAttr: "Imprimir", className: "btn btn-dark px-2 mr-2 rounded",
				exportOptions: {
					columns: [1, 2, 3, 4, 5, 6]
				}
			}
		],
		
		// Agregar o al inicio para que sirva, falta actualizar documentación
		"oLanguage": {
			"sSearch": "", // Para cambiar el label de search por buscar
			"sSearchPlaceholder": "Buscar",
			"sLengthMenu": "Mostrar _MENU_ datos", // Para cambiar show __MENU__ entries por Mostrar datos
			"sInfo": "Mostrando _START_ a _END_ de _TOTAL_ datos",
			// Cambiar los nombres en ingles de la paginación
			"oPaginate": {
				"sFirst": "Primera",
				"sPrevious": "Anterior",
				"sNext": "Siguiente",
				"sLast": "Ultima"
			}
		}
	});
});