const handleRequest = function (req) {
    return req.json();
}

const handleNoCityError = function (error) {
    console.log(error);
}

const handleCities = function (data) {
    const inputField = document.getElementById('location').value;

    for (const element in data) {
        if (data[element].name === inputField) {
            const city = data[element];
            const code = city.code;
            const currentWeaterUrl = `https://judgetests.firebaseio.com/forecast/today/${code}.json `;
            const nextWeaterUrl = `https://judgetests.firebaseio.com/forecast/upcoming/${code}.json `;

            fetch(currentWeaterUrl)
                .then(handleRequest)
                .then(handleCurrentWeather);

            fetch(nextWeaterUrl)
                .then(handleRequest)
                .then(handleNextWeater);
        }
    }
}

const handleCurrentWeather = function (currentWeather) {
    document.getElementById('forecast').style.display = 'block';

    const currentElement = document.getElementById('current');
    const forecastElement = document.createElement('div');
    forecastElement.classList.add('forecasts');

    const span = document.createElement('span');
    span.classList.add('condition');

    const forecastData = document.createElement('span');
    forecastData.classList.add('forecast-data');
    forecastData.textContent = currentWeather.name;

    const temp = document.createElement('span');
    temp.classList.add('forecast-data');
    temp.textContent = `${currentWeather.forecast.low}/${currentWeather.forecast.high}`;

    const condition = document.createElement('span');
    condition.classList.add('forecast-data');
    condition.textContent = currentWeather.forecast.condition;

    span.appendChild(forecastData);
    span.appendChild(temp);
    span.appendChild(condition);
    forecastElement.appendChild(span);
    currentElement.appendChild(forecastElement);
}

const handleNextWeater = function (nextWeater) {
    const upcoming = document.getElementById('upcoming');
    const forecastInfo = document.createElement('div');
    forecastInfo.classList.add('forecast-info');
    upcoming.appendChild(forecastInfo);

    for (const day of nextWeater.forecast) {
        const upcoming = document.createElement('span');
        upcoming.classList.add('upcoming');

        const temp = document.createElement('span');
        temp.classList.add('forecast-data');
        temp.textContent = `${day.low} C/${day.high} C`;

        const condition = document.createElement('span');
        condition.classList.add('forecast-data');
        condition.textContent = day.condition;

        upcoming.appendChild(temp);
        upcoming.appendChild(condition);

        forecastInfo.appendChild(upcoming);
    }
}

const clearOutput = function () {
    const todayElement = document.getElementsByClassName('forecasts')[0];
    const nextDaysElement = document.getElementsByClassName('forecast-info')[0];

    if (todayElement) {
        document.getElementById('current').removeChild(todayElement);
        document.getElementById('upcoming').removeChild(nextDaysElement);
    }
}

const handleClickEvent = function () {
    clearOutput();
    const citiesUrl = 'https://judgetests.firebaseio.com/locations.json';
    
    fetch(citiesUrl)
        .then(handleRequest)
        .then(handleCities)
        .catch(handleNoCityError);
}

function attachEvents() {
    document.getElementById('submit').addEventListener('click', handleClickEvent);
}

attachEvents();