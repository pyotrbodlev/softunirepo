const express = require('express');
const CubeModel = require('../models/cubeModel');

/**
 * @param {express.Request} req
 * @param {express.Response} res
 * @public
 */
const handleHomePage = (req, res) => {
    const { s, f, t } = req.query;
    const query = {};

    if (s) {
        query.name = { $regex : new RegExp(s, "i") };
    }

    if (f) {
        if (!query.difficultyLevel) {
            query.difficultyLevel = {};
        }
        query.difficultyLevel.$gt = f;
    }

    if (t) {
        if (!query.difficultyLevel) {
            query.difficultyLevel = {};
        }
        query.difficultyLevel.$lt = t;
    }
    
    CubeModel.find(query).lean().then(cubes => {
        res.render(__projectname + '/views/index.hbs', { cubes });
    }).catch(console.error);
};

/**
 * @param {express.Request} req
 * @param {express.Response} res
 * @public
 */
const handleAboutPage = (req, res) => {
    res.render(__projectname + '/views/about.hbs');
}

module.exports = { handleHomePage, handleAboutPage };