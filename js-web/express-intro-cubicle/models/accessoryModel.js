const mongoose = require('mongoose');

const accessorySchema = new mongoose.Schema(
    {
        name: String,
        imageUrl: String,
        description: String,
        cubes: [{ type: mongoose.Schema.Types.ObjectId, ref: 'Cube' }]
    }
);

module.exports = mongoose.model('Accessory', accessorySchema);
