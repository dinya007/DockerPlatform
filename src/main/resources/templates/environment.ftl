<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html"
      xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html"
      ng-app="dockerApp">
<head>
    <script src="/angular/angular.js"></script>
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/js/bootstrap.js"></script>
    <script src="/js/utils.js"></script>
    <script src="/js/app/appController.js"></script>
    <script src="/js/app/runningContainersController.js"></script>
    <script src="/js/app/selectedImagesController.js"></script>
    <script src="/js/app/stoppedContainersController.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/page.css">
    <link rel="stylesheet" type="text/css" href="/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/bootstrap/css/bootstrap-theme.css">
    <script>

    </script>
    <style>
        select {
            width: 100px;
        }
    </style>
</head>
<body ng-controller="mainCtrl">
<div class="container-fluid">
<#include "modals/newRunAppModal.ftl" >
<#include "modals/newStoppedAppModal.ftl" >

    <table class="table table-striped table-bordered table-condensed text-center" ng-repeat="host in hosts">
        <caption><h3>{{host.name}}</h3><h4>({{host.url}})</h4></caption>
        <tbody>
        <tr>
            <td class="col-lg-6">
            <#include "tables/runningTable.ftl" >
            </td>
            <td class="col-lg-6">
            <#include "tables/stoppedTable.ftl" >
            </td>
        </tr>
        </tbody>
    </table>
</div>

</body>
</html>