var routerApp = angular.module('routerApp', ['ui.router']);
routerApp.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/home');
    $stateProvider
    .state('home', {
        url:'/home',
        templateUrl: 'tpls/chargeMS/student-home.html',
        controller: function($scope, $http) {
            $(function() {
            	$.ajax({
                    url: "../notice_attainNotice.action",
                    type: "POST",
                    data: {
                    	title: "charge"
                    },
                    success: function(data) {
                        $("#notice").html(data);
                    }
                })
            })
        }
    })
    .state('jiaofei', {
        url:'/jiaofei',
        templateUrl: 'tpls/chargeMS/student-jiaofei.html',
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
    .state('chaxun', {
        url:'/chaxun',
        templateUrl: 'tpls/chargeMS/student-chaxun.html',
        controller: function($scope,$http) {
            $http.get("../pay_queryPay.action")
            .success(function(data) {
                $scope.topics = data;
            });
        }
    });
});
