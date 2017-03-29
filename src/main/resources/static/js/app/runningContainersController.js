app.controller('runningContainersController', ['$scope', '$http', '$location', function ($scope, $http, $location) {

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

    $scope.openLogs = function (host, container) {
        // <
        // a
        // ng - href = "#/logs/{{host.name}}/{{container.id}}"
        // target = "_blank" > logs
            // < / a >
            // $location.path("#/logs/" + host.name + "/" + container.id + ")", "_blank");
        $window.open("#/logs/" + host.name + "/" + container.id + ")", "_blank");
        // $window.open('/logs/?hostName=' + $scope.host.name + '&containerId=' + $scope.container.id, '_blank');
    };

}]);
