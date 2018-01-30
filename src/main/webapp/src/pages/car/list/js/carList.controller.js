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

        $scope.delete = function(index) {
            var id = $scope.cars[index].id;
            CarService.delete(id)
                .then(function(res){
                    CarService.list()
                        .then(function(res) {
                            $scope.cars = res.data;
                        }, function() {
                            $scope.cars = [];
                        });
                });
        };

        $scope.car = {
            id: '',
            name: '',
            type: '',
            fuel: '',
            price: ''
        };

        $scope.cars = [];
    }

})();