import  {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayWards();
});

function fetchAndDisplayWards() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayWards(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/wards', true);
    xhttp.send();
}

function displayWards(wards) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    wards.wards.forEach(ward => {
        tableBody.appendChild(createTableRow(ward));
    })
    tableBody.appendChild(createLinkCell('add new ward', '../ward_add/ward_add.html'));
}

function createTableRow(ward) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(ward.name));
    tr.appendChild(createLinkCell('view', '../ward_view/ward_view.html?ward=' + ward.id));
    tr.appendChild(createLinkCell('edit', '../ward_edit/ward_edit.html?ward=' + ward.id));
    tr.appendChild(createButtonCell('delete', () => deleteWard(ward)));
    return tr;
}

function deleteWard(ward) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayWards();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/wards/' + ward.id, true);
    xhttp.send();
}




