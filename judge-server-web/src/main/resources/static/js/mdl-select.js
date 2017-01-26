window.addEventListener('load', function() {
    initSelect();
});

function initSelect() {
    document.querySelectorAll('.mdl-select').forEach(initSelectDiv);
}

function initSelectDiv(selectDiv) {
    var label = selectDiv.querySelector('label');
    var select = selectDiv.querySelector('select');
    var optionList = select.querySelectorAll('option');

    var hiddenInput = createHiddenInput(select);
    var selectInput = createSelectInput(select);
    var selectLabel = createSelectLabel(select, label);

    var ulForSelect = createUlForSelect(selectDiv, select, optionList, hiddenInput, selectInput);

    removeAll(selectDiv);

    selectDiv.appendChild(hiddenInput);
    selectDiv.appendChild(selectInput);
    selectDiv.appendChild(selectLabel);
    selectDiv.appendChild(ulForSelect);

    setClass(selectDiv);
}

function createHiddenInput(select) {
    var input = document.createElement('input');
    input.id = select.id;
    input.name = select.name;
    input.type = 'hidden';

    return input;
}

function createSelectInput(select) {
    var input = document.createElement('input');
    input.id = select.id + '-mdl-select';
    input.type = 'text';
    input.readOnly = true;
    input.classList.add('mdl-textfield__input');

    return input;
}

function createSelectLabel(select, label) {
    var selectLabel = document.createElement('label');
    selectLabel.setAttribute('for', select.id  + '-mdl-select');
    selectLabel.classList.add('mdl-textfield__label');
    selectLabel.innerHTML = label.innerHTML;

    return selectLabel;
}

function createUlForSelect(selectDiv, select, optionList, hiddenInput, selectInput) {
    var ul = document.createElement('ul');
    ul.setAttribute('for', select.id + '-mdl-select');
    ul.classList.add('mdl-menu', 'mdl-js-menu', 'mdl-menu--bottom-left');

    optionList.forEach(function(option) {
        var li = document.createElement('li');
        li.classList.add('mdl-menu__item');
        li.innerHTML = option.innerHTML;
        li.addEventListener('click', function() {
            selectDiv.classList.add('is-dirty');
            hiddenInput.value = option.value;
            selectInput.value = option.innerHTML;
        });

        ul.appendChild(li);
    });

    return ul;
}

function removeAll(element) {
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
}

function setClass(selectDiv) {
    var className = 'mdl-textfield mdl-js-textfield';

    if (selectDiv.classList.contains('mdl-select--floating-label')) {
        className += ' mdl-textfield--floating-level';
    }

    if (selectDiv.classList.contains('mdl-select--full-width')) {
        className += ' mdl-textfield--full-width';
    }

    selectDiv.className = className;
}