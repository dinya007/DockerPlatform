app.controller('stoppedContainersController', ['$scope', '$http', function ($scope, $http) {
    $scope.removeContainer = function () {
        $http.delete("/action/removeContainer/" + $scope.host.name + "/" + $scope.container.id);
        $scope.updateStoppedContainers($scope.host.id);
    };

    $scope.startContainer = function () {
        $http.post("/action/startContainer/", getContainerInfo($scope));

        $scope.updateRunningContainers($scope.host.id);
        $scope.updateStoppedContainers($scope.host.id);
    };

}]);
