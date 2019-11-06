var myApp = angular.module('myApp', ['ui.grid', 'ui.grid.pagination', 'ui.grid.exporter']);

myApp.controller('IndexController', function IndexController($scope, $http) {

    $scope.clientes = [];
    $scope.clientesReport = [];
    $scope.servicosReport = [];
    $scope.servicos = [];
    $scope.cidades = [];
    $scope.clienteSave = {};

    $scope.gridOptions1 = {
        data: [],
        dataSource: {
            autoBind: false,
            url: "http://localhost:8090/api/clientes",
        },
        paginationPageSizes: [10, 20, 30, 40, 50, 75],
        paginationPageSize: 10,
        enablePaginationControls: true,
        enableFiltering: true,
        columnDefs: [
            { field: 'id', name: 'Cód cliente' },
            { field: 'nome', name: 'Nome' },
            { field: 'telefone', name: 'Telefone' },
            { field: 'email', name: 'Email' },
            { field: 'endereco', name: 'Endereço' },
            { field: 'cidade.nome', name: 'Cidade' },
            { name: 'Ações', cellTemplate: '<button title="Editar Usuario" style="margin:1px;" ng-click="grid.appScope.ConsultarCliente(row.entity.id)" class=" btn btn-sm btn-info">  <i class="fa fa-edit"></i> </button>' }
        ],
    };


    $scope.gridOptions2 = {
        data: [],
        dataSource: {
            autoBind: false,
            url: "http://localhost:8090/api/servicos",
        },
        paginationPageSizes: [10, 20, 30, 40, 50, 75],
        paginationPageSize: 10,
        enablePaginationControls: true,
        enableFiltering: true,
        columnDefs: [
            { field: 'id_servico', name: 'Cód serviço' },
            { field: 'cliente.nome', name: 'Cliente' },
            { field: 'data_cadastro', name: 'Data cadastro' },
            { field: 'data_termino', name: 'Data finalização' },
            { field: 'equipamento.tipo', name: 'Equipamento' },
            { field: 'equipamento.problema', name: 'Problema' },
            { field: 'status', cellTemplate: '<span>{{row.entity.status==="F" ? "Finalizado" : "Aberto"}}</span>' },
            { name: 'Ações', cellTemplate: '<button title="Finalizar serviço" style="margin:1px;" ng-show="row.entity.status===\'A\'" ng-click="grid.appScope.FinalizarServico(row.entity.id_servico)" class=" btn btn-sm btn-success">  <i class="fa fa-check"></i>  </button>' }

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
            data.forEach(element => {
                $scope.clientesReport.push([element.id, element.nome, element.telefone,
                element.email, element.endereco, element.cidade.nome]);
            });
        });

    }

    $scope.ConsultarCliente = function (id) {
        $http.get("http://localhost:8090/api/cliente/" + id).success(function (data) {
            $scope.clienteSave = data;
            $scope.clienteSave.estado = data.cidade.estado.id;
            $scope.clienteSave.cidade.id_cidade = data.cidade.id;
            $scope.ConsultarCidades(data.cidade.estado.id);
            $('#modalCliente').modal('show');
        });
    }

    $scope.GerarRelatorioClientes = function () {

        var header = function (data) {
            doc.setFontSize(12);
            doc.setTextColor(40);
            doc.text("Manutenção de equipamentos - Relatório de clientes", 20, 10);
        };

        var doc = new jsPDF();
        doc.autoTable({
            head: [['Código', 'Nome', 'Telefone', 'Email', 'Endereço', 'Cidade']],
            body: $scope.clientesReport,
            didDrawPage: header
        });
        doc.save('RelatórioClientes.pdf');
    }

    $scope.GerarRelatorioServicos = function () {

        var header = function (data) {
            doc.setFontSize(12);
            doc.setTextColor(40);
            doc.text("Manutenção de equipamentos - Relatório de serviços", 20, 10);
        };
        
        var doc = new jsPDF();
        doc.autoTable({
            head: [['Código', 'Cliente', 'Data cadastro', 'Data finalização', 'Equipamento', 'Problema', 'Status']],
            body: $scope.servicosReport,
            didDrawPage: header
        });
        doc.save('RelatórioServicos.pdf');
    }


    $scope.ConsultarServicos = function () {
        $http.get("http://localhost:8090/api/servicos").success(function (data) {
            $scope.gridOptions2.data = data;
            data.forEach(element => {
                var status = element.status =="A" ?"Aberto" :"Fechado";

                $scope.servicosReport.push([element.id_servico, element.cliente.nome, element.data_cadastro,
                    element.data_termino ,element.equipamento.tipo, element.equipamento.problema, status ]);
            });
        });

    }

    $scope.SalvarCliente = function (cliente) {

        $http.post("http://localhost:8090/api/cliente", cliente).success(function (data) {
            $('#modalCliente').modal('hide');
            $scope.ConsultarClientes();
        });
    }

    $scope.SalvarServico = function (servico) {
        servico.data_cadastro = new Date().toDateString();
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