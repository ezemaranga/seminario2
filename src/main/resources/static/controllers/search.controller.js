seminario2.controller('SearchController', function($scope, $location, SearchService, $sce) {

    var vm = this;

    vm.getMapsURL = getMapsURL;

    function init() {
        getPartidos();
    }

    function getPartidos() {
        SearchService.buscarPartidos().then(function (data) {
                vm.partidos = data;
        });
    }

    function getMapsURL(direccion) {
        return $sce.trustAsResourceUrl('https://www.google.com/maps/embed/v1/place?key=AIzaSyA3t75GJakScXK6VIOF6ecs4Op_zBW8AGo&q=' + direccion);
    }

    init();
});