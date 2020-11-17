// Require downloaded modules
const chalk = require('chalk')
const mongoose = require('mongoose')

// Database url
const url = process.env.MONGODB_URL + '/' + process.env.MONGODB_DB_NAME

// Connection to database
mongoose.connect(
    url,
    {
        useUnifiedTopology: true,
        useNewUrlParser: true,
        useCreateIndex: true,
        useFindAndModify: false
    }
)

console.log(`Connection to ${chalk.green.bold(process.env.MONGODB_DB_NAME)} MongoDB @ ${chalk.yellow.italic(url)}`)