seminario2.controller('PartidoController', function($scope, $location, PartidoService) {
    
        var vm = this;

        vm.loading = true;

        vm.currentUser = seminario2.controller('MainController').currentUser;
    
        if(!vm.currentUser) {
            $location.path( "/login" );
        }

        PartidoService.organizados(vm.currentUser.id).then(function(data) {
            if(data.partido) {
                vm.partidoCreado = true;
            } else {
                vm.partidoCreado = false;
            }
            vm.loading = false;
        });
        
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
            idUsuarioOrganizador: vm.currentUser.id,
            idUsuarioJugador: null,
            jugado: false
        };

        vm.crearPartido = function() {
            console.log(vm.nuevoPartido);
            PartidoService.crearPartido(vm.nuevoPartido).then(function (data) {
                console.log(data);
            });
        }
        
    });