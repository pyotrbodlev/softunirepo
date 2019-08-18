const elements = {
    btnSubmit: document.getElementById('submit'),
    btnLoadAll: document.getElementById('loadBooks'),
    tableRowTemplate: document.querySelector('tbody tr'),
    titleElement: document.getElementById('title'),
    authorElement: document.getElementById('author'),
    isbnElement: document.getElementById('isbn'),
    tableBody: document.getElementsByTagName('tbody')[0],
}

const url = 'https://baas.kinvey.com/appdata/kid_ByGVH9I4S/books';

const headers = {
    "Content-Type": 'application/json',
    "Authorization": 'Basic cm9vdDpwYXNzd29yZA=='
}

const clearInputFields = function () {
    elements.titleElement.value = '';
    elements.authorElement.value = '';
    elements.isbnElement.value = '';
}

const handleDeleteEvent = async function () {
    const bookId = this.parentNode.parentNode.getAttribute('id');
    const resp = await fetch(`${url}/${bookId}`, {
        method: 'delete',
        headers,
    });

    this.parentNode.parentNode.remove();
}

const handleConfirmEvent = async function () {
    //location.reload();
}

const handleUpdateEvent = function () {
    const row = this.parentNode.parentNode;
    row.getElementsByTagName('td')[0].innerHTML = `<input type="text" id="editedTitle" value="${row.getElementsByTagName('td')[0].textContent}"/>`;
    row.getElementsByTagName('td')[1].innerHTML = `<input type="text" value="${row.getElementsByTagName('td')[1].textContent}"/>`;
    row.getElementsByTagName('td')[2].innerHTML = `<input type="text" value="${row.getElementsByTagName('td')[2].textContent}"/>`;
    this.textContent = 'Confirm';
    this.addEventListener('click', handleConfirmEvent);
}

const handleSubmitEvent = async function () {
    const author = elements.authorElement.value;
    const title = elements.titleElement.value;
    const isbn = elements.isbnElement.value;

    const book = {
        author,
        title,
        isbn
    };

    const resp = await fetch('https://baas.kinvey.com/appdata/kid_ByGVH9I4S/books', {
        method: 'post',
        headers,
        body: JSON.stringify(book),
    });

    clearInputFields();
    location.reload();
}

const handleLoadEvent = async function () {
    elements.tableBody.innerHTML = '';

    const resp = await fetch('https://baas.kinvey.com/appdata/kid_ByGVH9I4S/books', {
        headers
    });
    const data = await resp.json();

    for (const index in data) {
        const book = data[index];
        let row = elements.tableRowTemplate.cloneNode(true);
        row.id = book._id;
        row.getElementsByTagName('td')[0].textContent = book.title;
        row.getElementsByTagName('td')[1].textContent = book.author;
        row.getElementsByTagName('td')[2].textContent = book.isbn;
        row.getElementsByTagName('td')[3].getElementsByTagName('button')[0].addEventListener('click', handleUpdateEvent);
        row.getElementsByTagName('td')[3].getElementsByTagName('button')[1].addEventListener('click', handleDeleteEvent);
        elements.tableBody.appendChild(row);
    }
}

elements.btnSubmit.addEventListener('click', handleSubmitEvent);
window.onload = handleLoadEvent;