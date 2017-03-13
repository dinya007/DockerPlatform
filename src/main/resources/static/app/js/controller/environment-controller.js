dockerApp.controller('environmentController', function ($scope, $routeParams, environmentService, hostService) {

    $scope.init = function(){
        $scope.environmentId = $routeParams.environmentId;

        environmentService.getEnvironment($scope.environmentId)
            .then(function (data) {
                $scope.activeEnvironment = data;
            });

        hostService.getAllHosts($scope.environmentId)
            .then(function (data) {
                $scope.hosts = data;
            });
    };



});