seminario2.controller('ProfileController', function($scope, $location,$sce,ProfileService, $sessionStorage, PartidoService) {

    var vm = this;

    vm.getStar = getStar;
    vm.editHabilidad = editHabilidad;
    vm.editUser = editUser;
    vm.goToEdit = goToEdit;
    vm.getMapsURL = getMapsURL;
    vm.videoJugador = {};

    vm.currentUser = $sessionStorage.currentUser;

    if(!vm.currentUser) {
        $location.path( "/login" );
    } else {
        PartidoService.getJugador(vm.currentUser.id).then(function(data) {
            $sessionStorage.currentUser = data.usuario;
            vm.currentUser = $sessionStorage.currentUser;
        })
    }

    function getMapsURL(direccion) {
        return $sce.trustAsResourceUrl('https://www.google.com/maps/embed/v1/place?key=AIzaSyA3t75GJakScXK6VIOF6ecs4Op_zBW8AGo&q=' + direccion);
    }

    function getStar(num) {
        var aux = num * 2;
        if(vm.currentUser) {
            if(aux <= vm.currentUser.reputacion) {
                return 'fa-star'
            } else if (aux-2 >= vm.currentUser.reputacion) {
                return 'fa-star-o'
            } else {
                return 'fa-star-half-o'
            }
        }
    }

    function editUser() {
        vm.loading = true;
        if(vm.videoJugador) vm.currentUser.video = vm.videoJugador.base64;
        ProfileService.editUser(vm.currentUser).then(function(data) {
            vm.loading = false;
            $location.path( "/profile" );
        });
    }

    function editHabilidad(currentValue, propiedad) {
        if(vm.currentUser[propiedad]=='VERDE') {
            vm.currentUser[propiedad] = 'ROJO';
        } else if (vm.currentUser[propiedad]=='AMARILLO') {
            vm.currentUser[propiedad] = 'VERDE';
        } else {
            vm.currentUser[propiedad] = 'AMARILLO';
        }
    }

    function goToEdit() {
        $location.path( "/editProfile" );
    }

    vm.cancelEdit = function() {
        $location.path( "/profile" );
    }

});