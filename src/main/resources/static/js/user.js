// 회원가입 이벤트 리스너
$("#btn-join").click(() => {
    join();
});

// 로그인 이벤트 리스너
$("#btn-login").click(() => {
    login();
});


// 2. 기능

// 회원가입 요청 메서드
async function join() {
    // (1) jquery로 username, password, email, addr 을 찾아서 자바스크립트 오브젝트로 만들기
    let joinDto = {
        username: $("#username").val(),
        password: $("#password").val(),
        email: $("#email").val(),
        addr: $("#addr").val()
    }

    // (2) fetch 요청하기 (json으로 변환해서)
    let response = await fetch("/api/join", {
        method: "POST",
        body: JSON.stringify(joinDto),
        headers: {
            'Content-Type': 'application/json; charset=utf-8',
        }
    });
    let responseParse = await response.json();

    // (3) 회원가입이 잘되면 알림창 띄우고 로그인 페이지로 이동하기
    if (responseParse.code == 1) {
        alert("회원가입 완료");
        location.href = "/loginForm";
    } else {
        alert("회원가입 실패");
    }
}

// 로그인 요청 메서드
async function login() {
    // (1) jquery로 username, password 를 찾아서 자바스크립트 오브젝트로 만들기
    let loginDto = {
        username: $("#username").val(),
        password: $("#password").val()
    }
    
    // (2) fetch 요청하기 (json으로 변환해서)
    let response = await fetch("/api/login", {
        method: "POST",
        body: JSON.stringify(loginDto),
        headers: {
            'Content-Type': 'application/json; charset=utf-8',
        }
    });
    let responseParse = await response.json();
    
    // (3) 회원가입이 잘되면 알림창 띄우고 로그인 페이지로 이동하기
    if (responseParse.code == 1) {
        alert("로그인 완료");
        location.href = "/";
    } else {
        alert("로그인 실패");
    }
}