'use strict';

angular.module('itmas_server', [ 'LocalStorageModule', 'ui.router', 'ui.bootstrap', 'ngResource' ])
    .run(function($rootScope, $state, Auth, Principal) {

        $rootScope.$on('$stateChangeStart', function(event, toState, toStateParams) {
            $rootScope.toState = toState;
            $rootScope.toStateParams = toStateParams;

            if (Principal.isIdentityResolved()) {
                Auth.authorize();
            }

            console.log('state changed to state');
            console.log(toState);
        });

        $rootScope.$on('$stateChangeSuccess',  function(event, toState, toParams, fromState, fromParams) {

            // Remember previous state unless we've been redirected to login or we've just
            // reset the state memory after logout. If we're redirected to login, our
            // previousState is already set in the authExpiredInterceptor. If we're going
            // to login directly, we don't want to be sent to some previous state anyway
            if (toState.name != 'login' && $rootScope.previousStateName) {
              $rootScope.previousStateName = fromState.name;
              $rootScope.previousStateParams = fromParams;
            }
        });
    })
    .config(function($stateProvider, $urlRouterProvider, $httpProvider) {
        $stateProvider.state('site', {
            'abstract': true,
            views: {
                'navbar@': {
                    templateUrl: '/app/components/navbar/navbar.html',
                    controller: 'NavbarController'
                }
            }
            ,
            resolve: {
                authorize: ['Auth',
                    function (Auth) {
                        return Auth.authorize();
                    }
                ]
            }
        });
   
        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        $httpProvider.defaults.xsrfCookieName = 'XSRF-TOKEN';
        $httpProvider.defaults.xsrfHeaderName = 'X-XSRF-TOKEN';

        //Auth.authorize();
    });