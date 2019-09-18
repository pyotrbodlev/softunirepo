const mongoose = require('mongoose')

const memeSchema = new mongoose.Schema({
    title: {type: String, required: true},
    creationDate: Date,
    description: String,
    gender: String,
})

const Image = mongoose.model('Meme', memeSchema)

module.exports = Image