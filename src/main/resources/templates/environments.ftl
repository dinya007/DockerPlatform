<!DOCTYPE html>

<html lang="en">
<head>
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