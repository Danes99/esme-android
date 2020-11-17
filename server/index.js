// Require downloaded modules
const chalk = require('chalk')

// Server Variables
const port = process.env.PORT

// Import Server
const app =  require('./src/app')

app.listen(
    port,
    () => console.log(`Server is up on ${chalk.cyan.bold(port)}`)
)