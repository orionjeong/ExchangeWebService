
var host = '/login';

function login() {
    var user = {
        username: $('#username').val(),
        password: $('#password').val(),
    };
    var method = 'POST';

    requestData(method, user);
    return false;
}
function requestData(method, data) {
    $.ajax({
        url: host,
        method: method,
        contentType: "application/json",
        data: JSON.stringify(data)
    }).done(function (response) {
        sessionStorage.setItem("token",response.token);
        sessionStorage.setItem("user",response.username);
        window.location.href = "/view/login";
    }).fail(function(jqXHR, textStatus, errorThrown){
        //권한 에러 처리 프론트에 위임
    var status = jqXHR.status;
        console.log(status);
    if(status==403){
        alert("아이디 비밀번호를 다시 확인해 주세요");
    }
    })
}
$(document).ready(function () {

    $('#btn_login').on("click", login);
    //아이디 저장 구현
   var checkboxState = localStorage.getItem("check");
   var sessionUser = sessionStorage.getItem("user");
    if(checkboxState!=null&&checkboxState=="true"){
        $("#checkbox").attr("checked", true);
        localStorage.setItem("check","true");
        if(sessionUser!=null){
            console.log(sessionUser);
            $("#username").val(sessionUser);
        }
    }else{
        $("#checkbox").attr("checked", false);
        localStorage.setItem("check","false");
    }

    $("#checkbox").change(function(){ // 체크박스에 변화가 있다면,
        if($("#checkbox").is(":checked")){ // ID 저장하기 체크했을 때,
            localStorage.setItem("check","true");
        }else{ // ID 저장하기 체크 해제 시,
            localStorage.setItem("check","false");
        }
    });


});
