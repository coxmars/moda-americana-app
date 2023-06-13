$('#deleteModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	var categoryId = button.data('category-id');
	var modal = $(this);
	modal.find('#deleteLink').attr('href', '/category/' + categoryId);
});