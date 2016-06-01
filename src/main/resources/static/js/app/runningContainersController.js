app.controller('runningContainersController', ['$scope', '$http', '$window', function ($scope, $http, $window) {

    $scope.restartContainer = function () {
        $http.post("/action/restartContainer/", getContainerInfo($scope));

        $scope.updateRunningContainers($scope.host.id);
    };

    $scope.stopContainer = function () {
        $http.post("/action/stopContainer/", getContainerInfo($scope));

        $scope.updateRunningContainers($scope.host.id);
        $scope.updateStoppedContainers($scope.host.id);
    };

    $scope.removeContainer = function () {
        $http.delete("/action/removeContainer/" + $scope.host.name + "/" + $scope.container.id);

        $scope.updateRunningContainers($scope.host.id);
    };

    $scope.openLogs = function () {
        $window.open('/logs/?hostName=' + $scope.host.name + '&containerId=' + $scope.container.id, '_blank');
    };

}]);