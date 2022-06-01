$(document).ready(function (){

    getUser();

    generateObjectsTable();

    let isOpen = false;




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
            cityName = $form.find("input[name='cityName']").val(),
            streetName = $form.find("input[name='streetName']").val(),
            houseNumber = $form.find("input[name='houseNumber']").val(),
            flatNumber = $form.find("input[name='flatNumber']").val();

        $.ajax({
            url:"/api/user/objects/new",
            type:"POST",
            data: {"userId": check_cookie_name("Id"),
            cityName, streetName, houseNumber, flatNumber},
            success: function (data) {
                addObj(data);
            }
        })
    })

    $("body").on("click", ".del-but",function (){
        let estateId = $(this).parent().parent().attr("id");
        let el = $(this);
        console.log(estateId)
        $.ajax({
            url:"/api/user/objects/delete",
            type:"DELETE",
            data:{"estateId": estateId,
            "userId": check_cookie_name("Id")},
            success: function () {
                el.parent().parent().remove();
            }
        })
    })

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

    function addObj(data) {
        $("#table-objects").append("<tr id='"+data["id"]+"'><td>" + data["cityName"] +
            "</td><td>" + data["streetName"] + "</td><td>" + data["houseNumber"] + "</td>" +
            "<td>" + data["flatNumber"] + "</td>" +
            "<td><button type='button' class='btn btn-danger btn-sm del-but'>Delete</button></td>" +
            "<td><button type='button' class='btn-primary'>Pay</button> </td></tr>")
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
                 console.log(data[i]["id"]);
                 $("#table-objects").append("<tr id='"+data[i]["id"]+"'><td>"+data[i]["cityName"]+
                 "</td><td>"+data[i]["streetName"]+"</td><td>"+data[i]["houseNumber"]+"</td>" +
                     "<td>"+data[i]["flatNumber"]+"</td>" +
                     "<td><button type='button' class='btn btn-danger btn-sm del-but'>Delete</button></td>" +
                     "<td><button type='button' class='btn-primary'>Pay</button> </td></tr>");
             }
            }

        })
    }
});