var app = angular.module('dockerApp', []);

app.controller('mainCtrl', ['$scope', '$http', '$window', '$timeout', function ($scope, $http, $window, $timeout) {
    $scope.runningContainers = [];
    $scope.stoppedContainers = [];
    $scope.selectedHost = {};
    $scope.selectedImage = {};
    $scope.selectedEnvironment = window.location.href.split("/").pop();

    $http.get("/hosts/all/" + $scope.selectedEnvironment).then(function (data) {
        $scope.hosts = data.data;

        for (var i = 0; i < $scope.hosts.length; i++) {
            var host = $scope.hosts[i];
            $scope.runningContainers[host.id] = {};
            $scope.stoppedContainers[host.id] = {};
            getRunningContainers(host.id);
            getStoppedContainers(host.id);
        }
    }, function (error) {
        alert(error.status + " : " + error.statusText);
    });

    $http.get("/hosts/repositoryImages").then(function (data) {
        $scope.images = data.data;
    }, function (error) {
        alert(error.status + " : " + error.statusText);
    });

    var getRunningContainers = function (hostId) {
        $http.get("/hosts/" + hostId + "/runningContainers").then(function (data) {
            $scope.runningContainers[hostId] = data.data;
        }, function (error) {
            alert(error.status + " : " + error.statusText);
        });
    };

    var getStoppedContainers = function (hostId) {
        $http.get("/hosts/" + hostId + "/stoppedContainers").then(function (data) {
            $scope.stoppedContainers[hostId] = data.data;
        }, function (error) {
            alert(error.status + " : " + error.statusText);
        });
    };

    $scope.setSelectedHost = function (host) {
        $scope.selectedHost = host;
    };

    $scope.setSelectedImage = function (image) {
        $scope.selectedImage = image;
    };

    $scope.updateStoppedContainers = function (hostId) {
        $timeout(function () {
            getStoppedContainers(hostId);
        }, 2000);
    };

    $scope.updateRunningContainers = function (hostId) {
        $timeout(function () {
            getRunningContainers(hostId);
        }, 2000);
    };


    $scope.reloadPage = function () {
        $timeout(function () {
            $window.location.reload();
        }, 1000);
    };

}]);