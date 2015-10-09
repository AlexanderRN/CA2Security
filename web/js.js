$(document).ready(function () {
    $("#btn1").click(function () {
        var id = $("#input1").val();
        $.ajax({
            url: "api/persons/complete/"+id,
            type: "GET",
            dataType: "json"
        }).done(function (dataFromServer) {
            $("#titel").text("Alle informationer om en person for id");
            $("#print1").text("Name: " + dataFromServer.firstName);
            $("#print1").append(" " + dataFromServer.lastName);
            $("#print1").append("<br>Email: " + dataFromServer.email);
            $("#print1").append("<br>Phones: " + dataFromServer.phones);
            $("#print1").append("<br>Hobbies: " + dataFromServer.hobbies);
        }).fail(function () {
            alert("Failed.");
        });  
    });
    
    $("#btn2").click(function () {
        var id = $("#input1").val();

        $.ajax({
            url: "api/persons/complete/"+id,
            type: "GET",
            dataType: "json"
        }).done(function (dataFromServer) {
            $("#titel").text("Kontaktinformationer om en person for id");
            $("#print1").text("Name: " + dataFromServer.firstName);
            $("#print1").append(" " + dataFromServer.lastName);
            $("#print1").append("<br>Email: " + dataFromServer.email);
            $("#print1").append("<br>Address: " + dataFromServer.address);
        }).fail(function () {
            alert("Failed.");
        });  
    });
});