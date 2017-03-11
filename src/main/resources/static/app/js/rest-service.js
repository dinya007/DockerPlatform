dockerApp.service('restService', function ($http, $q) {

    this.get = function (url) {
        var deferred = $q.defer();

        $http.get(url).then(function (data) {
            deferred.resolve(data.data);
        }, function (error) {
            deferred.reject(
                alert(error.status + " : " + error.statusText)
            );
        });

        return deferred.promise;
    };

});