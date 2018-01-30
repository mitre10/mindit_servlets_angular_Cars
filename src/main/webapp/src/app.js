(function () {
    'use strict';

    angular.module('carApp', ['ngRoute'])
        .config(Config);

    Config.$inject = ['$routeProvider', '$locationProvider'];

    function Config($routeProvider, $locationProvider) {
        $locationProvider.hashPrefix('');

        $routeProvider
            .when('/',
            {
                templateUrl: 'src/pages/car/list/list.html',
                controller: 'CarListCtrl'
            })
            .when('/carDetail/:carId',
                {
                    templateUrl: 'src/pages/car/details/detail.html',
                    controller: 'CarDetailCtrl'
                })
            .otherwise({redirectTo: '/'});
    }

})();
