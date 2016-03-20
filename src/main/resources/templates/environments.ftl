<!DOCTYPE html>

<html lang="en">

<body>

<#list environments as environment>
    <a href="/env/${environment.id}">${environment.name}</a>
    </br>

<#else>
    There are no available invironments
</#list>


</body>

</html>