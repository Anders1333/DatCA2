
document.getElementById("getallAddresses").onclick = getAllAddresses;
document.getElementById("getallUsersByCity").onclick = getAllUsersByCity;
document.getElementById("getallHobbies").onclick = getAllHobbies;
document.getElementById("getallUsersByHobby").onclick = getAllUsersByHobby;

var allAddressesDiv = document.getElementById("allAddressesDiv");
var allUsersByCityDiv = document.getElementById("allUsersByCityDiv");
var allUsersByHobbyDiv = document.getElementById("allUsersByHobbyDiv");
var allHobiesDiv = document.getElementById("allHobbiesDiv");


function getAllAddresses() {

    //Remove all items from list
    document.getElementById("addressesDataList").innerHTML = "";

    var streetLabel = '<label style="margin-right: 8px;">' + "Street: " + '</label>';
    var zipLabel = '<label style="margin-right: 8px;">' + "Zip: " + '</label>';
    var cityLabel = '<label style="margin-right: 8px;">' + "City: " + '</label>';
    var floorLabel = '<label style="margin-right: 8px;">' + "Floor: " + '</label>';
    var numberLabel = '<label style="margin-right: 8px;">' + "No: " + '</label>';

    allAddressesDiv.style.display = "block";

    $.getJSON('http://localhost:8084/DatCA2/API/Data/addresses', function (data) {


        var dataObject = data;
        var listItemString = $('#addressesListItem').html();
        dataObject.forEach(buildNewList);

        function buildNewList(item) {
            var listItem = $('<li class="list-group-item">' + listItemString + '</li>');

            var listItemTitle = $('.listAddressesZip', listItem);
            listItemTitle.html(zipLabel + item.zipCode);

            var listItemTitle = $('.listAddressesCity', listItem);
            listItemTitle.html(cityLabel + item.city);

            var listItemTitle = $('.listAddressesStreet', listItem);
            listItemTitle.html(streetLabel + item.street);

            var listItemTitle = $('.listAddressesNumber', listItem);
            listItemTitle.html(numberLabel + item.number);

            var listItemTitle = $('.listAddressesFloor', listItem);
            listItemTitle.html(floorLabel + item.floor);

            $('#addressesDataList').append(listItem);
        }
    });
}
;


function getAllUsersByCity() {

    //Remove all items from list
    document.getElementById("allUsersByCityDataList").innerHTML = "";

    var theStreetInput = document.getElementById("cityNameInput").value;
    allUsersByCityDiv.style.display = "block";

    var IdLabel = '<label style="margin-right: 8px;">' + "ID: " + '</label>';
    var firstNameLabel = '<label style="margin-right: 8px;">' + "Firstname: " + '</label>';
    var lastNameLabel = '<label style="margin-right: 8px;">' + "Lastname: " + '</label>';
    var EmailLabel = '<label style="margin-right: 8px;">' + "Email: " + '</label>';

    $.getJSON('http://localhost:8084/DatCA2/API/Data/persons/whoLivesIn/' + theStreetInput, function (data) {
        var dataObject = data;
        var listItemString = $('#allUsersByCityListItem').html();
        dataObject.forEach(buildNewList);

        function buildNewList(item) {
            var listItem = $('<li class="list-group-item">' + listItemString + '</li>');

            var listItemTitle = $('.listAllUsersByCityId', listItem);
            listItemTitle.html(IdLabel + item.id);
            var listItemTitle = $('.listAllUsersByCityFirstName', listItem);
            listItemTitle.html(firstNameLabel + item.firstName);
            var listItemTitle = $('.listAllUsersByCityLastName', listItem);
            listItemTitle.html(lastNameLabel + item.lastName);
            var listItemTitle = $('.listAllUsersByCityEmail', listItem);
            listItemTitle.html(EmailLabel + item.email);

            $('#allUsersByCityDataList').append(listItem);
        }
    });
}
;


function getAllHobbies() {

    //Remove all items from list
    document.getElementById("allHobbiesDataList").innerHTML = "";

    var hobbiesName = '<label style="margin-right: 8px;">' + "Hobby: " + '</label>';

    allHobiesDiv.style.display = "block";

    $.getJSON('http://localhost:8084/DatCA2/API/Data/hobbies/', function (data) {


        var dataObject = data;
        var listItemString = $('#allHobbiesListItem').html();
        dataObject.forEach(buildNewList);

        function buildNewList(item) {
            var listItem = $('<li class="list-group-item">' + listItemString + '</li>');

            var listItemTitle = $('.listAllHobbiesName', listItem);
            listItemTitle.html(hobbiesName + item.name);

            $('#allHobbiesDataList').append(listItem);
        }
    });
}
;


function getAllUsersByHobby() {
    //Remove all items from list
    document.getElementById("allUsersByHobbyDataList").innerHTML = "";

    var theStreetInput = document.getElementById("hobbyNameInput").value;
    allUsersByHobbyDiv.style.display = "block";

    var IdLabel = '<label style="margin-right: 8px;">' + "ID: " + '</label>';
    var firstNameLabel = '<label style="margin-right: 8px;">' + "Firstname: " + '</label>';
    var lastNameLabel = '<label style="margin-right: 8px;">' + "Lastname: " + '</label>';
    var EmailLabel = '<label style="margin-right: 8px;">' + "Email: " + '</label>';

    $.getJSON('http://localhost:8084/DatCA2/API/Data/hobbies/' + theStreetInput, function (data) {
        var dataObject = data;
        var listItemString = $('#allUsersByHobbyListItem').html();
        dataObject.forEach(buildNewList);


        function buildNewList(item) {
            var listItem = $('<li class="list-group-item">' + listItemString + '</li>');

            var listItemTitle = $('.listAllUsersByHobbyId', listItem);
            listItemTitle.html(IdLabel + item.id);
            var listItemTitle = $('.listAllUsersByHobbyFirstName', listItem);
            listItemTitle.html(firstNameLabel + item.firstName);
            var listItemTitle = $('.listAllUsersByHobbyLastName', listItem);
            listItemTitle.html(lastNameLabel + item.lastName);
            var listItemTitle = $('.listAllUsersByHobbyEmail', listItem);
            listItemTitle.html(EmailLabel + item.email);

            $('#allUsersByHobbyDataList').append(listItem);
        }
    });
}
;