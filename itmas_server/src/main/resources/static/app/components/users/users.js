'use strict';

angular.module('itmas_server')
    .config(function ($stateProvider) {
        $stateProvider
            .state('users', {
                parent: 'site',
                url: 'users',
                data: {
                    authorities: ['ROLE_ADMIN', 'ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: '/app/components/users/users.html',
                        controller: 'UsersController'
                    }
                }
            });
    });