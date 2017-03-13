dockerApp.controller('environmentsController', function ($scope, environmentService) {
    environmentService.getAllEnvironments().then(function (data) {
        $scope.allEnvironments = data;
    });
});