var routerApp = angular.module('routerApp', ['ui.router']);
routerApp.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/seeStudyPlan');
    $stateProvider
        .state('seeStudyPlan', {
            url: '/seeStudyPlan',
            templateUrl: 'jsp/tpls/teacherMS/seeStudyPlan.html',
            controller: function($rootScope) {
                //预加载---主页面
                loadMainP(".studyPlanTbody");
                //方法---预加载---主页面
                function loadMainP(parentElement) {
                    $(parentElement).html();
                    $.ajax({
                        url:"specialtycourse_queryTeacherCourse.action",
                        type:"GET",
                        success:function(data){
                        	//if-success
                        	var obj = JSON.parse(data);
                            var data_length = obj.length;
                            for (var i = 0; i < data_length; i++) {
                                $(parentElement).append('<tr data-id=' + obj[i].specialtyCourseId +'>' 
                                		+ '<td name="xueyuan">'
                                		+ obj[i].specialty.college.collegeName + '</td>' + '<td name="zhuanye">'
                                		+ obj[i].specialty.specialtyName + '</td>' + '<td name="xueqi">'
                                		+ obj[i].term + '</td>' + '<td name="course">'
                                		+ obj[i].teach.course.courseName + '</td>' + '</tr>');
                            }
                            $.ajax({
                                url:"teach_queryElectives.action",
                                type:"GET",
                                success:function(data){
                                	//if-success
                                	var obj = JSON.parse(data);
                                    var data_length = obj.length;
                                    for (var i = 0; i < data_length; i++) {
                                        $(parentElement).append('<tr><td name="xueyuan"></td>' 
                                        		+ '<td name="zhuanye"></td><td name="xueqi">'
                                        		+ '</td><td name="course">'
                                        		+ obj[i].course.courseName + '</td></tr>');
                                    }
                                }
                            })
                        }
                    })
                }
            }
        })
        .state('entryCompulsoryScore', {
            url: '/entryCompulsoryScore',
            templateUrl: 'jsp/tpls/teacherMS/entryCompulsoryScore.html',
            controller: function($rootScope) {
                //预加载---主页面
                loadMainP();

                //事件---entryScoreBtn
                $(".studyPlanTbody").on("click", ".entryScoreBtn", function() {
                    if ($(".modInfor").css("display") == "none") {
                        $("#pTable").css("visibility", "hidden");
                        $(".modInfor").show(300);
                    }
                    //预加载---根据缴费项id 加载 详细缴费标准
                    var data = {
                    		"specialtyCourseId": $(this).parent().parent().attr("data-id"),
                            "planXueyuan": $(this).parent().siblings("td[name='xueyuan']").text(),
                            "planZhuanye": $(this).parent().siblings("td[name='zhuanye']").text(),
                            "planCourse": $(this).parent().siblings("td[name='course']").text(),
                            "planXueqi": $(this).parent().siblings("td[name='xueqi']").text()
                        }
                        //console.log(data);
                    loadModInfor(data);
                })

                //事件---returnBtn 返回到主页面
                $("#returnBtn").on("click", function() {
                    if ($(".modInfor").css("display") == "block") {
                        $(".modInfor").hide(300);
                        $("#pTable").css("visibility", "visible");
                    }
                })

                //事件---只能输入数字
                $(".entryScoreTbody").on("keyup", ".onlyNum", function() {
                    $(this).val($(this).val().replace(/[^0-9]/g, ''));
                })
                $(".entryScoreTbody").on("afterpaste", ".onlyNum", function() {
                        $(this).val($(this).val().replace(/[^0-9]/g, ''));
                    })
                    //事件--- 0<=分数<=100
                $(".entryScoreTbody").on("change", ".scoreTextInput", function() {
                        if ($(this).val() <= 100 && $(this).val() >= 0) {

                        } else {
                            $(this).val("");
                            alert("0<=分数<=100");
                        }
                    })
                    //事件---提交分数
                $(".entryScoreTbody").on("click", ".subScoreBtn", function() {

                    if ($(this).parent().siblings("td[name='score']").find("input[type='text']").val()) {
                        //can submit
                        var data = {
                        	"specialtyCourseId": $(this).parent().parent().attr("data-id"),
                            "xueyuan": $("span#spanXueyuan").text(),
                            "zhuanye": $("span#spanZhuanye").text(),
                            "banji": $(this).parent().siblings("td[name='banji']").text(),
                            "xueqi": $("span#spanXueqi").text(),
                            "course": $("span#spanCourse").text(),
                            "stuId": $(this).parent().siblings("td[name='stuId']").text(),
                            "score": $(this).parent().siblings("td[name='score']").find("input[type='text']").val()
                        }
                        var $tr = $(this).parent().parent();
                        $.ajax({
                            url:"achievement_addAchievement.action",
                            type:"POST",
                            data:{
                            	score: data.score,
                            	term: data.xueqi,
                            	courseName: data.course,
                            	studentNumber: data.stuId
                            },
                            success:function(data){
                            	alert("提交成功！！！");
                            	var specialtyCourseId=$("p.titleP").attr("data-id");
                                $rootScope.paging("ul.pagination",{
                                    numUrl:"achievement_queryStudentCourseNumber.action",
                                    dataUrl:"achievement_queryStudentCourse.action",
                                    data:{
                                        achievementId: specialtyCourseId
                                    }
                                }, loadEntryScoreTbody);
                            }
                        })
                        //if-success
                    }

                })

                //方法---预加载---主页面
                function loadMainP() {
                    $.ajax({
                        url:"specialtycourse_queryTeacherCourse.action",
                        type:"GET",
                        success:function(data){
                        	//if-success
                        	var obj = JSON.parse(data);
                            $(".studyPlanTbody").html();
                            for (var i = 0; i < obj.length; i++) {
                                $(".studyPlanTbody").append('<tr data-id=' + obj[i].specialtyCourseId +'>' 
                                        + '<td name="xueyuan">'
                                        + obj[i].specialty.college.collegeName + '</td>' + '<td name="zhuanye">'
                                        + obj[i].specialty.specialtyName + '</td>' + '<td name="xueqi">'
                                        + obj[i].term + '</td>' + '<td name="course">'
                                        + obj[i].teach.course.courseName + '</td><td>'
                                        + '<input class="entryScoreBtn" type="button" value="录入成绩" />' 
                                        + '</td></tr>');
                            }
                        }

                    })
                }
                function loadStudyPlanTbody(obj) {
                    $(".studyPlanTbody").html();
                    for (var i = 0; i < obj.length; i++) {
                        $(".studyPlanTbody").append('<tr data-id=' + obj[i].specialtyCourseId +'>' 
                                + '<td name="xueyuan">'
                                + obj[i].specialty.college.collegeName + '</td>' + '<td name="zhuanye">'
                                + obj[i].specialty.specialtyName + '</td>' + '<td name="xueqi">'
                                + obj[i].term + '</td>' + '<td name="course">'
                                + obj[i].teach.course.courseName + '</td><td>'
                                + '<input class="seeScoreBtn" type="button" value="查看成绩" />' 
                                + '</td></tr>');
                    }
                }
                //方法---预加载---modInfor
                function loadModInfor(data) {
                    //load-title
                    $("span#spanXueyuan").html(data.planXueyuan);
                    $("span#spanZhuanye").html(data.planZhuanye);
                    $("span#spanXueqi").html(data.planXueqi);
                    $("span#spanCourse").html(data.planCourse);
                    $("p.titleP").attr("data-id", data.specialtyCourseId);
                    //load-table
                    /*$.ajax({
                        url:"achievement_queryStudentCourse.action",
                        type:"POST",
                        data:{
                        	achievementId: data.specialtyCourseId
                        },
                        success:function(data){
                        	//if-success
                        	var obj = JSON.parse(data);
                            $(".entryScoreTbody").html(""); //清空
                            for (var i = 0; i < obj.length; i++) {
                                $(".entryScoreTbody").append('<tr data-id=' + specialtyCourseId 
                                		+ '>' + '<td name="banji">'
                                		+ obj[i].studentClass +'</td><td name="stuId">' 
                                		+ obj[i].id + '</td><td name="stuName">'
                                		+ obj[i].name + '</td><td name="score">' 
                                		+ '<input type="text" class="scoreTextInput onlyNum" placeholder="只能输入数字" />' 
                                		+ '</td><td><input class="subScoreBtn" type="button" value="提交" />'
                                		+ '</td>' + '</tr>');
                            }
                        }
                    })*/
                    $rootScope.paging("ul.pagination",{
                        numUrl:"achievement_queryStudentCourseNumber.action",
                        dataUrl:"achievement_queryStudentCourse.action",
                        data:{
                            achievementId: data.specialtyCourseId
                        }
                    }, loadEntryScoreTbody);
                }
                function loadEntryScoreTbody(obj) {
                    $(".entryScoreTbody").html(""); //清空
                    for (var i = 0; i < obj.length; i++) {
                        $(".entryScoreTbody").append('<tr data-id=' + $("p.titleP").attr("data-id")
                                + '>' + '<td name="banji">'
                                + obj[i].studentclass.classNumber +'</td><td name="stuId">' 
                                + obj[i].studentNumber + '</td><td name="stuName">'
                                + obj[i].name + '</td><td name="score">' 
                                + '<input type="text" class="scoreTextInput onlyNum" placeholder="只能输入数字" />' 
                                + '</td><td><input class="subScoreBtn" type="button" value="提交" />'
                                + '</td>' + '</tr>');
                    }
                }
            }
        })
        .state('entryElectivesScore',{
            url: '/entryElectivesScore',
            templateUrl: 'jsp/tpls/teacherMS/entryElectivesScore.html',
            controller: function($rootScope) {
                //预加载---主页面
                loadMainP();

                //事件---entryScoreBtn
                $(".studyPlanTbody").on("click", ".entryScoreBtn", function() {
                    if ($(".modInfor").css("display") == "none") {
                        $("#pTable").css("visibility", "hidden");
                        $(".modInfor").show(300);
                    }
                    //预加载---根据缴费项id 加载 详细缴费标准
                    var data = {
                            "courseId": $(this).parent().parent().attr("data-id"),
                            "planCourse": $(this).parent().siblings("td[name='course']").text()
                        }
                        //console.log(data);
                    loadModInfor(data);
                })

                //事件---returnBtn 返回到主页面
                $("#returnBtn").on("click", function() {
                    if ($(".modInfor").css("display") == "block") {
                        $(".modInfor").hide(300);
                        $("#pTable").css("visibility", "visible");
                    }
                })

                //事件---只能输入数字
                $(".entryScoreTbody").on("keyup", ".onlyNum", function() {
                    $(this).val($(this).val().replace(/[^0-9]/g, ''));
                })
                $(".entryScoreTbody").on("afterpaste", ".onlyNum", function() {
                        $(this).val($(this).val().replace(/[^0-9]/g, ''));
                    })
                    //事件--- 0<=分数<=100
                $(".entryScoreTbody").on("change", ".scoreTextInput", function() {
                        if ($(this).val() <= 100 && $(this).val() >= 0) {

                        } else {
                            $(this).val("");
                            alert("0<=分数<=100");
                        }
                    })
                    //事件---提交分数
                $(".entryScoreTbody").on("click", ".subScoreBtn", function() {

                    if ($(this).parent().siblings("td[name='score']").find("input[type='text']").val()) {
                        //can submit
                        var data = {
                            "specialtyCourseId": $(this).parent().parent().attr("data-id"),
                            "course": $("span#spanCourse").text(),
                            "stuId": $(this).parent().siblings("td[name='stuId']").text(),
                            "score": $(this).parent().siblings("td[name='score']").find("input[type='text']").val()
                        }
                        var $tr = $(this).parent().parent();
                        $.ajax({
                            url:"achievement_addAchievement.action",
                            type:"POST",
                            data:{
                                score: data.score,
                                courseName: data.course,
                                studentNumber: data.stuId
                            },
                            success:function(data){
                                alert("提交成功！！！");
                                var courseId=$("p.titleP").attr("data-id");
                                $rootScope.paging("ul.pagination",{
                                    numUrl:"achievement_queryStudentElectivesNumber.action",
                                    dataUrl:"achievement_queryStudentElectives.action",
                                    data:{
                                        achievementId: courseId
                                    }
                                }, loadEntryScoreTbody);
                            }
                        })
                        //if-success
                    }

                })

                //方法---预加载---主页面
                function loadMainP(parentElement) {
                    
                    $.ajax({
                        url:"teach_queryElectives.action",
                        type:"GET",
                        success:function(data){
                            //if-success
                            var obj = JSON.parse(data);
                            $(".studyPlanTbody").html();
                            for (var i = 0; i < obj.length; i++) {
                                $(".studyPlanTbody").append('<tr data-id=' + obj[i].course.courseId +'>' 
                                        + '<td name="course">'
                                        + obj[i].course.courseName + '</td><td>'
                                        + '<input class="entryScoreBtn" type="button" value="录入成绩" />' 
                                        + '</td></tr>');
                            }
                            
                        }
                    })
                    /*$rootScope.paging("ul.pagination",{
                        numUrl:"teach_queryElectivesNumber.action",
                        dataUrl:"teach_queryElectives.action",
                        data:{
                            achievementId: data.courseId
                        }
                    }, loadEntryScoreTbody);*/
                }
                //方法---预加载---modInfor
                function loadModInfor(data) {
                    //load-title
                    $("span#spanCourse").html(data.planCourse);
                    $("p.titleP").attr("data-id", data.courseId);
                    //load-table
                    /*$.ajax({
                        url:"achievement_queryStudentElectives.action",
                        type:"POST",
                        data:{
                            achievementId: data.courseId
                        },
                        success:function(data){
                            //if-success
                            var obj = JSON.parse(data);
                            
                        }
                    })*/
                    $rootScope.paging("ul.pagination",{
                        numUrl:"achievement_queryStudentElectivesNumber.action",
                        dataUrl:"achievement_queryStudentElectives.action",
                        data:{
                            achievementId: data.courseId
                        }
                    }, loadEntryScoreTbody);
                }
                function loadEntryScoreTbody(obj) {
                    $(".entryScoreTbody").html(""); //清空
                    for (var i = 0; i < obj.length; i++) {
                        $(".entryScoreTbody").append('<tr data-id=' + $("p.titleP").attr("data-id")
                                + '>' + '<td name="banji">'
                                + obj[i].student.studentclass.classNumber +'</td><td name="stuId">' 
                                + obj[i].student.studentNumber + '</td><td name="stuName">'
                                + obj[i].student.name + '</td><td name="score">' 
                                + '<input type="text" class="scoreTextInput onlyNum" placeholder="只能输入数字" />' 
                                + '</td><td><input class="subScoreBtn" type="button" value="提交" />'
                                + '</td>' + '</tr>');
                    }
                }
            }
        })
        .state('seeCompulsoryScore', {
            url: '/seeCompulsoryScore',
            templateUrl: 'jsp/tpls/teacherMS/seeCompulsoryScore.html',
            controller: function($rootScope) {
                //预加载---主页面
                loadMainP();

                //事件---seeScoreBtn
                $(".studyPlanTbody").on("click", ".seeScoreBtn", function() {
                    if ($(".modInfor").css("display") == "none") {
                        $("#pTable").css("visibility", "hidden");
                        $(".modInfor").show(300);
                    }
                    //预加载---根据缴费项id 加载 详细缴费标准
                    var data = {
                            "planXueyuan": $(this).parent().siblings("td[name='xueyuan']").text(),
                            "planZhuanye": $(this).parent().siblings("td[name='zhuanye']").text(),
                            "planCourse": $(this).parent().siblings("td[name='course']").text(),
                            "planXueqi": $(this).parent().siblings("td[name='xueqi']").text()
                        }
                        //console.log(data);
                    loadModInfor(data);
                })

                //事件---returnBtn 返回到主页面
                $("#returnBtn").on("click", function() {
                    if ($(".modInfor").css("display") == "block") {
                        $(".modInfor").hide(300);
                        $("#pTable").css("visibility", "visible");
                    }
                })

                //事件---只能输入数字
                $(".seeScoreTbody").on("keyup", ".onlyNum", function() {
                    $(this).val($(this).val().replace(/[^0-9]/g, ''));
                })
                $(".seeScoreTbody").on("afterpaste", ".onlyNum", function() {
                        $(this).val($(this).val().replace(/[^0-9]/g, ''));
                    })
                    //事件--- 0<=分数<=100
                $(".seeScoreTbody").on("change", ".scoreTextInput", function() {
                        if ($(this).val() <= 100 && $(this).val() >= 0) {

                        } else {
                            $(this).val("");
                            alert("0<=分数<=100");
                        }
                    })
                    //事件---modScoreBtn
                $(".seeScoreTbody").on("click", ".modScoreBtn", function() {
                    if ($(this).val() == "修改") {
                        var tdScore = $(this).parent().siblings("td[name='score']");
                        tdScore.html('<input type="text" class="scoreTextInput onlyNum" placeholder="只能输入数字" value="' + tdScore.text() + '" />')
                        $(this).val("确定");
                    } else if ($(this).val() == "确定") {
                        if ($(this).parent().siblings("td[name='score']").find("input[type='text']").val()) {
                            //can submit
                            var data = {
                                "xueyuan": $("span#spanXueyuan").text(),
                                "zhuanye": $("span#spanZhuanye").text(),
                                "banji": $(this).parent().siblings("td[name='banji']").text(),
                                "xueqi": $("span#spanXueqi").text(),
                                "course": $("span#spanCourse").text(),
                                "stuId": $(this).parent().siblings("td[name='stuId']").text(),
                                "score": $(this).parent().siblings("td[name='score']").find("input[type='text']").val()
                            }
                            $.ajax({
                                url:"achievement_updateAchievement.action",
                                type:"POST",
                                data:{
                                	achievementId: $(this).parent().parent().attr("data-id"),
                                	score: data.score
                                },
                                success:function(data){
                                	alert("修改成功！！！");
                                }
                            })
                            //if-success
                            $(this).parent().siblings("td[name='score']").html(data.score);
                            $(this).val("修改");
                        }
                    }


                })


                //方法---预加载---主页面
                function loadMainP() {
                    $.ajax({
                        url:"specialtycourse_queryTeacherCourse.action",
                        type:"GET",
                        success:function(data){
                        	//if-success
                        	var obj = JSON.parse(data);
                            $(".studyPlanTbody").html();
                            for (var i = 0; i < obj.length; i++) {
                                $(".studyPlanTbody").append('<tr data-id=' + obj[i].specialtyCourseId +'>' 
                                        + '<td name="xueyuan">'
                                        + obj[i].specialty.college.collegeName + '</td>' + '<td name="zhuanye">'
                                        + obj[i].specialty.specialtyName + '</td>' + '<td name="xueqi">'
                                        + obj[i].term + '</td>' + '<td name="course">'
                                        + obj[i].teach.course.courseName + '</td><td>'
                                        + '<input class="seeScoreBtn" type="button" value="查看成绩" />' 
                                        + '</td></tr>');
                            }
                        }
                    })
                }
                //方法---预加载---modInfor
                function loadModInfor(data) {
                    //load-title
                    $("span#spanXueyuan").html(data.planXueyuan);
                    $("span#spanZhuanye").html(data.planZhuanye);
                    $("span#spanXueqi").html(data.planXueqi);
                    $("span#spanCourse").html(data.planCourse);

                    //load-table
                    /*$.ajax({
                        url:"achievement_queryAchievement.action",
                        type:"POST",
                        data:{
                        	term: data.planXueqi,
                        	courseName: data.planCourse
                        },
                        success:function(data){
                        	//if-success
                        	var obj = JSON.parse(data);
                            $(".seeScoreTbody").html(""); //清空
                            for (var i = 0; i < obj.length; i++) {
                                $(".seeScoreTbody").append('<tr data-id=' + obj[i].collegeId
                                		+ '><td name="banji">'
                                		+ obj[i].studentClass + '</td><td name="stuId">'
                                		+ obj[i].id + '</td><td name="stuName">'
                                		+ obj[i].name + '</td><td name="score">'
                                		+ obj[i].idcard + '</td><td>' 
                                		+ '<input class="modScoreBtn" type="button" value="修改" />' 
                                		+ '</td></tr>');
                            }
                        }
                    })*/
                    $rootScope.paging("ul#pagination2",{
                        numUrl:"achievement_queryAchievementNumber.action",
                        dataUrl:"achievement_queryAchievement.action",
                        data:{
                            term: data.planXueqi,
                            courseName: data.planCourse,
                            specialty: data.planZhuanye
                        }
                    }, loadSeeScoreTbody);
                }

                function loadSeeScoreTbody(obj) {
                    $(".seeScoreTbody").html(""); //清空
                    for (var i = 0; i < obj.length; i++) {
                        $(".seeScoreTbody").append('<tr data-id=' + obj[i].achievementId
                                + '><td name="banji">'
                                + obj[i].student.studentclass.classNumber + '</td><td name="stuId">'
                                + obj[i].student.studentNumber + '</td><td name="stuName">'
                                + obj[i].student.name + '</td><td name="score">'
                                + obj[i].score + '</td><td>' 
                                + '<input class="modScoreBtn" type="button" value="修改" />' 
                                + '</td></tr>');
                    }
                }
            }
        })
        .state('seeElectivesScore',{
            url: '/seeElectivesScore',
            templateUrl: 'jsp/tpls/teacherMS/seeElectivesScore.html',
            controller: function($rootScope) {
                //预加载---主页面
                loadMainP();

                //事件---seeScoreBtn
                $(".studyPlanTbody").on("click", ".seeScoreBtn", function() {
                    if ($(".modInfor").css("display") == "none") {
                        $("#pTable").css("visibility", "hidden");
                        $(".modInfor").show(300);
                    }
                    //预加载---根据缴费项id 加载 详细缴费标准
                    var data = {
                            "planCourse": $(this).parent().siblings("td[name='course']").text(),
                            "planXueqi": $(this).parent().siblings("td[name='xueqi']").text()
                        }
                        //console.log(data);
                    loadModInfor(data);
                })

                //事件---returnBtn 返回到主页面
                $("#returnBtn").on("click", function() {
                    if ($(".modInfor").css("display") == "block") {
                        $(".modInfor").hide(300);
                        $("#pTable").css("visibility", "visible");
                    }
                })

                //事件---只能输入数字
                $(".seeScoreTbody").on("keyup", ".onlyNum", function() {
                    $(this).val($(this).val().replace(/[^0-9]/g, ''));
                })
                $(".seeScoreTbody").on("afterpaste", ".onlyNum", function() {
                        $(this).val($(this).val().replace(/[^0-9]/g, ''));
                    })
                    //事件--- 0<=分数<=100
                $(".seeScoreTbody").on("change", ".scoreTextInput", function() {
                        if ($(this).val() <= 100 && $(this).val() >= 0) {

                        } else {
                            $(this).val("");
                            alert("0<=分数<=100");
                        }
                    })
                    //事件---modScoreBtn
                $(".seeScoreTbody").on("click", ".modScoreBtn", function() {
                    if ($(this).val() == "修改") {
                        var tdScore = $(this).parent().siblings("td[name='score']");
                        tdScore.html('<input type="text" class="scoreTextInput onlyNum" placeholder="只能输入数字" value="' + tdScore.text() + '" />')
                        $(this).val("确定");
                    } else if ($(this).val() == "确定") {
                        if ($(this).parent().siblings("td[name='score']").find("input[type='text']").val()) {
                            //can submit
                            var data = {
                                "banji": $(this).parent().siblings("td[name='banji']").text(),
                                "course": $("span#spanCourse").text(),
                                "stuId": $(this).parent().siblings("td[name='stuId']").text(),
                                "score": $(this).parent().siblings("td[name='score']").find("input[type='text']").val()
                            }
                            $.ajax({
                                url:"achievement_updateAchievement.action",
                                type:"POST",
                                data:{
                                    achievementId: $(this).parent().parent().attr("data-id"),
                                    score: data.score
                                },
                                success:function(data){
                                    alert("修改成功！！！");
                                }
                            })
                            //if-success
                            $(this).parent().siblings("td[name='score']").html(data.score);
                            $(this).val("修改");
                        }
                    }


                })


                //方法---预加载---主页面
                function loadMainP() {
                    $.ajax({
                        url:"teach_queryElectives.action",
                        type:"GET",
                        success:function(data){
                            //if-success
                            var obj = JSON.parse(data);
                            $(".studyPlanTbody").html();
                            for (var i = 0; i < obj.length; i++) {
                                $(".studyPlanTbody").append('<tr data-id=' + obj[i].course.courseId +'>' 
                                        + '<td name="course">'
                                        + obj[i].course.courseName + '</td><td>'
                                        + '<input class="seeScoreBtn" type="button" value="查看成绩" />' 
                                        + '</td></tr>');
                            }
                        }
                    })
                }

                //方法---预加载---modInfor
                function loadModInfor(data) {
                    //load-title
                    $("span#spanCourse").html(data.planCourse);

                    //load-table
                    /*$.ajax({
                        url:"achievement_queryAchievement.action",
                        type:"POST",
                        data:{
                        	courseName: data.planCourse
                        },
                        success:function(data){
                        	//if-success
                        	var obj = JSON.parse(data);
                            
                        }
                    })*/
                    $rootScope.paging("ul#pagination2",{
                        numUrl:"achievement_queryAchievementNumber.action",
                        dataUrl:"achievement_queryAchievement.action",
                        data:{
                        	courseName: data.planCourse
                        }
                    }, loadSeeScoreTbody);
                }
                function loadSeeScoreTbody(obj) {
                    $(".seeScoreTbody").html(""); //清空
                    for (var i = 0; i < obj.length; i++) {
                        $(".seeScoreTbody").append('<tr data-id=' + obj[i].achievementId
                                + '><td name="banji">'
                                + obj[i].student.studentclass.classNumber + '</td><td name="stuId">'
                                + obj[i].student.studentNumber + '</td><td name="stuName">'
                                + obj[i].student.name + '</td><td name="score">'
                                + obj[i].score + '</td><td>' 
                                + '<input class="modScoreBtn" type="button" value="修改" />' 
                                + '</td></tr>');
                    }
                }
            }
        });

});
routerApp.run(function($rootScope) {
    //分页系列操作
    $rootScope.paging = function(parentElement, jsonData, loadTbody) {
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
            url: jsonData.numUrl,
            type: "POST",
            data: jsonData.data,
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
            jsonData.data["num"] = num;
            $.ajax({
                url: jsonData.dataUrl,
                type: "POST",
                data: jsonData.data,
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
