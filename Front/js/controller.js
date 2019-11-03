var myApp = angular.module('myApp', []);

myApp.controller('IndexController', function AtletaController($scope,$http) {

    $scope.clientes = [];

    
        $http.get("http://localhost:8090/api/estados").success(function(data){
            $scope.estados = data; 
         });
     

    $scope.ConsultarClientes = function (){
        $http.get("http://localhost:8090/api/clientes").success(function(data){
            $scope.clientes = data; 
         });
    } 

    $scope.ConsultarClientes(); 
   
});