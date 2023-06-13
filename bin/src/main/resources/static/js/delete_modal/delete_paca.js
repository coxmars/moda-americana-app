$('#deleteModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	var productId = button.data('product-id');
	var modal = $(this);
	modal.find('#deleteLink').attr('href', '/product/delete/paca/' + productId);
});