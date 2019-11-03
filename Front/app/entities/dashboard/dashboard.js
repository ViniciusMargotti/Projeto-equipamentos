'use strict';

var angularModule = angular.module('myApp.dashboard', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('dashboard', {
    templateUrl: 'entities.dashboard.html',
    controller: 'DashboardCtrl'
  });
}])

angularModule.controller('DashboardCtrl', ['$scope', '$http',function($scope, $http) {
  $scope.clientes = [];

  $scope.GetClientes= function(){
    debugger;
    $http.get('http://localhost:8090/api/clientes').then(function successCallback(response) {
      debugger;
      $scope.clientes = response;
    }, function errorCallback(response) {
  
    });
  } 

  
  
  $scope.GetClientes();

}]);
