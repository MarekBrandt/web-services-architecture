import {getParameterByName, setTextNode} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {

    fetchAndDisplayPatient();
});


function fetchAndDisplayPatient() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            setTextNode('name', response.firstName + " " + response.lastName);
            setTextNode('pesel', response.pesel);
            setTextNode('age', response.age);
            setTextNode('ward', response.ward);
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/wards/' + getParameterByName('ward') + '/patients/'
        + getParameterByName('patient'), true);
    xhttp.send();
}

