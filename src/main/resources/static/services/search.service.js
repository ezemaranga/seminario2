(function () {
	'use strict';

	seminario2.factory('SearchService', SearchService);

	SearchService.$inject = ['$http', '$q'];

	function SearchService($http, $q) {

		var service = {
			buscarPartidos: buscarPartidos,
			getDistance: getDistance,
			getDistancias: getDistancias
		};

		function buscarPartidos() {

			var partidos = [
				{
					id: 1,
					dia: '6/11/2017',
					horario:'19:00',
					lugar: 'Av. Rivadavia 1580',
					tipo: 7,
					superficie: 'Sintetico'
				},
				{
					id: 2,
					dia: '10/11/2017',
					horario:'21:00',
					lugar: 'Av. Callao 678',
					tipo: 9,
					superficie: 'Cemento'
				},
				{
					id: 3,
					dia: '22/11/2017',
					horario:'14:00',
					lugar: 'Carlos Calvo 3581',
					tipo: 11,
					superficie: 'Cesped'
				},
				{
					id: 4,
					dia: '7/11/2017',
					horario:'23:30',
					lugar: 'Av Boedo 950',
					tipo: 5,
					superficie: 'Sintetico'
				}
			]

			var deferred = $q.defer();

            /*
			 * $http.post('/GCMSWeb/v2/legis/analyst.do', query)
			 * .success(function (data) { if (data.status != 200) {
			 * deferred.reject(data.body); }
			 * 
			 * deferred.resolve(formatResults(data.body)); }) .error(function
			 * (data) { deferred.reject(data.body); });
			 */
                
            deferred.resolve(partidos);

			return deferred.promise;
		}

		function getDistance(destino) {

			var deferred = $q.defer();

			$http.get('https://maps.googleapis.com/maps/api/distancematrix/json?origins=Lima%20775&key=AIzaSyAww3mb3jlZr5_52lDun4Vw6xydGrGFAxc&destinations=' + destino)
			.then(function (data) {
				if (data.status != 200) {
					deferred.reject(data.body);
				}

				deferred.resolve(data.data.rows[0].elements[0].distance);
			}, function (data) {
				deferred.reject(data);
			});

			
		}

		function getDistancias(partidos) {

			var deferred = $q.defer();

			var partidosConDistancia = [];

			for (var i = 0; i < partidos.length; i++) {
				getDistance(partidos[i].lugar).then(function() {
					

				});
			} 
	
			deferred.resolve(data.data.rows[0].elements[0].distance);

		}



		return service;
	}

})();