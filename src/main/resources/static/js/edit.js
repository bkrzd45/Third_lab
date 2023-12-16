const currentTestModel = {};

$(document).ready(function () {
    M.AutoInit();

    const testId = $('#testId').val();

    currentTestModel.id = Number(testId);

    loadQuestions();
});

function loadQuestions () {
    return $.ajax({
        url: '/api/questions/list',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(currentTestModel),
        success: function (response) {
            currentTestModel.questions = response;

            renderQuestionsList();
        },
        error: function () {
            return M.toast({html: 'Failed to load test questions'});
        }
    })
}

function saveTest () {
    currentTestModel.testTitle = $('#testTitle').val();

    return $.ajax({
        url: '/api/tests',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(currentTestModel),
        success: function (response) {
            if (response.id) {
                M.toast({html: 'Test saved'});

                console.log(response);

                return setTimeout(function () {
                    //return window.location.reload();
                }, 1000);
            }

            return M.toast({html: 'Failed to save the test'});
        },
        error: function () {
            return M.toast({html: 'Failed to save the test'});
        }
    });
}

function renderQuestionsList () {
    const questionsListContainer = $('#questionsList')

    questionsListContainer.empty();

    currentTestModel.questions.forEach(function (question, index) {
        const fieldInput = $(`
<div class="valign-wrapper">
    <div class="input-field">
        <input type="text" oninput="saveQuestionsLocal(${index}, this.value)" id="questionText${index}" th:value="${question.text}" class="validate">
        <label for="questionText${index}">Question text</label>
    </div>
    <a class="btn btn-flat saveBtn">Save</a>
</div>`);

        fieldInput.attr("question", JSON.stringify(question));
        fieldInput.find('.saveBtn').on('click', function () {
            currentTestModel.questions[index] = {
                text: fieldInput.find('#questionText' + index).val()
            };

            if (question.id) {
                currentTestModel.questions[index].id = question.id;
            }

            saveTest();
        });

        questionsListContainer.append(fieldInput);
    });
}

function saveQuestionsLocal (index, value) {
    currentTestModel.questions[index].text = value;
}

function addQuestion () {
    currentTestModel.questions.push({
        text: '',
        boundTest: {
            id: currentTestModel.id
        }
    });

    renderQuestionsList();
}