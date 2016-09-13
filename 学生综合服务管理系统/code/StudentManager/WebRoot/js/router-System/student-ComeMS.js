var routerApp = angular.module('routerApp', ['ui.router']);
routerApp.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/home');
    $stateProvider
        .state('home', {
            url: '/home',
            templateUrl: 'tpls/comeMS/student-home.html',
            controller: function($scope, $http) {
                $(function() {
                    $.ajax({
                        url: "../register_attainNotice.action",
                        type: "GET",
                        success: function(data) {
                            $("#notice").html(data);
                        }
                    })
                })
            }
        })
        .state('selectBed', {
            url: '/selectBed',
            templateUrl: 'tpls/comeMS/student-selectBed.html',
            controller: function($scope, $http) {
            	$(function() {
                    $(".roomSelect").on("click", ".roomBox", function() {
                        $(".roomBox").removeClass("onclick");
                        $(this).toggleClass('onclick');
                    })    
                    $(".formBtn").on("click",function(){
                    	var $select=$(".roomSelect").find(".onclick");
                    	var roomId=$select.find(".room").attr("data-id");
                    	$.ajax({
                    		url:"../room_chooseRoom.action",
                    		type:"POST",
                    		data:{
                    			roomId: roomId
                    		},
                    		success:function(data){
                    			alert(data);
                    			$(".roomBox").remove();
                    		},
                    		error:function(e1, e2){
                    			alert([e1, e2]);
                    		}
                    	})
                    })
                });
                $http.get("../room_query.action")
                    .success(function(data) {
                    	if (data != ""){
                            $scope.rooms = data;
                    	} else {
                    		$(".formBtn").hide(200);
                    	}
                    });
            }

        })
        .state('selectResult', {
            url: '/selectResult',
            templateUrl: 'tpls/comeMS/student-selectResult.html',
            controller: function($scope, $http) {
                $http.get("../student_queryRoom.action")
                    .success(function(data) {
                    	if (data != ""){
                    		$scope.rooms = data;
                    	}
                    });
            }
        })
        .state('allExpenses', {
            url: '/allExpenses',
            templateUrl: 'tpls/comeMS/student-allExpenses.html',
            controller: function($scope, $http) {
                $http.get("../feetype_queryStudentFeetype.action?value=1")
                    .success(function(data) {
                        $scope.expenses = data;
                    });
            }
        })
        .state('paying', {
            url: '/paying',
            templateUrl: 'tpls/comeMS/student-paying.html',
            controller: function($scope, $http) {
            	$(function(){
            		$("#pay").on("click", function(){
            			$.ajax({
                    		url:"../pay_addPay.action",
                    		type:"POST",
                    		data:{
                    			payId: $(":radio:checked").parent().next().attr("data-id")
                    		},
                    		//dataType:"JSON",
                    		success:function(data){
                    			alert("交费成功！！！");
                    			window.location.reload(true);
                    		},
                    		error:function(e1, e2){
                    			alert([e1, e2]);
                    		}
                    	})
            		})
            	})
                $http.get("../feetype_queryStudentFeetype.action?value=0")
                    .success(function(data) {
                        $scope.expenses = data;
                    });
            }
        })
        .state('bill', {
            url: '/bill',
            templateUrl: 'tpls/comeMS/student-bill.html',
            controller: function($scope,$http) {
                $http.get("../pay_queryPay.action")
                .success(function(data) {
                    $scope.topics = data;
                });
            }
        })
        .state('printForm', {
            url: '/printForm',
            templateUrl: 'tpls/comeMS/student-printForm.html',
            controller: function($scope, $http) {
                $http.get("../student_getPersonalMessage.action")
                    .success(function(data) {
                        $scope.perInfors = data;
                        $.ajax({
                    		url:"../pay_queryPay.action",
                    		type:"POST",
                    		success:function(data){
                    			var obj = JSON.parse(data);
                    			for (var i = 0; i < obj.length; i++){
                    				$("tbody").append("<tr><td>" + obj[i].feetype.fee.feeName + "</td>" +
                    						"<td>已缴</td></tr>");
                    			}
                    			$.ajax({
                            		url:"../feetype_queryStudentFeetype.action?value=0",
                            		type:"POST",
                            		success:function(data){
                            			var obj = JSON.parse(data);
                            			for (var i = 0; i < obj.length; i++){
                            				$("tbody").append("<tr><td>" + obj[i].fee.feeName + "</td>" +
                            						"<td>未缴</td></tr>");
                            			}
                            		}
                            	})
                    		}
                    	})
                    });
            }
        })
//        .state('appointment', {
//            url: '/appointment',
//            templateUrl: 'tpls/comeMS/student-appointment.html'
//        })
//        .state('healthCare', {
//            url: '/healthCare',
//            templateUrl: 'tpls/comeMS/student-healthCare.html'
//        });
});
