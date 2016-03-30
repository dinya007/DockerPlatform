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
List of environments:
<#list environments as environment>
    </br>
    <span style="margin-left:2em"><a href="/env/${environment.id}">${environment.name}</a></span>
<#else>
    There are no available invironments
</#list>


</body>

</html>