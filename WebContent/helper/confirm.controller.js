/**
 * Created by dream on 2016/8/17.
 */
(function(){
    'use strict';
    angular.module('app.confirm',[])
        .controller('ConfirmController',ConfirmController);
    ConfirmController.$inject = ['$location','AuthenticationService','DataService','factory','$uibModalInstance','parentVm','param'];

    function ConfirmController($location,AuthenticationService,DataService,factory,$uibModalInstance,parentVm, param) {
        var vm = this;
        vm.title = param.title;
        vm.msg = param.msg;
        vm.closeModal = closeModal;
        vm.changePwd = changePwd;
        vm.type = param.type;
        vm.newPwd = '';

        function closeModal(){
            factory.modalClose($uibModalInstance);
        }

        function changePwd(){
            var params = {
                phone_number:param.phoneNum,
                new_password:vm.newPwd
            };
            if(vm.newPwd == vm.newPwd2nd){
                DataService.changePwd(params).then(function(response){
                    if(response.accept){
                        console.log("change password ok!");
                    }
                    if(response.reject){
                        vm.error = response.error;
                    }
                });
            }
            vm.closeModal();
        }

    }




})();