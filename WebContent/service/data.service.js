/**
 * Created by dream on 2016/8/9.
 */
(function() {
    'use strict';
    angular.module('app')
        .factory('DataService', DataService);
    DataService.$inject = ['$cookies', '$location', '$http'];
    function DataService($cookies, $location, $http) {
        return {
            register:register,
            forgetPwd:forgetPwd,
            changePwd:changePwd,
            getCode:getCode,
            userDetail:userDetail,
            changeName:changeName,
            getLawList:getLawList,

        };
        
        function register(params) {
            var http = baseHttp();
            // http.method = 'GET';
            // http.url = './json/register.data.json';
            http.method = 'POST';
            http.url = 'Register';
            http.params = params;
            return $http(http).then(success, fail);
        }

        function forgetPwd(params) {
            var http = baseHttp();
            // http.method = 'GET';
            // http.url = './json/forgetPwd.data.json';
            http.method = 'POST';
            http.url = 'ForgetPwd';
            http.params = params;
            return $http(http).then(success, fail);
        }

        function changePwd(params) {
            var http = baseHttp();
            // http.method = 'GET';
            // http.url = './json/changePwd.data.json';
            http.method = 'POST';
            http.url = 'ChangePwd';
            http.params = params;
            return $http(http).then(success, fail);
        }

        function getCode(params) {
            var http = baseHttp();
            // http.method = 'GET';
            // http.url = './json/changePwd.data.json';
            http.method = 'POST';
            http.url = 'IdentifyCode';
            http.params = params;
            return $http(http).then(success, fail);
        }

        function userDetail(params) {
            var http = baseHttp();
            // http.method = 'GET';
            // http.url = './json/changePwd.data.json';
            http.method = 'POST';
            http.url = 'UserDetail';
            http.params = params;
            return $http(http).then(success, fail);
        }

        function changeName(params) {
            var http = baseHttp();
            // http.method = 'GET';
            // http.url = './json/changePwd.data.json';
            http.method = 'POST';
            http.url = 'ChangeName';
            http.params = params;
            return $http(http).then(success, fail);
        }

        function getLawList(){
            var http = baseHttp();
            http.method = 'GET';
            http.url = './json/lawList.data.json';
            // http.method = 'POST';
            // http.url = 'ArticleList';
            return $http(http).then(success, fail);
        }

        //-------------------------------------------------
        function baseHttp(){
            return {
                headers:{'content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}
            };
        }

        function success(response){
            var result = {};
             if(response.data.status == 1){
                 result.accept = true;
                 result.data = response.data.data;
                 return result;
            }else{
                 result.reject = true;
                 result.error = "web server error"
                 return  result;
            }
        }

        function fail(){
            return  {
                reject:true,
                error:"http error"
            };
        }

    }
})();

