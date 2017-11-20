seminario2.controller('LoginController', function($scope, $location, LoginService) {

    var vm = this;

    vm.nuevoUsuario = {};

    vm.loginData = {};
    
    vm.login = function() {
        
    }

    vm.alert = function() {
        alert( "login controller" );
    }

    vm.signup = function() {
        vm.loading = true;
        LoginService.crearUsuario(vm.nuevoUsuario).then(function() {
            vm.loading = false;
            $location.path( "/login" );
        }, function(data) {
            alert(data.mensaje);
        });
    }

    vm.login = function() {
        vm.loading = true;
        LoginService.login(vm.loginData).then(function() {
            vm.loading = false;
            $location.path( "/profile" );
        }, function(data) {
            alert(data.mensaje);
        });
    }

});