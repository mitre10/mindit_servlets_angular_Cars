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

        $scope.displayEditView = false;
        $scope.idToEdit;
        $scope.showEditButton = true;

        $scope.clickEdit = function(index){
            $scope.displayEditView = true;
            $scope.showEditButton=false;
            $scope.idToEdit = index;
            $scope.car = $scope.cars[index];
        };

        $scope.clickEditBack = function(){
            $scope.displayEditView = false;
            $scope.showEditButton=true;
        };

        $scope.edit = function(){
            $scope.showEditButton=true;
            CarService.put($scope.car)
                .then(function(res){
                    CarService.list()
                        .then(function (res) {
                            $scope.cars = res.data;
                        }, function () {
                            $scope.cars = [];
                        });
                    $scope.displayEditView = false;
                });
        };

        $scope.create = function(){
            $scope.displayEditView = true;
            $scope.showEditButton=false;
        };

        $scope.clickCreateBack = function(){
            $scope.displayEditView = false;
            $scope.showEditButton=true;
        };

        $scope.saveCreate = function(){
            $scope.showEditButton=true;
            CarService.post($scope.car)
                .then(function(res){
                    CarService.list()
                        .then(function (res) {
                            $scope.cars = res.data;
                        }, function () {
                            $scope.cars = [];
                        });
                    $scope.displayEditView = false;
                });
        };
    }

})();