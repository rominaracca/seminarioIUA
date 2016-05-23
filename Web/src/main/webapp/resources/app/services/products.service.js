(function() {
    angular
        .module('seminario')
        .factory('productsService', productsService);

    productsService.$inject = ['$http', 'URL_API_PRODUCT'];

    function productsService(http, URL_API_PRODUCT) {

			var factory = {
				remove: remove,
				update: update,
				create: add,
				list: list,
				search: search
			};

    	return factory;

			////////////////////////////////

  		function list(){
  			return http.get(URL_API_PRODUCT);
  		}

			function search(query){
				return http.get(URL_API_PRODUCT + '?description='+query);
			}

			function add(product){
				return http.post(URL_API_PRODUCT + '/', product);
			}

		 	function update(product){ // FIXME: agregar id
				return http.put(URL_API_PRODUCT + '/' + id, product);
			}

		 	function remove(id){
				return http.delete(URL_API_PRODUCT + '/' + id);
			}
  	}
})();
