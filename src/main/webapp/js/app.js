var app = angular.module("shubidu", []);

app.controller("URLController", function($scope, $http, $location) {
    $scope.createURL = function createURL() {
        var data = { link: $scope.link};
        alert($location.path("/"));
        $http.post("/", data).success(function(data, status, headers) {
            $scope.url = data;
        });
    };
});