function attachEvents() {
    const loadBtn = document.querySelector('.load');
    loadBtn.addEventListener('click', handleLoadEvent);

    const createBtn = document.querySelector('.add');
    createBtn.addEventListener('click', handleCreateEvent);
};

function handleCreateEvent() {
    const fieldSet = document.getElementById('addForm');

    const angler = fieldSet.getElementsByClassName('angler')[0].value;
    const weight = fieldSet.getElementsByClassName('weight')[0].value;
    const species = fieldSet.getElementsByClassName('species')[0].value;
    const location = fieldSet.getElementsByClassName('location')[0].value;
    const bait = fieldSet.getElementsByClassName('bait')[0].value;
    const captureTime = fieldSet.getElementsByClassName('captureTime')[0].value;

    const item = {
        angler,
        weight,
        species,
        location,
        bait,
        captureTime
    };

    persist(item);
}

function handleLoadEvent() {
    const allUrl = 'https://fisher-game.firebaseio.com/catches.json';
    document.getElementById('catches').innerHTML = '';

    fetch(allUrl).then(handleRequest).then(loadAllCatches);
}

function loadAllCatches(data) {
    const catches = document.getElementById('catches');
    for (const key in data) {
        const value = data[key];

        const element = createElement(key, value);
        catches.appendChild(element);
    }
}

function createElement(key, value) {
    const div = document.createElement('div');
    div.classList.add('catch');
    div.setAttribute('data-id', key);

    const anglerLable = document.createElement('label');
    anglerLable.textContent = "Angler";
    div.appendChild(anglerLable);

    const anglerInput = document.createElement('input');
    anglerInput.type = 'text';
    anglerInput.value = value.angler;
    anglerInput.className = 'angler';
    div.appendChild(anglerInput);

    div.appendChild(document.createElement('hr'));

    const locationLable = document.createElement('label');
    locationLable.textContent = "Location";
    div.appendChild(locationLable);

    const locationInput = document.createElement('input');
    locationInput.type = 'text';
    locationInput.value = value.location;
    locationInput.className = 'location';
    div.appendChild(locationInput);
    div.appendChild(document.createElement('hr'));

    const updateBtn = document.createElement('button');
    const deleteBtn = document.createElement('button');
    updateBtn.className = 'update';
    deleteBtn.className = 'delete';
    updateBtn.textContent = 'Update';
    deleteBtn.textContent = 'Delete';

    deleteBtn.addEventListener('click', handleDeleteEvent);

    div.appendChild(updateBtn);
    div.appendChild(deleteBtn);
    return div;
}

function handleDeleteEvent() {
    const id = this.parentNode.getAttribute('data-id');

    const url = 'https://fisher-game.firebaseio.com/catches/';
    const deleteUrl = url + id + '.json';

    fetch(deleteUrl, {
        method: 'delete'
    })
}

function handleRequest(req) {
    return req.json();
}

function persist(item) {
    const postUrl = 'https://fisher-game.firebaseio.com/catches.json';

    fetch(postUrl, {
        method: 'post',
        body: JSON.stringify(item)
    });
}

attachEvents();