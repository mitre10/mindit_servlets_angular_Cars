(function () {

    'use strict';

    angular.module("carApp")
        .controller("CarEditCtrl", CarEditCtrl);

    CarEditCtrl.$inject = ['$scope', '$routeParams', 'CarService'];

    function CarEditCtrl($scope, $routeParams, CarService) {
        CarService.put($routeParams.carId);

        $scope.car = {
            id: '',
            name: '',
            type: '',
            fuel: '',
            price: ''
        };


    }

})();