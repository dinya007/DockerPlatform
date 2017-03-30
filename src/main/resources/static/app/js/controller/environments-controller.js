dockerApp.controller('environmentsController', function ($scope, environmentService) {
    environmentService.getAllEnvironments().then(function (data) {
        $scope.allEnvironments = data;
    });

    $scope.createEnvironment = function(name) {
        var environment = {};
        environment.name = name;

        environmentService.create(environment)
            .then(function(data) {
                $scope.allEnvironments.push(data);
            });
    }

});