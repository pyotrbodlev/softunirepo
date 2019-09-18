const fs = require('fs')
const db = require('../config/dataBase')
const url = require('url')
const qs = require('querystring')
const shortid = require('shortid')
const formidable = require('formidable')

const express = require('express')
const router = express.Router()

let viewAll = async (req, res) => {
  let data = await db.getAll()

  console.log(data)

  let responseString = ''
  for (let meme of data) {
    responseString += `<div class="meme">
    <a href="/getDetails?id=${meme.id}">
    <a class="memeTitle">${meme.title}" </a>          
    </div>`
  }

  const filePath = '/home/peter/Documents/GitHub/softunirepo/JSWeb/NodeJS/MockSkeleton/views/viewAll.html';

  res.sendFile(filePath);
}

let viewAddMeme = (req, res, status = null) => {
  fs.readFile('./views/addMeme.html', (err, data) => {
    if (err) {
      console.log(err)
      return
    }

    //    let genres = tagDb.getDb()
    let exitString = ''

    console.log(exitString)

    // for (let genre of genres) {
    //   exitString += `<option value="${genre.title}">${genre.title}</option>`
    // }

    if (status === 'err') {
      data = data
        .toString()
        .replace(
          '<div id="replaceMe">{{replaceMe}}</div>',
          '<div id="errBox"><h2 id="errMsg">Please fill all fields</h2></div>'
        )
    }
    if (status === 'suc') {
      data = data
        .toString()
        .replace(
          '<div id="replaceMe">{{replaceMe}}</div>',
          '<div id="succssesBox"><h2 id="succssesMsg">Movie Added</h2></div>'
        )
    }
    res.send(data
      .toString()
      .replace('<div id="replaceMe">{{replaceMe2}}</div>', exitString))
  })
}

let getDetails = (req, res) => {
  let targetId = qs.parse(url.parse(req.url).query).id

  let targetedMeme = db.getDb().find(target => {
    return target.id === targetId
  })

  let replaceString = `<div class="content">
  <img src="${targetedMeme.memeSrc}" alt=""/>
  <h3>Title  ${targetedMeme.title}</h3>
  <p> ${targetedMeme.description}</p>
  <button><a href="${targetedMeme.posterSrc}" download="${targetedMeme.title}.jpg" >Download Meme</a></button>
  </div>`

  fs.readFile('./views/details.html', (err, data) => {
    if (err) {
      console.log(err)
      return
    }
    data = data
      .toString()
      .replace('<div id="replaceMe">{{replaceMe}}</div>', replaceString)
    defaultResponse(data, res)
  })
}

let addMeme = (req, res) => {
  let form = new formidable.IncomingForm()

  form.parse(req, (err, fields, files) => {
    console.log(fields)
    if (err) {
      console.log(err)
      return
    }
    if (fieldChecker(fields)) {
      viewAddMeme(req, res, 'err')
    } else {
      db.saveMeme(fields.memeTitle, null, fields.memeDescription)

      viewAddMeme(req, res, 'suc')
    }
  })
}

let getSearchMeme = (req, res) => {
  fs.readFile('./views/searchMeme.html', (err, data) => {
    if (err) {
      console.log(err)
      return
    }
    let genres = tagDb.getDb()
    let exitString = '<option value="all">all</option>'

    for (let genre of genres) {
      exitString += `<option value="${genre.title}">${genre.title}</option>`
    }

    data = data
      .toString()
      .replace('<div id="replaceMe">{{replaceMe}}</div>', exitString)
    defaultResponse(data, res)
  })
}

let searchForMeme = (req, res) => {
  let data = db.getDb()
  let tags = tagDb.getDb()
  let params = req.querypath
  let title = params.memeTitle
  let selectedGenre = params.genreSelect

  let sorted = []

  if (selectedGenre !== 'all') {
    let demo = tags.find(x => {
      return x.id == selectedGenre
    })
    let arrSelection = demo.memeArr

    for (let meme of data) {
      if (arrSelection.indexOf(meme.id) !== -1) {
        sorted.push(meme)
      }
    }
  } else {
    sorted = data
  }

  if (title !== '') {
    sorted = sorted.filter(elem => {
      if (elem.title.indexOf(title) !== -1) {
        return elem
      }
    })
  }
  sorted = sorted
    .sort((a, b) => {
      return b.dateStamp - a.dateStamp
    })
    .filter(meme => {
      return meme.privacy === 'on'
    })

  let responseString = ''
  for (let meme of sorted) {
    responseString += `<div class="meme">
  <a href="/getDetails?id=${meme.id}">
  <img class="memePoster" src="${meme.memeSrc}"/>          
  </div>`
  }

  fs.readFile('./views/viewAll.html', (err, html) => {
    if (err) {
      console.log(err)
      return
    }
    const express = require('express')
    const router = express.Router()
    html = html
      .toString()
      .replace('<div id="replaceMe">{{replaceMe}}</div>', responseString)

    defaultResponse(html, res)
  })
}

let createGenreView = (req, res) => {
  fs.readFile('./views/addGenre.html', (err, data) => {
    if (err) {
      console.log(err)
    }
    defaultResponse(data, res)
  })
}


router.get('/viewAllMemes', viewAll)
router.get('/addMeme', viewAddMeme)
router.post('/addMeme', addMeme)
router.get('/getDetails', getDetails)
router.get('/searchMeme', getSearchMeme)
router.get('/sSearchMeme', searchForMeme)
router.get('/addGenre', createGenreView)
router.get('/aaddGenre', createGenreView)

module.exports = router
