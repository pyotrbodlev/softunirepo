const fs = require('fs');

let storage = {};

const put = (key, value) => {
    if (typeof key === 'string') {
        if (!storage[key]) {
            storage[key] = value;
        } else {
            throw new Error('This key already exist');
        }
    } else {
        throw new Error('Key must be a string');
    }
}

const get = (key) => {
    if (typeof key === 'string') {
        if (storage[key]) {
            return storage[key];
        } else {
            throw new Error('There is no such key in the storage');
        }
    } else {
        throw new Error('Key must be a string');
    }
}

const getAll = () => {
    return storage;
}

const update = (key, newValue) => {
    if (typeof key === 'string') {
        if (storage[key]) {
            storage[key] = newValue;
        } else {
            throw new Error('There is no existing key');
        }
    } else {
        throw new Error('Key must be a string');
    }
}

const deleteValue = (key) => {
    if (typeof key === 'string') {
        if (storage[key]) {
            //storage[key] = undefined;
            delete storage[key];
        } else {
            throw new Error('There is no existing key');
        }
    } else {
        throw new Error('Key must be a string');
    }
}

const clear = () => {
    storage = {};
}

const save = () => {
    const jsonData = JSON.stringify(getAll());

    fs.writeFile("storage.json", jsonData, function (err) {
        if (err) {
            console.log(err);
        }
    });
}

const load = () => {
    const dataString = fs.readFileSync('./storage.json');
    storage = JSON.parse(dataString);
}

module.exports = {
    put, getAll, get, update, deleteValue, clear, save, load,
}