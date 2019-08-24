const handleClickEvent = function (ev) {
    const target = ev.target;
    if (target.tagName === 'BUTTON') {
        const parent = target.parentNode;

        if (parent.querySelector('p').style.display) {
            parent.querySelector('p').style.display = '';
        } else {
            parent.querySelector('p').style.display = 'none';
        }
    }
}

document.querySelector('.monkeys').addEventListener('click', handleClickEvent);