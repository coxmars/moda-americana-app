$('#deleteModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	var messageId = button.data('message-id');
	var modal = $(this);
	modal.find('#deleteLink').attr('href', '/contact/delete/' + messageId);
});