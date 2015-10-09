$(document).ready(function () {
    $("#btn1").click(function () {
        var id = $("#input1").val();
        $.ajax({
            url: "api/persons/complete/" + id,
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
            url: "api/persons/complete/" + id,
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

    $("#btn5").click(function () {

        $.ajax({
            url: "api/persons/complete/",
            type: "GET",
            dataType: "json"
        }).done(function (dataFromServer) {
            dataFromServer.forEach(function (person) {
                $("#alle").append(makeRow(person));
            });
        }).fail(function () {
            alert("Failed.");
        });
    });

    $("#btn6").click(function () {
        var id = $("#input1").val();
        $.ajax({
            url: "api/persons/phone/" + id,
            type: "GET",
            dataType: "json"
        }).done(function (dataFromServer) {
                $("#titel").text("Kontaktinformationer om en person for phone");
                $("#print1").text("Name: " + dataFromServer.firstName);
                $("#print1").append(" " + dataFromServer.lastName);
                $("#print1").append("<br>Email: " + dataFromServer.email);
        }).fail(function () {
            alert("Failed.");
        });
    });

    $("#btn7").click(function () {
        var id = $("#input1").val();
        $.ajax({
            url: "api/persons/zip/" + id,
            type: "GET",
            dataType: "json"
        }).done(function (dataFromServer) {
                $("#titel").text("Kontaktinformationer om en person for phone");
                $("#print1").text("Name: " + dataFromServer.firstName);
                $("#print1").append(" " + dataFromServer.lastName);
                $("#print1").append("<br>Email: " + dataFromServer.email);
        }).fail(function () {
            alert("Failed.");
        });
    });
    

    function makeRow(person) {
        var row = "<tr><td>" + person.firstName + "</td><td>" + person.lastName + "</td><td>" + person.email + "</td>\n\
        <td>" + person.street + "</td><td>" + person.additionalInfo + "</td><td>" + person.zipCode + "</td><td>" + person.city + "</td></tr>";
        return row;
    }

    $("#btn4").click(function () {
        var id = $("#input1").val();

        $.ajax({
            url: "api/persons/delete/" + id,
            type: "DELETE",
            dataType: "json"
        }).done(function (dataFromServer) {
            $("#titel").text("Personen er hermed slettet.");
        }).fail(function () {
            alert("Failed.");
        });
    });

    $("#btn3").click(function () {
        var fname = $("#input2").val();
        var lname = $("#input3").val();
        var email = $("#input4").val();
        var data = {firstName: fname, lastName: lname, email: email};
        $.ajax({
            url: "api/persons/",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(data)
        }).done(function (dataFromServer) {
            $("#respons").text("Person has been added");
        }).fail(function () {
            alert("Failed.");
        });
    });
});