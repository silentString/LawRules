/**
 * Created by dream on 2016/7/29.
 */
(function(){
    'use strict';
    angular.module('app.login',[])
        .controller('LoginController',LoginController);
    LoginController.$inject = ['$location','AuthenticationService','DataService','$window'];

    function LoginController($location,AuthenticationService,DataService,$window){
        var vm = this;
        vm.type = 'login';
        vm.login = login;
        vm.register = register;
        vm.forgetPwd = forgetPwd;
        vm.changePwd = changePwd;
        vm.getCode = getCode;
        vm.cancel = cancel;

        function login(){
            vm.loadingData = true;
            AuthenticationService.login(vm.phoneNumber,vm.password).then(loginSuccess,loginError)
                .then(function(){
                    vm.loadingData = false;
                });
        }

        function loginSuccess(response){
            response = response.data;
            if(response.status == 1){
                if(response.data.loginResult === "SUCCESS"){
                    var data = {};
                    data.nickName = response.data.nick_name;
                    data.phoneNumber = vm.phoneNumber;
                    AuthenticationService.setCredential(data);
                    $location.path('/home');
                }else if(response.data.loginResult === "EXPIRED"){

                }else{
                    console.log('FAIL!');
                }

            }else{
                console.log('server Error!');
            }
        }

        function loginError(response){
            console.log('http Error!');
        }


        function register(){
            var params = {
                nick_name:vm.nickName,
                phone_number:vm.phoneNumber,
                code:vm.idCode,
                password:vm.password
            };
            DataService.register(params).then(function(response){
                if(response.accept){
                    if(response.data === 'EXIST'){
                        vm.error = "user exist";
                    }else if(response.data === 'FAIL'){
                        vm.error = "register failed";
                    }else if(response.data === 'WRONGCODE'){
                        vm.error = "id code error";
                    }else{
                        vm.type = 'pay';
                    }
                }
                if(response.reject){
                    vm.error = response.error;
                }
            });
        }

        function forgetPwd(){
            var params = {
                phone_number:vm.phoneNumber,
                code:vm.idCode,
            };
            DataService.forgetPwd(params).then(function(response){
                if(response.accept){
                    if(response.data.passed == 'TRUE'){
                        vm.type = 'changePwd';
                        vm.oldPwd = response.data.old_password;
                    }else{
                        vm.error = response.data.passed;
                    }
                }
                if(response.reject){
                    vm.error = response.error;
                }
            });
        }

        function changePwd(){
            var params = {
                phone_number:vm.phoneNumber,
                new_password:vm.newpassword
            };
            DataService.changePwd(params).then(function(response){
                if(response.accept){
                    if(response.data.changed == 'TRUE'){
                        var data = {};
                        data.nickName = response.data.nick_name;
                        data.phoneNumber = vm.phoneNumber;
                        AuthenticationService.setCredential(data);
                        $location.path('/home');
                    }else{
                        vm.error = response.data.pass;
                    }
                }
                if(response.reject){
                    vm.error = response.error;
                }
            });
        }

        function cancel(){
            $window.history.back();
        }

        function getCode(){
            var params = {
                phone_number:vm.phoneNumber,
            };
            DataService.getCode(params).then(function(response){
                if(response.accept){
                        console.log("waite for message!");
                }
                if(response.reject){
                    vm.error = response.error;
                }
            });
        }



    }


})();