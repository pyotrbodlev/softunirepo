const storage = require('./storage');

storage.load();
const all = storage.getAll();

console.log(all);