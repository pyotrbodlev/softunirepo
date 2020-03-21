const mongoose = require('mongoose');

const cubeSchema = new mongoose.Schema(
    {
        name: String,
        imageUrl: String,
        description: String,
        difficultyLevel: Number,
        accessories: [{ type: mongoose.Schema.Types.ObjectId, ref: 'Accessory' }]
    }
);

module.exports = mongoose.model('Cube', cubeSchema);
