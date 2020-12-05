const http = require('http')

const data = JSON.stringify({
	"email": "clement.stauner@gmail.com",
	"password": "Computer0987"
})

// const options = {
//     hostname: 'www.google.com'
// }

const options = {
    hostname: 'node-thinkit.herokuapp.com',
    path: '/users/login',
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    }
}

// const options = {
//     hostname: 'localhost',
//     port: 80,
//     path: '/users/login',
//     method: 'POST',
//     headers: {
//       'Content-Type': 'application/json'
//     }
// }

const req = http.request(options, res => {
    console.log(`statusCode: ${res.statusCode}`)

    res.on('data', d => {
        process.stdout.write(d)
    })
})

req.on('error', error => {
    console.error(error)
})

req.write(data)
req.end()