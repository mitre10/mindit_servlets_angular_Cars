(function () {

    'use strict';

    angular.module("carApp")
        .service("CarService", CarService);

    CarService.$inject = ['$http'];

    function CarService($http) {
        return {
            list: function () {
                return $http.get('cars');
            },

            get: function (id) {
                var requestConfig = {
                    params: {id: id}
                };
                return $http.get('cars', requestConfig);
            },

            delete: function (id) {
                var requestConfig = {
                    params: {id: id}
                };
                return $http.delete('cars', requestConfig);
            },

            post: function (car) {
                return $http.post('cars', car, {});
            },

            put: function (car) {
                return $http.put('cars', car, {});
            }
        };
    }

})();