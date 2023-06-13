$('#deleteModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	var userId = button.data('user-id');
	var modal = $(this);
	modal.find('#deleteLink').attr('href', '/user/delete/' + userId);
});