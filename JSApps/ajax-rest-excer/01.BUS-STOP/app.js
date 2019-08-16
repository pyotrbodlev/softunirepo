function getInfo() {
    const busIdElement = document.getElementById('stopId');
    const stopElement = document.getElementById('stopName');
    const busesElement = document.getElementById('buses');

    const req = new XMLHttpRequest();

    const handleError = function () {
        stopElement.textContent = 'Error';
        busesElement.innerHTML = '';
    }

    req.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            const busObj = JSON.parse(this.responseText);
            stopElement.textContent = busObj.name;
            
            for(let bus in busObj.buses){
                const resultText = (`Bus ${bus} arrives in ${busObj.buses[bus]} minutes!`);
                const li = document.createElement('li');
                li.textContent = resultText;
                busesElement.appendChild(li);
            }
        } else {
            handleError();
        }
    };

    req.open("GET",
        `https://judgetests.firebaseio.com/businfo/${busIdElement.value}.json`, true);

    req.send();
}