seminario2.controller('ProfileController', function($scope, $location, ProfileService) {

    var vm = this;

    vm.currentUser = {};

    vm.getStar = getStar;

    function init() {
        ProfileService.getUser().then(function (data) {
            vm.currentUser = data;
        });
    }

    function getStar(num) {
        var aux = num * 2;
        if(aux <= vm.currentUser.calificacion) {
            return 'fa-star'
        } else if (aux-2 >= vm.currentUser.calificacion) {
            return 'fa-star-o'
        } else {
            return 'fa-star-half-o'
        }
    }

    init();

});