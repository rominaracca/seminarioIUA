(function() {
    'use strict';

    angular
        .module('seminario')
        .controller('CategoryCtrl', CategoryCtrl);

    CategoryCtrl.$inject = ['categoriesService', '$log'];

    
    /* @ngInject */
    function CategoryCtrl(categoriesService, $log) {
        var vm = this;

        vm.categories = null;
        //vm.seleccionarTarea = seleccionarTarea;

        activate();

        function activate() {

        	categoriesService.list()
	        	.then(
	        			function(resp){
	        				console.log(resp.data);
	        				$log.log(resp);
	        				vm.categories = resp.data;
	        			},
	        			function(respErr){
	        				$log.log(respErr);
	        			}
	        	);
        	

        }

        /*
        function seleccionarTarea(idSelectedNotification) {
         
        }
         */

    }
})();

