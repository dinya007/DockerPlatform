dockerApp.service('hostService', function (restService) {

    this.getAllHostsByEnvironment = function (environmentId) {
        return restService.get('/hosts/all/' + environmentId)
    };

    this.getAllHosts = function () {
        return restService.get('/hosts/all')
    };

    this.getRunningContainers = function (hostId, environmentId) {
        return restService.get('/hosts/' + hostId + '/runningContainers/' + environmentId)
    };

    this.getStoppedContainers = function (hostId, environmentId) {
        return restService.get('/hosts/' + hostId + '/stoppedContainers/' + environmentId)
    };

});