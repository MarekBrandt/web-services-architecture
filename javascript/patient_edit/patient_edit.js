import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplayPatient();
});

/**
 * Fetches currently logged user's characters and updates edit form.
 */
function fetchAndDisplayPatient() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/wards/' + getParameterByName('ward') + '/patients/'
        + getParameterByName('patient'), true);
    xhttp.send();
}

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayPatient();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/wards/' + getParameterByName('ward') + '/patients/'
        + getParameterByName('patient'), true);

    const request = {
        'firstName': document.getElementById('firstName').value,
        'lastName': document.getElementById('lastName').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}

