var app = angular.module("wm",[]);
app.controller("homeController",function ($scope,$http) {
    $scope.list = {};
    $scope.findRecord = function() {
        $http.get("../record/findRecord.do").success(function (response) {
            $scope.list = response;
            $scope.findRepositoryList()
        });
    };

    $scope.findRepositoryList = function () {
        $http.get("../repository/findAll.do").success(function (response) {
            $scope.repositoryList = response;
        });
    }
});