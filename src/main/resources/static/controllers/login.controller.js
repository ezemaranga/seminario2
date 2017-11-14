seminario2.controller('LoginController', function($scope, $location) {

    var vm = this;
    
    vm.login = function() {
        $location.path( "/profile" );
    }

    vm.alert = function() {
        alert( "login controller" );
    }
});