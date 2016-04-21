<table class="table table-striped table-bordered text-center">
    <caption><h4>Running apps</h4>
        <button class="glyphicon glyphicon-plus" data-toggle="modal" data-target="#newRunAppModal"
                ng-click="setSelectedHost(host)"></button>
    </caption>
    <tbody>
    <tr>
        <th class="text-center">Name</th>
        <th class="text-center">Address</th>
        <th class="text-center">Actions</th>
    </tr>
    <tr ng-repeat="container in runningContainers[host.id]"
        ng-controller="runningContainersController">
        <td>{{container.name}}</td>
        <td><a href="http://{{host.url}}:{{container.port}}">
            http://{{host.url}}:{{container.port}}</a>
        </td>
        <td>
            <button ng-click="restartContainer()">restart</button>
            <button ng-click="stopContainer()">stop</button>
            <button ng-click="removeContainer()">remove</button>
            <button ng-click="openLogs()">logs</button>
        </td>
    </tr>
    </tbody>
</table>