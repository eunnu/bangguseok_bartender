
function login() {
    var id = document.querySelector('#id');
    var pw = document.querySelector('#pw');

    if(id.value == "" || pw.value == ""){
        alert("아이디 혹은 비밀번호를 확인하세요")
    }
    else{
        location.href = 'list.html';
    }
}

function back() {
    location.href = 'index.html';
}

function join_id() {
    var id = document.querySelector('#id');
    var pw = document.querySelector('#pw');
    var r_pw = document.querySelector('#r_pw');

    if(id.value == "" || pw.value == "" || r_pw.value == "") {
        alert("빈칸을 확인해주세요.")
    }
    else {
        if(pw.value !== r_pw.value) {
            alert("비밀번호가 일치하지 않습니다.")
        }
        else {
            location.href = 'list.html';
        }
    }
}