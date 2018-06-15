var filesUpload = document.getElementById("image"),
    fileList = document.getElementById("file-list");
var fileName;
var productId;
function traverseFiles (files) {
    var li,
        file,
        fileInfo;
    fileList.innerHTML = "";

    for (var i=0, il=files.length; i<il; i++) {
        li = document.createElement("li");
        file = files[i];
        fileInfo = "<div><strong>FileName:</strong> "
            + file.name + "</div>";
        fileName=file.name;
        li.innerHTML = fileInfo;
        fileList.appendChild(li);
    };
};

filesUpload.onchange = function () {
    traverseFiles(this.files);
};

//ajax
var host = '/api/exchange';

function getUrlParams() {
    var params = {};
    window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (str, key, value) {
        params[key] = value;
    });
    return params;
}

function save(productId) {
    var exchange = {
        productId:productId,
        title:$('#title').val(),
        contents: $('#contents').val(),
        category: $('#category').val(),
        image:fileName
    };

    var method = 'POST';

    requestData(method, product);
    return false;
}

function requestData(method, data) {

    $.ajax({
        url: host,
        method: method,
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(data)
    }).done(function () {
        alert("교환에 대한 처리가 완료되었습니다.");
        window.location.href = '/view/productDetail?productId='+productId;
    }).fail(function(jqXHR, textStatus, errorThrown){
        //권한 에러 처리 프론트에 위임

        alert("교환에 대한 처리가 실패하였습니다.");
        window.location.href = '/view/productDetail?productId='+productId;    })
}

$(document).ready(function () {
    var params = getUrlParams();
    productId = params.productId;
    $('#imageForm').submit(function(){
        save( params.productId);
        return true;
    } ) ;


});
