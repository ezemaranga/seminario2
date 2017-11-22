seminario2.controller('PartidoController', function($scope, $location, PartidoService, $route, $templateCache, $http) {
    
        var vm = this;

        vm.loading = true;

        var MainCtrl = seminario2.controller('MainController');

        vm.currentUser = MainCtrl.currentUser;

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
                    PartidoService.getJugador(data.partidos[0].idUsuarioJugador).then(function(data) {
                        vm.jugadorAceptado = data.usuario;
                    })
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
                	            	// reload
	            	var currentPageTemplate = $route.current.templateUrl;
			        $templateCache.remove(currentPageTemplate);
			        $route.reload();
                $http.get('/websocket/refreshMatches?userId=' + vm.currentUser.id);
            });
        }

        vm.aceptarPostulacion = function(jugador) {
            seminario2.controller('MainController').jugadorAceptado = jugador;
            PartidoService.aceptarPostulacion(jugador.id, vm.idMiPartido).then(function(data) {
                console.log(data);
                                	            	// reload
	            	var currentPageTemplate = $route.current.templateUrl;
			        $templateCache.remove(currentPageTemplate);
			        $route.reload();
            });
        }

        vm.getStar = function(num, reputacion) {
            var aux = num * 2;
            if(aux <= reputacion) {
                return 'fa-star'
            } else if (aux-2 >= reputacion) {
                return 'fa-star-o'
            } else {
                return 'fa-star-half-o'
            }
        }

        vm.calificarJugador = function() {
            console.log(vm.calificacionParaJugador);
            PartidoService.valorarJugador(vm.calificacionParaJugador, vm.partidoActual.id).then(function(data) {
                // reload
                var currentPageTemplate = $route.current.templateUrl;
                $templateCache.remove(currentPageTemplate);
                $route.reload();
                $http.get('/websocket/showReview?userId=' + vm.partidoActual.idUsuarioJugador + '&calificacion=' + vm.calificacionParaJugador);
            });
        }
        
    });