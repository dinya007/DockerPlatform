app.controller('selectedImageController', ['$scope', '$http', function ($scope, $http) {
    $scope.createContainer = function () {
        var containerCreationDto = {};

        containerCreationDto.hostName = $scope.selectedHost.name;
        containerCreationDto.imageName = $scope.selectedImage;
        containerCreationDto.name = $scope.selectedAppName;
        containerCreationDto.port = $scope.selectedPort;
        containerCreationDto.environmentId = $scope.selectedEnvironment;

        $http.put("/action/createContainer", containerCreationDto);
        
        $scope.updateStoppedContainers($scope.selectedHost.id);
    };

    $scope.createAndStartContainer = function () {
        var containerCreationDto = {};
        containerCreationDto.hostName = $scope.selectedHost.name;
        containerCreationDto.imageName = $scope.selectedImage;
        containerCreationDto.name = $scope.selectedAppName;
        containerCreationDto.port = $scope.selectedPort;
        containerCreationDto.environmentId = $scope.selectedEnvironment;

        $http.put("/action/createStartContainer", containerCreationDto);

        $scope.updateRunningContainers($scope.selectedHost.id);
    };

}])
;
