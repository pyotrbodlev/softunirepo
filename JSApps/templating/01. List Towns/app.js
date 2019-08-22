const saveAllTowns = function (townsNames) {
    const towns = townsNames.split(', ').map(t => {
        return {
            name: t
        };
    });
    if (townsNames) {
        Array.from(towns).forEach(t => repo.persistTown(t));
    }
}

const getTemplate = function () {
    return `<ul class="town_list">
                {{#each towns}}
                    <li>{{this}}</li>
                {{/each}}
            </ul>`;
}

const handleLoadBtnEvent = async function () {
    const townsNames = document.getElementById('towns').value;
    saveAllTowns(townsNames);

    const dataTowns = await repo.getAllTowns();

    const allTowns = {
        towns: Array.from(dataTowns).map(t => t.name)
    }

    const template = Handlebars.compile(getTemplate());
    const ul = template(allTowns);
    document.getElementById('root').innerHTML = ul;
    document.getElementById('towns').value = '';
}

document.getElementById('btnLoadTowns').addEventListener('click', handleLoadBtnEvent);