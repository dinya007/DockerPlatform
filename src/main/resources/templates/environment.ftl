<!DOCTYPE html>

<html lang="en">
<head>

</head>
<body>
    Environment name: ${environment.name}
    </br>
    Available hosts:
    </br>
    <#list environment.hosts as host>
        <span style="margin-left:2em"><a href="/hosts/${host.id}">${host.name}</a></span>
        </br>
    <#else>
        There are no available hosts for this environment
    </#list>
</body>

</html>