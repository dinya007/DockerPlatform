<!-- New stopping app modal -->
<div id="newStopAppModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Create new app</h4>
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
                    <#include "appCreationInfoModal.ftl" >
                    <tr ng-repeat="imageName in images" ng-controller="selectedImageController">
                        <td>{{imageName.name}}</td>
                        <td>
                            <select ng-change="$scope.selectedImage = selectedImage" ng-model="selectedImage">
                                <option ng-repeat="tag in imageName.tags" value="{{imageName.name}}:{{tag}}">{{tag}}
                                </option>
                            </select>
                        </td>
                        <td>
                            <button data-toggle="modal" data-target="#newAppCreationInfoModal"
                                    ng-click="setSelectedImage(selectedImage);">Create</button>
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
