angular.module('market-front').controller('statController', function ($scope, $http, $location, $localStorage) {

    $scope.getTopProducts = function (period, quantity) {
        $http({
            url: 'http://localhost:5555/stat/api/v1/stat/top_items',
            method: 'GET',
            params: {
                period: period,
                quantity: quantity
            }
        }).then(function (response){
            $scope.topProducts = response.data;
        })
    };
    $scope.getTopProducts(5, 10);
});