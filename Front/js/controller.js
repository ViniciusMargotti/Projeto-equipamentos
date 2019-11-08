var myApp = angular.module('myApp', ['ui.grid', 'ui.grid.pagination', 'ui.grid.exporter', 'ui.grid.resizeColumns', 'ui.grid.moveColumns','ui.grid.autoResize']);

myApp.controller('IndexController', function IndexController($scope, $http) {

    $scope.clientes = [];
    $scope.clientesReport = [];
    $scope.servicosReport = [];
    $scope.servicos = [];
    $scope.cidades = [];
    $scope.clienteSave = {};
    $scope.servicoSave = {};
    $scope.filtro = {};

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
            { name: 'Ações', cellTemplate: '<button title="Editar Usuario" style="margin:1px;" ng-click="grid.appScope.ConsultarCliente(row.entity.id)" class=" btn btn-sm btn-info">  <i class="fa fa-edit">Editar</i> </button>' }
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
            { field: 'id_servico', name: 'Cód serviço', cellTooltip: true  },
            { field: 'cliente.nome', name: 'Cliente', cellTooltip: true  },
            { field: 'data_cadastro', name: 'Data cadastro', cellTooltip: true  },
            { field: 'data_termino', name: 'Data finalização', cellTooltip: true  },
            { field: 'equipamento.tipo', name: 'Equipamento' , cellTooltip: true },
            { field: 'equipamento.problema', name: 'Problema', cellTooltip: true },
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
            $scope.clientes = data;
            $scope.clientesReport = [];
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
            debugger;
            $scope.clienteSave.cidade.id = data.cidade.id;
            $scope.ConsultarCidades(data.cidade.estado.id);
            $('#modalClienteEdit').modal('show');
        });
    }

    $scope.ConsultarServico = function (id) {
        $http.get("http://localhost:8090/api/servico/" + id).success(function (data) {
            $scope.servicoSave.id = data.id_servico;
            $scope.servicoSave.marca = data.equipamento.marca;
            $scope.servicoSave.tipo = data.equipamento.tipo;
            $scope.servicoSave.problema = data.equipamento.problema;
            $scope.servicoSave.id_cliente = data.cliente.id;
            $scope.ConsultarClientes();
            $('#modalServico').modal('show');
        });
    }

    $scope.GerarRelatorioClientes = function () {

        var header = function (data) {
            doc.setFontSize(12);
            doc.setTextColor(40);
            doc.text("Manutenção de Equipamentos - Relatório de Clientes", 20, 10);
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
            doc.text("Manutenção de Equipamentos - Relatório de Serviços", 20, 25);
        };

        var doc = new jsPDF("p", "pt", "a2");
        doc.autoTable({
            head: [['Código', 'Cliente', 'Data cadastro', 'Data finalização', 'Equipamento', 'Problema', 'Status']],
            body: $scope.servicosReport,
            didDrawPage: header
        });
        doc.save('RelatórioServicos.pdf');
    }


    $scope.ConsultarServicos = function () {
        var status = $scope.filtro.status ==null  ||  $scope.filtro.status == undefined ? "T" : $scope.filtro.status;
        $http.get("http://localhost:8090/api/servicos/"+status).success(function (data) {
            $scope.gridOptions2.data = data;
            $scope.servicosReport=[];
            data.forEach(element => {
                var status = element.status == "A" ? "Aberto" : "Fechado";

                $scope.servicosReport.push([element.id_servico, element.cliente.nome, element.data_cadastro,
                element.data_termino, element.equipamento.tipo, element.equipamento.problema, status]);
            });
        });

    }

    $scope.SalvarCliente = function (cliente) {

        $http.post("http://localhost:8090/api/cliente", cliente).success(function (data) {
            $('#modalCliente').modal('hide');
            $scope.ConsultarClientes();
        });
    }

    $scope.SalvarClienteExistente = function (cliente) {
        debugger;
        $http.put("http://localhost:8090/api/cliente", cliente).success(function (data) {
            $('#modalClienteEdit').modal('hide');
            $scope.ConsultarClientes();
        });
    }

    $scope.SalvarServico = function (servico) {
        servico.data_cadastro = new Date().toLocaleDateString();
        $http.post("http://localhost:8090/api/servico", servico).success(function (data) {
            $('#modalServico').modal('hide');
            $scope.servicoEdit = true;
            $scope.ConsultarServicos();
        });
    }


    $scope.FinalizarServico = function (servico) {
        servico.data_termino = new Date().toLocaleDateString();
        $http.put("http://localhost:8090/api/finalizaServico", servico).success(function (data) {
            $scope.ConsultarServicos();
        });
    }

    $scope.ConsultarClientes();
    $scope.ConsultarServicos();


    $scope.mascara = function (t) {
        t = t.replace(/\D/g, ""); //Remove tudo o que não é dígito
        t = t.replace(/^(\d{2})(\d)/g, "($1) $2"); //Coloca parênteses em volta dos dois primeiros dígitos
        t = t.replace(/(\d)(\d{4})$/, "$1-$2"); //Coloca hífen entre o quarto e o quinto dígitos
         $scope.clienteSave.telefone = t;
        return t;
    };




});