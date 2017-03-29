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
        .when('/logs/:hostName/:containerId', {
            controller: 'logsController',
            templateUrl: '/app/views/logs.html'
        })
        .otherwise({
            redirectTo: '/'
        });
});