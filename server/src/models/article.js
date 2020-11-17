// Require downloaded modules
const mongoose = require('mongoose')

// Article Schema
const schemaArticle = new mongoose.Schema(
    {
        title: { 
            type: String,
            required: true,
            trim: true
        },
        content: { 
            type: String,
            required: true,
            trim: true
        },
        completed: { 
            type: Boolean,
            default: false
        },
        owner: {
            type: mongoose.Schema.Types.ObjectId,
            required: true,
            ref: 'User'
        }
    },
    {
        timestamps: true
    }
)

// Article database model
const Article = mongoose.model(
    'Article',
    schemaArticle
)

module.exports = Article