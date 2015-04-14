//initialize the angular app
var app = angular.module("pillstrApp", ['ngRoute']);

//configure app routes
app.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            /*when('/', {
                templateUrl: 'index.html',
                controller: 'indexController'
            }).*/
            when('/login', {
                templateUrl: 'templates/login.html',
                controller: 'loginController'
            }).
            when('/account', {
                templateUrl: 'templates/account.html',
                controller: 'accountController'
            }).
            when('/home', {
                templateUrl: 'templates/home.html',
                controller: 'homeController'
            }).
            when('/prescriptions', {
                templateUrl: 'templates/prescriptions.html',
                controller: 'prescriptionController'
            }).
            when('/settings', {
                templateUrl: 'templates/settings.html',
                controller: 'settingController'
            }).
            otherwise({
                redirectTo: '/login'
            });
    }]);


//Login controller
app.controller("loginController", function($scope, $location){
$scope.createAcct = function(){
    $location.path('/account');
}
});

//Create Account controller
app.controller("accountController", function($scope, $location){
    $scope.createAcct = function(){
        $location.path('/home');
    }
});

//Home controller
app.controller("homeController", function($scope){

});

//Prescription controller
app.controller("prescriptionController", function($scope){

});

//Settings controller
app.controller("settingController", function($scope){

});
