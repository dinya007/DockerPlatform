app.controller('runningContainersController', ['$scope', '$http', function ($scope, $http) {

    $scope.restartContainer = function () {
        $http.post("/action/restartContainer/", getContainerInfo($scope));
    };

    $scope.stopContainer = function () {
        $http.post("/action/stopContainer/", getContainerInfo($scope));
    };

    $scope.removeContainer = function () {
        $http.delete("/action/removeContainer/" + $scope.host.name + "/" + $scope.container.id);
    };

    $scope.getLogs = function () {
        $http.get("/hosts/logs/" + $scope.host.name + "/" + $scope.container.id);
    };

}]);
