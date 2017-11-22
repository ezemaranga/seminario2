seminario2.controller('MainController', function($rootScope, $scope, $location, $route, $templateCache, $sessionStorage) {

    var vm = this;
    
    $rootScope.$on('$routeChangeStart', function() {
        $rootScope.loading = true;
    });

    $rootScope.$on('$routeChangeSuccess', function() {
        $rootScope.loading = false;
    });

    vm.profile = function() {
        vm.reloadView();
        $location.path( "/profile" );
    }

    vm.partidos = function() {
        vm.reloadView();
        $location.path( "/partido" );
    }

    vm.search = function() {
        vm.reloadView();
        $location.path( "/search" );
    }

    vm.logout = function() {
        delete $sessionStorage.currentUser;
        $location.path( "/login" );
    }

    vm.reloadView = function() {
        var currentPageTemplate = $route.current.templateUrl;
        $templateCache.remove(currentPageTemplate);
        $route.reload();
    }
    
});