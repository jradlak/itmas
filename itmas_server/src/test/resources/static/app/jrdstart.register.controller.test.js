describe("itmas_server", function() {

	beforeEach(module('itmas_server'));
	var $httpBackend, $controller, $http;
	beforeEach(inject(function($injector) {
		$httpBackend = $injector.get('$httpBackend');
		$controller = $injector.get('$controller');
		$http = $injector.get('$http');
	}));
	afterEach(function() {
		$httpBackend.verifyNoOutstandingExpectation();
		$httpBackend.verifyNoOutstandingRequest();
	});

    describe("Register Controller", function() {
        it('it sais <password do not match> if password\'s are not the same', function() {
            var $scope = {};
            var controller = $controller('RegisterController', { $scope: $scope });
            $scope.registerAccount = {};
            $scope.registerAccount.password = "pass1";
            $scope.registerAccount.confirmPassword = "pass2";
            $scope.doNotMatch = '';
            $scope.register();

            expect($scope.doNotMatch).toEqual('ERROR');
        });

        it('it should register correct user', function() {
            var $scope = {};
            var controller = $controller('RegisterController', { $scope: $scope });

            $scope.success = '';
            $httpBackend.expectPOST('api/register').respond(200);
            $scope.register();
            $httpBackend.flush();

            expect($scope.success).toEqual('OK');
        })

        it('it should not register user with existing login name', function() {
            var $scope = {};
            var controller = $controller('RegisterController', { $scope: $scope });

            $scope.errorUserExists = '';
            $httpBackend.expectPOST('api/register').respond(400, 'login already in use');
            $scope.register();
            $httpBackend.flush();

            expect($scope.errorUserExists).toEqual('ERROR');
        })
    });
});