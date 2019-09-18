const express = require('express')
const router = express.Router()

const filePath = '/home/peter/Documents/GitHub/softunirepo/JSWeb/NodeJS/MockSkeleton/views/home.html'

router.get('/', (req, res) => {
  res.sendFile(filePath)
})

module.exports = router;