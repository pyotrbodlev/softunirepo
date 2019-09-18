const mongoose = require('mongoose')

const tagShema = new mongoose.Schema({
    name: String,
    creationDate: Date,
    images: [],
})

const Tag = mongoose.model('Tag', tagShema)

module.exports = Tag
