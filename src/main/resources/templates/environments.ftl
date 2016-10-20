<!DOCTYPE html>

<html lang="en">
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
</head>
<body>

<table class="table table-striped table-bordered text-center">
    <caption><h4>Enabled environments</h4></caption>
    <tbody>
    <tr>
        <th class="text-center">Name</th>
    </tr>
    <#list environments as environment>
    <tr>
        <td><a href="/env/${environment.id}">${environment.name}</a></td>
    </tr>
    </#list>
    </tbody>
</table>


</body>

</html>