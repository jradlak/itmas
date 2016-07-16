'use strict';

angular.module('itmas_server')
    .controller('UsersController', function($scope, User) {
        $scope.users = User.query({}, function (result) {
            $scope.users = result;
            console.log(result);
        });
    });