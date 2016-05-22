angular.module('seminario').controller('ProductController', ['$scope', 'productsService', '$log', function($scope, productsService, $log){

	$scope.productos = [];

	$scope.opt = {editando:false, searchText: null};
	$socpe.tituloForm = function(){
		return $scope.opt.editando ? 'Editando producto' : 'Nuevo producto';
	};

	$scope.product = {};

	productsService.list()
	.then(
			function(resp){
				$log.log(resp);
				$scope.productos = resp.data;
			},
			function(respErr){
				$log.log(respErr);
			}
	);


	$scope.refreshList = function(){
	productsService.search($scope.opt.searchText)
	.then(
			function(resp){
				$log.log(resp);
				$scope.productos = resp.data;
			},
			function(respErr){
				$log.log(respErr);
			}
	);
	};
	$scope.refreshList();

	$scope.guardar = function(){
		if($scope.opt.editando){
			productsService.update($scope.product)
			then(
					function(resp){
						$log.log(resp);
						$scope.productos.push(resp.data);
						$scope.cancelar();
					},
					function(respErr){
						$log.log(respErr);
					}
				);
		}else{
			productsService.add($scope.product)
			then(
					function(resp){
						$log.log(resp);
						$scope.productos.push(resp.data);
						$scope.cancelar();
					},
					function(respErr){
						$log.log(respErr);
					}
				);
		}
	};

	$scope.cancelar = function(){
		$scope.product = {};
		$scope.opt.editando = false;
	};

	$scope.editar = function(id){
		$scope.products.forEach(function(val, idx){
			if(val.id === id){
				$scope.product = angular.copy($scope.productos[idx]);
				return false;
			}
		})
		//$scope.product = $scope.productos[index]
		$scope.opt.editando = true;
	};

	$scope.eliminar = function(id){
		productsService.delete(id)
		then(
				function(resp){

				},
				function(respErr){
					$log.log(respErr);
				}
			);
	}

}]);
