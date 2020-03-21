const mongodb = require('mongodb');

const mongoUri = 'mongodb://localhost:27017';
const { MongoClient } = mongodb;
const client = new MongoClient(mongoUri);

client.connect(function (err) {
    if (err) { console.log(err); return; }

    const db = client.db('node-su');
    const users = db.collection('users');

    const user = {
        name: "Pesho",
        age: 23,
        courses: [
            'NodeJs',
            'AngularJS'
        ]
    };

    //users.insert(user).then(user => console.log(user)).catch(err => console.error(err));
});
