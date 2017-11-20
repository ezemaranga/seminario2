seminario2.controller('SearchController', function($scope, $location, PartidoService, $sce) {

    var vm = this;

    vm.getMapsURL = getMapsURL;

    vm.currentUser = seminario2.controller('MainController').currentUser;

    if(!vm.currentUser) {
        $location.path( "/login" );
    }

    function init() {
        getPartidos();
    }

    function getPartidos() {
        vm.loading = true;
        navigator.geolocation.getCurrentPosition(function(data) {
            PartidoService.buscarPartidos('lat=' + data.coords.latitude + '&lon=' + data.coords.longitude + '&idUsuario=' + vm.currentUser.id).then(function (data) {
                    vm.partidos = data;
                    vm.loading = false;
            });
        });
    }

    function getMapsURL(direccion) {
        return $sce.trustAsResourceUrl('https://www.google.com/maps/embed/v1/place?key=AIzaSyA3t75GJakScXK6VIOF6ecs4Op_zBW8AGo&q=' + direccion);
    }

    init();
});