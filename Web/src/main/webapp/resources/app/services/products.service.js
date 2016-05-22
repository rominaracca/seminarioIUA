angular.module('compras')
.constant('URL_API_ROOT', '../api/v1/product')
.factory('productsService', [ '$http', 'URL_API_ROOT', function($http, URL_API_ROOT){
	//similar a productRSController
	
	return {
		
		list: function(){
			return $http.get(URL_API_ROOT + '/list');
		},
		search: function(q){
			return $http.get(URL_API_ROOT + '/list/filter?q='+q);
		},
		add:  function(product){
			return $http.post(URL_API_ROOT + '/', product);
		},
		update: function(product){
			return $http.put(URL_API_ROOT + '/', product);
		},
		delete: function(id){
			return $http.delete(URL_API_ROOT + '/' + id);
		}
	};
	
	
}])