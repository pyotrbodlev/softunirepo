module.exports = {
    development: {
        port: process.env.PORT || 8000,
        mongodbUri: 'mongodb://localhost:27017'
    },
    production: {}
};