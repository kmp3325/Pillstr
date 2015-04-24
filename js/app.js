//initialize the angular app

var app = angular.module("pillstrApp", ['ngRoute', 'ui.bootstrap']);

var apiBaseURL = "http://129.21.61.152:8080/";


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
app.controller("prescriptionController", function($scope, $http){
    sessionStorage.setItem('auth', true);
    $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
    //sessionStorage.getItem('userId');
    var userId = 1;
    var prescriptionUrl = apiBaseURL + 'prescriptions/-/by-userId/' + userId;
    $scope.prescriptions = [];
    $http.get(prescriptionUrl)
        .success(function(data, status, headers, config) {
            console.log("GET: " + prescriptionUrl + " was successful");

            if(status === 204)
            {
                console.log("empty result");
            }
            else if(Array.isArray(data))
            {
                $scope.prescriptions = data;
            }
            else
            {
                $scope.prescriptions = [data];
            }

            for(var i = 0; i < $scope.prescriptions.length; i++)
            {
                var reminderUrl = apiBaseURL + 'events/-/by-prescriptionId/' + $scope.prescriptions[i].id;
                $scope.prescriptions[i].metaEvents = [];

                (function(i) {
                    $http.get(reminderUrl)
                        .success(function(data2, status2, headers2, config2) {
                            console.log("GET: " + reminderUrl + " successful");

                            if(status === 204)
                            {
                                console.log("empty result");
                                return;
                            }
                            else if(!Array.isArray(data2))
                            {
                                data2 = [data2];
                            }

                            for(var y = 0; y < data2.length; y++)
                            {
                                var match = false;
                                for(var z = 0; z < $scope.prescriptions[i].metaEvents.length; z++)
                                {
                                    if($scope.prescriptions[i].metaEvents[z].hour == data2[y].hour &&
                                        $scope.prescriptions[i].metaEvents[z].minutes == data2[y].minutes)
                                    {
                                        match = true;
                                        $scope.prescriptions[i].metaEvents[z].days.push(data2[y].day);
                                        $scope.prescriptions[i].metaEvents[z].ids.push(data2[y].id);
                                        break;
                                    }
                                }
                                if(!match)
                                {
                                    data2[y].days = [data2[y].day];
                                    data2[y].ids = [data2[y].id];
                                    $scope.prescriptions[i].metaEvents.push(data2[y]);
                                }
                            }
                        })
                        .error(function(data, status, headers, config) {
                            console.log("GET: " + reminderUrl + " failed");
                        });
                })(i);
            }
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
            val: 0
        },
        {
            name: 'Monday',
            display: 'Mo',
            val: 1
        },
        {
            name: 'Tuesday',
            display: 'Tu',
            val: 2
        },
        {
            name: 'Wednesday',
            display: 'We',
            val: 3
        },
        {
            name: 'Thursday',
            display: 'Th',
            val: 4
        },
        {
            name: 'Friday',
            display: 'Fr',
            val: 5
        },
        {
            name: 'Saturday',
            display: 'Sa',
            val: 6
        }
    ];

    $scope.padTime = function(val) {
        var str = '' + val;
        var pad = '00';
        return pad.substring(0, pad.length - str.length) + str;
    };

    $scope.getTime = function(event) {
        return $scope.padTime(event.hour) + ':' + $scope.padTime(event.minute);
    };

    $scope.toggleDay = function(event, day) {
        var index = event.days.indexOf(day.val);
        if(index >= 0) {
            event.days.splice(index, 1);
        }
        else {
            event.days.push(day.val);
        }
        console.log(event);
    };

    $scope.isDayChecked = function(event, day) {
        return event.days.indexOf(day.val) >= 0;
    };

    $scope.edit = function(prescription) {
        prescription.editing = true;
        prescription.original = JSON.parse(JSON.stringify(prescription));
    };

    $scope.cancelEdit = function(prescription) {
        prescription.confirmingDelete = false;
        if(prescription.isNew) {
            $scope.prescriptions.splice($scope.prescriptions.indexOf(prescription), 1);
        }

        prescription.editing = false;
        prescription.displayName = prescription.original.displayName;
        prescription.dosage = prescription.original.dosage;
        prescription.quantity = prescription.original.quantity;
        prescription.notes = prescription.original.notes;
        prescription.metaEvents = prescription.original.metaEvents;
        prescription.original = undefined;
    };

    $scope.saveEdit = function(prescription) {
        prescription.confirmingDelete = false;
        var parameters = {
            name: prescription.name,
            userId: prescription.userId,
            displayName: prescription.displayName,
            quantity: prescription.quantity,
            notes: prescription.notes,
            dosage: prescription.dosage,
            remind: prescription.remind
        };

        console.log(parameters);
        prescription.editing = false;

        if(prescription.isNew) {
            var data = JSON.stringify(parameters);
            prescription.isNew = false;

            //$http.post(apiBaseURL + 'prescriptions/', data)
            //    .success(function(){
            //        console.log("POST of new prescription successful");
            //    })
            //    .error(function() {
            //        console.log("POST of new prescription unsuccessful");
            //        console.log("POST of new prescription unsuccessful");
            //    });
            $http({
                url: apiBaseURL + 'prescriptions',
                method: 'POST',
                params: parameters,
                data: parameters
            });
        }

        else {
            $http({
                url: apiBaseURL + 'prescriptions/' + prescription.id,
                method: 'PUT',
                params: parameters
            })
            .success(function() {
                console.log("PUT of prescription successful");
            })
            .error(function() {
                console.log("PUT of prescription unsuccessful");
            });
        }
    };

    $scope.addPrescription = function() {
        var newPrescription = {
            isNew: true,
            editing: true,
            name: '',
            userId: userId,
            displayName: '',
            quantity: 0,
            notes: '',
            dosage: 0,
            remind: true
        };
        $scope.prescriptions.push(newPrescription);
    };

    $scope.addEvent = function(prescription) {
        prescription.metaEvents.push({
            prescriptionId: prescription.id,
            days: []
        });
    };

    $scope.toggleRemind = function(prescription) {
        $http({
            url: apiBaseURL + 'prescriptions/' + prescription.id,
            method: 'PUT',
            params: {remind: prescription.remind}
        })
    };

    $scope.confirmingDelete = function(prescription) {
        if(prescription.confirmingDelete === undefined) {
            prescription.confirmingDelete = false;
        }

        return prescription.confirmingDelete;
    };

    $scope.deletePrescription = function(prescription) {
        if(prescription.isNew) {
            $scope.prescriptions.splice($scope.prescriptions.indexOf(prescription), 1);
        }
        else {
            $http({
                url: apiBaseURL + 'prescriptions/' + prescription.id,
                method: 'DELETE'
            })
                .success(function() {
                    console.log('DELETE of prescription successful');
                    $scope.prescriptions.splice($scope.prescriptions.indexOf(prescription), 1);
                })
                .error(function() {
                    console.log('DELETE of prescription unsuccessful');
                })

        }
    };
});

//Settings controller
app.controller("settingController", function($scope){
    sessionStorage.setItem('auth', true);
});

