const http = require('http');
const fs = require('fs');
const url = require('url');
const movieHandler = require('./handlers/movieHandler');

const port = 8080;

http.createServer(frontController).listen(port);

/**
 * 
 * @param {http.ClientRequest} req 
 * @param {http.ServerResponse} res 
 */
function frontController(req, res) {
    const urlController = url.parse(req.url);
    const path = urlController.path;

    if (path == '/') {
        fs.readFile('./views/home.html', function (err, data) {
            res.writeHead(200, { 'Content-Type': 'text/html' });
            res.write(data);
            res.end();
        });
    } else if (path.startsWith('/public/css')) {
        fs.readFile('./public/css/main.css', function (err, data) {
            res.writeHead(200, { 'Content-Type': 'text/css' });
            res.write(data);
            res.end();
        });
    } else if (path.startsWith('/public/images')) {
        fs.readFile('./public/images/nodeLogo.png', function (err, data) {
            res.writeHead(200, { 'Content-Type': 'image/png' });
            res.write(data);
            res.end();
        });
    } else if (path == '/addMovie' && req.method == 'GET') {
        fs.readFile('./views/addMovie.html', function (err, data) {
            res.writeHead(200, { 'Content-Type': 'text/html' });
            res.write(data);
            res.end();
        });
    } else if (path == '/addMovie' && req.method == 'POST') {
        fs.readFile('./views/home.html', function (err, data) {
            res.writeHead(200, { 'Content-Type': 'text/html' });
            res.write(data);
            res.end();
        });
    } else if (path == '/viewAllMovies' && req.method == 'GET'){
        movieHandler(req, res);
    }
}

console.log(`Server started at port: ${port}`);