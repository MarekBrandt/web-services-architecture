import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => updateInfoAction(event));
});

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/wards/' + new URLSearchParams(window.location.search).get('ward') + '/patients/', true);

    const request = {
        'firstName': document.getElementById('firstName').value,
        'lastName': document.getElementById('lastName').value,
        'age': parseInt(document.getElementById('age').value),
        'pesel': document.getElementById('pesel').value,
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');;

    console .log(xhttp);

    xhttp.send(JSON.stringify(request));
}

