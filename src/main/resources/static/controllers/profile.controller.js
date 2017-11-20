seminario2.controller('ProfileController', function($scope, $location, ProfileService) {

    var vm = this;

    vm.getStar = getStar;
    vm.editHabilidad = editHabilidad;
    vm.editUser = editUser;
    vm.goToEdit = goToEdit;

    vm.currentUser = seminario2.controller('MainController').currentUser;

    function init() {

    }

    function getStar(num) {
        var aux = num * 2;
        if(aux <= vm.currentUser.reputacion) {
            return 'fa-star'
        } else if (aux-2 >= vm.currentUser.reputacion) {
            return 'fa-star-o'
        } else {
            return 'fa-star-half-o'
        }
    }

    function editUser() {
        vm.loading = true;
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

    init();

});