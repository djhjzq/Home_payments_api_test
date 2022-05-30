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

    let isAuthenticated = false;

    $("#form-login").submit(function (event){
        event.preventDefault();

        let $form = $(this),
            email = $form.find("input[name='email'").val(),
            password = $form.find("input[name='password'").val();

        loginPost(email, password);

    })

    function showUser() {
        if(isAuthenticated) {
            $.ajax({
                url:""
            })
        }
    }



    function loginPost(email, password) {
        $.ajax({
            url:"/api/auth/login",
            type:"POST",
            async:false,
            data: {email, password},
            success: function (data) {
                let urlLogin;
                if(data["role"] === "ROLE_USER") {
                    urlLogin = "/api/user"
                } else {
                    urlLogin = "/api/admin"
                }
                window.localStorage.setItem("id", data["id"]);
                window.localStorage.setItem("token", data["token"]);
                $.ajax({
                    url:urlLogin,
                    type:"GET",
                    async: false,
                    headers: {
                        "Authorization": "Bearer " + data["token"],
                    },
                    success: function (data) {
                        $("body").replaceWith(data);
                        isAuthenticated = true;
                    }
                })
            },
            error: function () {
                $("#warning-msg").replaceWith("Incorrect password or email.")
            }
        })
    }

});

