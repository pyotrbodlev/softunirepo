function solve() {
    let url = 'https://judgetests.firebaseio.com/schedule/';
    let nextStopId = 'depot.json';
    let stopName = '';

    function depart() {
        fetch(url + nextStopId)
            .then(info => info.json())
            .then(data => {
                stopName = data.name;
                nextStopId = (data.next + '.json');
                document.querySelector('.info').textContent = 'Next stop ' + data.name;
                document.getElementById('depart').disabled = true;
                document.getElementById('arrive').disabled = false;
            });
    }

    function arrive() {
        document.querySelector('.info').textContent = 'Arriving at ' + stopName;
        document.getElementById('depart').disabled = false;
        document.getElementById('arrive').disabled = true;
    }

    return {
        depart,
        arrive
    };
}

let result = solve();