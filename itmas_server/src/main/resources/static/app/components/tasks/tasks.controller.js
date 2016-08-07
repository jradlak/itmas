'use strict';

angular.module('itmas_server')
    .controller('TasksController', function($scope, Task) {
        $scope.tasks = Task.query({'login': 'user'}, function (result) { //TODO: make it works with all users!!!
            $scope.tasks = result;
            console.log(result);
        });
    });