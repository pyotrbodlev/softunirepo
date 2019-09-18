const mongoose = require('mongoose')
const connectionString = 'mongodb://127.0.0.1:27017/memes'
const Meme = require('../models/memeModel')

mongoose.connect(connectionString)

function saveMeme(title, gender, description) {
  const meme = new Meme({ title, gender, description, creationDate: Date.now()})
  meme.save()
}

function getMemeById(id) {
  return Meme.findById(id);
}

function getAll(){
  return Meme.find()
}

module.exports = {
  saveMeme, getMemeById, getAll
}