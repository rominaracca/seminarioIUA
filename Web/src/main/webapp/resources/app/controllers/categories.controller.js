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


		dialogController.$inject = ['$mdDialog', 'categoriesService'];
		function dialogController ($mdDialog, categoriesService){
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
		        				$log.log(resp);
		        				console.log(resp.data);
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
				 				vm.categories.push(resp.data);
				 				console.log(resp.data);
				 			},
				 			function(respErr){
				 				$log.log(respErr);
				 			}
			 			);
			 };
			 
			 function saveUpdateCategory(index, desc){
				var cUp = {
				          description: desc
				        }
				 categoriesService.update(index+1, cUp)
				 	.then(
				 			function(resp){
				 				console.log(resp.data);
				 			},
				 			function(respErr){
				 				console.log(respErr);
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
                 clickOutsideToClose: true
               })
               .then(function(answer) {
                  console.log(answer);
                });
        };




    }
})();
