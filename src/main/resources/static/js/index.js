$(document).ready(function () {
    M.AutoInit();
});

function deleteTest (testId) {
    if (confirm('Are you sure want to delete test')) {
        $.ajax({
            url: '/api/tests/' + testId,
            method: 'DELETE',
            success: function () {
                return window.location.reload();
            },
            error: function () {
                return M.toast({html: 'Failed to delete the test'});
            }
        })
    }
}

function createTest (form) {
    return $.ajax({
        url: '/api/tests',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            testTitle: form.find('#testTitle').val()
        }),
        success: function (response) {
            if (response.id) {
                M.toast({html: 'Test created'});

                return setTimeout(function () {
                    return window.location.href = '/edit/' + response.id;
                }, 1000);
            }

            return M.toast({html: 'Failed to create test'});
        },
        error: function () {
            return M.toast({html: 'Failed to create test'});
        }
    });
}

function initForm () {
    M.Modal.getInstance(document.getElementById('createTestModal')).open();
}