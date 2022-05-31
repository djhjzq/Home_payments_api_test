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
            email = $form.find("input[name='email']").val(),
            password = $form.find("input[name='password']").val();

        loginPost(email, password);

    })

    $("#form-registry").submit(function (event){
        event.preventDefault();

        let $form = $(this),
            firstName = $form.find("input[name='firstName']").val(),
            lastName = $form.find("input[name='lastName']").val(),
            email = $form.find("input[name='email']").val(),
            password = $form.find("input[name='email']").val();

        registryUser(firstName, lastName, email, password);

    })

    function registryUser(firstName, lastName, email, password) {
        $.ajax({
            url: "/api/auth/registry",
            type: "POST",
            async: false,
            data: {firstName, lastName, email, password},
            success: function () {
                loginPost(email, password);
            },
            error: function () {
                $("#warning-msg-r").replaceWith("Something goes wrong...")
            }
        })
    }

    function loginPost(email, password) {
        $.ajax({
            url:"/api/auth/login",
            type:"POST",
            async:false,
            data: {email, password},
            success: function () {
                let urlLogin;
                let role = check_cookie_name("Role");
                console.log(role);
                if (role === "ROLE_USER") {
                    urlLogin = "/api/user"
                } else {
                    urlLogin = "/api/admin"
                }
                window.location.href = urlLogin;
            },
            error: function () {
                $("#warning-msg").replaceWith("Incorrect password or email.")
            }
        })
    }
    function check_cookie_name(name)
    {
        let match = document.cookie.match(new RegExp('(^| )' + name + '=([^;]+)'));
        if (match) {
            return match[2];
        }
        else{
            console.log('--something went wrong---');
        }
    }
});

