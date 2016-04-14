<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html"
      xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html"
      ng-app="dockerApp">
<head>
    <script src="/angular/angular.js"></script>
    <script src="/js/jquery.js"></script>
    <script src="/js/utils.js"></script>
    <script src="/js/appController.js"></script>
    <script src="/js/runningContainersController.js"></script>
    <script src="/js/selectedImagesController.js"></script>
    <script src="/js/stoppedContainersController.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/page.css">
    <link rel="stylesheet" type="text/css" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/bootstrap/css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="/bootstrap/js/bootstrap.min.js">
    <script>

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
            <button ng-click="getLogs()">logs</button>
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