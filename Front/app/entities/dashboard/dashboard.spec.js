'use strict';

describe('myApp.dashboard module', function() {

  beforeEach(module('myApp.dashboard', 'ngRoute'));

  describe('dashboard controller', function(){

    it('should ....', inject(function($controller) {
      //spec body
      var DashboardCtrl = $controller('DashboardCtrl');
      expect(DashboardCtrl).toBeDefined();
    }));

  });
});