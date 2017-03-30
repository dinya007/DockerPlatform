dockerApp.service('environmentService', function (restService) {

    this.getAllEnvironments = function () {
        return restService.get('/environment/all');
    };

    this.getEnvironment = function (id) {
        return restService.get('/environment/' + id);
    };

    this.create = function (environment) {
        return restService.post('/environment', environment);
    };

});