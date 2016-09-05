'use strict';

angular.module('itmas_server')
    .controller('UsersDetailController', function($scope, $stateParams, User) {
        $scope.user = {};
        $scope.load = function (login) {
            User.get({login: login}, function(result) {
                $scope.user = result;
            });
        };

        $scope.load($stateParams.login);
    });