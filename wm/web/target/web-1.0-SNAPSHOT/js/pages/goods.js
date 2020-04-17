var app = angular.module("wm",['pagination']);

app.controller("goodsController",function ($scope,$http) {

    $scope.list = {};
    $scope.findAll = function() {
        $http.get("../goods/findAll.do").success(function (response) {
            $scope.list = response;
        });
    };

    $scope.findPage = function(pageNum, pageSize) {
        $http.get("../goods/findPage.do?pageNum=" + pageNum + "&pageSize=" + pageSize).success(function (response) {
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

    var goodsName = document.getElementById("GoodsName");
    $scope.id = -1;

    $scope.search = function(pageNum, pageSize) {
        $scope.selectType();
        $http.get("../goods/search.do?goods_name="+encodeURI(goodsName.value)+"&id=" + $scope.id + "&pageNum=" + pageNum + "&pageSize=" + pageSize).success(function (response) {
            $scope.paginationConf.totalItems = response.total;
            $scope.list = response.rows;
        })
    };

    $scope.selectType = function() {
        angular.forEach($scope.types, function (v, k) {
            if (v.goods_type === $scope.goodsType) {
                $scope.id = k+1;
            }
        });
    };

    $scope.findGoodsType = function () {
        $http.get("../goodsType/findGoodsType.do").success(function (response) {
            $scope.types = response;
        });
    };

    $scope.sureDelete = function () {
        $http.get("../goods/delete.do?id=" + $scope.goods_id).success(function (response) {
            if (response.success) {
                $("#successModal").modal("show");
                $scope.reloadList();
            } else {
                alert("删除失败");
            }
        })
    };

    // show delete modal
    $scope.delete = function(id) {
        $("#deleteModal").modal("show");
        $scope.goods_id = id;
    };

    $scope.pojo = {};
    $scope.sureAdd = function () {
        var method = "add";
        if ($scope.pojo.id != null) {
            method = "update";
        }

        if (method === "add") {
            if ($scope.goods_img == null){
                alert("未选择图片");
                return;
            }
        }
        $scope.selectType();
        $scope.pojo.goods_type = $scope.id;
        var time = new Date();
        $scope.pojo.create_time = time;
        $scope.pojo.update_time = time;

        var form = new FormData();
        var file = document.getElementById("fileUpload").files[0];
        var pojo =JSON.stringify($scope.pojo);

        form.append('file', file);
        form.append('pojo',pojo);


        $http({
            method: 'POST',
            url: '/goods/'+method+".do",
            data: form,
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity
        }).success(function (data) {
            $("#successModal").modal("show");
            $scope.reloadList();
        }).error(function (data) {
            alert("更新失败")
        })
    };

        // $http.post("../goods/"+method+".do", $scope.pojo).success(function (response) {
        //     if (response.success) {
        //         $("#successModal").modal("show");
        //         $scope.reloadList();
        //     } else {
        //         alert(response.message)
        //     }
        // });



    // 修改前的查询
    $scope.findOneById = function(id) {
        $http.get("../goods/findOneById.do?id=" + id).success(function (response) {
            $scope.pojo = response;
            $scope.goodsType = $scope.pojo.goods_type;
        });
    };


});