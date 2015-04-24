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

    sessionStorage.setItem('auth', false);
    $scope.login = function(){

        var request={
            username : $scope.username,
            password : $scope.password
        };
        console.log(request);

        $http.get('http://129.21.61.152:8080/users/-/check-password/'+request.username+'/'+request.password+'', request).
            success(function(data) {
                $scope.response = data;

                if($scope.response == true){
                    sessionStorage.setItem('currUser', request.username);
                    $location.path('/home');
                }
                else{
                    $scope.state = false;
                    $scope.username = "";
                    $scope.password = "";
                }
            }).
            error(function(data) {
                console.log("Error occurred in login.");

                $scope.state = false;
                $scope.username = "";
                $scope.password = "";
            });
    }

    $scope.createAcct = function(){
        $location.path('/account');
    }
});

//Create Account controller
app.controller("accountController", function($scope, $location, $http){
    sessionStorage.setItem('contrl', "accountController");
    sessionStorage.setItem('auth', false);
    $scope.createNewAcct = function(){
        var request={
            name : $scope.namE,
            email : $scope.email,
            password : $scope.password,
            username : $scope.username,
            phone : $scope.phone
        };
        var url = 'http://129.21.61.152:8080/users?name='+request.name+'&email='+request.email+'&password='+request.password+'&username='+request.username+'&phone='+request.phone+'';
       // console.log("url is: " + url);
        $http.post(url).
            success(function(data) {
                $location.path('/home');
                sessionStorage.setItem('auth', true);
                sessionStorage.setItem('currUser', request.username);
            }).
            error(function(data) {
                console.log("Error occurred in creating new account.");
                console.log(data);
            });
    }
});

//Home controller
app.controller("homeController", function($scope){
    sessionStorage.setItem('contrl', "homeController");
    sessionStorage.setItem('auth', true);
});

//Prescription controller
app.controller("prescriptionController", function($scope){
    sessionStorage.setItem('auth', true);

});

//Settings controller
app.controller("settingController", function($scope, $http, $location){
    sessionStorage.setItem('auth', true);
    var username = sessionStorage.getItem('currUser');
    $http.get('http://129.21.61.152:8080/users/-/by-name/'+username+'').
        success(function(data) {
            $scope.response = data;
            $scope.uId = $scope.response.id;

            $scope.namE = $scope.response.name;
            $scope.email = $scope.response.email;
            $scope.username = $scope.response.username;
            $scope.password = $scope.response.password;
            $scope.phone = $scope.response.phone;
        }).
        error(function(data) {
            console.log("Error occurred in getting user id.");
        });

    $scope.cancelSettings = function(){
        $location.path('/home');
    }

    $scope.saved = false;
    $scope.saveAcct = function(){
        var request={
            name : $scope.namE,
            email : $scope.email,
            password : $scope.password,
            username : $scope.username,
            phone : $scope.phone
        };
        var url = 'http://129.21.61.152:8080/users?name='+request.name+'&email='+request.email+'&password='+request.password+'&username='+request.username+'&phone='+request.phone+'';
        console.log("url is: " + url);

        $http.post(url).
            success(function(data) {
                $scope.saved = true;
                $scope.deleteAcct();
            }).
            error(function(data) {
                console.log("Error occurred in saving settings.");
                console.log(data);
            });
        $scope.saved = true;
    }

    $scope.deleteAcct = function(){
        $http.delete('http://129.21.61.152:8080/users/'+$scope.uId+'').
            success(function(data) {
                if($scope.saved == false){
                    $location.path('/login');
                }
                $scope.saved = false;
            }).
            error(function(data) {
                console.log("Error occurred in deleting user.");
            });
    }
});


