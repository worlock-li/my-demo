var app = angular.module("wm", []);

app.controller("outStockController",function ($scope,$http) {

    $scope.findCK = function () {
        $http.get("../repository/findAll.do").success(function (response) {
            $scope.repositorys = response;
            $scope.getName();
            $scope.getSuppliers();
        });
    };

    $scope.CKId = 0;
    $scope.findCKId = function(list, address) {
        angular.forEach(list, function (v, k) {
            if (address === v.address) {
                $scope.CKId = v.id;
            }
        })
    };

    $scope.selectGoods = function() {
        $scope.findGooodsByCKId();
    };

    $scope.findGooodsByCKId = function () {
        $scope.goodss = {};
        $scope.findCKId($scope.repositorys, $scope.address);
        if ($scope.CKId === 0) {
            return;
        }
        $http.get("../goods/findGoodsByCKId.do?id=" + $scope.CKId).success(function (response) {
            $scope.goodss = response;
        });
    };

    $scope.getName = function () {
        $http.get("../login/getName.do").success(function (response) {
            $scope.username = response.name;
        })
    };

    $scope.numYz = function () {
        if (isNaN($scope.goodsNum)) {
            document.getElementById("goodsNum").style.borderColor = "#e68179"
        } else {
            document.getElementById("goodsNum").style.borderColor = "#7ad1e4"
        }

        if ($scope.goodsNum >= $scope.maxNum) {
            $scope.goodsMxaNum = "超出";
            document.getElementById("goodsNum").style.borderColor = "#e68179"
        } else {
            $scope.findMaxNum();
        }
    };

    $scope.refresh = function () {
        window.location.reload();
    };

    $scope.getSuppliers = function () {
        $http.get("../supplier/findAll.do").success(function (response) {
            $scope.suppliers = response;
        })
    };

    $scope.findMaxNum = function () {
        $scope.goodsMxaNum = 0;
        angular.forEach($scope.goodss, function (v, k) {
            if ($scope.goodsName === v.goods_name) {
                $scope.maxNum = v.num;
                $scope.goodsWhValue = JSON.parse(v.wh_value);
                angular.forEach($scope.goodsWhValue, function (i, j) {
                    if ($scope.CKId === i.repository_id) {
                        $scope.goodsMxaNum = i.num;
                    }
                })
            }
        })
    };

    $scope.sureOutStock = function () {
        $scope.goodsZnum = $scope.maxNum;
        angular.forEach($scope.goodss, function (v, k) {
            if ($scope.goodsName === v.goods_name) {
                $scope.whValue = JSON.parse(v.wh_value);
                angular.forEach($scope.whValue, function (i, j) {
                    if ($scope.CKId === i.repository_id) {
                        i.num -= $scope.goodsNum;
                        $scope.goodsZnum -= $scope.goodsNum;
                    }
                })
            }
        });

        var goodsWhValue = [];
        angular.forEach($scope.whValue, function (v, k) {
            if (v.num !== 0) {
                goodsWhValue.push(v);
            }
        });

        $scope.record = {};
        $scope.record.operation = "出库";
        $scope.record.goods_name = $scope.goodsName;
        $scope.record.num = $scope.goodsNum;
        $scope.record.repository_id = $scope.CKId;
        $scope.record.supplier = $scope.supplier;
        $scope.record.work_man = $scope.username;
        $scope.record.change_time = $scope.rcTime;
        $scope.record.remarks = $scope.beiZhu;

        $http.post("../goods/outStock.do?whValue=" + JSON.stringify(goodsWhValue) + "&goods_name=" + encodeURI($scope.goodsName) + "&num=" + $scope.goodsZnum , $scope.record).success(function (response) {
            if (response.success) {
                alert(response.message)
            } else {
                alert(response.message)
            }
        });
    }

});