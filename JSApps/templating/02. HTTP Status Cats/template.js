(() => {
    renderCatTemplate();

    function renderCatTemplate() {
        const listTemplate = `
            <ul>
                {{#each cats}}
                    {{> cat}}
                {{/each}}
            </ul>`;

        const templateString = document.getElementById('cat-template');
        Handlebars.registerPartial('cat', templateString.innerHTML);

        const template = Handlebars.compile(listTemplate);

        const str = template({
            cats
        });

        document.getElementById('allCats').innerHTML = str;
    }

})();