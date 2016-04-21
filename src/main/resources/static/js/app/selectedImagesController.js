app.controller('selectedImageController', ['$scope', '$http', function ($scope, $http) {
    $scope.createContainer = function () {
        var containerCreationDto = {};

        containerCreationDto.hostName = $scope.selectedHost.name;
        containerCreationDto.imageName = $scope.selectedImage;
        $http.put("/action/createContainer", containerCreationDto);

        $scope.updateStoppedContainers($scope.selectedHost.id);
    };

    $scope.createAndStartContainer = function () {
        var createContainerAction = {};
        createContainerAction.hostName = $scope.selectedHost.name;
        createContainerAction.imageName = $scope.selectedImage;

        $http.put("/action/createStartContainer", createContainerAction);

        $scope.updateRunningContainers($scope.selectedHost.id);
    };

}]);
