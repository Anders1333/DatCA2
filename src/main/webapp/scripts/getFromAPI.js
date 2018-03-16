document.getElementById("getUser").onclick = getUserByID;
document.getElementById("deleteUser").onclick = deleteUserById;
document.getElementById("getZip").onclick = getCityByZipCode;
document.getElementById("getAllZip").onclick = getAllZipCodes;
document.getElementById("getAllPhoneNumbs").onclick = getAllPhoneNumbers;
document.getElementById("getAllUsers").onclick = getAllUsers;

var allZipCodeDiv = document.getElementById("allZipCodeDiv");
var allPhoneNumDiv = document.getElementById("allPhoneNumDiv");
var allUsersDiv = document.getElementById("allUsersDiv");

function getUserByID() {
    var id = document.getElementById("idInput").value;
    if (id <= 0) {
        window.alert("Please enter a valid id");
        return null;
    } else {
        console.log("Trying to get user with id: " + id);

        fetch("http://localhost:8084/DatCA2/API/Data/persons" + "/" + id)
                .then(res => res.json())
                .then(data => document.getElementById("userInfoOutput").innerHTML =
                            '<ul class="list-group" style="margin-bottom: 0px;">'
                            + '<li class="list-group-item">' + "<strong>ID: </strong>" + data.id + '</li>'
                            + '<li class="list-group-item">' + "<strong>Firstname: </strong>" + data.firstName + '</li>'
                            + '<li class="list-group-item">' + "<strong>Lastname: </strong>" + data.lastName + '</li>'
                            + '<li class="list-group-item">' + "<strong>Email: </strong>" + data.email + '</li>'
                            + '<ul>'
                );
    }
}
;


function deleteUserById() {
    var id = document.getElementById("idDeleteInput").value;

    console.log("Trying to delete user with id: " + id);

    var theUser = {
        id: id
    };


    //Post javascript as json
    var settings = {
        body: JSON.stringify(theUser), // must match 'Content-Type' header
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        method: 'DELETE', // *GET, PUT, DELETE, etc.
    };


    fetch("http://localhost:8084/DatCA2/API/Data/persons/" + id, settings)
            .then(res => res.json());
}
;




function getCityByZipCode() {
    var id = document.getElementById("zipInput").value;

    if (id <= 99) {
        window.alert("Please enter a valid zipcode");
        return null;
    } else {
        console.log("Trying to get city by zipcode: " + id);

        fetch("http://localhost:8084/DatCA2/API/Data/cities" + "/" + id)
                .then(res => res.json())
                .then(data => document.getElementById("zipInfoOutput").innerHTML =
                            '<ul class="list-group">'
                            + '<li class="list-group-item">' + "<strong>ID: </strong>" + data.zipCode + '</li>'
                            + '<li class="list-group-item">' + "<strong>City:  </strong>" + data.city + '</li>'
                            + '<ul>'
                );
    }
}
;

function getAllZipCodes() {

    //Remove all items from list
    document.getElementById("zipDataList").innerHTML = "";

    var cityLabel = '<label style="margin-right: 8px;">' + "CITY: " + '</label>';
    var zipLabel = '<label style="margin-right: 8px;">' + "ZIP: " + '</label>';

    allZipCodeDiv.style.display = "block";

    $.getJSON('http://localhost:8084/DatCA2/API/Data/cities', function (data) {
        var dataObject = data;
        var listItemString = $('#zipListItem').html();
        dataObject.forEach(buildNewList);

        function buildNewList(item) {
            var listItem = $('<li class="list-group-item">' + listItemString + '</li>');

            //The Zip Code
            var listItemTitle = $('.listZipCode', listItem);
            listItemTitle.html(zipLabel + item.zipCode);

            //The City
            var listItemTitle = $('.listZipCity', listItem);
            listItemTitle.html(cityLabel + item.city);

            $('#zipDataList').append(listItem);
        }
    });
}
;


function getAllPhoneNumbers() {

    //Remove all items from list
    document.getElementById("phoneDataList").innerHTML = "";

    var phoneNumLabel = '<label style="margin-right: 8px;">' + "Phone No: " + '</label>';

    allPhoneNumDiv.style.display = "block";

    $.getJSON('http://localhost:8084/DatCA2/API/Data/phonenumbers', function (data) {


        var dataObject = data;
        var listItemString = $('#phoneListItem').html();
        dataObject.forEach(buildNewList);

        function buildNewList(item) {
            var listItem = $('<li class="list-group-item">' + listItemString + '</li>');

            var listItemTitle = $('.listPhoneNumber', listItem);
            listItemTitle.html(phoneNumLabel + item.number);

            $('#phoneDataList').append(listItem);
        }
    });
}
;



function getAllUsers() {

    //Remove all items from list
    document.getElementById("allUsersDataList").innerHTML = "";

    var IdLabel = '<label style="margin-right: 8px;">' + "ID: " + '</label>';
    var firstNameLabel = '<label style="margin-right: 8px;">' + "Firstname: " + '</label>';
    var lastNameLabel = '<label style="margin-right: 8px;">' + "Lastname: " + '</label>';
    var EmailLabel = '<label style="margin-right: 8px;">' + "Email: " + '</label>';

    allUsersDiv.style.display = "block";

    $.getJSON('http://localhost:8084/DatCA2/API/Data/persons', function (data) {
        var dataObject = data;
        var listItemString = $('#allUsersListItem').html();
        dataObject.forEach(buildNewList);

        function buildNewList(item) {
            var listItem = $('<li class="list-group-item">' + listItemString + '</li>');

            var listItemTitle = $('.listAllUsersId', listItem);
            listItemTitle.html(IdLabel + item.id);

            var listItemTitle = $('.listAllUsersFirstName', listItem);
            listItemTitle.html(firstNameLabel + item.firstName);

            var listItemTitle = $('.listAllUsersEmail', listItem);
            listItemTitle.html(EmailLabel + item.email);

            var listItemTitle = $('.listAllUsersLastName', listItem);
            listItemTitle.html(lastNameLabel + item.lastName);
            $('#allUsersDataList').append(listItem);
        }
    });
}
;