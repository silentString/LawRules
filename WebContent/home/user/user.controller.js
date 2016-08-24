/**
 * Created by dream on 2016/8/9.
 */
(function () {
    'use strict';
    angular.module('app.user',[])
        .controller('UserController',UserController);
    UserController.$inject = ['$location','AuthenticationService','factory','DataService','$uibModal'];
    function UserController($location, AuthenticationService,factory, DataService,$uibModal){
        var vm = this;
        vm.modifyPwd = modifyPwd;
        function modifyPwd(){
            var param = {};
            param.title = "提示";
            param.msg = "您使用的日期已经截止，\n请充值并兑换日期后登录";
            factory.openConfirmModal(param, vm, $uibModal);
        }
    }
})();