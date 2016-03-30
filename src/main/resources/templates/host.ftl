<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
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

            $(".container-start").click(function () {
                var createContainerAction = {}
                createContainerAction.hostName = $(this).attr('host-name');
                createContainerAction.imageName = $(this).attr("image-name");
                createContainerAction.imageTag = $(this).attr("image-tag");

                $.putRq("/action/createContainer", JSON.stringify(createContainerAction), null);
            });


            $(".container-stop").click(function () {
                var stopContainerAction = {}
                stopContainerAction.hostName = $(this).attr('host-name');
                stopContainerAction.containerId = $(this).attr("container-id");

                $.postRq("/action/stopContainer", JSON.stringify(stopContainerAction), null);
            });

            $(".container-remove").click(function () {
                var removeContainerAction = {}
                removeContainerAction.hostName = $(this).attr('host-name');
                removeContainerAction.containerId = $(this).attr("container-id");

                $.deleteRq("/action/removeContainer", JSON.stringify(removeContainerAction), null);
            });

        });
    </script>

</head>

<body>

    Host name: ${host.name}
    <br/>
    Host url: ${host.url}
    </br></br>
    Available images from registry:
    </br>

    <#list images as image>
    <span style="margin-left:2em">${image.name}</span>
        </br>
        <#list image.tags as tag>
            <span style="margin-left:4em">${tag} <button class="container-start" host-name="${host.name}" image-name="${image.name}" image-tag="${tag}">start</button></span>
            </br>
        </#list>
    </br>

    <#else>
    There are no available images
    </#list>

</br>
Running containers:
    </br>
    -----------------------------------------------
    <br/>
    <#list containers as container>
    <span style="margin-left:2em">Name: ${container.name} </span>
    </br>
    <span style="margin-left:2em">Address: <a href="http://${host.url}:${container.port}"> http://${host.url}:${container.port} </a> </span>
    </br>
    <span style="margin-left:2em">Status: ${container.status} </span>
    <br/>
    <span style="margin-left:2em">Networks: </span>
        <#list container.networks as network>
        </br>
        <span style="margin-left:4em">Name: ${network} </span>
        </#list>
    </br>
    <button class="container-remove" host-name="${host.name}" container-id="${container.id}">remove</button>
    <button class="container-stop" host-name="${host.name}" container-id="${container.id}">stop</button>
    </br>
    -----------------------------------------------
    </br>
    <#else>
    <span style="margin-left:2em">There are no running containers</span>
    </#list>


</body>

</html>