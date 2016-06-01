<table class="table table-striped table-bordered table-condensed text-center full-width-div">
    <caption><h4>Stopped apps</h4>
        <button class="glyphicon glyphicon-plus" data-toggle="modal"
                data-target="#newStopAppModal" ng-click="setSelectedHost(host)"></button>
    </caption>
    <tbody>
    <tr>
        <th class="text-center">Name</th>
        <th class="text-center">Actions</th>
    </tr>
    <tr ng-repeat="container in stoppedContainers[host.id]"
        ng-controller="stoppedContainersController">
        <td>{{container.name}}</td>
        <td>
            <button ng-click="startContainer()">start</button>
            <button ng-click="removeContainer()">remove</button>
        </td>
    </tr>
    </tbody>
</table>