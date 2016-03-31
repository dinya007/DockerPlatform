<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html"
      xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
    <script src="/jquery-ui-1.11.4.custom/external/jquery/jquery.js"></script>
    <script src="/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
    <script src="/js/utils.js"></script>
    <link rel="stylesheet" type="text/css" href="/jquery-ui-1.11.4.custom/jquery-ui.min.css">
    <link rel="stylesheet" type="text/css" href="/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css">
    <link rel="stylesheet" type="text/css" href="/jquery-ui-1.11.4.custom/jquery-ui.structure.min.css">
    <link rel="stylesheet" type="text/css" href="/css/page.css">

    <script>
        $(function() {
            $("button").button();
        });

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

            var getContainerAction = function(thisRef) {
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

    <span id="hostName" host-name="${host.name}">Host name: ${host.name}</span>
    <br/>
    Host url: ${host.url}
    </br></br>
    <b>-----------------------------------------------</b>
    </br>
    <b>Running containers:</b>
    </br>
    -----------------------------------------------
    <br/>
    <#list runningContainers as container>
    <span class="space-2">Name: ${container.name} </span>
    </br>
    <span class="space-2">Address: <a href="http://${host.url}:${container.port}"> http://${host.url}:${container.port} </a> </span>
    </br>
    <span class="space-2">Status: ${container.status} </span>
    </br>
    <span class="space-2">Base image: ${container.baseImage} </span>
    <br/>
    <span class="space-2">Networks: </span>
        <#list container.networks as network>
        </br>
        <span class="space-4">Name: ${network} </span>
        </#list>
    </br>
    <button class="container-restart" host-name="${host.name}" container-id="${container.id}">restart</button>
    <button class="container-stop" host-name="${host.name}" container-id="${container.id}">stop</button>
    <button class="container-remove" host-name="${host.name}" container-id="${container.id}">remove</button>
    </br>
    -----------------------------------------------
    </br>
    <#else>
    <span class="space-2">There are no running containers</span>
    </#list>
    </br></br>
    <b>-----------------------------------------------</b>
    </br>
    <b>Stopped containers:</b>
    </br>
    -----------------------------------------------
    <br/>
    <#list stoppedContainers as container>
    <span class="space-2">Name: ${container.name} </span>
    </br>
    <span class="space-2">Address: <a href="http://${host.url}:${container.port}"> http://${host.url}:${container.port} </a> </span>
    </br>
    <span class="space-2">Status: ${container.status} </span>
    </br>
    <span class="space-2">Base image: ${container.baseImage} </span>
    <br/>
    <span class="space-2">Networks: </span>
        <#list container.networks as network>
        </br>
        <span class="space-4">Name: ${network} </span>
        </#list>
    </br>
    <button class="container-start" host-name="${host.name}" container-id="${container.id}">start</button>
    <button class="container-remove" host-name="${host.name}" container-id="${container.id}">remove</button>
    </br>
    -----------------------------------------------
    </br>
    <#else>
    <span class="space-2">There are no stopped containers</span>
    </#list>
    </br>
    <b>-----------------------------------------------</b>
    </br>
    <b>Available images from registry:</b>
    </br>
    <#list images as image>
    -----------------------------------------------
    </br>
    <span class="space-2">${image.name}</span>
        <select>
            <#list image.tags as tag>
                <option value="${image.name}:${tag}">${tag}</option>
            </#list>
        </select>
    <button class="container-create">create</button>
    <button class="container-create-and-start">start</button>
    </br>
    <#else>
    </br>
    <span class="space-2">There are no available images</span>
    </#list>
    </br>
    <b>-----------------------------------------------</b>


</br>

</body>

</html>