var myApp = angular.module('myApp', []);

myApp.controller('IndexController', function AtletaController($scope, $http) {

    $scope.clientes = [];
    $scope.cidades = [];
    $scope.cliente = {};


    $http.get("http://localhost:8090/api/estados").success(function (data) {
        $scope.estados = data;
    });

    $scope.ConsultarCidades = function (id) {
        $http.get("http://localhost:8090/api/cidadeByEstado/" + id).success(function (data) {
            $scope.cidades = data;
        });
    }


    $scope.ConsultarClientes = function () {
        $http.get("http://localhost:8090/api/clientes").success(function (data) {
            $scope.clientes = data;
        });
    }

    $scope.SalvarCliente = function (cliente) {
        $http.post("http://localhost:8090/api/cliente", cliente).success(function (data) {
            $scope.clientes = data;
        });
        $scope.ConsultarClientes();
        $('#exampleModal').modal('hide')
    }

    $scope.limparCliente = function () {
        $scope.cliente = [];
    }

    $scope.ConsultarClientes();

});