<!DOCTYPE html>

<html lang="en">
<head>
    <script src="/jquery-ui-1.11.4.custom/external/jquery/jquery.js"></script>
    <script src="/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/jquery-ui-1.11.4.custom/jquery-ui.min.css">
    <link rel="stylesheet" type="text/css" href="/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css">
    <link rel="stylesheet" type="text/css" href="/jquery-ui-1.11.4.custom/jquery-ui.structure.min.css">
    <link rel="stylesheet" type="text/css" href="/css/page.css">
    <script>
        $(function() {
            $( "button" ).button();
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
            <span style="margin-left:4em">${tag} <button>start</button><button>stop</button></span>
            </br>
        </#list>
    </br>

    <#else>
    There are no available images
    </#list>

</br>
Running containers:

    <br/>
    <#list containers as container>
    <span style="margin-left:2em">${container.name} : ${container.address}</span>

    </br>

    <#else>
    There are no available images
    </#list>


</body>

</html>