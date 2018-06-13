
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
        }

    }).fail(function(jqXHR, textStatus, errorThrown){
        //권한 에러 처리 프론트에 위임
        var status = jqXHR.status;
        console.log(status);

    })

}
$(document).ready(function () {

    $('#joinBtn').on("click", save);
});
