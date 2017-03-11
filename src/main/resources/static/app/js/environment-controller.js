dockerApp.controller('environmentController', function ($scope, $routeParams, environmentService) {

    environmentService.getEnvironment($routeParams.environmentId)
        .then(function (data) {
            $scope.activeEnvironment = data;
        });

});