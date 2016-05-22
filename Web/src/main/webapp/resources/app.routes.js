angular
	.config(function ($stateProvider, $urlRouterProvider) {

    // For any unmatched url, redirect to /
    $urlRouterProvider.otherwise("/product");

    // Set up the states
    $stateProvider
      .state('product', {
        url: '/product',
        templateUrl: 'app/views/products.views.html',
        controller: 'ProductCtrl',
        controllerAs: 'vm'
      })

  });