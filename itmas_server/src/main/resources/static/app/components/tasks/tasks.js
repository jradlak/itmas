'use strict';

angular.module('itmas_server')
    .config(function ($stateProvider) {
        $stateProvider
            .state('tasks', {
                parent: 'site',
                url: 'tasks',
                data: {
                    authorities: ['ROLE_ADMIN', 'ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: '/app/components/tasks/tasks.html',
                        controller: 'TasksController'
                    }
                }
            });
    });