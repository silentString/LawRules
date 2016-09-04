/**
 * Created by dream on 2016/7/28.
 */
(function () {
    'use strict';
    angular.module('app',[
        'ngRoute',
        'ngCookies',
        'ngCkeditor',
        'ui.bootstrap',
        // 'authentication.service',
        // 'DataService',
        'app.login',
        'app.home',
        'app.home.user',
        'app.home.law',
        'app.confirm'
    ]);

})();