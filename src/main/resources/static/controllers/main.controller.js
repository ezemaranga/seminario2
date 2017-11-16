seminario2.controller('MainController', function($rootScope, $scope, $location) {

    var vm = this;
    
    $rootScope.$on('$routeChangeStart', function() {
        $rootScope.loading = true;
    });

    $rootScope.$on('$routeChangeSuccess', function() {
        $rootScope.loading = false;
    });

    vm.profile = function() {
        $location.path( "/profile" );
    }

    vm.partidos = function() {
        $location.path( "/partido" );
    }

    vm.search = function() {
        $location.path( "/search" );
    }
    
});