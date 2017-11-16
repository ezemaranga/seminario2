seminario2.config(function($routeProvider) {
    $routeProvider
    .when("/login", {
        templateUrl : "views/login.html",
        controller : "LoginController",
        controllerAs : "Login",
        reloadOnSearch: false
    })
    .when("/profile", {
        templateUrl : "views/profile.html",
        controller : "ProfileController",
        controllerAs : "Profile",
        reloadOnSearch: false
    })
    .when("/search", {
        templateUrl : "views/search.html",
        controller : "SearchController",
        controllerAs : "Search",
        reloadOnSearch: false
    })
    .when("/partido", {
        templateUrl : "views/partido.html",
        controller : "PartidoController",
        controllerAs : "Partido",
        reloadOnSearch: false
    })
    .otherwise({
        redirectTo: '/login', 
        reloadOnSearch: false
    });
});
