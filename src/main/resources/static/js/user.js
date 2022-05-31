$(document).ready(function (){

    getUser();

    generateObjectsTable();

    let isOpen = false;

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

    $("#add-obj").click(function (){
        if (isOpen === false) {
            $("#addObject-form").css("display", "block")
            isOpen = true;
            $(this).html("Hide")
        }
        else {
            $("#addObject-form").css("display", "none")
            isOpen = false;
            $(this).html("Add new");
        }
    })


    $("#form-Obj").submit(function (event) {
        event.preventDefault();

        let $form = $(this),
            cityName = $form.find("input[name='firstName']").val(),
            StreetName = $form.find("input[name='lastName']").val(),
            houseNumber = $form.find("input[name='email']").val(),
            flatNumber = $form.find("input[name='email']").val();



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

    function generateObjectsTable() {
        $.ajax({
            url: "/api/user/objects/all",
            type: "POST",
            data:{"userId":  check_cookie_name("Id")},
            dataType: "json",
            async: false,
            success: function (data) {
             let l = data.length;
             for (let i = 0; i < l; i++) {
                 $("#table-objects").append("<tr><td>"+data[i]["cityName"]+
                 "</td><td>"+data[i]["streetName"]+"</td><td>"+data[i]["houseNumber"]+"</td>" +
                     "<td>"+data[i]["flatNumber"]+"</td></tr>");
             }
            }

        })
    }
});