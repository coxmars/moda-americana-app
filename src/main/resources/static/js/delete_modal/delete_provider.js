$('#deleteModal').on('show.bs.modal', function(event) {
	console.log('sas')
	var button = $(event.relatedTarget);
	var providerId = button.data('provider-id');
	var modal = $(this);	
	modal.find('#deleteLink').attr('href', '/provider/delete/' + providerId);
});