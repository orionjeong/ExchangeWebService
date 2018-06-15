
var host = '/api/product/';

function getUrlParams() {
    var params = {};
    window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (str, key, value) {
        params[key] = value;
    });
    return params;
}


function save(productId) {
    var comment = {
        contents:$('#commentContents').val(),
        productId:productId
    };

    var method = 'POST';

    requestData(method, comment);
    return false;
}

function requestData(method, data) {

    $.ajax({
        url: "/api/comment",
        method: method,
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(data)
    }).done(function () {
        alert("댓글에 대한 처리가 완료되었습니다.");
        window.location.href = '/view/productDetail?productId='+ data.productId;

    }).fail(function(jqXHR, textStatus, errorThrown){
        //권한 에러 처리 프론트에 위임
        console.log(jqXHR.status);
        if(jqXHR.status=="403"){
            alert("로그인이 필요합니다.");
            window.location.href = '/view/login';
        }

    })
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
                str+= '<P>교환상품설명 :'+exchange[i].contents+' </P><br>';
                    str+='<P>교환상품이름 :'+exchange[i].title+'</P><br>';
                str+= '<P>교환신청자 :'+exchange[i].username+' </P><br>';
                str+='</div> </div> </nav>';

            }
            document.getElementById("exchange").innerHTML=str;

        });
        $.get("/api/comment/list/search?productId="  + params.productId, function (comment) {
            var str="";
            for(i=0; i<comment.length; i++){
                str+='<nav class="about">';
                str+='<div class="about-content">';
                str+='<P>작성자 :'+comment[i].userId+'</P><br>';
                str+= '<P>댓글내용 :'+comment[i].contents+' </P><br>';
                str+='</div> </div> </nav>';
            }
            document.getElementById("comment").innerHTML=str;
        });
    }

    $('.comment-btn-wrap > button').click( function(){
        save(params.productId);
    })
    document.getElementById("exchangeBtn").addEventListener("click", function () {
    move(params.productId) ;
    })

});