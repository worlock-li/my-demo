var app = angular.module("wm",[]);
app.controller("indexController",function ($scope,$http) {
	function exitUser(){
		var flag = confirm("是否确定退出?");
		if(flag){
			window.location.href="http://localhost:8080/wm/loginOut"
		}
	}

	$scope.changePassword = function() {
		$("#myModal").modal("show");
	};

	$scope.getName = function () {
		$http.get("../login/getName.do").success(function (response) {
			$scope.username = response.name;
		})
	};
	var errorMessage = document.getElementById("errorMessage");
	var errorMessageText = document.getElementById("errorMessageText");

	$scope.sureChangePassword = function () {
		$http.get("../user/findOne.do?username="+$scope.username).success(function (response) {
			$scope.entity = response;
			if ($scope.oldPassword == null || $scope.oldPassword === "" || $scope.newPassword == null || $scope.newPassword === "" || $scope.nextNewPassword ==null || $scope.nextNewPassword === "") {
				errorMessageText.innerHTML = "请输入正确格式";
				errorMessage.style.backgroundColor = "#e6c2c8";
				errorMessage.style.display = "block";
				setFlashMessage();
			} else if ($scope.oldPassword !== $scope.entity.password) {
				errorMessageText.innerHTML = "请输入正确的原始密码";
				errorMessage.style.backgroundColor = "#e6c2c8";
				errorMessage.style.display = "block";
				setFlashMessage();
			} else if ($scope.newPassword !== $scope.nextNewPassword) {
				errorMessageText.innerHTML = "请保证两次密码一致";
				errorMessage.style.backgroundColor = "#e6c2c8";
				errorMessage.style.display = "block";
				setFlashMessage();
			} else  if ($scope.newPassword.length < 6) {
				errorMessageText.innerHTML = "请输入大于6位数的密码";
				errorMessage.style.backgroundColor = "#e6c2c8";
				errorMessage.style.display = "block";
				setFlashMessage();
			} else {
				changePassword($scope.entity.id, $scope.newPassword);
				$scope.oldPassword = "";
				$scope.newPassword = "";
				$scope.nextNewPassword = "";
			}

		});
	};

	function changePassword(id, password) {
		$http.get("../user/changePassword.do?id=" + $scope.entity.id + "&password=" + $scope.newPassword).success(function (response) {
			errorMessageText.innerHTML = "修改成功";
			errorMessage.style.backgroundColor = "#64dc62";
			errorMessage.style.display = "block";
			setFlashMessage();
		});
	}

	function setFlashMessage() {
		setTimeout(function () {
			document.getElementById("errorMessage").style.display = "none";
		}, 3000)
	}
});

