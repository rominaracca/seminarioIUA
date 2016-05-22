(function() {
    'use strict';

    angular
        .module('seminario')
        .factory('categoriesService', categoriesService);

    factory.$inject = ['$http', 'URL_API_CATEGORY'];
    
    function categoriesService() { 
    	return {
    		
    		list: function(){
    			return $http.get(URL_API_CATEGORY);
    		}
    	};
    }
})();