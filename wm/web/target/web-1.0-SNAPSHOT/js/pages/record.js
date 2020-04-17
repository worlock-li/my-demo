var app = angular.module("wm",['pagination']);

app.controller("recordController",function ($scope,$http) {

    $scope.list = {};
    $scope.findAll = function() {
        $http.get("../record/findAll.do").success(function (response) {
            $scope.list = response;
        });
    };

    $scope.findPage = function(pageNum, pageSize) {
        $http.get("../record/findPage.do?pageNum=" + pageNum + "&pageSize=" + pageSize).success(function (response) {
            $scope.paginationConf.totalItems = response.total;
            $scope.list = response.rows;
        })
    };

    $scope.reloadList = function() {
        $scope.search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    };

    $scope.paginationConf = {
        currentPage: 1,     //当前页
        totalItems: 10,     //总条数
        itemsPerPage: 5,   //每一页显示的数量
        perPageOptions: [10, 20, 30, 40, 50],   //选择每页显示的数量
        onChange: function () {
            $scope.reloadList();//重新加载
        }
    };

    $scope.record = {};
    //查询
    $scope.search = function (pageNum, pageSize) {
        $http.post("../record/search.do?pageNum=" + pageNum + "&pageSize=" + pageSize, $scope.record).success(function (response) {
            $scope.paginationConf.totalItems = response.total;
            $scope.list = response.rows;
        })
    };

    $scope.findCK = function () {
        $http.get("../repository/findAll.do").success(function (response) {
            $scope.repositorys = response;
        });
    };


});