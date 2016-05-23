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
        
		
		
		function dialogController ($mdDialog){
			 var vm = this;
			
			 vm.cancelDialog = function(){
				 console.log("cerrar");
		    	  $mdDialog.cancel();
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

