seminario2.controller('MainController', function($rootScope, $scope, $location, $route, $templateCache) {

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

    vm.reloadView = function() {
        var currentPageTemplate = $route.current.templateUrl;
        $templateCache.remove(currentPageTemplate);
        $route.reload();
    }
    
});