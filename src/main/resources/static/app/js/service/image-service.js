dockerApp.service('imageService', function (restService) {

    this.getRepositoryImages = function () {
        return restService.get('/images')
    };

});