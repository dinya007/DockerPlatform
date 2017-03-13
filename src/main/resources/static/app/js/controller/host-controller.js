dockerApp.controller('hostController', function ($scope, hostService) {

    $scope.init = function (host, environmentId) {
        $scope.currentHost = host;

        hostService.getRunningContainers(environmentId)
            .then(function (data) {
                $scope.runningContainers = data;
            });

        hostService.getStoppedContainers(environmentId)
            .then(function (data) {
                $scope.stoppedContainers = data;
            });
    };


});