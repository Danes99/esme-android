// Require downloaded modules
const express = require('express')
const bodyParser = require("body-parser")

// Require routers
const userRouter = require('./routers/user')
const routerArticle = require('./routers/article')

// Start Database connection
require('./db/mongoose')

const app = express()

// Define express config
app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())

// Use routers
app.use('/article', routerArticle)
app.use(userRouter)

module.exports = app