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

        function closeModal(){
            factory.modalClose($uibModalInstance);
        }

    }




})();