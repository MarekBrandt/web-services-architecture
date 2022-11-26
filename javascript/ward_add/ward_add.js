import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => updateInfoAction(event));
});

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/wards/', true);

    const request = {
        'name': document.getElementById('name').value,
        'areaInSquareMeters': parseFloat(document.getElementById('area').value),
        'numberOfBeds': parseInt(document.getElementById('beds').value),
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');;

    xhttp.send(JSON.stringify(request));
}

