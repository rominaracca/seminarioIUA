(function() {
    'use strict';

    angular
        .module('seminario')
        .factory('categoriesService', categoriesService);
    
    categoriesService.$inject = ['$http', 'URL_API_CATEGORY'];
    
    function categoriesService($http, URL_API_CATEGORY) { 
    	return {
    		
    		list: function(){
    			return $http.get(URL_API_CATEGORY);
    		}
    	};
    }
})();