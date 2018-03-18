
document.getElementById("createUser").onclick = createUser;
document.getElementById("addHobbyButton").onclick = addHobbyToArray;
document.getElementById("addPhoneNumber").onclick = addPhoneNumber;

var hobbyIdArray = [];
var phoneNumberArray = [];

function addHobbyToArray() {
    var q = parseInt(document.getElementById("hobbySelector").value);
    if (hobbyIdArray.includes(q)) {
        return null; //Cant add the same hobby twice
    } else {
        hobbyIdArray.push(q);
        console.log(hobbyIdArray);
    }
}
function addPhoneNumber() {
    var q = parseInt(document.getElementById("phoneNumberInput").value);
    phoneNumberArray.push(q);
    console.log(phoneNumberArray);
    document.getElementById("phoneNumberInput").value = "";

}

function createUser() {

    var firstNameInput = document.getElementById("firstNameInput").value;
    var lastNameInput = document.getElementById("lastNameInput").value;
    var emailInput = document.getElementById("emailInput").value;
    var streetInput = document.getElementById("streetNameInput").value;
    var streetNumberInput = document.getElementById("streetNumberInput").value;
    var streetFloorInput = document.getElementById("floorNumberInput").value;
    var zipCodeInput = document.getElementById("zipCodeInput").value;
    var cityInput = document.getElementById("cityNameInput").value;

    console.log("Creating user");

    var userInfo = {
        firstName: firstNameInput,
        lastName: lastNameInput,
        email: emailInput,
        hobbyIds: hobbyIdArray,
        phoneNumbers: phoneNumberArray,
        address: {street: streetInput, number: parseInt(streetNumberInput), floor: parseInt(streetFloorInput), zipCode: {zipCode: parseInt(zipCodeInput), city: cityInput}}
    };


    //Post javascript as json
    var settings = {
        body: JSON.stringify(userInfo), // must match 'Content-Type' header
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        method: 'POST', // *GET, PUT, DELETE, etc.
    };

    console.log(settings);

    fetch("http://localhost:8084/DatCA2/API/Data/persons/", settings)
            .then(res => res.json())
            .then(data => window.alert("User added.")
            );
}
;
