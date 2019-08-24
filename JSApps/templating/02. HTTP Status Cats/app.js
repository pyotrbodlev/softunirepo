const handleClickEvent = function (ev) {
    const target = ev.target;

    if (target.classList.contains('showBtn')) {
        const info = target.parentNode;

        if (info.querySelector('.status').style.display) {
            info.querySelector('.status').style.display = '';
        } else {
            info.querySelector('.status').style.display = 'none';
        }
    }
}

document.body.addEventListener('click', handleClickEvent);