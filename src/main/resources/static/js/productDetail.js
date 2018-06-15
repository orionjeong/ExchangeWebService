
var host = '/api/product/';

function getUrlParams() {
    var params = {};
    window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (str, key, value) {
        params[key] = value;
    });
    return params;
}
function move(productId) {
    window.location.href = '/view/addexchange?productId='+productId;
}
$(document).ready(function () {
    var params = getUrlParams();
    if (params.productId) {
        $.get(host + params.productId, function (product) {
                $('.productName').text(product.title),
                $('.category2').val(product.category),
                $('.main-image > img').attr("src", "/productImage/"+product.image),
                $('.provider-id > .info').text(product.provider),
                $('.contents > .info').text(product.contents),
                $('.views > .info').text(product.views),
                $('.likes > .info').text(product.likes)
        });

        $.get("/exchange/list/search?productId"  + params.productId, function (exchange) {
            // $('.productName').text(product.title),
            //     $('.category2').val(product.category),
            //     $('.main-image > img').attr("src", "/productImage/"+product.image),
            //     $('.provider-id > .info').text(product.provider),
            //     $('.contents > .info').text(product.contents),
            //     $('.views > .info').text(product.views),
            //     $('.likes > .info').text(product.likes)
            console.log(product);
        });
    }


    document.getElementById("exchangeBtn").addEventListener("click", function () {
    move(params.productId) ;
    })

});