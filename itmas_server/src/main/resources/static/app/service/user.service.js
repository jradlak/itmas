'use strict';

angular.module('itmas_server')
    .factory('User', function ($resource) {
        return $resource('api/users/:login', {}, {
                'query': {method: 'GET', isArray: true},
                });
        });