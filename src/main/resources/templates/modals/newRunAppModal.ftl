<!-- New running app modal -->
<div id="newRunAppModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Run new app</h4>
            </div>
            <div class="modal-body">
                <table class="table table-striped table-bordered text-center">
                    <caption><h4>Available images from registry</h4></caption>
                    <tbody>
                    <tr>
                        <th class="text-center">Name</th>
                        <th class="text-center">Versions</th>
                        <th class="text-center">Actions</th>
                    </tr>
                    <tr ng-repeat="image in images" ng-controller="selectedImageController">
                        <td>{{image.name}}</td>
                        <td>
                            <select ng-change="$scope.selectedImage = selectedImage" ng-model="selectedImage">
                                <option ng-repeat="tag in image.tags" value="{{image.name}}:{{tag}}">{{tag}}
                                </option>
                            </select>
                        </td>
                        <td>
                            <button ng-click="createAndStartContainer()" data-dismiss="modal">start</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>