dockerApp.service('containerService', function (restService) {

    this.delete = function (container) {
        return restService.delete("/action/container/delete/" + container.hostName + "/" + container.id);
    };

    this.start = function (container) {
        return restService.post("/action/container/start", container)
    };

    this.stop = function (container) {
        return restService.post("/action/container/stop", container)
    };

    this.restart = function (container) {
        return restService.post("/action/container/restart", container)
    };

});
