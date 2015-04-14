//initialize the angular app
var app = angular.module("pillstrApp", ['ngRoute']);

//configure app routes
app.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
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
app.controller("loginController", function($scope, $location, $http){

    $scope.login = function(){

        var request={
            email : $scope.email,
            password : $scope.password
        };
        console.log(request);

        $http.post('/login', request).
            success(function(data) {
                $scope.response = data;
                $scope.response = true;

                if($scope.response == true){

                    $location.path('/home');
                }
            }).
            error(function(data) {
                console.log("Error occurred in login.");
                console.log(data);

                $scope.state = false;
                $scope.email = "";
                $scope.password = "";
            });
    }

    $scope.createAcct = function(){
        $location.path('/account');
    }
});

//Create Account controller
app.controller("accountController", function($scope, $location, $http){
    $scope.createNewAcct = function(){
        var request={
            name : $scope.namE,
            email : $scope.email,
            password : $scope.password
        };
        console.log(request);

        $http.post('/account', request).
            success(function(data) {
                $scope.response = data;
                $scope.response = true;

                if($scope.response == true){
                    $location.path('/home');
                }
            }).
            error(function(data) {
                console.log("Error occurred in creating new account.");
                console.log(data);
            });
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

