$('#deleteModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	var unitaryId = button.data('unitary-id');
	var modal = $(this);
	modal.find('#deleteLink').attr('href', '/product/delete/unitary/' + unitaryId);
});