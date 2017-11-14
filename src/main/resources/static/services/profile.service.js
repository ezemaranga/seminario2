(function () {
	'use strict';

	seminario2.factory('ProfileService', ProfileService);

	ProfileService.$inject = ['$http', '$q'];

	function ProfileService($http, $q) {

		var service = {
			getUser: getUser
		};

		function getUser() {

			var user = {
				"id": "59fb372140113a00d8ad9c52",
				"username": "greobasco",
				"password": "123",
				"email": "greobasco@gmail.com",
				"edad": 25,
				"latitud": 1,
				"longitud": 1,
				"ataja": "VERDE",
				"defensa": "ROJO",
				"ataque": "AMARILLO",
				"estado": "VERDE",
				"habilidad": "VERDE",
				"tactica": "ROJO",
				"jugados": [],
				"organizados": [],
				"reputacion": "UTIL",
				calificacion : 7
			}

			var deferred = $q.defer();

            /*
            $http.post('/GCMSWeb/v2/legis/analyst.do', query)
				.success(function (data) {
					if (data.status != 200) {
						deferred.reject(data.body);
					}

					deferred.resolve(formatResults(data.body));
				})
				.error(function (data) {
					deferred.reject(data.body);
                });
            */
                
            deferred.resolve(user);

			return deferred.promise;
		}

		return service;
	}

})();