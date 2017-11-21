(function() {
	'use strict';

	seminario2.factory('PartidoService', PartidoService);

	PartidoService.$inject = [ '$http', '$q' ];

	function PartidoService($http, $q) {

		var service = {
			buscarPartidos : buscarPartidos,
			crearPartido : crearPartido,
			postularme: postularme,
			organizados: organizados,
			getPostulados: getPostulados,
			aceptarPostulacion: aceptarPostulacion
		};

		function buscarPartidos(params) {

			var deferred = $q.defer();

			$http.get('/partidos/all?' + params).then(
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

		function postularme(partidoId, usuarioId) {
			
			var deferred = $q.defer();

			var params = {
				"idPartido": partidoId,
				"idJugador": usuarioId
		  	};

			$http.post('/partidos/postularme', params).then(
					function(data) {
						if (data.status != 200) {
							deferred.reject(data);
						}

						deferred.resolve(data.data);
					}, function(data) {
				deferred.reject(data);
			});

			return deferred.promise;
		}

		
		function organizados(usuarioId) {
			
			var deferred = $q.defer();

			$http.get('/partidos/organizados?idOrganizador=' + usuarioId).then(
					function(data) {
						if (data.status != 200) {
							deferred.reject(data);
						}

						deferred.resolve(data.data);
					}, function(data) {
				deferred.reject(data);
			});

			return deferred.promise;
		}

		function getPostulados(idPartido) {
			
			var deferred = $q.defer();

			$http.get('/partidos/postulaciones?idPartido=' + idPartido).then(
					function(data) {
						if (data.status != 200) {
							deferred.reject(data);
						}

						deferred.resolve(data.data);
					}, function(data) {
				deferred.reject(data);
			});

			return deferred.promise;
		}

		function aceptarPostulacion(idUsuario, idPartido) {
			
			var deferred = $q.defer();

			var params = {
				"idPartido": idPartido,
				"idUsuarioJugador": idUsuario
		  	}

			$http.post('/partidos/aceptar', params).then(
					function(data) {
						if (data.status != 200) {
							deferred.reject(data);
						}

						deferred.resolve(data.data);
					}, function(data) {
				deferred.reject(data);
			});
			
			$http.get('/websocket/acceptUserForMatch?' + 'userId=' + idUsuario + '&matchId=' + idPartido);

			return deferred.promise;
		}

		return service;
	}

})();