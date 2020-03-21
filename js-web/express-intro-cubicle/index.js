
global.__projectname = __dirname;

const env = process.env.NODE_ENV || 'development';
const config = require('./config/config')[env];

const mongoose = require('mongoose');

mongoose.connect(config.mongodbUri).then(() => {
    const app = require('express')();
    require('./config/express')(app);
    require('./config/routes')(app);

    app.listen(config.port, console.log(`Listening on port ${config.port}! Now its up to you...`));
}).catch(console.error);