var routerApp = angular.module('routerApp', ['ui.router']);
routerApp.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/home');
    $stateProvider
        .state('home', {
            url: '/home',
            templateUrl: 'tpls/trainingPS/student-home.html',
            controller: function($scope, $http) {
                $http.get("../student_getPersonalMessage.action")
                    .success(function(data) {
                        $scope.perInfors = data;
                    });
            }
        })
        .state('selectCourse', {
            url: '/selectCourse',
            templateUrl: 'tpls/trainingPS/student-selectCourse.html',
            controller: function($rootScope) {
                $("#confirm").on("click", function() {
                    var $courseId = $(".bigRadio:checked").parent().next();
                    var $courseName = $courseId.next();
                    var $teacherNumber = $courseId.next().next().next().next().find("input");
                    $.ajax({
                        url: "../course_chooseCourse.action",
                        type: "POST",
                        data: {
                            courseId: $courseId.text(),
                            courseName: $courseName.text(),
                            courseType: $teacherNumber.val()
                        },
                        //dataType:"JSON",
                        success: function(data) {
                            alert(data);
                            window.location.reload(true);
                        },
                        error: function(e1, e2) {
                            alert([e1, e2]);
                        }
                    })
                });
                function loadSelectCourseTbody(obj){
                    $(".selectCourseTbody").html("");
                    for (var i = 0; i < obj.length; i++) {
                        $(".selectCourseTbody").append('<tr>' + '<td>' + 
                        		'<input class="bigRadio" type="radio" name="course" />' 
                        		+ '</td>' + '<td>' + obj[i].course.courseId + '</td>' + '<td>' 
                        		+ obj[i].course.courseName + '</td>' + '<td>'
                        		+ obj[i].course.courseType + '</td>' + '<td>' 
                        		+ obj[i].course.credit + '</td>' + '<td>' 
                        		+ obj[i].teacher.teacherName 
                        		+ '<input type="hidden" value="' + obj[i].teacher.teacherNumber + '" />' 
                        		+ '</td>' + '</tr>');
                    }
                }
                //分页事件
                $rootScope.paging("ul.pagination", "../course_queryCourseNumber.action", "../course_queryCourse.action", loadSelectCourseTbody);
            }
        })
        .state('selectResult', {
            url: '/selectResult',
            templateUrl: 'tpls/trainingPS/student-selectResult.html',
            controller: function($scope, $http) {
                $http.get("../choosing_queryResult.action")
                    .success(function(data) {
                        $scope.courses = data;
                    });
            }
        })
        .state('cancelCourse', {
            url: '/cancelCourse',
            templateUrl: 'tpls/trainingPS/student-cancelCourse.html',
            controller: function($scope, $http) {
                $(function() {
                    $("#cancel").on("click", function() {
                        var choosingId = $(".bigRadio:checked").parent().attr("data-id");
                        $.ajax({
                            url: "../choosing_deleteChoosing.action",
                            type: "POST",
                            data: {
                                choosingId: choosingId
                            },
                            //dataType:"JSON",
                            success: function(data) {
                                alert("退课成功");
                                $(".bigRadio:checked").parent().parent().remove();
                            },
                            error: function(e1, e2) {
                                alert([e1, e2]);
                            }
                        })
                    });
                    $http.get("../choosing_queryResult.action")
                        .success(function(data) {
                            $scope.courses = data;
                        });
                });
            }
        })
        .state('nowGrade', {
            url: '/nowGrade',
            templateUrl: 'tpls/trainingPS/student-nowGrade.html',
            controller: function($rootScope) {
                function loadNowGradeTbody(course){
                    $(".nowGradeTbody").html("");
                    for (var i = 0; i < course.length; i++) {
                        $(".nowGradeTbody").append('<tr>' + '<td>' + course[i].course.courseId + '</td>' + '<td>' + course[i].course.courseName + '</td>' + '<td>' + course[i].course.courseType + '</td>' + '<td>' + course[i].course.credit + '</td>' + '<td>' + course[i].score + '</td>' + '</tr>');
                    }
                }
                //配置一切 里面填url1,url2
                $rootScope.paging("ul.pagination", "../achievement_queryNowGradeNumber.action", "../achievement_queryNowGrade.action", loadNowGradeTbody);
            }
        })
        .state('allGrade', {
            url: '/allGrade',
            templateUrl: 'tpls/trainingPS/student-allGrade.html',
            controller: function($rootScope) {

                function loadAllGradeTbody(course) {
                    $(".allGradeTbody").html("");
                    for (var i = 0; i < course.length; i++) {
                        $(".allGradeTbody").append('<tr>' + '<td>' + course[i].course.courseId + '</td>' + '<td>' + course[i].course.courseName + '</td>' + '<td>' + course[i].course.courseType + '</td>' + '<td>' + course[i].course.credit + '</td>' + '<td>' + course[i].score + '</td>' + '</tr>');
                    }
                }

                $rootScope.paging("ul.pagination", "../achievement_queryAllGradeNumber.action", "../achievement_queryAllGrade.action", loadAllGradeTbody);
            }
        });
});
routerApp.run(function($rootScope) {
    //---通用方法---

    //分页系列操作
    $rootScope.paging = function(parentElement, url1, url2, loadTbody) {
        //分页事件
        $(parentElement).off("click").on("click", "li a", function() {
                //console.log($(this).text());
                //下一页
                if ($(this).attr("name") == "next") {
                    var nownum = parseInt($(parentElement).find("li.active").text());
                    //console.log(nownum);
                    if (!nownum) {
                        nownum = 1;
                        alert("页码不存在?改为默认1");
                    }
                    //console.log($("ul.pagination").find("li").length);
                    var allLength = $(parentElement).find("li").length - 2;
                    if (nownum + 1 <= allLength) {
                        loadNumPage(nownum + 1);
                    }
                }
                //选择页码
                if ($(this).attr("name") == "num") {
                    loadNumPage($(this).text());
                }
                //上一页
                if ($(this).attr("name") == "prev") {
                    var nownum = parseInt($(parentElement).find("li.active").text());
                    //console.log(nownum);
                    if (!nownum) {
                        nownum = 2;
                        alert("页码不存在?改为默认1");
                    }
                    if (nownum - 1 > 0) {
                        loadNumPage(nownum - 1);
                    }
                }
            })
            //获取分页数量
        $.ajax({
            url: url1,
            type: "GET",
            success: function(data) {
                var num = parseInt(data);
                $(parentElement).html("");
                if (num >= 1) {
                    var html = '<li><a name="prev">&laquo;</a></li>';
                    for (var i = 0; i < num; i++) {
                        html += '<li><a name="num">' + (i + 1) + '</a></li>';
                    }
                    html += '<li><a name="next">&raquo;</a>';
                    $(parentElement).html(html);
                    loadNumPage(1);
                }else{
                    loadTbody([]);
                }
            
            }
        })

        function loadNumPage(num) {
            $.ajax({
                url: url2,
                type: "POST",
                data:{
                    num:num
                },
                success: function(data) {
                    if (data){
                        var obj = JSON.parse(data);
                        loadTbody(obj);
                        //清空 li的active
                        $(parentElement).find("li").attr("class", "");
                        //设置active
                        $(parentElement).find("li").eq(num).attr("class", "active");
                    }
                }
            })
        }
    }

})
