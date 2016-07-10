'use strict';

angular.module('itmas_server')
    .factory('Auth', function Auth($rootScope, $state, Register, $http, localStorageService, Principal) {
        return {
            login: function(credentials) {
                 var data = 'username=' + encodeURIComponent(credentials.username) +
                     '&password=' + encodeURIComponent(credentials.password);
                 return $http.post('api/authentication', data, {
                     headers: {
                         'Content-Type': 'application/x-www-form-urlencoded'
                     }
                 }).success(function (response) {
                     Principal.identity(true);
                     return response;
                 });
            },
            authorize: function(force) {
                return Principal.identity(force)
                    .then(function() {
                        var isAuthenticated = Principal.isAuthenticated();

                        if ($rootScope.toState.data.authorities && $rootScope.toState.data.authorities.length > 0 && !Principal.hasAnyAuthority($rootScope.toState.data.authorities)) {
                            if (isAuthenticated) {
                                // user is signed in but not authorized for desired state
                                $state.go('accessdenied');
                            }
                            else {
                                // user is not authenticated. stow the state they wanted before you
                                // send them to the signin state, so you can return them when you're done
                                $rootScope.previousStateName = $rootScope.toState;
                                $rootScope.previousStateNameParams = $rootScope.toStateParams;

                                // now, send them to the signin state so they can log in
                                $state.go('login');
                            }
                        }
                    });
            },
            createAccount: function(account, callback) {
                var cb = callback || angular.noop;
                return Register.save(account,
                    function () {
                        return cb(account);
                    },
                    function (err) {
                        //this.logout();
                        return cb(err);
                    }.bind(this)).$promise;
            },
            logout: function () {
                Principal.authenticate(null);
                // Reset state memory
                $rootScope.previousStateName = undefined;
                $rootScope.previousStateNameParams = undefined;
                $http.post('api/logout', {}).finally(function() {
					$state.go("home");
				});
            },
        }
    });