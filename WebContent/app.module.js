/**
 * Created by dream on 2016/7/28.
 */
(function () {
    'use strict';
    angular.module('app',[
        'ngRoute',
        'ngCookies',
        // 'authentication.service',
        // 'DataService',
        'app.login',
        'app.home'
    ]);

})();