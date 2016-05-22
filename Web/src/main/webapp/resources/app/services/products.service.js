angular.module('compras')
.constant('URL_API_ROOT', '../api/v1/products')
.factory('productsService', [ '$http', 'URL_API_ROOT', function($http, URL_API_ROOT){
	
	return {
		
		list: function(){
			return $http.get(URL_API_ROOT);
		},
		search: function(q){
			return $http.get(URL_API_ROOT + '?description='+q);
		},
		add:  function(product){
			return $http.post(URL_API_ROOT + '/', product);
		},
		update: function(product){
			return $http.put(URL_API_ROOT + '/' + id, product);
		},
		remove: function(id){
			return $http.delete(URL_API_ROOT + '/' + id);
		}
	};
	
	
}])