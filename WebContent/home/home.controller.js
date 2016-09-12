/**
 * Created by dream on 2016/7/28.
 */
(function(){
    'use strict';
    angular.module('app.home',[])
        .controller('HomeController',HomeController);
    HomeController.$inject = ['$location','AuthenticationService','$cookies','DataService','$scope'];

    function HomeController($location,AuthenticationService,$cookies,DataService,$scope){
        var vm = this;
        vm.username =  AuthenticationService.userData.nickName;
        vm.phoneNumber = AuthenticationService.userData.phoneNumber;
        vm.logout = logout;
        vm.type = 'lawRules';
        vm.data = [];
        vm.sendToLaw = sendToLaw;

        function logout(){
            AuthenticationService.logout();
            $location.path('/login');
        }

        function sendToLaw(){
            $scope.$broadcast('to-child', 'child');
        }
    }

})();