var app = angular.module('myApp', [])
    .factory('timeoutHttpIntercept', function ($rootScope, $q) {
        return {
            'request': function(config) {
                config.timeout = 150000;
                return config;
            }
        };
    });
app.config(['$httpProvider', function($httpProvider) {
    // $httpProvider.defaults.timeout = 20000;
    $httpProvider.interceptors.push('timeoutHttpIntercept');
}])
app.controller('myCtrl', function($scope, $http) {
    this.retrieve = function() {
        console.log('username='+ $scope.name);

        $http.get('http://127.0.0.1:8304/api/db-gateway/rest/db/' + $scope.name)
    .then(function (response) {
        console.log('response: '+ response.data);
        $scope.locations = response.data;
    }, function (response) {
        console.log('error: '+response);
    });
    }


//    this.add = function() {
//        var message = {
//            userName: $scope.name,
//            locations: [$scope.locations]
//        }
//        $http.post('http://127.0.0.1:8300/rest/db/add', message)
//        //$http.post('http://127.0.0.1:8304/api/db-gateway/rest/db/add', message)
//            .then(function(response) {
//                console.log('response: '+ response.data);
//            }, function(response) {
//                console.log('error: '+ response);
//            });
//    }
});
