dockerApp.controller('environmentController', function ($scope, $routeParams, $window, $timeout, environmentService, hostService, imageService, containerService) {

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

        imageService.getRepositoryImages()
            .then(function (data) {
            $scope.repositoryImages = data;
        });

    };

    $scope.setSelectedHost = function(host){
        $scope.selectedHost = host;
    };

    $scope.setSelectedImage = function(image){
        $scope.selectedImage = image;
    };

    $scope.createContainer = function (selectedContainerName, selectedPort, isStart) {
        var container = {};
        container.imageName = $scope.selectedImage;
        container.hostName = $scope.selectedHost.name;
        container.name = selectedContainerName;
        container.port = selectedPort;
        container.environmentId = $scope.environmentId;

        containerService.create(container, isStart).then(function (data) {
            $timeout(function () {
                $scope.init();
            }, 5000);

        });
    };

    $scope.openLogs = function (host, container) {
        $window.open("#/logs/" + host.name + "/" + container.id, "_blank");
    };

});