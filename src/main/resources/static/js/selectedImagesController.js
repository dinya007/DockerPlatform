app.controller('selectedImageController', ['$scope', '$http', function ($scope, $http) {
    $scope.createContainer = function () {
        var containerCreationDto = {};

        containerCreationDto.hostName = $scope.host.name;
        containerCreationDto.imageName = $scope.selectedImage;
        $http.put("/action/createContainer", containerCreationDto);
    };

    $scope.createAndStartContainer = function () {
        var createContainerAction = {};
        createContainerAction.hostName = $scope.host.name;
        createContainerAction.imageName = $scope.selectedImage;

        $http.put("/action/createStartContainer", createContainerAction);
    };

}]);
