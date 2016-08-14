/**
 * Created by dream on 2016/7/29.
 */
(function(){
    'use strict';
    angular.module('app')
        .config(['$routeProvider',function ($routeProvider) {
        $routeProvider
            .when('/login', {
                templateUrl: './login/login.html',
                controller: 'LoginController',
                controllerAs:'vm'
            })
            .when('/home', {
                templateUrl: './home/home.html',
                controller: 'HomeController',
                controllerAs:'vm'
            })
            .otherwise({
                redirectTo: '/home'
            });
    }]);

})();