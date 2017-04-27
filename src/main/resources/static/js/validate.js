jQuery('.confirmPassword').validate({
    rules: {
        "password": {
            minlength: 3
        },
        "password-confirm": {
            minlength: 3,
            equalTo : "#user_password"
        }
    }
});

$('submitButton').click(function () {
    console.log($('.confirmPassword').valid());
});