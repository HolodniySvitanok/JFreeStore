/**
 * Created by Admin on 03.11.2016.
 */

function basket(URL, id) {

    var count = $('#count_' + id).val();

    if (count < 1) {
        $('#count_' + id).val('');
        return;
    }
    var idProduct = id;

    var purchase = new Object();
    purchase.idProduct = idProduct;
    purchase.count = count;


    var object = JSON.stringify(purchase);

    $.ajax({
        url: URL,
        contentType: 'application/json; charset=UTF-8',
        dataType: 'text',
        type: "POST",
        data: object,

        success: function (result) {

            $('#count_' + id).val('');
            var obj = jQuery.parseJSON(result);

            $('#totalPr').html(obj.totalPrice);
        },
        error: function () {

        }
    });
}