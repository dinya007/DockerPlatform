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

dockerApp.filter('minus', function(){
    return function(arr1, arr2){
        return arr1.filter(function(n) {
            return arr2.indexOf(n) === -1
        });
    };
});

dockerApp.filter('swarmHosts', function(){
    return function(arr1, arr2){
        return arr1.filter(function(n) {
            return arr2.indexOf(n) === -1
        });
    };
});