<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html"
      xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html"
      ng-app="logs">
<head>
    <script src="/angular/angular.js"></script>
    <script src="/angular/angular-route.js"></script>
    <script src="/js/app/logs.js"></script>
</head>
<body ng-controller="logController">

<p ng-repeat="log in logs track by $index">{{log}}</p>

</body>