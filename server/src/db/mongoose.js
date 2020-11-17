// Require downloaded modules
const mongoose = require('mongoose')

// Connection to database
mongoose.connect(
    process.env.MONGODB_URL,
    {
        useUnifiedTopology: true,
        useNewUrlParser: true,
        useCreateIndex: true,
        useFindAndModify: false
    }
)

console.log(`Connection to MongoDB`)