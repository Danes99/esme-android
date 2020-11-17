// Require downloaded modules
const express = require('express')

const Article = require('../models/article')
const auth = require('../middleware/auth')

const router = new express.Router()

// Read every articles
router.get(
    '/',
    auth,
    async (req, res) => {

        // Search options
        // https://mongoosejs.com/docs/api.html#query_Query-setOptions
        const limit = isNaN(req.query.limit) ? null : parseInt(req.query.limit)
        const skip = isNaN(req.query.skip) ? null : parseInt(req.query.skip)
        const sort = {}
        const match = {}

        if (req.query.match) {
            const parts1 = req.query.match.split(',').map(
                e => {
                    const query = e.split('=')
                    match[query[0]] = query[1]
                }
            )
        }

        if (req.query.sortBy) {
            const parts2 = req.query.sortBy.split(':')
            sort[parts2[0]] = parts2[1] === 'desc' ? -1 : 1
        }

        try {
            // https://mongoosejs.com/docs/api.html#model_Model.find
            const articles = await Article.find({ 
                owner: req.user._id, ...match
            })
            .limit(limit)
            .skip(skip)
            .sort(sort)

            if (!articles) {
                return res.status(404).send([])
            }

            return res.send(articles)
        }
        catch (error) {
            res.status(500).send(error)
        }
    }
)

// Read article
router.get(
    '/:id',
    auth,
    async (req, res) => {
        try {
            // article = await Article.findById(_id)
            const article = await Article.findOne(
                {
                    _id: req.params.id,
                    owner: req.user._id
                }
            )

            if (!article) {
                return res.status(404).send()
            }

            res.status(201).send(article)
        }
        catch (error) {
            res.status(500).send()
        }
    }
)

// Create article
router.post(
    '/',
    auth,
    async (req, res) => {
        const article = new Article(
            {
                ...req.body,
                owner: req.user._id
            }
        )

        try {
            await article.save()
            res.status(201).send(article)
        }
        catch (error) {
            res.status(400).send(error)
        }
    }
)

// Update article
router.patch(
    '/:id',
    auth,
    async (req, res) => {
        const updates = Object.keys(req.body)
        const allowedUpdates = ['completed', 'content', 'title']

        const isValidOperation = updates.every(
            update => allowedUpdates.includes(update)
        )

        if (!isValidOperation) {
            return res.status(400).send({ error: 'Invalid updates!' })
        }

        try {
            // const article = await article.findById(req.params.id)

            const article = await Article.findOne(
                {
                    _id: req.params.id,
                    owner: req.user._id
                }
            )

            if (!article) {
                return res.status(404).send()
            }

            updates.forEach(update => article[update] = req.body[update])
            await article.save()

            res.send(article)
        }
        catch (error) {
            res.status(500).send(error)
        }
    }
)

// Delete article
router.delete(
    '/:id',
    auth,
    async (req, res) => {
        try {
            const article = await Article.findOneAndDelete(
                {
                    _id: req.params.id,
                    owner: req.user._id
                }
            )

            if (!article) {
                return res.status(404).send()
            }

            res.status(201).send(article)
        }
        catch (error) {
            res.status(500).send()
        }
    }
)

module.exports = router