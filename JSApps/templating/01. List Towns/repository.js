(async function (scope) {
    const url = 'https://baas.kinvey.com/appdata/kid_ByGVH9I4S/towns';

    const headers = {
        "Content-Type": 'application/json',
        "Authorization": 'Basic cm9vdDpwYXNzd29yZA=='
    }

    const getAllTowns = async function () {
        const resp = await fetch(url, {
            headers
        });
        const data = await resp.json();

        return data;
    }

    const persistTown = async function (town) {
        const resp = await fetch(url, {
            method: "POST",
            headers,
            body: JSON.stringify(town),
        });
    }

    scope.repo = {
        getAllTowns,
        persistTown
    }
}(window));