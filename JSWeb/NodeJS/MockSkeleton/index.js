const express = require('express')
const app = express()
const homeRouter = require('./handlers/homeRouter')
const memeRouter = require('./handlers/memeRouter')
const port = 8080

app.use(express.static('public'))
app.use('/public', express.static('public'))
app.use('/public', express.static(__dirname + '/public'))
app.use('/', homeRouter)
app.use('/', memeRouter)

app.listen(port, () => {
  console.log('Im listening on ' + port)
})