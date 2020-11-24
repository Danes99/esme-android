// CRUD create update delete

// const mongodb = require('mongodb')

// const MongoClient = mongodb.MongoClient
// const ObjectID = mongodb.ObjectID

const { MongoClient, ObjectID } = require('mongodb')

const connectionURL = 'mongodb://127.0.01:27017'
const databaseName = 'task-manager'

MongoClient.connect(
    connectionURL,
    { useUnifiedTopology: true },
    (error, client) => {
        // { useUnifiedTopology: true }
        // { useNewUrlParser: true }
        if (error) {
            return console.log('Unable to connect to database!')
        }

        const db = client.db(databaseName)

        db.collection('User').deleteMany(
            { age: 27 }
        ).then(
            result => { console.log(result) }
        ).catch(
            error => { console.log(error) }
        )
    }
)