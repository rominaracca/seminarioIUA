(function() {

	angular
	.module('seminario')
	.controller('ProductCtrl', productsController);

	productsController.$inject = ['productsService', 'categoriesService', '$stateParams', '$mdDialog', '$mdToast'];

	function productsController(productsService, categoriesService, params, dialog, toast) {

		var vm = this;
		vm.products = [];
		vm.productTmp = {};
		vm.editProduct = editProduct;
		vm.addProduct = addProduct;
		vm.confirmDelete = confirmDelete;

		activate();

		/////////////////////////////////////

		function activate() {
			if(params.category === null){
				productsService.list(params.query)
				.then(
					function(resp){
						console.log(resp);
						vm.products = resp.data;
					},
					function(respErr){
						console.log(respErr);
						//TODO toast msg
					}
				);
			}
			else {
				categoriesService.getProducts(params.category)
				.then(
					function(resp){
						console.log(resp);
						vm.products = resp.data;
					},
					function(respErr){
						console.log(respErr);
						//TODO toast msg
					}
				);
			}
		}

		function removeProduct(id) {
			productsService.remove(id)
			.then(
				function(resp){
					var index = findInArrayProducts(id);
					vm.products.splice(index, 1);
					toast.show(
						toast.simple({position:"top right"})
						.textContent('Producto eliminado')
						.hideDelay(3000)
					);
				},
				function(respErr){
					toast.show(
						toast.simple({position:"top right"})
						.textContent('No pudo eliminarse el producto')
						.hideDelay(3000)
					);
				}
			);
		}

		function editProduct() {
			productsService.update(vm.productTmp)
			.then(
				function(resp){
					console.log(resp);
					// TODO: update in array
				},
				function(respErr){
					console.log(respErr);
				}
			);
		}

		function addProduct() {
			productsService.add(vm.productTmp)
			.then(
				function(resp){
					console.log(resp);
					// TODO: agregar al arreglo
				},
				function(respErr){
					console.log(respErr);
				}
			);
		}

		function findInArrayProducts(id){
			vm.products.forEach(function(val, index){
				if(val.id === id){
					return index;
				}
			});
		}

		function confirmDelete(ev, prod) {
			var confirm = dialog.confirm()
										.title('Está seguro de eliminar este producto?')
										.textContent('El producto '+prod.description+' será eliminado.')
										.targetEvent(ev)
										.ok('Eliminar')
										.cancel('Cancelar');
			dialog.show(confirm).then(function() {
				removeProduct(prod.id);
			}, function() {
			});
		}
	}
}());
