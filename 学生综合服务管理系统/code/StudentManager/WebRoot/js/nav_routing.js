var routerApp = angular.module('routerApp', ['ui.router']);
routerApp.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/home');
    $stateProvider
    .state('home', {
        templateUrl: 'tpls/home.html'
    })
    .state('about', {
        templateUrl: 'tpls/about.html'
    });
});

