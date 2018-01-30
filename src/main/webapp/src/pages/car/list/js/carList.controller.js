(function () {

    'use strict';

    angular.module("carApp")
        .controller("CarListCtrl", CarListCtrl);

    CarListCtrl.$inject = ['$scope', 'CarService'];

    function CarListCtrl($scope, CarService) {

        CarService.list()
            .then(function (res) {
                $scope.cars = res.data;
            }, function () {
                $scope.cars = [];
            });
    }

})();