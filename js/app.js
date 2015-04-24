//initialize the angular app
var app = angular.module("pillstrApp", ['ngRoute']);
var apiBaseURL = "http://74.74.160.36:8080/";

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
    };

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
                sessionStorage.setItem('auth', true);
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
app.controller("prescriptionController", function($scope, $http){
    sessionStorage.setItem('auth', true);
    //sessionStorage.getItem('userId');
    var userId = 1;
    var prescriptionUrl = apiBaseURL + 'prescriptions/' + userId;
    $scope.prescriptions = [];
    $http.get(prescriptionUrl)
        .success(function(data, status, headers, config) {
            console.log("GET: " + prescriptionUrl + " was successful");
            if(Array.isArray(data))
            {
                $scope.prescriptions = data;
            }
            else
            {
                $scope.prescriptions = [data];
            }
            console.log(data);
        })
        .error(function() {
            console.log("GET: " + prescriptionUrl + " error");
        });

    var reminderUrl = apiBaseURL + 'reminders/';
    $scope.reminders = [];

    $scope.isEditing = function(prescription) {
        if(!prescription.hasOwnProperty('editing')) {
            prescription.editing = false;
        }

        return prescription.editing;
    };


    $scope.days = [
        {
            name: 'Sunday',
            display: 'Su',
            val: 0,
            checked: false
        },
        {
            name: 'Monday',
            display: 'Mo',
            val: 1,
            checked: false
        },
        {
            name: 'Tuesday',
            display: 'Tu',
            val: 2,
            checked: false
        },
        {
            name: 'Wednesday',
            display: 'We',
            val: 3,
            checked: false
        },
        {
            name: 'Thursday',
            display: 'Th',
            val: 4,
            checked: false
        },
        {
            name: 'Friday',
            display: 'Fr',
            val: 5,
            checked: false
        },
        {
            name: 'Saturday',
            display: 'Sa',
            val: 6,
            checked: false
        }
    ];
});

//Settings controller
app.controller("settingController", function($scope){
    sessionStorage.setItem('auth', true);
});

