/**
 * Created by dream on 2016/8/9.
 */
(function () {
    'use strict';
    angular.module('app.home.user',[])
        .controller('UserController',UserController);
    UserController.$inject = ['$location','AuthenticationService','factory','DataService','$uibModal'];
    function UserController($location, AuthenticationService,factory, DataService,$uibModal){
        var vm = this;
        vm.infoType = 'userInfo';
        vm.changeName = false;
        vm.modifyName = modifyName;
        vm.modifyPhoneNum = modifyPhoneNum;
        vm.modifyPwd = modifyPwd;
        vm.username =  AuthenticationService.userData.nickName;
        vm.phoneNumber = AuthenticationService.userData.phoneNumber;
        vm.userInfo = {};
        var params = {
            phone_number:vm.phoneNumber,
        };
        
        DataService.userDetail(params).then(function(response){
            if(response.accept){
                vm.userInfo.registerTime = response.data.register_time;
                vm.userInfo.userStatus = response.data.user_status;
                vm.userInfo.score = response.data.score;
                vm.userInfo.endTime = response.data.end_time;
            }
            if(response.reject){
                vm.error = response.error;
            }
        });

        function modifyName(){
            var params = {
                phone_number:vm.phoneNumber,
                new_name:vm.newName,
            };
            DataService.changeName(params).then(function(response){
                if(response.accept){
                    vm.username = vm.newName;
                    var data = {};
                    data.nickName = vm.newName;
                    data.phoneNumber = vm.phoneNumber;
                    AuthenticationService.setCredential(data);
                    vm.changeName = false;
                }
                if(response.reject){
                    vm.error = response.error;
                }
            });
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
            param.phoneNum = vm.phoneNumber;
            factory.openConfirmModal(param, vm, $uibModal);
        }
    }
})();