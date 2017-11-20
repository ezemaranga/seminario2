(function () {
	'use strict';

	seminario2.factory('LoginService', LoginService);

	LoginService.$inject = ['$http', '$q'];

	function LoginService($http, $q) {

		var service = {
			crearUsuario: crearUsuario,
			login: login
		};

		function crearUsuario(usuario) {

			var deferred = $q.defer();
           
            $http.post('/usuarios/registracion', usuario)
				.then(function (data) {
					if (data.data.codigoRespuesta != 1) {
						deferred.reject(data.data);
					}

					deferred.resolve(data.data);
				}, function (data) {
					deferred.reject(data.data);
                });
           
			return deferred.promise;
		}

		function login(loginData) {
			
				var deferred = $q.defer();
				
				$http.post('usuarios/login', loginData)
					.then(function (data) {
						if (data.data.codigoRespuesta != 1) {
							deferred.reject(data.data);
						}
	
						deferred.resolve(data.data);
					}, function (data) {
						deferred.reject(data.data);
					});
				
				return deferred.promise;
			}

		return service;
	}

})();