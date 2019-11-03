'use strict';

// Declare app level module which depends on views, and core components
var app = angular.module('myApp', [
  'ngRoute',
  'myApp.dashboard',
  'myApp.version'
]);

app.config(function($routeProvider){
  $routeProvider
  .when('/', {
    templateUrl : 'entities/dashboard/dashboard.html',
    controller: 'DashboardCtrl'
  })
    .otherwise({redirectTo: '/'});
});
