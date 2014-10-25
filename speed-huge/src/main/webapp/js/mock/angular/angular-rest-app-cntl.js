var restApp = angular.module('restApp', ['ngResource']);

restApp.controller('appController', ['$scope','$resource', function($scope, $resource){
    $scope.getUser = function() {
        var User = $resource('/huge/test/angular/get/:userId',{userId:'@id'});
        var user = User.get({userId: 25});
        
        $scope.queryUser = user;
    }
}]);