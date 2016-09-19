'use strict';

angular.module('itmas_server')
    .controller('TasksDetailController', function($scope, $stateParams, Task) {
        $scope.task = {};       
        $scope.load = function (id) {
            Task.get({id: id}, function(result) {
                $scope.task = result;                                
            });
        };

        $scope.load($stateParams.id);
    });