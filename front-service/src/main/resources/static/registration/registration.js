angular.module('market-front').controller('regController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/auth/';

    $scope.regNewUser = function () {
        $http.post(contextPath + 'registration', $scope.regUser)
            .then(function (response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.regUser.username, token: response.data.token};
                    $localStorage.regUser = null;
                    $location.path('/');
                }
            });
    };
});