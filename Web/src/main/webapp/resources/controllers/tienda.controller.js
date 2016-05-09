angular.module('tienda')
	.controller('TiendaController', 
			['$scope', function($scope){
		$scope.titulo="Tienda";
		$scope.productos = [ 
		  {descripcion:"Leche", precio:34.32},
		  {descripcion:"Arroz", precio:34.50},
		  {descripcion:"Vino", precio:34.60}
		  ];
	}]);