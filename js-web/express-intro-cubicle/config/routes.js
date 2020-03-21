const {homeController, cubicController, accessoryController} = require('../controllers')

module.exports = (app) => {
    app.get('/', homeController.handleHomePage);
    app.get('/about', homeController.handleAboutPage);
    app.get('/create', cubicController.handleCreatePage);
    app.post('/create', cubicController.handleCreatePagePost);
    app.get('/details/:id', cubicController.handleDetailsPage);
    app.get('/create/accessory', accessoryController.handleCreateAccessoryGet);
    app.post('/create/accessory', accessoryController.handleCreateAccessoryPost);
    app.get('/attach/accessory/:id', accessoryController.handleAttachAccessoryGet);
    app.post('/attach/accessory/:id', accessoryController.handleAttachAccessoryPost);
};