'use strict';

angular.module('itmas_server')
    .controller('RegisterController',
        function($scope, Auth) {
            $scope.doNotMatch = null;
            $scope.errorUserExists = null;

            $scope.registerAccount = {};
            $scope.register = function() {
                if ($scope.registerAccount.password !== $scope.registerAccount.confirmPassword) {
                    console.log('passwords do not match');
                    $scope.doNotMatch = 'ERROR';
                } else {
                    $scope.doNotMatch = null;
                    $scope.error = null;
                    $scope.errorUserExists = null;
                    $scope.errorEmailExists = null;

                    Auth.createAccount($scope.registerAccount).then(function () {
                        console.log('create account success');
                        $scope.success = 'OK';
                    }).catch(function (response) {
                        $scope.success = null;
                        if (response.status === 400 && response.data === 'login already in use') {
                            console.log('login already in use');
                            $scope.errorUserExists = 'ERROR';
                        } else if (response.status === 400 && response.data === 'e-mail address already in use') {
                            $scope.errorEmailExists = 'ERROR';
                        } else {
                            $scope.error = 'ERROR';
                        }
                    });
                }
            }
        });
