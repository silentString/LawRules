/**
 * Created by dream on 2016/8/17.
 */
(function(){
        'use strict';
        angular.module('app')
            .factory('factory',factory);
        function factory(){
            return{
                modalOpen:function(modal, modalConfig){
                    return  modal.open(modalConfig);
                },
                modalClose:function(modal,modalConfig){
                    return modal.close();
                },
                openConfirmModal:function(param, vm, $uibModal){
                    var modalConfig = {};
                    modalConfig.resolve = {};
                    modalConfig.resolve.param = function () {
                        return param;
                    };
                    modalConfig.resolve.parentVm = function () {
                        return vm;
                    };
                    modalConfig.templateUrl = 'helper/confirm.html';
                    modalConfig.controller = 'ConfirmController';
                    modalConfig.backdrop = 'static';
                    modalConfig.controllerAs = 'vm';
                    modalConfig.windowsClass = 'management-modal';
                    return this.modalOpen($uibModal,modalConfig);
                }

            };
        }


})();