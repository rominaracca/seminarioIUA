(function() {

  angular
  .module('seminario')
  .controller('newProductController', newProductCtrl);

  newProductCtrl.$inject = ['$mdDialog', 'categoriesService'];

  /* @ngInject */
  function newProductCtrl(dialog, categoriesService) {

    var vm = this;

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
      vm.product = {
        description: "",
        tags: [],
        category: "",
        price: "",
        code: ""
      };
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
