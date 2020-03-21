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

const handleDetailsPage = (req, res) => {
    const cubicId = req.params.id;
    CubeModel.findById(cubicId).populate('accessories').lean().then(cube => {
        res.render(__projectname + '/views/updatedDetailsPage.hbs', {cube})
    }).catch(err => console.error(err));
}

/**
 * Check if the given `lang`s are acceptable,
 * otherwise you should respond with 406 "Not Acceptable".
 *
 * @param {express.Request} req
 * @param {express.Response} res
 * @public
 */
const handleCreatePage = (req, res) => {
    res.render(__projectname + '/views/create.hbs');
}

/**
 * Check if the given `lang`s are acceptable,
 * otherwise you should respond with 406 "Not Acceptable".
 *
 * @param {express.Request} req
 * @param {express.Response} res
 * @public
 */
const handleCreatePagePost = (req, res) => {
    let body = req.body;

    new CubeModel(body).save().then(cube => {
        console.log(cube);
        res.redirect('/');
    }).catch(err => console.error(err));
}


module.exports = { handleDetailsPage, handleCreatePage, handleCreatePagePost };