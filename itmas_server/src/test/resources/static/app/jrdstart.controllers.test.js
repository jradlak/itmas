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

	describe("Default headers", function() {

		it("include X-Requested-With", function() {
			$httpBackend.expectGET('', function(headers) {
				expect(headers['X-Requested-With']).toEqual('XMLHttpRequest');
				return true;
			}).respond(200);
			$http.get('')
			$httpBackend.flush();
		});
	});

	describe("Home Controller", function() {
		it("says Hello Test when controller loads", function() {
			$httpBackend.expectGET('/api/resource/').respond(200, {
				id : 4321,
				content : 'Hello Test'
			});

            var $scope = {};
			var controller = $controller('HomeController', { $scope: $scope });
			$httpBackend.flush();
			expect($scope.greeting.content).toEqual('Hello Test');
		});
	});

});