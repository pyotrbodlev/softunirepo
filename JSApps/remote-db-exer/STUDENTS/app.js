const url = 'https://baas.kinvey.com/appdata/kid_ByGVH9I4S/students';

const headers = {
    "Content-Type": 'application/json',
    "Authorization": 'Basic cm9vdDpwYXNzd29yZA=='
}

const createTableRowContednt = function (element, content) {
    let el = document.createElement(element);
    el.textContent = content;

    return el;
}

const createColumns = function (data) {
    const bodyElement = document.getElementsByTagName('tbody')[0];
    for (const key in data) {
        console.log(data[key].fisrtName);

        const tr = document.createElement('tr');

        const idRow = document.createElement('td');
        idRow.textContent = +key + 1;
        tr.appendChild(idRow);

        const firstNameRow = createTableRowContednt('td', data[key].fisrtName)
        tr.appendChild(firstNameRow);

        const lastNameRow = createTableRowContednt('td', data[key].lastName);
        tr.appendChild(lastNameRow);

        const FNRow = createTableRowContednt('td', data[key].facultyNumber);
        tr.appendChild(FNRow);

        const gradeRow = createTableRowContednt('td', data[key].grade);
        tr.appendChild(gradeRow);

        bodyElement.appendChild(tr);
    }
}

const resolve = async function () {
    const resp = await fetch(url, {
        headers
    });
    const data = await resp.json();

    createColumns(data);
}

resolve();