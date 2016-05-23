(function() {

  angular
  .module('seminario')
  .controller('editProductController', editProductCtrl);

  editProductCtrl.$inject = ['$mdDialog', 'categoriesService', 'product'];

  /* @ngInject */
  function editProductCtrl(dialog, categoriesService, product) {

    var vm = this;
    console.log(product);

    vm.product = {};
    vm.separator = [];
    vm.autocomplete = "";
    vm.categories = [];

    vm.cancel = cancel;
    vm.querySearch = querySearch;
    vm.submit = submit;

    activate();

    /////////////////////////////////////////

    function activate() {
      angular.copy(product, vm.product);
      vm.separator.push(32);//whitespace code
      vm.separator.push(9); //tab code
      categoriesService.list()
      .then(
        function(resp){
          vm.categories = resp.data;
        }
      );
    }

    function querySearch (query) {
      var results = query ? vm.categories.filter( createFilterFor(query) ) : [];
      return results;
    }

    function createFilterFor(query) {
      var lowercaseQuery = angular.lowercase(query);
      return function filterFn(category) {
        return (angular.lowercase(category.description).indexOf(lowercaseQuery) === 0);
      };
    }

    function submit() {
      dialog.hide(vm.product);
    }

    function cancel() {
      dialog.cancel();
    }
  }
})();
