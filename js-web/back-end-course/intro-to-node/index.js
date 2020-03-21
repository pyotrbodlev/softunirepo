const fs = require('fs');

fs.readFile('./package.json', function(err, data) {
 if (err) {return;}

 console.log('asd');
});