
var host = '/api/user';

function save() {
    var user = {
        username: $('#username').val(),
        password: $('#password').val(),
        name: $('#name').val(),
        email: $('#email').val(),
        phone: $('#phone').val()
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
        if(state.stateCode=="200"){
            window.location.href = "/";
        }else{
            alert(state.messege);
        }

    });
}
$(document).ready(function () {

    $('#joinBtn').on("click", save);
});
