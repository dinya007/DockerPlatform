var app = angular.module('logs', ['ngRoute']);

app.controller('logController', ['$scope', '$http', '$interval', function ($scope, $http, $interval) {

    $scope.logs = [];

    $scope.hostName = location.search.split('?')[1].split('&')[0].split('=')[1];
    $scope.containerId = location.search.split('?')[1].split('&')[1].split('=')[1];

    $interval(function(){
        $http.get("/logs/" + $scope.hostName + "/" + $scope.containerId).then(function (data) {
            $scope.logs = data.data;
        }, function (error) {
            alert(error.status + " : " + error.statusText);
        });
    }, 2000);

}]);