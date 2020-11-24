// Require downloaded modules
const express = require('express')

// Require routers
const userRouter = require('./routers/user')
const routerArticle = require('./routers/article')

// Start Database connection
require('./db/mongoose')

const app = express()

// Define express config
app.use(express.json())

// Use routers
app.use('/article', routerArticle)
app.use(userRouter)

module.exports = app