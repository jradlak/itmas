'use strict';

angular.module('itmas_server')
    .controller('HomeController', function($http, $scope, Principal) {
        $scope.isAuthenticated = Principal.isAuthenticated;
        $scope.greeting = {};
        $http.get('/api/resource/').success(function(data) {
            $scope.greeting = data;
        })
    });