var app = angular.module("hlt",['pagination']);
app.controller("brandController",function($scope,$http){

    $scope.findAll = function(){
        $http.get("../brand/findAll.do").success(function(response){
            $scope.list = response;
        })
    }

    $scope.findPage = function(pageNum,pageSize){
        $http.get("../brand/findPage.do?pageNum="+pageNum+"&pageSize="+pageSize).success(function(response){
            $scope.paginationConf.totalItems = response.total;
            $scope.list = response.rows;
        })
    }

    $scope.reloadList = function(){
        $scope.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
    }

    $scope.save = function(){
        // (url,param)
        var method = "add";
        if($scope.entity.id != null){
            method = "update";
        }
        $http.post("../brand/"+method+".do",$scope.entity).success(function (response) {
            if(response.success){
                //刷新页面
                $scope.reloadList();
            }else{
                alert(response.message);
            }
        })
    }



    $scope.findOne = function(id){
        $http.get("../brand/findOne.do?id="+id).success(function (response) {
            $scope.entity = response;
        })
    }

    //定义一个集合用来存储所有勾选的id
    $scope.selectIds = [];

    $scope.updateSelection = function($event,id){

        //如果勾上 加到集合中
       if($event.target.checked){
           $scope.selectIds.push(id);
       }else{
           //如果没有勾上就删除
           //获取到id的索引
           var index = $scope.selectIds.indexOf(id);
           //splice
           //  index :  移除的元素的索引
           //  count :  移除元素的个数
           $scope.selectIds.splice(index,1);
       }
    }

    $scope.dele = function(){
        $http.get("../brand/dele.do?ids="+$scope.selectIds).success(function (response) {
            if(response.success){
                $scope.reloadList();
            }else{
                alert(response.message);
            }
        })
    }
    // 记录全选框的状态
    $scope.isSelectAll = false;
    $scope.selectAll = function($event){
        $scope.isSelectAll = $event.target.checked;
    }




    $scope.searchEntity={};

    $scope.search = function(pageNum,pageSize){
        $http.post("../brand/search.do?pageNum="+pageNum+"&pageSize="+pageSize,$scope.searchEntity).success(function (response) {
            $scope.paginationConf.totalItems = response.total;
            $scope.list = response.rows;
        })
    }





    $scope.paginationConf = {
        currentPage: 1,     //当前页
        totalItems: 10,     //总条数
        itemsPerPage: 10,   //每一页显示的数量
        perPageOptions: [10, 20, 30, 40, 50],   //选择每页显示的数量
        onChange: function () {
            $scope.reloadList();//重新加载
        }
    }
})