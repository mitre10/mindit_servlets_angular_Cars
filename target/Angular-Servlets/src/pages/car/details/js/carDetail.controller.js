(function () {

    'use strict';

    angular.module("carApp")
        .controller("CarDetailCtrl", CarDetailCtrl);

    CarDetailCtrl.$inject = ['$scope', '$routeParams', 'CarService'];

    function CarDetailCtrl($scope, $routeParams, CarService) {
        CarService.get($routeParams.todoId)
            .then(function (res) {
                $scope.car = res.data;
            }, function () {
                $scope.car = {};
            });
    }

})();