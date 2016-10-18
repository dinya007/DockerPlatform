var getContainerInfo = function (scope) {
    var containerAction = {};
    containerAction.hostName = scope.host.name;
    containerAction.id = scope.container.id;
    containerAction.name = scope.selectedAppName;
    containerAction.port = scope.selectedPort;
    return containerAction;
};

$.putRq = function (url, data, callback) {

    if ($.isFunction(data)) {
        type = type || callback,
            callback = data,
            data = {}
    }

    return $.ajax({
        url: url,
        type: 'PUT',
        success: callback,
        data: data,
        contentType: "application/json; charset=utf-8"
    });
}

$.postRq = function (url, data, callback) {

    if ($.isFunction(data)) {
        type = type || callback,
            callback = data,
            data = {}
    }

    return $.ajax({
        url: url,
        type: 'POST',
        success: callback,
        data: data,
        contentType: "application/json; charset=utf-8"
    });
}

$.deleteRq = function (url, data, callback) {

    if ($.isFunction(data)) {
        type = type || callback,
            callback = data,
            data = {}
    }

    return $.ajax({
        url: url,
        type: 'DELETE',
        success: callback,
        data: data,
        contentType: "application/json; charset=utf-8"
    });
}