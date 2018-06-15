
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

        $.get("/api/exchange/list/search?productId="  + params.productId, function (exchange) {
            var str="";
            for(i=0; i<exchange.length; i++){
            str+='<nav class="about">';
                    str+='<div class="about-content"><img src="/productImage/'+exchange[i].image+'" alt=""></div>';
                    str+='<div class="about-content">';
                    str+='<P>교환상품이름 :'+exchange[i].title+'</P><br>';
                str+= '<P>교환상품설명 :'+exchange[i].contents+' </P><br>';
                str+='</div> </div> </nav>';

            }
            document.getElementById("exchange").innerHTML=str;

            // $('.productName').text(product.title),
            //     $('.category2').val(product.category),
            //     $('.main-image > img').attr("src", "/productImage/"+product.image),
            //     $('.provider-id > .info').text(product.provider),
            //     $('.contents > .info').text(product.contents),
            //     $('.views > .info').text(product.views),
            //     $('.likes > .info').text(product.likes)
            console.log(exchange);
        });
        $.get("/api/comment/list/search?productId="  + params.productId, function (comment) {
            // $('.productName').text(product.title),
            //     $('.category2').val(product.category),
            //     $('.main-image > img').attr("src", "/productImage/"+product.image),
            //     $('.provider-id > .info').text(product.provider),
            //     $('.contents > .info').text(product.contents),
            //     $('.views > .info').text(product.views),
            //     $('.likes > .info').text(product.likes)
            console.log(comment);
        });
    }


    document.getElementById("exchangeBtn").addEventListener("click", function () {
    move(params.productId) ;
    })

});