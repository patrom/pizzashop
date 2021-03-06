'use strict';

/* Directives */

var AppDirectives = angular.module('AngularSpringApp.directives', []);

AppDirectives.directive('appVersion', [ 'version', function(version) {
	return function(scope, elm, attrs) {
		elm.text(version);
	};
} ]);

AppDirectives.directive('alertBar',['$parse',function($parse) {
	return {
		restrict : 'A',
		template : '<div class="alert alert-error alert-bar"'
				+ 'ng-show="errorMessage">'
				+ '<button type="button" class="close" ng-click="hideAlert()">'
				+ 'x</button>'
				+ '{{errorMessage}}</div>',
		link : function(scope, elem, attrs) {
			var alertMessageAttr = attrs['alertmessage'];
			scope.errorMessage = null;
			scope.$watch(alertMessageAttr, function(newVal) {
				if(newVal && newVal.data){
					scope.errorMessage = newVal.data;
				}else{
					scope.errorMessage = newVal;
				}
			});
			scope.hideAlert = function() {
				scope.errorMessage = null;
				// Also clear the error message on the
				// bound variable.
				// Do this so that if the same error
				// happens again
				// the alert bar will be shown again
				// next time.
					$parse(alertMessageAttr).assign(scope,null);
				};
			}
		};
	} ]);
