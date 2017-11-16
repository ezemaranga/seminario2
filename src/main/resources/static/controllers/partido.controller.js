seminario2.controller('PartidoController', function($scope, $location) {
    
        var vm = this;
        
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
            }
        };
        
    });