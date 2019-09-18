const mongoose = require('mongoose');

const connectionString = 'mongodb://127.0.0.1:27017/test_db';
mongoose.connect(connectionString);

//module.exports = mongoose.connect(connectionString)
