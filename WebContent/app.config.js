/**
 * Created by dream on 2016/7/29.
 */
(function(){
    'use strict';
    angular.module('app')   
        .run(run);
    run.$inject = ['$rootScope','$location','$cookies','AuthenticationService'];
    function run($rootScope,$location,$cookies,AuthenticationService,$scope){
        $rootScope.$on('$locationChangeStart',function(event,next,current){
            AuthenticationService.userData = $cookies.getObject('userData') || {};
                var loggedIn = $cookies.getObject('userData') !== undefined;
            if(loggedIn){
                $location.path('/home')
            }else {
                $location.path('/login');
            }
        });

    }

})();