(function() {

	angular
		.module('seminario')
		.controller('ProductsCtrl', productsController);

	productsController.$inject = ['productsService', '$log'];

	function productsController(productsService, log) {
		var vm = this;
		vm.products = [];
		vm.productTmp = {};
		vm.searchText = "";
		vm.removeProduct = removeProduct;
		vm.editProduct = editProduct;
		vm.addProduct = addProduct;
		vm.searchProducts = searchProducts;

		activate();

		/////////////////////////////////////

		function activate() {
			productsService.list()
			.then(
				function(resp){
					log.log(resp);
					vm.products = resp.data;
				},
				function(respErr){
					log.log(respErr);
					//TODO toast msg
				}
			);
		}

		function removeProduct(id) {
			productsService.remove(id)
				.then(
					function(resp){
						log.log(resp);
						// TODO: remove item from array
					},
					function(respErr){
						log.log(respErr);
						// TODO: show toast
					}
				);
		}

		function editProduct() {
			productsService.update(vm.productTmp)
				.then(
					function(resp){
						log.log(resp);
						// TODO: update in array
					},
					function(respErr){
						log.log(respErr);
					}
				);
		}

		function addProduct() {
			productsService.add(vm.productTmp)
				.then(
					function(resp){
						log.log(resp);
						// TODO: agregar al arreglo
					},
					function(respErr){
						log.log(respErr);
					}
				);
		}

		function searchProducts(query) {
			productsService.search($scope.opt.searchText)
				.then(
					function(resp){
						log.log(resp);
						vm.products = resp.data;
					},
					function(respErr){
						log.log(respErr);
						// TODO: toast
					}
				);
		}
	}

	function findInArrayProducts(id){
		vm.products.forEach(function(val, idx){
			if(val.id === id){
				// $scope.product = angular.copy($scope.productos[idx]);
				// return false;
			}
		});
	}

}());
