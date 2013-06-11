'use strict';

var App = angular.module('AngularSpringApp', [ 'ui.bootstrap', 'AngularSpringApp.controllers',
		'AngularSpringApp.filters', 'AngularSpringApp.services',
		'AngularSpringApp.directives' ]);

// Declare app level module which depends on filters, and services
App.config([ '$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {

	$routeProvider.when('/pizzas', {
		controller : 'PizzaController',
		templateUrl : 'resources/pizzas/layout.html'
	});


	$routeProvider.otherwise({
		redirectTo : '/pizzas'
	});
	
	$httpProvider.responseInterceptors.push('errorInterceptor');
	$httpProvider.defaults.headers.common['Accept-Language'] = 'fr';
	
} ]);


