function attachEvents() {
    const url = 'https://phonebook-nakov.firebaseio.com/phonebook';
    const getUrl = url + '.json';
    const ul = document.getElementById('phonebook');

    const handleLoadEvent = function () {
        fetch(getUrl)
            .then(req => req.json())
            .then(data => {
                ul.innerHTML = '';

                Object.entries(data).forEach(element => {
                    const li = document.createElement('li');
                    li.textContent = `${element[1].person}: ${element[1].phone}`;
                    const btnDelete = document.createElement('button');
                    btnDelete.textContent = 'Delete';
                    btnDelete.addEventListener('click', handleDeleteEvent(element[0]));
                    li.appendChild(btnDelete);
                    ul.appendChild(li);
                });
            });
    }

    const handleDeleteEvent = function (key) {
        return function () {
            const deleteUrl = url + "/" + key + '.json';
            fetch(deleteUrl, {
                method: 'delete'
            }).then(response =>
                response.json().then(json => {
                    return json;
                })
            );
        }
    }

    const handleCreateEvent = function () {
        const person = document.getElementById('person').value;
        const phone = document.getElementById('phone').value;

        let user = { person, phone };

        fetch(getUrl, {
            method: 'post',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });

            document.getElementById('person').value = '';
            document.getElementById('phone').value = '';
        }

    document.getElementById('btnLoad').addEventListener('click', handleLoadEvent);
    document.getElementById('btnCreate').addEventListener('click', handleCreateEvent);
}

attachEvents();