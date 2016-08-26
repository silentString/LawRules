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
        vm.changeName = false;
        vm.modifyName = modifyName;
        vm.modifyPhoneNum = modifyPhoneNum;
        vm.modifyPwd = modifyPwd;

        function modifyName(nickName){
            console.log(nickName);
        }

        function modifyPhoneNum(){
            var param = {};
            param.title = "提示";
            param.msg = "请联系QQ25301001修改";
            factory.openConfirmModal(param, vm, $uibModal);
        }

        function modifyPwd(){
            var param = {};
            param.title = "提示";
            param.type = "changePwd";
            factory.openConfirmModal(param, vm, $uibModal);
        }
    }
})();