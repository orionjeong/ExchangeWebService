var filesUpload = document.getElementById("image"),
    fileList = document.getElementById("file-list");
var fileName;
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
var host = '/api/product';

function getUrlParams() {
    var params = {};
    window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (str, key, value) {
        params[key] = value;
    });
    return params;
}

function save() {
    var product = {
        productId:$('#productId').text(),
        title:$('#title').val(),
        contents: $('#contents').val(),
        category: $('#category').val(),
        image:fileName
    };

    var method = 'POST';

    if(product.productId)
        method = 'PUT';
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
        alert("상품에 대한 처리가 완료되었습니다.");
        window.location.href = '/view/index';
    }).fail(function(jqXHR, textStatus, errorThrown){
        //권한 에러 처리 프론트에 위임

        alert("상품에 대한 처리가 실패하였습니다.");
        window.location.href = '/view/index';
    })
}

$(document).ready(function () {
    var params = getUrlParams();
    if (params.productId) {
        $.get(host + "/" + params.productId, function (product) {
                $('#productId').text(product.productId);
                $('#title').val(product.title),
                $('#category').val(product.category),
                $('#contents').val(product.contents),
                $('#image').val(product.image)
        });
    }
    $('#imageForm').submit(function(){
        save();
        return true;
    } ) ;


});
