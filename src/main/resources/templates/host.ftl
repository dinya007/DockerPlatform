<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html"
      xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
    <script src="/js/jquery.js"></script>
    <script src="/js/utils.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/page.css">
    <link rel="stylesheet" type="text/css" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/bootstrap/css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="/bootstrap/js/bootstrap.min.js">


    <script>

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

                $.putRq("/action/createContainer", JSON.stringify(createContainerAction), null);
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

<body>


<div class="container">
    <div class="row">
        <div class="col-lg-3">
            <h3 id="hostName" host-name="${host.name}">Host name: ${host.name}</h3>
        </div>
    </div>
    <div class="row">
        <div class="col lg-3">
            <h3>Host url: ${host.url}</h3>
        </div>
    </div>
</div>

<div class="container">
    <h4>Running containers:</h4>


<#list runningContainers as container>

    <div class="row">
        <div class="col-lg-3">
            <p>Name: ${container.name} </p>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-3">
            <p>Address: <a href="http://${host.url}:${container.port}"> http://${host.url}:${container.port} </a></p>
        </div>
    </div>


    <div class="row">
        <div class="col-lg-3">
            <p>Status: ${container.status}</p>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-3">
            <p>Base image: ${container.baseImage}</p>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-3">
            <p>Networks: </p>
            <div class="container">
                <div class="col-lg-3">
                    <#list container.networks as network>
                        <div class="row">Name: ${network}</div>
                    </#list>
                </div>
            </div>
        </div>
    </div>

    <button class="container-restart" host-name="${host.name}" container-id="${container.id}">restart</button>
    <button class="container-stop" host-name="${host.name}" container-id="${container.id}">stop</button>
    <button class="container-remove" host-name="${host.name}" container-id="${container.id}">remove</button>
<#else>
    <span class="space-2">There are no running containers</span>
</#list>

</div>


<div class="container">
    <h4>Stopped containers:</h4>


<#list stoppedContainers as container>

    <div class="row">
        <div class="col-lg-3">
            <p>Name: ${container.name} </p>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-3">
            <p>Address: <a href="http://${host.url}:${container.port}"> http://${host.url}:${container.port} </a></p>
        </div>
    </div>


    <div class="row">
        <div class="col-lg-3">
            <p>Status: ${container.status}</p>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-3">
            <p>Base image: ${container.baseImage}</p>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-3">
            <p>Networks: </p>
            <div class="container">
                <div class="col-lg-3">
                    <#list container.networks as network>
                        <div class="row">Name: ${network}</div>
                    </#list>
                </div>
            </div>
        </div>
    </div>

    <button class="container-start" host-name="${host.name}" container-id="${container.id}">start</button>
    <button class="container-remove" host-name="${host.name}" container-id="${container.id}">remove</button>
<#else>
    <span class="space-2">There are no stopped containers</span>
</#list>

</div>


<table class="table table-striped table-bordered text-center">
    <caption><h4>Available images from registry</h4></caption>
    <tbody>
    <#list images as image>
    <tr>

        <td>${image.name}</td>
        <td>
            <select>
                <#list image.tags as tag>
                    <option value="${image.name}:${tag}">${tag}</option>
                </#list>
            </select>
        </td>
        <td>
            <button class="container-create">create</button>
        </td>
        <td>
            <button class="container-create-and-start">start</button>
        </td>
    </tr>
    <#else>
    <tr>
        <td>There are no available images</td>
    </tr>
    </#list>
    </tbody>


</table>
<div class="container">
    <div class="col-lg-3">
        <p>
        <h4></h4>
        </p>
    </div>
</div>
<div class="conatainer">
    <div class="col-lg-3">


    </div>

</div>

</body>

</html>