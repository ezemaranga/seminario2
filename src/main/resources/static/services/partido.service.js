(function() {
	'use strict';

	seminario2.factory('PartidoService', PartidoService);

	PartidoService.$inject = [ '$http', '$q' ];

	function PartidoService($http, $q) {

		var service = {
			buscarPartidos : buscarPartidos,
			crearPartido : crearPartido
		};

		function buscarPartidos() {

			var deferred = $q.defer();

			$http.get('/partidos/all').then(
					function(data) {
						if (data.status != 200) {
							deferred.reject(data);
						}

						deferred.resolve(data.data.partidos);
					}, function(data) {
				deferred.reject(data);
			});

			return deferred.promise;
		}

		function crearPartido(partido) {
			
			var deferred = $q.defer();

			$http.post('/partidos/creacion', {partido: partido}).then(
					function(data) {
						if (data.status != 200) {
							deferred.reject(data);
						}

						deferred.resolve(data.data.partido);
					}, function(data) {
				deferred.reject(data);
			});

			return deferred.promise;
		}

		return service;
	}

})();