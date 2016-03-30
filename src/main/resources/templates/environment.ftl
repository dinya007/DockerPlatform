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
            $("button").button();
        });
    </script>

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