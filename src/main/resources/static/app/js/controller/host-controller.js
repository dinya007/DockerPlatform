dockerApp.controller('hostController', function ($scope, $window, hostService, containerService) {

    var update = function(){
        hostService.getRunningContainers($scope.environmentId)
            .then(function (data) {
                $scope.runningContainers = data;
            });

        hostService.getStoppedContainers($scope.environmentId)
            .then(function (data) {
                $scope.stoppedContainers = data;
            });
    };

    $scope.init = function (host, environmentId) {
        $scope.currentHost = host;
        $scope.environmentId = environmentId;
        update();
    };

    $scope.startContainer = function (container) {
        containerService.start(container).then(function (data) {
            update();
        });

    };

    $scope.deleteContainer = function (container) {
        containerService.delete(container).then(function (data) {
            update();
        });

    };

    $scope.restartContainer = function (container) {
        containerService.restart(container).then(function (data) {
            update();
        });

    };

    $scope.stopContainer = function (container) {
        containerService.stop(container).then(function (data) {
            update();
        });

    };

});