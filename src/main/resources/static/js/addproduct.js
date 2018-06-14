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
var host = '/api/products';

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

    if(product.id)
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
        window.location.href = 'productList';
    });
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
