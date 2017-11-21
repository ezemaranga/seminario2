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
        LoginService.crearUsuario(vm.nuevoUsuario).then(function(data) {
            vm.loading = false;
            $location.path( "/login" );
        }, function(data) {
            alert(data.mensaje);
        });
    }

    vm.login = function() {
        vm.loading = true;
        LoginService.login(vm.loginData).then(function(data) {
            vm.loading = false;
            seminario2.controller('MainController').currentUser = data.usuario;
            if(data.usuario.edad) {
                $location.path( "/profile" );
            } else {
                $location.path( "/editProfile" );
            }
            //Suscripcion a la queue del usuario
            var queue = '/topic/user' + data.usuario.id;
            console.log(queue);
	        var subscription = ws.subscribe(queue,
	        	function (payload, headers, res) {
	            	console.log(payload);
	            });
	        
        }, function(data) {
            alert(data.mensaje);
        });
    }

});