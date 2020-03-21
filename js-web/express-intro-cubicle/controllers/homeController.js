const express = require('express');
const CubeModel = require('../models/cubeModel');

/**
 * Check if the given `lang`s are acceptable,
 * otherwise you should respond with 406 "Not Acceptable".
 *
 * @param {express.Request} req
 * @param {express.Response} res
 * @public
 */
const handleHomePage = (req, res) => {
    CubeModel.find().lean().then(cubes => {
        res.render(__projectname + '/views/index.hbs', { cubes });
    }).catch(console.error);
};

/**
 * Check if the given `lang`s are acceptable,
 * otherwise you should respond with 406 "Not Acceptable".
 *
 * @param {express.Request} req
 * @param {express.Response} res
 * @public
 */
const handleAboutPage = (req, res) => {
    res.render(__projectname + '/views/about.hbs');
}

module.exports = { handleHomePage, handleAboutPage };