
function executarFuncao() {
    $(document).ready(function () {
        $('.edit-btn').on('click', function () {
            var productId = $(this).data('id');
            var productName = $(this).data('name');
            var productDescription = $(this).data('description');
            var productValue = $(this).data('value');
            var productBarcode = $(this).data('barcode');
    
            $('#exampleModal input[name="Productname"]').val(productName);
            $('#exampleModal textarea[name="Productdescription"]').val(productDescription);
            $('#exampleModal input[name="Productvalue"]').val(productValue);
            $('#exampleModal input[name="Productbarcode"]').val(productBarcode);
    
            // Você pode adicionar mais campos aqui conforme necessário
        });
    });
}

