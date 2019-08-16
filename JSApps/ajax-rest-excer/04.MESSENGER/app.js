function attachEvents() {
    const url = 'https://rest-messanger.firebaseio.com/messanger.json';
    const messages = document.getElementById('messages');

    const clearInputFields = function () {
        document.getElementById('author').value = '';
        document.getElementById('content').value = '';
    }

    const handleRefreshBtn = function () {
        messages.textContent = '';

        fetch(url)
            .then(info => info.json())
            .then(data => {
                Object.values(data).forEach(value => {
                    const messageText = `${value.author}: ${value.content}\n`;
                    messages.textContent += messageText;
                })
            });
    }

    const handleSendAction = function () {
        const author = document.getElementById('author').value;
        const content = document.getElementById('content').value;

        const element = { author, content };

        fetch(url, {
            method: 'post',
            body: JSON.stringify(element)
        })

        clearInputFields();
    }


    document.getElementById('submit').addEventListener('click', handleSendAction);
    document.getElementById('refresh').addEventListener('click', handleRefreshBtn);
}

attachEvents();