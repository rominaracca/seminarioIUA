angular
	.module("seminario")
	.config(function ($stateProvider, $urlRouterProvider) {

    // For any unmatched url, redirect to /
    $urlRouterProvider.otherwise("/main");

    // Set up the states
    $stateProvider
      .state('main', {
        url: '/main',
        templateUrl: 'app/views/main.html'
      })
      // .state('main.product', {
      //   url: '/product',
      //   templateUrl: '../app/views/products.views.html',
      //   controller: 'ProductCtrl',
      //   controllerAs: 'vm'
      // })

  });
