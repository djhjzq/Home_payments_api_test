$(document).ready(function (){

    getUser();

    function getUser() {
        let userId = check_cookie_name("Id");
        $.ajax({
            url:"/api/user/"+userId,
            type: "GET",
            success: function (data) {
                $("#welcome-text").append(data["firstName"]+" "+
                data["lastName"]+".");
            }
        })
    }


    $("#logout-button").click(function () {
        window.location.href="/api/logout"
    })

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