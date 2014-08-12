$(window).bind("load", function() {
    $(".spinner").hide();
});

var app = angular.module("shubidu", []);
app.controller("URLController", function($scope, $http, $location) {
    $scope.createURL = function createURL(form) {
        if (!form.$valid) {
            return;
        }
        $(".spinner").toggle(1500);
        var data = { link: $scope.link};
        $http.post("/", data).success(function(data, status, headers, config) {
            $scope.url = data;
            $(".spinner").toggle(1500);
        }).error(function(data, status, headers, config){
            $(".spinner").toggle(1500);
        });
    };
});