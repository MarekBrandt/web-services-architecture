import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayWard();
    fetchAndDisplayPatients();
});

function fetchAndDisplayPatients() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayPatients(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/wards/' + getParameterByName('ward') + '/patients', true);
    xhttp.send();
}

function displayPatients(patients) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    patients.patients.forEach(patient => {
        tableBody.appendChild(createTableRow(patient));
    })
    tableBody.appendChild(createLinkCell('add patient', '../patient_add/patient_add.html?ward=' + getParameterByName('ward')));
}

function createTableRow(patient) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(patient.firstName + " " + patient.lastName));
    tr.appendChild(createLinkCell('view', '../patient_view/patient_view.html?ward='
        + getParameterByName('ward') + '&patient=' + patient.pesel))
    tr.appendChild(createLinkCell('edit', '../patient_edit/patient_edit.html?ward='
        + getParameterByName('ward') + '&patient=' + patient.pesel));
    tr.appendChild(createButtonCell('delete', () => deletePatient(patient)));
    return tr;
}


function deletePatient(patient) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayPatients();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/wards/' + getParameterByName('ward')
        + '/patients/' + patient.pesel, true);
    xhttp.send();
}



function fetchAndDisplayWard() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayWard(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/wards/' + getParameterByName('ward'), true);
    xhttp.send();
}

function displayWard(ward) {
    setTextNode('name', ward.name);
    setTextNode('id', ward.id);
    setTextNode('area', ward.areaInSquareMeters);
    setTextNode('beds', ward.numberOfBeds);
}
