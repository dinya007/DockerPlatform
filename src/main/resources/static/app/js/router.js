var dockerApp = angular.module('dockerApp', ['ngRoute']);

dockerApp.config(function ($routeProvider) {
    $routeProvider.when('/', {
        controller: 'environmentsController',
        templateUrl: '/app/views/environments.html'
    })
        .when('/environment/:environmentId', {
            controller: 'environmentController',
            templateUrl: '/app/views/environment.html'
        })
        .otherwise({
            redirectTo: '/'
        });
});