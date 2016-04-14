<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html"
      xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html"
      ng-app="dockerApp">
<head>
    <script src="/js/jquery.js"></script>
    <script src="/js/utils.js"></script>
    <script src="/angular/angular.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/page.css">
    <link rel="stylesheet" type="text/css" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/bootstrap/css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="/bootstrap/js/bootstrap.min.js">
    <script>

        var app = angular.module('dockerApp', []);

        app.controller('runningContainersController', ['$scope', '$http', function ($scope, $http) {

            $scope.restartContainer = function () {
                $http.post("/action/restartContainer/", getContainerAction($scope));
            };

            $scope.stopContainer = function () {
                $http.post("/action/stopContainer/", getContainerAction($scope));
            };

            $scope.removeContainer = function () {
                $http.delete("/action/removeContainer/" + $scope.host.name + "/" + $scope.container.id);
            };

        }]);

        app.controller('stoppedContainersController', ['$scope', '$http', function ($scope, $http) {
            $scope.removeContainer = function () {
                $http.delete("/action/removeContainer/" + $scope.host.name + "/" + $scope.container.id);
            };

            $scope.startContainer = function () {
                $http.post("/action/startContainer/", getContainerAction($scope));
            };

        }]);

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

        app.controller('mainCtrl', ['$scope', '$http', '$location', '$rootScope', function ($scope, $http, $location, $rootScope) {

            $http.get(window.location.pathname + "/hostInfo").then(function (data) {
                $scope.host = data.data;
            }, function (error) {
                alert(error);
            });

            $http.get(window.location.pathname + "/repositoryImages").then(function (data) {
                $scope.images = data.data;
            }, function (error) {
                alert(error);
            });


            $http.get(window.location.pathname + "/runningContainers").then(function (data) {
                $scope.runningContainers = data.data;
            }, function (error) {
                alert(error);
            });

            $http.get(window.location.pathname + "/stoppedContainers").then(function (data) {
                $scope.stoppedContainers = data.data;
            }, function (error) {
                alert(error);
            });

        }]);

        var getContainerAction = function(scope) {
            var containerAction = {};
            containerAction.hostName = scope.host.name;
            containerAction.containerId = scope.container.id;
            return containerAction;
        };

//        $(document).ready(function () {
//
//            $(".container-create-and-start").click(function () {
//                var createContainerAction = {}
//                createContainerAction.hostName = $("#hostName").attr("host-name");
//                createContainerAction.imageName = $(this).prev().prev().val();
//
//                $.putRq("/action/createStartContainer", JSON.stringify(createContainerAction), null);
//            });
//
//            $(".container-create").click(function () {
//
//                var createContainerAction = {}
//                createContainerAction.hostName = $("#hostName").attr("host-name");
//                createContainerAction.imageName = $(this).prev().val();
//
//                $http.put("/action/createContainer", createContainerAction);
//
////                $.putRq("/action/createContainer", JSON.stringify(createContainerAction), null);
//            });
//
//            $(".container-start").click(function () {
//                $.postRq("/action/startContainer", JSON.stringify(getContainerAction(this)), null);
//            });
//
//
//            $(".container-stop").click(function () {
//                $.postRq("/action/stopContainer", JSON.stringify(getContainerAction(this)), null);
//            });
//
//            $(".container-remove").click(function () {
//                $.deleteRq("/action/removeContainer", JSON.stringify(getContainerAction(this)), null);
//            });
//
//            $(".container-restart").click(function () {
//                $.postRq("/action/restartContainer", JSON.stringify(getContainerAction(this)), null);
//            });
    </script>
    <style>
        select {
            width: 100px;
        }
    </style>
</head>
<body ng-controller="mainCtrl">

<table class="table table-striped table-bordered text-center">
    <tbody>
    <tr>
        <th class="text-center">Host name</th>
        <th class="text-center">Host url</th>
    </tr>
    <tr>
        <td id="hostName" host-name="{{host.name}}">{{host.name}}</td>
        <td>{{host.url}}</td>
    </tr>
    </tbody>
</table>

<table class="table table-striped table-bordered text-center">
    <caption><h4>Running containers</h4></caption>
    <tbody>
    <tr>
        <th class="text-center">Name</th>
        <th class="text-center">Address</th>
        <th class="text-center">Status</th>
        <th class="text-center">Base image</th>
        <th class="text-center">Networks</th>
        <th class="text-center">Actions</th>
    </tr>
    <tr ng-repeat="container in runningContainers" ng-controller="runningContainersController">
        <td>{{container.name}}</td>
        <td><a href="http://{{host.url}}:{{container.port}}"> http://{{host.url}}:{{container.port}}</a></td>
        <td>{{container.status}}</td>
        <td>{{container.baseImage}}</td>
        <td>
            <p ng-repeat="network in container.networks">{{network}}</p>
        </td>
        <td>
            <button ng-click="restartContainer()">restart</button>
            <button ng-click="stopContainer()">stop</button>
            <button ng-click="removeContainer()">remove</button>
        </td>
    </tr>
    </tbody>
</table>

<table class="table table-striped table-bordered text-center">
    <caption><h4>Stopped containers</h4></caption>
    <tbody>
    <tr>
        <th class="text-center">Name</th>
        <th class="text-center">Status</th>
        <th class="text-center">Base image</th>
        <th class="text-center">Networks</th>
        <th class="text-center">Actions</th>
    </tr>
    <tr ng-repeat="container in stoppedContainers" ng-controller="stoppedContainersController">
        <td>{{container.name}}</td>
        <td>{{container.status}}</td>
        <td>{{container.baseImage}}</td>
        <td>
            <p ng-repeat="network in container.networks">{{network}}</p>
        </td>
        <td>
            <button ng-click="startContainer()">start</button>
            <button ng-click="removeContainer()">remove</button>
        </td>
    </tr>
    </tbody>
</table>

<table class="table table-striped table-bordered text-center">
    <caption><h4>Available images from registry</h4></caption>
    <tbody>
    <tr>
        <th class="text-center">Name</th>
        <th class="text-center">Versions</th>
        <th class="text-center">Actions</th>
    </tr>
    <tr ng-repeat="image in images" ng-controller="selectedImageController">
        <td>{{image.name}}</td>
        <td>
            <select ng-change="$scope.selectedImage = selectedImage" ng-model="selectedImage">
                <option ng-repeat="tag in image.tags" value="{{image.name}}:{{tag}}">{{tag}}</option>
            </select>
        </td>
        <td>
            <button ng-click="createContainer()">create</button>
            <button ng-click="createAndStartContainer()">start</button>
        </td>
    </tr>
    </tbody>
</table>

</body>
</html>