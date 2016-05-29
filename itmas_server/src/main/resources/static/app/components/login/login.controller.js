'use strict';
angular.module('itmas_server')
    .controller('LoginController',
		function($rootScope, $scope, $state, Auth, Principal) {

			var authenticate = function(credentials, callback) {
                if (credentials) {
                    Auth.login(credentials).then(function (data) {
                        $rootScope.authenticated = true;
                        $rootScope.error = false;
                        callback && callback($rootScope.authenticated);
                    }).catch(function (err) {
                        $rootScope.authenticated = false;
                        $rootScope.error = true;
                        callback && callback(false);
                    });
                }
			}

			authenticate();

			$scope.credentials = {};
			$scope.login = function() {
				authenticate($scope.credentials, function(authenticated) {
					if (authenticated) {
						console.log("Login succeeded")
						$state.go("home");
						$scope.error = false;
						$rootScope.authenticated = true;
					} else {
						console.log("Login failed")
						$state.go("login")
						$scope.error = true;
						$rootScope.authenticated = false;
					}
				})
			};

			$scope.logout = function() {
				Auth.logout();
			}
		})