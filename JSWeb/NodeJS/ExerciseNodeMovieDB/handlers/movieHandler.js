const movies = require('../config/dataBase');
const fs = require('fs');

const resp = (req, res) => {
    fs.readFile('./views/viewAll.html', function (err, data) {
        res.writeHead(200, { 'Content-Type': 'text/html' });
        res.write(data);
        res.end();
    });
};

module.exports = resp;