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
				list: list
			};

    	return factory;

			////////////////////////////////

			function list(query){
        if(query)
			    return http.get(URL_API_PRODUCT + '?description='+query);
        else
          return http.get(URL_API_PRODUCT);
			}

			function add(product){
				return http.post(URL_API_PRODUCT, product);
			}

		 	function update(product){
				return http.put(URL_API_PRODUCT + '/' + product.id, product);
			}

		 	function remove(id){
				return http.delete(URL_API_PRODUCT + '/' + id);
			}
  	}
})();
