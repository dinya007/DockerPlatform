dockerApp.controller('logsController', function ($scope, $routeParams, $interval, restService) {

    $interval(function(){
        restService.get("/logs/"+$routeParams.hostName+"/"+$routeParams.containerId)
        .then(function (data) {
            $scope.logs = data;
        });
    }, 1000);

});