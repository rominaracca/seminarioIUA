(function() {
    'use strict';

    angular
        .module('seminario')
        .controller('CategoryCtrl', CategoryCtrl);

    CategoryCtrl.$inject = ['categoriesService', '$log', '$state', '$mdDialog'];


    /* @ngInject */
    function CategoryCtrl(categoriesService, $log, state, $mdDialog) {
        var vm = this;

        vm.categories = [];
        vm.searchText = "";

        vm.searchProducts = searchProducts;
        vm.adminCategory = adminCategory;


        activate();

        function activate() {
        	categoriesService.list()
	        	.then(
	        			function(resp){
	        				//console.log(resp.data);
	        				$log.log(resp);
	        				vm.categories = resp.data;
	        			},
	        			function(respErr){
	        				$log.log(respErr);
	        			}
	        	);
        };

        function searchProducts(query) {
        	state.go('main.product', {query: query});
		};

		

		/**************************** DIALOGO CONTROLADOR ************************************/
		
		dialogController.$inject = ['$mdDialog', 'categoriesService', '$mdToast'];
		function dialogController ($mdDialog, categoriesService, toast){
			 var vm = this;

			 vm.categories = [];
			 vm.newcategory = "";
			 
			 vm.cancelDialog = cancelDialog;
			 vm.saveNewCategory = saveNewCategory;
			 vm.saveUpdateCategory = saveUpdateCategory;

			 activate();

	        function activate() {
	        	
	        	 categoriesService.list()
		        	.then(
		        			function(resp){
		        				vm.categories = resp.data;
		        			},
		        			function(respErr){
		        				$log.log(respErr);
		        			}
		        	);
	        }
		        
			 function cancelDialog(){
				 activate();
		    	 $mdDialog.cancel();
			 };

			 function saveNewCategory(){
		        var c = {
		          description: vm.newCategory
		        }
				 categoriesService.add(c)
				 .then(
				 			function(resp){
				 				toast.show(
										toast.simple({position:"top right"})
										.textContent('Categoria creada')
										.hideDelay(3000)
									);
								window.scrollTo(0, 0);
				 				vm.categories.unshift(resp.data);
				 			},
				 			function(respErr){
				 				toast.show(
										toast.simple({position:"top right"})
										.textContent('Error al crear la categoria')
										.hideDelay(3000)
									);
				 				$log.log(respErr);
				 			}
			 			);
			 };
			 
			 function saveUpdateCategory(id, desc){
				var cUp = {
				          description: desc
				        }
				 categoriesService.update(id, cUp)
				 	.then(
				 			function(resp){
				 				toast.show(
										toast.simple({position:"top right"})
										.textContent('Categoria actualizada')
										.hideDelay(3000)
									);
								window.scrollTo(0, 0);
				 			},
				 			function(respErr){
				 				toast.show(
										toast.simple({position:"top right"})
										.textContent('Error al actualizar la categoria')
										.hideDelay(3000)
									);
				 				$log.log(respErr);
				 			}
			 			);
			 };

		};


        function adminCategory(ev){
        	$mdDialog.show({
                 controller: dialogController,
                 controllerAs: 'vm',
                 templateUrl: 'app/views/category-admin.modal.html',
                 targetEvent: ev,
                 clickOutsideToClose: false
               })
               .then(function() {
            	   //activate();
                });
        };




    }
})();
