(function() {
    'use strict';

    angular
        .module('seminario')
        .controller('CategoryCtrl', CategoryCtrl);

    CategoryCtrl.$inject = ['categoriesService', '$log', '$state'];

    
    /* @ngInject */
    function CategoryCtrl(categoriesService, $log, state) {
        var vm = this;

        vm.categories = [];
        vm.searchText = "";
        
        vm.searchProducts = searchProducts;

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
        }

        function searchProducts(query) {
        	state.go('main.product', {query: query});
		}
        
    }
})();

