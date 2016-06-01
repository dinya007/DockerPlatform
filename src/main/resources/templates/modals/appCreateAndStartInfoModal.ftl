<!-- New stopping app modal -->
<div id="newAppCreateAndStartInfoModal" class="modal fade" role="dialog" ng-controller="selectedImageController">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"></button>
                <h4 class="modal-title">Detail info for new app</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="name">App name:</label>
                    <input type="text" class="form-control" id="name" ng-model="selectedAppName"/>
                </div>
                <div class="form-group">
                    <label for="port">Port:</label>
                    <input type="text" class="form-control" id="port" placeholder="Optional" ng-model="selectedPort">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="createAndStartContainer()">Ok
                </button>
            </div>
        </div>
    </div>
</div>
