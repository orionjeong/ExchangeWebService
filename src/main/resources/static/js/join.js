
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
    }).done(function (jqXHR, state) {
        alert("회원가입이 완료되었습니다.");
        window.location.href = "/view/index";
    }).fail(function(jqXHR, textStatus, errorThrown){
        //권한 에러 처리 프론트에 위임
        var status = jqXHR.status;
        if(status=="409"){
            alert("중복된 아이디가 존재합니다.");
        }

        console.log(status);

    })

}
$(document).ready(function () {

    $('#joinBtn').on("click", save);
});
