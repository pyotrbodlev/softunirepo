$(() => {
    const listTemplate = `{{#each monkeys}}{{> monkey}}{{/each}}`;

    const monkeyTemplate = document.getElementById('monkey-template').innerHTML;
    Handlebars.registerPartial('monkey', monkeyTemplate);

    const template = Handlebars.compile(listTemplate);

    const html = template({
        monkeys 
    });

    document.querySelector('.monkeys').innerHTML = html;
})