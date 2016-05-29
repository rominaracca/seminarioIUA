(function() {

  angular
  .module('seminario')
  .controller('editProductController', editProductCtrl);

  editProductCtrl.$inject = ['$mdDialog', 'categoriesService', 'product'];

  /* @ngInject */
  function editProductCtrl(dialog, categoriesService, product) {

    var vm = this;

    vm.product = {};
    vm.separator = [];
    vm.autocomplete = "";
    vm.categories = [];

    vm.cancel = cancel;
    vm.submit = submit;

    activate();

    /////////////////////////////////////////

    function activate() {
      angular.copy(product, vm.product);
      vm.product.category = JSON.stringify(vm.product.category);
      vm.separator.push(32);//whitespace code
      vm.separator.push(9); //tab code
      categoriesService.list()
      .then(
        function(resp){
          vm.categories = resp.data;
        }
      );
    }

    function submit() {
      if(!vm.product.category){
        vm.newForm.category.$touched = true;
        return;
      }
      vm.product.category = JSON.parse(vm.product.category);
      dialog.hide(vm.product);
    }

    function cancel() {
      dialog.cancel();
    }
  }
})();
