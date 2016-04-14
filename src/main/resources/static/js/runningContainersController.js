app.controller('runningContainersController', ['$scope', '$http', '$window', function ($scope, $http, $window) {

    $scope.restartContainer = function () {
        $http.post("/action/restartContainer/", getContainerInfo($scope));
    };

    $scope.stopContainer = function () {
        $http.post("/action/stopContainer/", getContainerInfo($scope));
    };

    $scope.removeContainer = function () {
        $http.delete("/action/removeContainer/" + $scope.host.name + "/" + $scope.container.id);
    };

    $scope.openLogs = function () {
        $window.open('/logs/?hostName=' + $scope.host.name + '&containerId=' + $scope.container.id, '_blank');
    };

}]);
