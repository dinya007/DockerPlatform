<!DOCTYPE html>

<html lang="en">

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

    <#else>
    There are no available images
    </#list>

</body>

</html>