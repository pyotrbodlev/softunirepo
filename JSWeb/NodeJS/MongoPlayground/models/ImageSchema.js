const mongoose = require('mongoose')

const imageSchema = new mongoose.Schema({
    url: String,
    creationDate: Date,
    description: String,
    tags: [],
})

const Image = mongoose.model('Image', imageSchema)

module.exports = Image
