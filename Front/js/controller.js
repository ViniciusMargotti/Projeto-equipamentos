var myApp = angular.module('myApp',['ui.grid', 'ui.grid.pagination','ui.grid.exporter']);

myApp.controller('IndexController', function IndexController($scope, $http) {

    $scope.clientes = [];
    $scope.servicos = [];
    $scope.cidades = [];
    $scope.cliente = {};

    $scope.gridOptions1 = {
        data: [],
        dataSource: {
            autoBind: false,
            url: "http://localhost:8090/api/clientes",
        },
        paginationPageSizes: [10,20,30,40,50,75],
        paginationPageSize: 10,
        enablePaginationControls: true,
        enableFiltering: true,
        columnDefs: [
          {field: 'id' , name: 'Cód cliente' },
          {field: 'nome' , name: 'Nome' },
          {field: 'telefone' , name: 'Telefone' },
          {field: 'email' , name: 'Email' },
          {field: 'endereco' , name: 'Endereço' }
        ],
      };

      
    $scope.gridOptions2 = {
        data: [],
        dataSource: {
            autoBind: false,
            url: "http://localhost:8090/api/servicos",
        },
        paginationPageSizes: [10,20,30,40,50,75],
        paginationPageSize: 10,
        enablePaginationControls: true,
        enableFiltering: true,
        columnDefs: [
          {field: 'id' , name: 'Cód serviço' },
          {field: 'status' , name: 'Status' },
         
        ],
      };


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
            $scope.gridOptions1.data = data;
            $scope.clientes = data;
        });

    }

    $scope.ConsultarServicos = function () {
        $http.get("http://localhost:8090/api/servicos").success(function (data) {
            $scope.gridOptions2.data = data;
        });

    }

    $scope.SalvarCliente = function (cliente) {
        $http.post("http://localhost:8090/api/cliente", cliente).success(function (data) {
            $('#exampleModal').modal('hide');
            $scope.ConsultarClientes();
        });
    }

    $scope.limparCliente = function () {
        $scope.cliente = [];
    }

    $scope.ConsultarClientes();
    $scope.ConsultarServicos();

});