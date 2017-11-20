seminario2.controller('PartidoController', function($scope, $location, PartidoService) {
    
        var vm = this;

        
        vm.currentUser = seminario2.controller('MainController').currentUser;
    
        if(!vm.currentUser) {
            $location.path( "/login" );
        }
        
        vm.partidoCreado = false;

        vm.nuevoPartido = {
            horario: new Date(0, 0, 0, 19, 00, 0),
            dia: new Date(),
            habilidades: {
                ataja: "VERDE",
				defensa: "ROJO",
				ataque: "AMARILLO",
				estado: "VERDE",
				habilidad: "VERDE",
				tactica: "ROJO"
            },
            fechaHora: null,
            idUsuarioOrganizador: 1,
            idUsuarioJugador: 2,
            jugado: false
        };

        vm.crearPartido = function() {
            console.log(vm.nuevoPartido);
            PartidoService.crearPartido(vm.nuevoPartido).then(function (data) {
                console.log(data);
            });
        }
        
    });