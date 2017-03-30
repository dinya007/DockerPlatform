dockerApp.service('hostService', function (restService) {

    this.getAllHosts = function (environmentId) {
        return restService.get('/hosts/all/' + environmentId)
    };

    this.getAllHosts = function () {
        return restService.get('/hosts/all')
    };

    this.getRunningContainers = function (environmentId) {
        return restService.get('/hosts/' + environmentId + '/runningContainers')
    };

    this.getStoppedContainers = function (environmentId) {
        return restService.get('/hosts/' + environmentId + '/stoppedContainers')
    };

});