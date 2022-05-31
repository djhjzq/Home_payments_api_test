function toggleSignup(){
    document.getElementById("login-toggle").style.backgroundColor="#fff";
    document.getElementById("login-toggle").style.color="#222";
    document.getElementById("signup-toggle").style.backgroundColor="#57b846";
    document.getElementById("signup-toggle").style.color="#fff";
    document.getElementById("login-form").style.display="none";
    document.getElementById("signup-form").style.display="block";
}

function toggleLogin(){
    document.getElementById("login-toggle").style.backgroundColor="#57B846";
    document.getElementById("login-toggle").style.color="#fff";
    document.getElementById("signup-toggle").style.backgroundColor="#fff";
    document.getElementById("signup-toggle").style.color="#222";
    document.getElementById("signup-form").style.display="none";
    document.getElementById("login-form").style.display="block";
}

$(document).ready(function (){

    $("#form-login").submit(function (event){
        event.preventDefault();

        let $form = $(this),
            email = $form.find("input[name='email'").val(),
            password = $form.find("input[name='password'").val();

        loginPost(email, password);

    })

    function loginPost(email, password) {
        $.ajax({
            url:"/api/auth/login",
            type:"POST",
            async:false,
            data: {email, password},
            success: function (data) {
                let urlLogin;
                if (data["role"] === "ROLE_USER") {
                    urlLogin = "/api/user"
                } else {
                    urlLogin = "/api/admin"
                }
                window.location.href=urlLogin;
            },
            error: function () {
                $("#warning-msg").replaceWith("Incorrect password or email.")
            }
        })
    }

});

