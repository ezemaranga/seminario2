seminario2.controller('PartidoController', function($scope, $location, PartidoService, $route, $templateCache) {
    
        var vm = this;

        vm.loading = true;

        vm.currentUser = seminario2.controller('MainController').currentUser;
        vm.jugadorAceptado = seminario2.controller('MainController').jugadorAceptado;

        vm.postulacionAceptada = false;
    
        if(!vm.currentUser) {
            $location.path( "/login" );
        }

        PartidoService.organizados(vm.currentUser.id).then(function(data) {
            if(data.partidos.length > 0) {
                vm.partidoCreado = true;
                vm.partidoActual = data.partidos[0];
                PartidoService.getPostulados(data.partidos[0].id).then(function(data1) {
                    vm.postulados = data1.postulados;
                    vm.idMiPartido = data1.partido.id;
                });
                if(data.partidos[0].idUsuarioJugador) {
                    vm.postulacionAceptada = true;
                }
            } else {
                vm.partidoCreado = false;
            }
            vm.loading = false;
        });
        
        vm.partidoCreado = false;

        vm.nuevoPartido = {
            horario: null,
            dia: null,
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
            vm.nuevoPartido.horario = vm.horario.getHours() + ':' + vm.horario.getMinutes();
            vm.nuevoPartido.dia = vm.dia.getDate() + '/' + (vm.dia.getMonth()+1)  + '/' + vm.dia.getFullYear();
            PartidoService.crearPartido(vm.nuevoPartido).then(function (data) {
                var currentPageTemplate = $route.current.templateUrl;
                $templateCache.remove(currentPageTemplate);
                $route.reload();
            });
        }

        vm.aceptarPostulacion = function(jugador) {
            seminario2.controller('MainController').jugadorAceptado = jugador;
            PartidoService.aceptarPostulacion(jugador.id, vm.idMiPartido).then(function(data) {
                console.log(data);
            });
        }
        
    });