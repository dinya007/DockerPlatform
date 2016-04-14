var app = angular.module('dockerApp', []);

app.controller('mainCtrl', ['$scope', '$http', '$location', '$rootScope', function ($scope, $http, $location, $rootScope) {

    $http.get(window.location.pathname + "/hostInfo").then(function (data) {
        $scope.host = data.data;
    }, function (error) {
        alert(error.status + " : " + error.statusText);
    });

    $http.get(window.location.pathname + "/repositoryImages").then(function (data) {
        $scope.images = data.data;
    }, function (error) {
        alert(error.status + " : " + error.statusText);
    });


    $http.get(window.location.pathname + "/runningContainers").then(function (data) {
        $scope.runningContainers = data.data;
    }, function (error) {
        alert(error.status + " : " + error.statusText);
    });

    $http.get(window.location.pathname + "/stoppedContainers").then(function (data) {
        $scope.stoppedContainers = data.data;
    }, function (error) {
        alert(error.status + " : " + error.statusText);
    });

}]);