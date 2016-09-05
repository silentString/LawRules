/**
 * Created by dream on 2016/8/6.
 */
(function () {
    'use strict';
    angular.module('app.home.law',[])
        .controller('LawController',LawController);
    LawController.$inject = ['$location','AuthenticationService','factory','DataService','$uibModal'];
    function LawController($location, AuthenticationService,factory, DataService,$uibModal) {
        var vm = this;
        var everyPage = 10;//每页显示个数
        vm.data = [];//所有law数据
        vm.displayLaw = displayLaw;
        vm.page = [];//页码列表
        vm.displayData = [];//显示当前页法条
        vm.pageNum = 1;//总页数
        vm.currentPage = 1;//当前页
        vm.showArticle = showArticle;
        vm.lawList = true;//显示list
        vm.currentArticle = '';
        vm.printLaw = printLaw;
        DataService.getLawList().then(function(response){
            if(response.accept){
                vm.data = response.data;
                for(var i = 0 ; i < everyPage; i++){
                    if(i >= vm.data.length )break;
                    vm.displayData.push(vm.data[i]);
                }
                vm.pageNum =   Math.ceil(vm.data.length / everyPage);
                var total = 1;
                while(total <= vm.pageNum){
                    vm.page.push(total);
                    total++;
                }
            }
            if(response.reject){
                vm.error = response.error;
            }
        });

        function displayLaw(num){
            if(num == -1){//previous page
                if(vm.currentPage <= 1){
                    return;
                }else{
                    num = num -1;
                }
            }else if(num == 0){//next page
                if(vm.currentPage >= vm.pageNum){
                    return;
                }else{
                    num = num + 1;
                }
            }
            vm.currentPage = num;
            vm.displayData = [];
            for(var i = (num-1) * everyPage ; i < (num-1) * everyPage + everyPage; i++){
                // console.log(i);
                if(i >= vm.data.length )break;
                vm.displayData.push(vm.data[i]);
            }
            // console.log(vm.displayData);
        }

        function showArticle(path){
            vm.lawList = false;
            vm.currentArticle = path;
        }

        function printLaw(){
                $("#article").jqprint();
        }
    }
})();