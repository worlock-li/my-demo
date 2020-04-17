var app = angular.module("wm",[]);
app.controller("userController",function ($scope,$http) {
    $scope.entity = "";
    $scope.login = function (username, password) {
        var usernameValue = document.getElementById("username").value;
        var  passwordValue = document.getElementById("password").value;
        var  errorMessageContent = document.getElementById("errorMessageContent");
        var  errorMessage = document.getElementById("errorMessage");

        if (usernameValue === "" || usernameValue == null || passwordValue === "" || passwordValue == null){
            errorMessageContent.innerHTML = "请填入正确格式";
            errorMessage.style.display = "block";
            setFlashMessage();
            return;
        } else {
            var login = document.getElementById("login");
            login.submit();
        }
    };

    function setFlashMessage() {
        setTimeout(function () {
            document.getElementById("errorMessage").style.display = "none";
        }, 3000)
    }
});