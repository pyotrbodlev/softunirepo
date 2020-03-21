const mongoose = require('mongoose');
const { ObjectId } = mongoose.Schema.Types;

const accessorySchema = new mongoose.Schema(
    {
        name: String,
        imageUrl: String,
        description: String,
        cubes: [{ type: ObjectId, ref: 'Cube' }]
    }
);

module.exports = mongoose.model('Accessory', accessorySchema);
