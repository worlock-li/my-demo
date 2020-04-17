var app = angular.module("wm",['pagination']);
app.controller("supplierController",function ($scope,$http) {

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

    $scope.searchInfo = "";
    //查询
    $scope.search = function (pageNum, pageSize) {
        $http.get("../supplier/search.do?searchInfo=" + encodeURI($scope.searchInfo) + "&pageNum=" + pageNum + "&pageSize=" + pageSize).success(function (response) {
            $scope.paginationConf.totalItems = response.total;
            $scope.list = response.rows;
        })
    };

    $scope.entity = {};
    $scope.pojo = {};
    // show delete modal
    $scope.delete = function(id) {
        $("#deleteModal").modal("show");
        $scope.entity.id = id;
    };

    $scope.sureDelete = function () {
        $http.get("../supplier/delete.do?id="+$scope.entity.id).success(function (response) {
            $("#successModal").modal("show");
            $scope.reloadList();
        });
    }

    // 修改前的查询
    $scope.findOneById = function(id) {
        $http.get("../supplier/findOneById.do?id=" + id).success(function (response) {
            $scope.pojo = response;
        });
    };

    // 添加管理员
    $scope.sureAdd = function () {

        var method = "add";
        if ($scope.pojo.id != null) {
            method = "update";
        }

        $http.post("../supplier/"+method+".do", $scope.pojo).success(function (response) {
            if (response.success) {
                $("#successModal").modal("show");
                $scope.reloadList();
            } else {
                alert(response.message)
            }
        });

    };

});