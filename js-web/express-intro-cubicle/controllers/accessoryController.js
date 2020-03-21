const express = require('express');
const AccessoryModel = require('../models/accessoryModel');
const CubeModel = require('../models/cubeModel');

/**
 * @param {express.Request} req
 * @param {express.Response} res
 * @public
 */
const handleCreateAccessoryGet = (req, res) => {
    res.render(__projectname + '/views/createAccessory.hbs');
};

/**
 * @param {express.Request} req
 * @param {express.Response} res
 * @public
 */
const handleCreateAccessoryPost = (req, res) => {
    let body = req.body;

    new AccessoryModel(body).save().then(accessory => {
        console.log(accessory);
        res.redirect('/');
    }).catch(err => console.error(err));
}

/**
 * @param {express.Request} req
 * @param {express.Response} res
 * @public
 */
const handleAttachAccessoryGet = async (req, res) => {
    try {
        const cubicId = req.params.id;

        const cube = await CubeModel.findById(cubicId).lean();
        const accessories = await AccessoryModel.find().lean();

        res.render(__projectname + '/views/attachAccessory.hbs', { cube, accessories });
    } catch (e) {
        console.error(e);
    }
}

/**
 * @param {express.Request} req
 * @param {express.Response} res
 * @public
 */
const handleAttachAccessoryPost = (req, res) => {
    const cubicId = req.params.id;
    const { accessory: accessoryId } = req.body;

    CubeModel.findById(cubicId).then(cube => {
            cube.accessories.push(accessoryId);
            cube.save().then(() => res.redirect('/'));
        }).catch(err => {
            console.error(err);
        });
}

module.exports = { handleCreateAccessoryGet, handleCreateAccessoryPost, handleAttachAccessoryGet, handleAttachAccessoryPost };