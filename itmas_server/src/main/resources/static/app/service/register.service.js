'use strict';

angular.module('itmas_server')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });