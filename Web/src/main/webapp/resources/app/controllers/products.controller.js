(function() {

	angular
	.module('seminario')
	.controller('ProductCtrl', productsController);

	productsController.$inject = ['productsService', 'categoriesService', '$stateParams', '$mdDialog', '$mdToast'];

	function productsController(productsService, categoriesService, params, dialog, toast) {

		var vm = this;
		vm.products = [];
		vm.confirmDelete = confirmDelete;
		vm.createProductDialog = createProductDialog;
		vm.editProductDialog = editProductDialog;
		activate();

		/////////////////////////////////////

		function activate() {
			if(params.category === null){
				productsService.list(params.query)
				.then(
					function(resp){
						vm.products = resp.data;
					},
					function(respErr){
						toast.show(
							toast.simple({position:"top right"})
							.textContent('Fallo de conexión')
							.hideDelay(3000)
						);
					}
				);
			}
			else {
				categoriesService.getProducts(params.category)
				.then(
					function(resp){
						vm.products = resp.data;
					},
					function(respErr){
						toast.show(
							toast.simple({position:"top right"})
							.textContent('Fallo de conexión')
							.hideDelay(3000)
						);
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

		function findInArrayProducts(id){
			for (var i = 0; i < vm.products.length; i++) {
				if(vm.products[i].id === id){
					return i;
				}
			}
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

		function createProductDialog(ev) {
			dialog.show({
				controller: 'newProductController',
				controllerAs: "vm",
				templateUrl: 'app/views/new.product.modal.html',
				targetEvent: ev,
				clickOutsideToClose: false,
			})
			.then(function(product) {
				productsService.create(product)
					.then(
						function (resp) {
							toast.show(
								toast.simple({position:"top right"})
								.textContent('Producto creado')
								.hideDelay(3000)
							);
							window.scrollTo(0, 0);
							vm.products.unshift(resp.data);
						},
						function (resp) {
							toast.show(
								toast.simple({position:"top right"})
								.textContent('Error al crear el producto')
								.hideDelay(3000)
							);
						}
					);
			});
		}

		function editProductDialog(ev, product) {
			dialog.show({
				controller: 'editProductController',
				controllerAs: "vm",
				locals:{
					product:product
				},
				templateUrl: 'app/views/edit.product.modal.html',
				targetEvent: ev,
				clickOutsideToClose: false,
			})
			.then(function(product) {
				productsService.update(product)
					.then(
						function (resp) {
							toast.show(
								toast.simple({position:"top right"})
								.textContent('Producto actualizado')
								.hideDelay(3000)
							);
							var index = findInArrayProducts(product.id);
							vm.products.splice(index, 1, product);
						},
						function (resp) {
							toast.show(
								toast.simple({position:"top right"})
								.textContent('Error al actualizar el producto')
								.hideDelay(3000)
							);
						}
					);
			});
		}
	}
}());
