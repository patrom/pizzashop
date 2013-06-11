angular.module('AngularSpringApp.controllers', []).controller(
		'PizzaController', ['$scope','$http', '$window', 'PizzasLoader', 'SingleLoader', 'errorService',
		                    function($scope, $http, $window, PizzasLoader, SingleLoader, errorService) {	
			var languages = [{name:'NL'}, {name:'FR'}];
			$scope.languages = languages;
			$scope.selectedLanguage = languages[0];
			
			$scope.errorService = errorService;
			
			$scope.addPizza = function(pizza) {
				var topping = new Topping("Tomato");
				var toppings = new Array(topping);
				pizza.toppings = toppings;
				pizza.beforeDate = new Date();
//				$http.post('pizzas/addPizza', pizza).success(function() {
//					$scope.error = '';
//					$scope.findPizzas();
//				}).error(function(data, status, headers, config) {
//					$scope.error = msg(data);
//				 });
				PizzasLoader.save(pizza, received);
				
				$scope.pizza.name = '';	
			};
			
			$scope.changeLang = function(lang){
				if(lang !== $scope.selectedLanguage){
					$scope.selectedLanguage = lang;
					$scope.findPizzas();
				}
			};
			$scope.findPizzas = function() {
//				$http.get('pizzas/findPizzas.json').success(function(pizzas) {
//					$scope.pizzas = pizzas;
//				});
				console.log(PizzasLoader);
				$scope.pizzas = PizzasLoader.query({lang: $scope.selectedLanguage.name});
			};
			
			$scope.findPizza = function() {
				$scope.piz = SingleLoader($scope.inputId);
			};
			
			$scope.removePizza = function(pizza) {
//				 $http.delete('pizzas/deletePizza/' + id).success(function() {
//					 $scope.findPizzas();
//				 }).error(function(data, status, headers, config) {
//					 $scope.error = msg(data);
//				 });
//				 PizzasLoader.delete({id:pizza.id}, received, msg);
				PizzasLoader.deleteId({id:pizza.id},received);
			};
			
			
			$scope.removeAllPizzas = function() {
//				 $http.delete('pizzas/deleteAll').success(function() {
//					 $scope.findPizzas();
//					 $scope.error = '';
//				 });
				 PizzasLoader.remove(received);
			};
			
			function Topping(name){
				this.name = name;
			};
			
			function received(){
				$scope.errorService.clear();
				$scope.findPizzas();
			};
			
			$scope.calculate = function(){
				$http.get('pizzas/calc').success(function(message) {
					$window.alert(message);
				});
			};
			
			$scope.findPizzas();
			
		} ]);

//angular.module('AngularSpringApp.login', []).controller(
//		'LoginController', ['$scope','$http', function($scope, $http) {
//			
//			$scope.register = function() {
//				var user = new Person("pat", "passpat");
//				$http.post('login/createUser', user).success(function() {
//					
//				});
//				
//			};
//			
//			function Person(name, password){
//				this.username = name;
//				this.password = password;
//				this.enabled = true;
//			};
//			
//		} ]);

var msg = function(data){
	var exceptions = '';
	for(prop in data){
		exceptions += prop + ': ' + data[prop];
	}
	 return exceptions;
};




