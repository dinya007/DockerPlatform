dockerApp.controller('environmentsController', function ($scope, environmentService, hostService) {

    environmentService.getAllEnvironments().then(function (data) {
        $scope.allEnvironments = data;
    });

    hostService.getAllHosts()
        .then(function (data) {
            $scope.allHosts = data;
        });

    $scope.createEnvironment = function(name, swarmHost) {
        var environment = {};
        environment.hosts = [];
        environment.name = name;
        environment.hosts.push(JSON.parse(swarmHost));

        environmentService.create(environment)
            .then(function(data) {
                $scope.allEnvironments.push(data);
            });
    }

});