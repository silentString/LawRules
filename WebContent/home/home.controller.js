/**
 * Created by dream on 2016/7/28.
 */
(function(){
    'use strict';
    angular.module('app.home',[])
        .controller('HomeController',HomeController);
    HomeController.$inject = ['$location','AuthenticationService','$cookies','DataService'];

    function HomeController($location,AuthenticationService,$cookies,DataService){
        var vm = this;
        vm.username =  AuthenticationService.userData.nickName;
        vm.phoneNumber = AuthenticationService.userData.phoneNumber;
        vm.logout = logout;
        vm.type = 'home';
        vm.userCenter = userCenter;

        vm.userInfo = {
            nickName:"dd",
            registerTime:"ss",
            userStatus:"aa",
            score:"ff",
            endTime:"cc"
        };


        function userCenter(){
            vm.type = 'userCenter';
            var params = {
                phone_number:vm.phoneNumber,
            };
            DataService.userDetail(params).then(function(response){
                if(response.accept){
                    vm.userInfo.nickName = response.data.nick_name;
                    vm.userInfo.registerTime = response.data.register_time;
                    vm.userInfo.userStatus = response.data.user_status;
                    vm.userInfo.score = response.data.score;
                    vm.userInfo.endTime = response.data.end_time;
                }
                if(response.reject){
                    vm.error = response.error;
                }
            });
        }

        function logout(){
            AuthenticationService.logout();
            $location.path('/login');
        }
    }

})();