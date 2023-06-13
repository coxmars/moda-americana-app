$('#deleteModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	var providerId = button.data('provider-id');
	var modal = $(this);
	modal.find('#deleteLink').attr('href', '/provider/delete/' + providerId);
});