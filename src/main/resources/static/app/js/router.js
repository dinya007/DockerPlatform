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

dockerApp.filter('minus', function () {
    return function (allHosts, hosts) {
        var newHosts = [];
        for (var i = 0; i < allHosts.length; i++) {
            var add = true;
            for (var j = 0; j < hosts.length; j++) {
                if (allHosts[i].id === hosts[j].id) add = false;
            }
            if (add) newHosts.push(allHosts[i]);
        }
        return newHosts;
    };
});

dockerApp.filter('swarmMasters', function () {
    return function (allHosts) {
        if (allHosts !== undefined) {
            var swarmHosts = [];
            for (var i = 0; i < allHosts.length; i++) {
                var host = allHosts[i];
                if (host.swarmMaster) swarmHosts.push(host);
            }
            return swarmHosts;
        }
    };
});