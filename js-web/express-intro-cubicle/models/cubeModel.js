const mongoose = require('mongoose');
const { ObjectId } = mongoose.Schema.Types;

const cubeSchema = new mongoose.Schema(
    {
        name: String,
        imageUrl: String,
        description: String,
        difficultyLevel: Number,
        accessories: [{ type: ObjectId, ref: 'Accessory' }]
    }
);

module.exports = mongoose.model('Cube', cubeSchema);
