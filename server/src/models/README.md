# MongoDB relational model
This the relational model for mongoDB (mongoose).

# User
```JSON
{
    "_id": "5fb3b8519ab7de12c50adbc3",
    "name": "Clement",
    "age": 0,
    "email": "clement.stauner@gmail.com",
    "password": "$2a$12$v1rdqb69kNe74438p.6eLehrh9hm41xOvr7tzfWyWfCY8sfaOzHGS",
    "tokens": [],
    "createdAt": "2020-11-17T11:47:29.647Z",
    "updatedAt": "2020-11-23T08:39:52.053Z",
    "__v": 6
}
```

- _id: 16-bits integer
- age: integer
- name: string
- email: string
- password: string
- tokens: array of string (JSON Web Token)
- createdAt: UTC Date
- updatedAt: UTC Date
- __v: integer (variable specific to MongoDB)

# Article
```JSON
{
    "completed": false,
    "isFavorite": false,
    "_id": "5fb3b9902aae9a1eab9afa1a",
    "title": "New York Times",
    "content": "This is some awesome content!",
    "owner": "5fb3b8519ab7de12c50adbc3",
    "createdAt": "2020-11-17T11:52:48.421Z",
    "updatedAt": "2020-11-23T08:40:45.173Z",
    "__v": 0
}
```
- completed: boolean
- isFavorite: boolean
- _id: 16-bits integer
- title: string
- content: string
- owner: 16-bits integer (foreign key)
- createdAt: UTC Date
- updatedAt: UTC Date
- __v: integer (variable specific to MongoDB)