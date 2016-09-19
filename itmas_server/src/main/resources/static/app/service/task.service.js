'use strict';

angular.module('itmas_server')
    .factory('Task', function ($resource) {
        return $resource('api/tasks/:login:id', {}, {
                'query': {method: 'GET', isArray: true},
                'get': {
                    method: 'GET',
                    transformResponse: function (data) {
                        data = angular.fromJson(data);
                        return data;
                    }
                }
                });
        });