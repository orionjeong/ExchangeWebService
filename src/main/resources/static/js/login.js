
var host = '/login';

function login() {
    var user = {
        username: $('#username').val(),
        password: $('#password').val(),
    };
    var method = 'POST';
    if(user.id)
        method = 'PUT';
    requestData(method, user);
    return false;
}
function requestData(method, data) {
    $.ajax({
        url: host,
        method: method,
        contentType: "application/json",
        data: JSON.stringify(data)
    }).done(function (state) {
        sessionStorage.setItem("token",token);
        window.location.href = "/index";
    });
}
$(document).ready(function () {
    $('#btn_login').on("click", login);
});
