'use strict';

angular.module('itmas_server')
    .factory('Task', function ($resource) {
        return $resource('api/allTasks/:login', {}, {
                'query': {method: 'GET', isArray: true},
                });
        });