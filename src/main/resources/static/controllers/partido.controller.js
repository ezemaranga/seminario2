seminario2.controller('PartidoController', function($scope, $location, PartidoService, $route, $templateCache) {
    
        var vm = this;

        vm.loading = true;

        vm.currentUser = seminario2.controller('MainController').currentUser;
    
        if(!vm.currentUser) {
            $location.path( "/login" );
        }

        PartidoService.organizados(vm.currentUser.id).then(function(data) {
            if(data.partido) {
                vm.partidoCreado = true;
                PartidoService.getPostulados(data.partido.id).then(function(data1) {
                    vm.postulados = data1.postulados;
                });
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
        
    });