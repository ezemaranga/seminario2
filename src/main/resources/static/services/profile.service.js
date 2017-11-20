(function () {
	'use strict';

	seminario2.factory('ProfileService', ProfileService);

	ProfileService.$inject = ['$http', '$q'];

	function ProfileService($http, $q) {

		var service = {
			getUser: getUser,
			editUser: editUser
		};

		function getUser() {
			
			return "push push";
		}

		function editUser(user) {
			
			var deferred = $q.defer();
			
			$http.post('usuarios/perfil', {usuario: user})
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