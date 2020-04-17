var app = angular.module("wm", []);

app.controller("warehousingController",function ($scope,$http) {

    $scope.findGoodsType = function () {
        $http.get("../goodsType/findGoodsType.do").success(function (response) {
            $scope.typeList = response;
            $scope.selectGoodsName();
            $scope.findCK();
            $scope.getName();
            $scope.getSuppliers()
        });
    };

    $scope.typeId = 0;
    $scope.selectGoodsName = function () {
        angular.forEach($scope.typeList, function (i, j) {
            if ($scope.goodsType === i.goods_type) {
                $scope.typeId = i.id;
            }
        });
        $http.get("../goods/findGoodsByTypeId.do?id=" + $scope.typeId).success(function (response) {
            $scope.goodsList = response;
        });
    };

    $scope.numYz = function () {
        if (isNaN($scope.goodsNum)) {
            document.getElementById("goodsNum").style.borderColor = "#e68179"
        } else {
            document.getElementById("goodsNum").style.borderColor = "#7ad1e4"
        }

    };

    $scope.findCK = function () {
        $http.get("../repository/findAll.do").success(function (response) {
            $scope.repositorys = response;
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

    $scope.refresh = function () {
        window.location.reload();
    };

    $scope.goods = null;

    $scope.sureWareHousing = function () {
        $scope.findAllGoods();
        $scope.whValue = [];
        $scope.findCKId($scope.repositorys, $scope.repository);
            $scope.whValue = JSON.parse($scope.goods.wh_value);
            var temp = true;
            $scope.goodsZnum = 0;

            if ($scope.whValue === null) {
                $scope.whValue = [];
                $scope.goodsZnum = $scope.goodsNum;
            } else {
                angular.forEach($scope.whValue, function (i, j) {
                    if ($scope.CKId === i.repository_id) {
                        i.num = parseInt(i.num);
                        i.num += parseInt($scope.goodsNum);
                        temp = false;
                    }
                    $scope.goodsZnum += parseInt(i.num);
                });
            }

            if (temp) {
                var arr =
                    {
                        "repository_id":$scope.CKId,
                        "num":parseInt($scope.goodsNum)
                    };
                $scope.whValue.push(arr);
            }

        $scope.record = {};
        $scope.record.operation = "入库";
        $scope.record.goods_name = $scope.goodsName;
        $scope.record.type = $scope.goodsType;
        $scope.record.num = $scope.goodsNum;
        $scope.record.repository_id = $scope.CKId;
        $scope.record.supplier = $scope.supplier;
        $scope.record.work_man = $scope.username;
        $scope.record.change_time = $scope.rcTime;
        $scope.record.remarks = $scope.beiZhu;

        if ($scope.record.goods_name === null || $scope.record.num === null || $scope.record.repository_id === null || $scope.record.supplier === null || $scope.record.change_time === null) {
            alert("请填入完整信息");
            return;
        }

        $http.post("../goods/wareHousing.do?whValue=" + JSON.stringify($scope.whValue) + "&goods_name=" + encodeURI($scope.goodsName) + "&num=" + $scope.goodsZnum , $scope.record).success(function (response) {
            if (response.success) {
                alert(response.message)
            } else {
                alert(response.message)
            }
        });

    };

    $scope.findAllGoods = function () {
        $http.get("../goods/findAll.do").success(function (response) {
            $scope.list = response;
        });

        angular.forEach($scope.list, function (i, j) {
            if ($scope.goodsName === i.goods_name) {
                $scope.goods = i;
            }
        })
    };

    $scope.getName = function () {
        $http.get("../login/getName.do").success(function (response) {
            $scope.username = response.name;
        })
    };

    $scope.getSuppliers = function () {
        $http.get("../supplier/findAll.do").success(function (response) {
            $scope.suppliers = response;
        })
    }

});