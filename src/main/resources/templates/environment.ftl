<!DOCTYPE html>

<html lang="en">

<body>
    Environment name: ${environment.name}
    </br>
    Available hosts:
    </br>
    <#list environment.hosts as host>
        <a href="/hosts/${host.id}">${host.name}</a>
        </br>
    <#else>
        There are no available hosts for this environment
    </#list>
</body>

</html>