seminario2.controller('LoginController', function($scope, $location, LoginService, $route, $templateCache) {

	var MainCtrl = seminario2.controller('MainController');

    var vm = this;

    vm.nuevoUsuario = {};

    vm.loginData = {};
    
    vm.login = function() {
        
    }

    vm.alert = function() {
        alert( "login controller" );
    }

    vm.signup = function() {
        vm.loading = true;
        LoginService.crearUsuario(vm.nuevoUsuario).then(function(data) {
            vm.loading = false;
            $location.path( "/login" );
        }, function(data) {
            alert(data.mensaje);
        });
    }

    vm.login = function() {
        vm.loading = true;
        LoginService.login(vm.loginData).then(function(data) {
            vm.loading = false;
            seminario2.controller('MainController').currentUser = data.usuario;
            if(data.usuario.edad) {
                $location.path( "/profile" );
            } else {
                $location.path( "/editProfile" );
            }
            //Suscripcion a la queue del usuario
            var userQueue = '/topic/user' + data.usuario.id;
			var generalQueue = '/topic/all' + data.usuario.id;
			var userReviewQueue = '/topic/user/review' + data.usuario.id;
	        var subscription = ws.subscribe(userQueue,
	        	function (payload, headers, res) {
	            	console.log(payload);
	            	var message = "<div class=\"modalFelicidades\">¡Felicidades!</div>";
	            	message += "<div class=\"modalTextoSeleccionado\">Has sido seleccionado para el siguiente partido:</div>";
	            	message += "<div class=\"modalPartidoDatos\">Direccion: " + payload.direccion + "</div>";
	            	message += "<div class=\"modalPartidoDatos\">Dia: " + payload.dia + "</div>";
	            	message += "<div class=\"modalPartidoDatos\">Horario: " + payload.horario + "</div>";
	            	document.getElementsByClassName('modal-body')[0].innerHTML = message;
	            	document.getElementsByClassName('titulomodal')[0].innerHTML = "Aceptado";
	            	openModalUserAccepted();
	            });
	        var subscription2 = ws.subscribe(generalQueue,
	        	function (payload, headers, res) {
	            	console.log(payload);
	            	// reload
	            	var currentPageTemplate = $route.current.templateUrl;
			        $templateCache.remove(currentPageTemplate);
			        $route.reload();
	            	//alert("Refreshqueue");
	            });
	        var subscription3 = ws.subscribe(userReviewQueue,
	        	function (payload, headers, res) {
	            	console.log(payload);
	            	var message = "<div class=\"modalFelicidades\">¡Calificación recibida!</div>";
	            	message += "<div class=\"modalPartidoDatos calificacion\">" + payload.calificacion + "</div>";
	            	document.getElementsByClassName('titulomodal')[0].innerHTML = "Calificación";
	            	document.getElementsByClassName('modal-body')[0].innerHTML = message;
	            	openModalUserAccepted();
	            });
	       okbtn.onclick = function() {
              modal.style.display = "none";
			    var xmlhttp = new XMLHttpRequest();
			    xmlhttp.open("GET", "/websocket/refreshMatches?userId=" + data.usuario.id, true);
			    xmlhttp.send();
          }
	        
        }, function(data) {
            alert(data.mensaje);
        });
    }

});