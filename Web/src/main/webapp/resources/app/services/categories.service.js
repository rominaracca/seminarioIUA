(function() {
    'use strict';

    angular
        .module('seminario')
        .factory('categoriesService', categoriesService);

    categoriesService.$inject = ['$http', 'URL_API_CATEGORY'];

    function categoriesService(http, URL_API_CATEGORY) {

    	var factory = {
    			list: list,
    			getProducts: getProducts,
    			add: add,
    			update: update
    	};

    	return factory;

    	function list(){
    		return http.get(URL_API_CATEGORY);
  		}

    	function getProducts(id){
    		return http.get(URL_API_CATEGORY + '/' + id + '/products');
    	}
    	
    	function update(id, category){
    		return http.put(URL_API_CATEGORY + '/' + id, category);
  		}

    	function add(category){
			return http.post(URL_API_CATEGORY, category);
		}
    	
    }
})();
