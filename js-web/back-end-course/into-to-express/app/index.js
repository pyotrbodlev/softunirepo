const express = require('express');
const { a } = require('./requester');

const app = express();
const port = 8000;

app.get('/', (req, res) => res.send('Hello from http2'));

app.listen(port, () => {
    console.log('Server listening on port ' + port);
})
