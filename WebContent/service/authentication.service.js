/**
 * Created by dream on 2016/7/28.
 */
(function(){
    'use strict';
    angular.module('app')
        .factory('AuthenticationService',AuthenticationService);
    AuthenticationService.$inject = ['$cookies','$location','$http'];

    function AuthenticationService($cookies,$location,$http){
        var userData = {};
        return {
            login:login,
            logout:logout,
            setCredential:setCredential,
            userData:userData
        };
        function login(phoneNumber, password){
            return $http({
                //method:'GET',
                //url:'./json/login.data.json',
                 method:'POST',
                url:'Login',
                params:{phone_number:phoneNumber,password:password},
                headers:{'content-Type':'application/json;charset=utf-8'}
            });
        }

        function logout(){
            userData = {};
            $cookies.remove('userData');
        }

        function setCredential(data){
            userData.phoneNumber = data.phoneNumber;
            userData.nickName = data.nickName;
            // userData.userId = data.userId;
            // userData.sessionId = data.sessionId;
            $cookies.putObject('userData', userData);
        }
    }


})();