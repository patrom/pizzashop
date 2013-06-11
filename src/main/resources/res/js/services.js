'use strict';

/* Services */

var services = angular.module('AngularSpringApp.services', [ 'ngResource' ]);

services.factory('PizzasLoader', [ '$resource', function($resource) {
	return $resource('/pizzashop/pizzas/:Id', {
		Id : "@Id"
	}, {
		deleteId : {
			method : 'DELETE',
			params : {
				test : 55
			}
		}
	});
} ]);

services.factory('SingleLoader', [ 'PizzasLoader', '$route', '$q',
		function(PizzasLoader, $route, $q) {
			return function(id) {
				var delay = $q.defer();
				PizzasLoader.get({
					Id : id
				}, function(pizza) {
					delay.resolve(pizza);

				}, function() {
					delay.reject('Unable to fetch pizza: ' + id);
				});
				return delay.promise;
			};
		} ]);

services.factory('errorInterceptor', function($q, errorLog, errorService) {
	return function(promise) {
		return promise.then(function(response) {
			// Do nothing
			return response;
		}, function(response) {
			// My notify service updates the UI with the error message
			errorService.setError(response);
			// Also log it in the console for debug purposes
			errorLog(response);
			return $q.reject(response);
		});
	};
});

services.factory('errorLog', function($log) {
	return function(error) {
		return $log.error(error);
	};
});

services.factory('errorService', function() {
	return {
		errorMessage : null,
		setError : function(msg) {
			this.errorMessage = msg;
		},
		clear : function() {
			this.errorMessage = null;
		}
	};
});

