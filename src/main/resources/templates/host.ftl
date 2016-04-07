<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html"
      xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html"
      ng-app="dockerApp">
<head>
    <script src="/js/jquery.js"></script>
    <script src="/js/utils.js"></script>
    <script src="/angular/angular.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/page.css">
    <link rel="stylesheet" type="text/css" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/bootstrap/css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="/bootstrap/js/bootstrap.min.js">
    <script>

        var app = angular.module('dockerApp', []);

        app.controller('mainCtrl', ['$scope', '$http', '$location', '$rootScope', function ($scope, $http, $location, $rootScope) {
            $scope.createContainer = function () {
                $http.put("/action/createContainer", createContainerAction);
            };

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

        $(document).ready(function () {

            $(".container-create-and-start").click(function () {
                var createContainerAction = {}
                createContainerAction.hostName = $("#hostName").attr("host-name");
                createContainerAction.imageName = $(this).prev().prev().val();

                $.putRq("/action/createStartContainer", JSON.stringify(createContainerAction), null);
            });

            $(".container-create").click(function () {

                var createContainerAction = {}
                createContainerAction.hostName = $("#hostName").attr("host-name");
                createContainerAction.imageName = $(this).prev().val();

                $http.put("/action/createContainer", createContainerAction);

//                $.putRq("/action/createContainer", JSON.stringify(createContainerAction), null);
            });

            $(".container-start").click(function () {
                $.postRq("/action/startContainer", JSON.stringify(getContainerAction(this)), null);
            });


            $(".container-stop").click(function () {
                $.postRq("/action/stopContainer", JSON.stringify(getContainerAction(this)), null);
            });

            $(".container-remove").click(function () {
                $.deleteRq("/action/removeContainer", JSON.stringify(getContainerAction(this)), null);
            });

            $(".container-restart").click(function () {
                $.postRq("/action/restartContainer", JSON.stringify(getContainerAction(this)), null);
            });

            var getContainerAction = function (thisRef) {
                var containerAction = {}
                containerAction.hostName = $(thisRef).attr('host-name');
                containerAction.containerId = $(thisRef).attr("container-id");
                return containerAction;
            }


        });
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
    <tr ng-repeat="container in runningContainers">
        <td>{{container.name}}</td>
        <td><a href="http://{{host.url}}:{{container.port}}"> http://{{host.url}}:{{container.port}}</a></td>
        <td>{{container.status}}</td>
        <td>{{container.baseImage}}</td>
        <td>
            <p ng-repeat="network in container.networks">{{network}}</p>
        </td>
        <td>
            <button class="container-restart" host-name="{{host.name}}" container-id="{{container.id}}">restart
            </button>
            <button class="container-stop" host-name="{{host.name}}" container-id="{{container.id}}">stop</button>
            <button class="container-remove" host-name="{{host.name}}" container-id="{{container.id}}">remove</button>
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
    <tr ng-repeat="container in stoppedContainers">
        <td>{{container.name}}</td>
        <td>{{container.status}}</td>
        <td>{{container.baseImage}}</td>
        <td>
            <p ng-repeat="network in container.networks">{{network}}</p>
        </td>
        <td>
            <button class="container-start" host-name="{{host.name}}" container-id="{{container.id}}">start</button>
            <button class="container-remove" host-name="{{host.name}}" container-id="{{container.id}}">remove</button>
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
    <tr ng-repeat="image in images">
        <td>{{image.name}}</td>
        <td>
            <select>
                <option ng-repeat="tag in image.tags" value="{{image.name}}:{{tag}}">{{tag}}</option>
            </select>
        </td>
        <td>
            <button ng-click="createContainer()">create</button>
            <button class="container-create-and-start">start</button>
        </td>
    </tr>
    </tbody>
</table>

</body>
</html>