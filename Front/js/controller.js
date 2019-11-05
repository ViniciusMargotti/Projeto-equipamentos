var myApp = angular.module('myApp',['ui.grid', 'ui.grid.pagination','ui.grid.exporter']);

myApp.controller('IndexController', function IndexController($scope, $http) {

    $scope.clientes = [];
    $scope.servicos = [];
    $scope.cidades = [];
    $scope.clienteSave={};

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
          {field: 'endereco' , name: 'Endereço' },
          { name: 'Ações', cellTemplate: '<button title="Editar Usuario" style="margin:1px;" ng-click="grid.appScope.ConsultarCliente(row.entity.id)" class=" btn btn-sm btn-info">  <i class="fa fa-edit"></i> </button>'}
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
          {field: 'id_servico' , name: 'Cód serviço' },
          {field: 'cliente.nome' , name: 'Cliente'},
          {field: 'equipamento.tipo' , name: 'Equipamento'},
          {field: 'equipamento.problema' , name: 'Problema'},
          {field: 'status', cellTemplate: '<span>{{row.entity.status==="F" ? "Finalizado" : "Aberto"}}</span>'},
          { name: 'Ações', cellTemplate: '<button title="Finalizar serviço" style="margin:1px;" ng-show="row.entity.status===\'A\'" ng-click="grid.appScope.FinalizarServico(row.entity.id_servico)" class=" btn btn-sm btn-success">  <i class="fa fa-check"></i>  </button>'}

        ]
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

    $scope.ConsultarCliente = function (id) {
        $http.get("http://localhost:8090/api/cliente/"+id).success(function (data) {
            debugger;
            $scope.clienteSave = data;
            $scope.clienteSave.estado = data.cidade.estado.id;
            $scope.clienteSave.cidade.id_cidade = data.cidade.id;
            $scope.ConsultarCidades(data.cidade.estado.id);
            $('#modalCliente').modal('show');
        });
    }

    $scope.ConsultarServicos = function () {
        $http.get("http://localhost:8090/api/servicos").success(function (data) {
            $scope.gridOptions2.data = data;
        });

    }

    $scope.SalvarCliente = function (cliente) {
        debugger;
        $http.post("http://localhost:8090/api/cliente", cliente).success(function (data) {
            $('#modalCliente').modal('hide');
            $scope.ConsultarClientes();
        });
    }

    $scope.SalvarServico = function (servico) {
        debugger;
        $http.post("http://localhost:8090/api/servico", servico).success(function (data) {
            $('#modalServico').modal('hide');
            $scope.ConsultarServicos();
        });
    }

    $scope.FinalizarServico = function (servico) {
        $http.put("http://localhost:8090/api/finalizaServico", servico).success(function (data) {
            $scope.ConsultarServicos();
        });
    }

    $scope.ConsultarClientes();
    $scope.ConsultarServicos();
    

});