//initialize the angular app
var app = angular.module("pillstrApp", ['ngRoute', 'ui.bootstrap']);

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
    $scope.reminders = [
        {
            "id": 0,
            "prescription": "Gabapentin",
            "userId": 7,
            "displayName": "Neurontin",
            "quantity": 6.2,
            "day": 0,
            "time": "9:00",
            "taken": true,
            "remaining": 10,
            "notes": "Lorem nostrud tempor sunt sint ea cillum culpa enim culpa excepteur."
        },
        {
            "id": 1,
            "name": "Citalopram Hydrobromide",
            "userId": 7,
            "displayName": "Celexa",
            "quantity": 3.3,
            "day": 0,
            "time": "12:00",
            "taken": false,
            "remaining": 20,
            "notes": "Cupidatat amet reprehenderit esse culpa eiusmod dolore veniam."
        },
        {
            "id": 2,
            "name": "CIV Alprazolam",
            "userId": 10,
            "displayName": "Xanax",
            "quantity": 6.2,
            "day": 2,
            "time": "15:00",
            "taken": false,
            "remaining": 6,
            "notes": "Fugiat amet irure adipisicing do cupidatat nostrud cupidatat mollit duis enim id do deserunt."
        },
        {
            "id": 3,
            "name": "Clonazepam",
            "userId": 2,
            "displayName": "Klonopin",
            "quantity": 9.7,
            "day": 4,
            "time": "10:00",
            "taken": true,
            "remaining": 12,
            "notes": "Eu qui est minim proident non qui."
        },
        {
            "id": 4,
            "name": "Ciprofloxacin Hydrochloride",
            "userId": 8,
            "displayName": "Cipro",
            "quantity": 3.6,
            "day": 4,
            "time": "18:00",
            "taken": false,
            "remaining": 31,
            "notes": "Nisi excepteur deserunt magna velit enim exercitation consectetur fugiat deserunt amet aute."
        },
        {
            "id": 5,
            "name": "Oxycodone and Acetaminophen",
            "userId": 6,
            "displayName": "Percocet",
            "quantity": 0.5,
            "day": 6,
            "time": "10:30",
            "taken": false,
            "remaining": 27,
            "notes": "Proident sit veniam eu laboris veniam ut adipisicing."
        }
    ];

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

//Prescription controller
app.controller("prescriptionController", function($scope){
    sessionStorage.setItem('auth', true);

    $scope.prescriptions = [
        {
            "id": 0,
            "name": "Slovak Republic",
            "userId": 7,
            "displayName": "Palau",
            "quantity": 6.2,
            "notes": "Lorem nostrud tempor sunt sint ea cillum culpa enim culpa excepteur."
        },
        {
            "id": 1,
            "name": "Netherlands",
            "userId": 7,
            "displayName": "Tennessee",
            "quantity": 3.3,
            "notes": "Cupidatat amet reprehenderit esse culpa eiusmod dolore veniam."
        },
        {
            "id": 2,
            "name": "Togo",
            "userId": 10,
            "displayName": "Massachusetts",
            "quantity": 6.2,
            "notes": "Fugiat amet irure adipisicing do cupidatat nostrud cupidatat mollit duis enim id do deserunt."
        },
        {
            "id": 3,
            "name": "Myanmar",
            "userId": 2,
            "displayName": "Delaware",
            "quantity": 9.7,
            "notes": "Eu qui est minim proident non qui."
        },
        {
            "id": 4,
            "name": "Fiji",
            "userId": 8,
            "displayName": "Nebraska",
            "quantity": 3.6,
            "notes": "Nisi excepteur deserunt magna velit enim exercitation consectetur fugiat deserunt amet aute."
        },
        {
            "id": 5,
            "name": "Canada",
            "userId": 6,
            "displayName": "Utah",
            "quantity": 0.5,
            "notes": "Proident sit veniam eu laboris veniam ut adipisicing."
        }
    ];

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

