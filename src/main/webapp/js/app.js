var app = angular.module("shubidu", []);
app.controller("URLController", function($scope, $http, $location) {
    $scope.createURL = function createURL(form) {
        if (!form.$valid) {
            return;
        }
        var data = { link: $scope.link};
        $scope.spinner = true;
        $scope.location = location;
        $http.post("/", data).success(function(data, status, headers, config) {
            $scope.spinner = false;
            $scope.url = data;
        }).error(function(data, status, headers, config){
            $scope.spinner = false;
        });
    };

    $scope.reset = function reset() {
        $scope.url = null;
        $scope.link = ""
    }
});