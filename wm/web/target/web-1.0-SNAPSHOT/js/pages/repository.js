var app = angular.module("wm",['pagination']);
app.controller("repositoryController",function ($scope,$http) {

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
        $http.get("../repository/search.do?searchInfo=" + encodeURI($scope.searchInfo) + "&pageNum=" + pageNum + "&pageSize=" + pageSize).success(function (response) {
            $scope.paginationConf.totalItems = response.total;
            $scope.list = response.rows;
        })
    };

    $scope.detailsList = null;

    $scope.findDetails = function (id) {
        $scope.goodss = [];
        $http.get("../goods/findDetails.do?id="+id).success(function (response) {
            $scope.capacity = 0;
            $scope.goodsZNum = 0;
            $scope.detailsList = response;
            if (response === [] || response.length === 0) {
                $("#nullGoodsModal").modal("show");
                return;
            }
            $scope.findWhValue(id, $scope.detailsList);
            var yy = ($scope.goodsZNum / $scope.capacity)*100;
            var kx = 100 - yy;
            document.getElementById("yy").style.width = yy + '%';
            document.getElementById("yy").innerText = yy + '%';
            document.getElementById("kx").style.width = kx + '%';
            document.getElementById("kx").innerText = kx + '%';
            $scope.findGoodsDetails(id, $scope.detailsList);
            $("#detailsModal").modal("show");
        });

    };

    $scope.findWhValue = function(id, list) {
        angular.forEach(list, function (v, k) {
            $scope.whValue = JSON.parse(v.wh_value);
            angular.forEach($scope.whValue, function (i, j) {
                if (id === i.repository_id) {
                    $scope.goodsZNum += i.num;
                }
            });
            $scope.capacity = v.capacity;
        });
    };

    $scope.goodss = [];
    $scope.findGoodsDetails = function(id, list) {
        angular.forEach(list, function (v, k) {
            $scope.goods = {};
            $scope.goods.goodsName = v.goods_name;
            $scope.whValue = JSON.parse(v.wh_value);
            angular.forEach($scope.whValue, function (i, j) {
                if (id === i.repository_id) {
                    $scope.goods.goodsNum = i.num;
                }
            });
            $scope.goodss.push($scope.goods);
        });
    };

    // 修改前的查询
    $scope.findOneById = function(id) {
        $http.get("../repository/findOneById.do?id=" + id).success(function (result) {
            $scope.pojo = result;
        });
    };

    function toPercent(point){
        if (point===0) {
            return 0;
        }
        var str=Number(point*100).toFixed();
        str+="%";
        return str;
    }

    $scope.entity = {};
    // show delete modal
    $scope.delete = function(id) {
        $("#deleteModal").modal("show");
        $scope.entity.id = id;
    };

    $scope.sureDelete = function () {
        $http.get("../goods/findDetails.do?id="+$scope.entity.id).success(function (response) {
            if (response === [] || response.length === 0) {
                $http.get("../repository/delete.do?id="+$scope.entity.id).success(function (response) {
                    $("#successModal").modal("show");
                    $scope.reloadList();
                });
            } else {
                alert("请保证仓库内货物为空")
            }
        });
    };

    $scope.findAdmin = function () {
        $http.get("../adminInfo/findAll.do").success(function (response) {
            $scope.adminInfos = response;
        });
    };

    // 添加修改管理员
    $scope.sureAdd = function () {

        if (isNaN($scope.pojo.capacity)) {
            alert("请输入数字");
            return;
        }

        var method = "add";
        if ($scope.pojo.id != null) {
            method = "update";
        }
        var admin_id = 0;
        angular.forEach($scope.adminInfos, function (v, k) {
            if (v.username === $scope.pojo.username) {
                admin_id = v.id;
            }
        });

        var re_status = null;
        if (method === "add") {
            re_status = "可用";
        } else  {
            $http.get("../goods/findDetails.do?id="+$scope.pojo.id).success(function (response) {
                if (response === []) {
                    re_status = "可用";
                } else {
                    $scope.goodsZNum = 0;
                    $scope.findWhValue($scope.pojo.id, response);
                }
            });

            if($scope.goodsZNum / $scope.pojo.capacity <= 1) {
                re_status = "可用";
            } else {
                re_status = "已满";
            }
        }

        $http.get("../repository/"+method+".do?address="+encodeURI($scope.pojo.address)+"&capacity="+$scope.pojo.capacity+"&admin_id="+admin_id+"&status1=" + encodeURI(re_status) + "&id=" + $scope.pojo.id).success(function (response) {
            if (response.success) {
                $("#successModal").modal("show");
                $scope.reloadList();
            } else {
                alert(response.message)
            }
        });

    };

});