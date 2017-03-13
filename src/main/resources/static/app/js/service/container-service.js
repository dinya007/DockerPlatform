dockerApp.service('containerService', function (restService) {

    $scope.removeContainer = function () {
        restService.delete("/action/removeContainer/" + $scope.host.name + "/" + $scope.container.id);
    };

    $scope.startContainer = function () {
        $http.post("/action/startContainer/", getContainerInfo($scope));

        $scope.updateRunningContainers($scope.host.id);
        $scope.updateStoppedContainers($scope.host.id);
    };

});
