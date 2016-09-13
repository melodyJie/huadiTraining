var routerApp = angular.module('routerApp', ['ui.router']);
routerApp.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/addCourse');
    $stateProvider
        .state('addCourse', {
            url: '/addCourse',
            templateUrl: 'tpls/trainingPS/admin-addCourse.html',
            controller: function($rootScope) {
                //0-10Input标签限制事件
                $(".f0to10Input").on("change", function() {
                    if (!$(this).val().match("^[1-9]{1}$|^10$")) {
                        $(this).val("");
                        alert('输入格式不正确!');
                    }
                })
                $("#courseName").on("blur", function(){
                	if ($("#courseName").val()) {
                		$.ajax({
                        	url: "../course_judgeCourse.action",
                        	type: "POST",
                        	data: {
                        		courseName: $("#courseName").val(),
                        	},
                        	success: function(data) {
                        		if (data != ""){
                        			alert(data);
                        			$("#courseName").val("");
                        			$("span[name='courseNameError']").text("");
                        		}
                        	}
                		})
                	}
                })
                $("#submit").on("click", function() {
                    $("span[name='courseNameError']").text("");
                    $("span[name='courseCreditError']").text("");
                    var ok=true;
                    if (!$("#courseName").val()) {
                        $("span[name='courseNameError']").text("不能为空!!!");
                        ok=false;
                    } else {
                    	$("span[name='courseNameError']").text("");
                    }
                    if (!$("#credit").val()) {
                        $("span[name='courseCreditError']").text("不能为空!!!");
                        ok=false;
                    } else {
                    	$("span[name='courseCreditError']").text("");
                    }
                    if(ok){
                        $.ajax({
                        	url: "../course_addCourse.action",
                        	type: "POST",
                        	data: {
                        		courseName: $("#courseName").val(),
                        		courseType: $("#type option:selected").text(),
                        		credit: $("#credit").val()
                        	},
                        	success: function(data) {
                        		alert("添加成功！！！");
                        		window.location.reload(true);
                        	}
                        })
                    }
                })
            }
        })
        .state('doCourse', {
            url: '/doCourse',
            templateUrl: 'tpls/trainingPS/admin-doCourse.html',
            controller: function($rootScope) {
                //加载course表格
                function onLoading(){
                    $rootScope.paging("ul.pagination",{
                        numUrl:"../course_queryAllCoursesNumber.action",
                        dataUrl:"../course_queryAllCourses.action",
                        data:{
                        }
                    }, loadDoCourseTable);
                }
                onLoading();

                //事件--删除按钮事件
                $(".doCourseTbody").on("click", ".courseDelBtn", function() {
                    $.ajax({
                        url: "../course_deleteCourse.action",
                        type: "POST",
                        data: {
                        	courseId: $(this).parent().siblings("td[name='courseId']").text()
                        },
                        success: function(data) {
                        	alert("删除成功！！！");
                        	onLoading();
                        },
                        error:function(){
                            alert("删除失败!!!");
                        }
                    })
                })
                //事件--搜索按钮事件
                $("#toSearchingBtn").on("click",function(){
                    var searchData=$("#searchInput").val();
                    //TODO--last
                    $rootScope.paging("ul.pagination",{
                        numUrl:"../course_queryAllCoursesNumber.action",
                        dataUrl:"../course_queryAllCourses.action",
                        data:{
                            input:searchData
                        }
                    }, loadDoCourseTable);
                })

                function loadDoCourseTable(obj) {
                    $(".doCourseTbody").html("");
                    for (var i = 0; i < obj.length; i++) {
                        var tr = document.createElement("tr");
                        //-课程id  列
                        var td_courseId = document.createElement("td");
                        $(td_courseId).attr("name", "courseId");
                        //TODO--设定<td name="courseId"> 标签的 数值
                        $(td_courseId).html(obj[i].courseId);
                        tr.appendChild(td_courseId);

                        //-课程名称 列
                        var td_courseName = document.createElement("td");
                        $(td_courseName).attr("name", "courseName");
                        //TODO--设定<td name="courseName"> 标签的 数值
                        $(td_courseName).html(obj[i].courseName);
                        tr.appendChild(td_courseName);

                        //-课程类型 列
                        var td_courseType = document.createElement("td");
                        $(td_courseType).attr("name", "courseType");
                        //TODO--
                        $(td_courseType).html(obj[i].courseType);
                        /*var td_courseType_select = document.createElement("select");

                        var td_courseType_select_option1 = document.createElement("option");
                        //TODO--设定-带查看-选项的value值
                        $(td_courseType_select_option1).attr("value", "");
                        $(td_courseType_select_option1).html("必修");
                        td_courseType_select.appendChild(td_courseType_select_option1);

                        var td_courseType_select_option2 = document.createElement("option");
                        //TODO--设定-带查看-选项的value值
                        $(td_courseType_select_option2).attr("value", "");
                        $(td_courseType_select_option2).html("选修");
                        td_courseType_select.appendChild(td_courseType_select_option2);

                        td_courseType.appendChild(td_courseType_select);*/
                        tr.appendChild(td_courseType);

                        //-课程学分 列
                        var td_courseCredit = document.createElement("td");
                        $(td_courseCredit).attr("name", "courseCredit");
                        //TODO--设定<td name="courseCredit"> 标签的 数值
                        $(td_courseCredit).html(obj[i].credit);
                        tr.appendChild(td_courseCredit);

                        //-操作 列
                        var td_caozuo = document.createElement("td");

                        var del_input = document.createElement("input");
                        $(del_input).attr("type", "button");
                        $(del_input).attr("class", "courseDelBtn");
                        $(del_input).attr("value", "删除");
                        td_caozuo.appendChild(del_input);
                        tr.appendChild(td_caozuo);

                        $(".doCourseTbody").append(tr);
                    }
                }
            }
        })
        .state('doStudyPlan', {
            url: '/doStudyPlan',
            templateUrl: 'tpls/trainingPS/admin-doStudyPlan.html',
            controller: function($rootScope, $scope, $http) {
            	function createTable(data){
            		var obj = JSON.parse(data);
        			$("tbody").html("");
                	//if-success
                    var data_length = obj.length; //数据长度
                    for (var i = 0; i < data_length; i++) {
                        var tr = document.createElement("tr");
                        //-课程id  列
                        var td_courseId = document.createElement("td");
                        $(td_courseId).attr("name", "courseId");
                        $(td_courseId).attr("data-id", obj[i].specialtyCourseId);
                        $(td_courseId).html(obj[i].teach.course.courseId);
                        tr.appendChild(td_courseId);

                        //-课程名称 列
                        var td_courseName = document.createElement("td");
                        $(td_courseName).attr("name", "courseName");
                        $(td_courseName).html(obj[i].teach.course.courseName);
                        tr.appendChild(td_courseName);

                        //-课程类型 列
                        var td_courseType = document.createElement("td");
                        $(td_courseType).attr("name", "courseType");
                        $(td_courseType).html(obj[i].teach.course.courseType);
                        tr.appendChild(td_courseType);

                        //-课程学分 列
                        var td_courseCredit = document.createElement("td");
                        $(td_courseCredit).attr("name", "courseCredit");
                        //TODO--设定<td name="courseCredit"> 标签的 数值
                        $(td_courseCredit).html(obj[i].teach.course.credit);
                        tr.appendChild(td_courseCredit);
                        
                        //-教师 列
                        var td_courseTeacher = document.createElement("td");
                        $(td_courseTeacher).attr("name", "courseTeacher");
                        $(td_courseTeacher).html(obj[i].teach.teacher.teacherName);
                        tr.appendChild(td_courseTeacher);

                        //-操作 列
                        var td_caozuo = document.createElement("td");

                        var del_input = document.createElement("input");
                        $(del_input).attr("type", "button");
                        $(del_input).attr("class", "delCourseBtn");
                        $(del_input).attr("value", "删除");
                        td_caozuo.appendChild(del_input);
                        tr.appendChild(td_caozuo);

                        $("tbody").append(tr);
                    }
                    loadCourse();
            	}
                //预加载---学院
                $.ajax({
                    url: "../college_queryCollege.action",
                    type: "GET",
                    success: function(data) {
                        $("#xueyuan1").html("<option value=''>请选择</option>");
                        var obj = JSON.parse(data);
                        for (var i in obj) {
                            var option = document.createElement("option");
                            $(option).val(obj[i].collegeId);
                            $(option).text(obj[i].collegeName);
                            $(option).appendTo($("#xueyuan1"));
                        }
                    }
                });

                //预加载---学期 (学期需要预加载么?)


                //事件---选完学院 加载专业下拉菜单
                $("#xueyuan1").on("change", function() {
                    //console.log($(".xueyuan").val());
                    var data = $("#xueyuan1").val();
                    $("#zhuanye1").html("<option value=''>请选择</option>");
                    $("#course").html("<option value=''>请选择</option>");
                    $("#teacher").html("<option value=''>请选择</option>");
                    if (data) {
                        $.ajax({
                            url: "../specialty_querySpecialty.action",
                            type: "POST", //需改为POST,传数据
                            data: {
                                specialtyId: data
                            },
                            success: function(data) {
                                var obj = JSON.parse(data);
                                for (var i in obj) {
                                    var option = document.createElement("option");
                                    $(option).val(obj[i].specialtyId);
                                    $(option).text(obj[i].specialtyName);
                                    $(option).appendTo($("#zhuanye1"));
                                }
                            }
                        })
                    }
                });
                //专业选择改变
                $("#zhuanye1").on("change", function() {
                    $("#course").html("<option value=''>请选择</option>");
                    $("#teacher").html("<option value=''>请选择</option>");
                });
                //事件---select搜索 提交按钮事件
                $("#searchBtn").on("click", function() {
                	$.ajax({
                		url:"../specialtycourse_querySpecialtycourse.action",
                		type:"POST",
                		data: {
                			specialtyCourseId: $("#zhuanye1").val(),
                			term: $("#term").val()
                		},
                		success:function(data){
                			createTable(data);
                		}
                	})
                	loadCourse();   
                })
                
                function loadCourse(){
                	//预加载---可以添加的课程(避免重复添加)--学院专业学期选定后自动加载
                	$.ajax({
                		url:"../specialtycourse_queryChooseCourse.action",
                		type:"POST",
                		data: {
                			specialtyCourseId: $("#zhuanye1").val(),
                			term: $("#term").val()
                		},
                		success:function(data){
                			var obj = JSON.parse(data);
                			$("#teacher").html("<option value=''>请选择</option>");
                			$("#course").html("<option value=''>请选择</option>");
                            for (var i in obj) {
                                var option = document.createElement("option");
                                $(option).val(obj[i].courseId);
                                $(option).text(obj[i].courseName);
                                $(option).appendTo($("#course"));
                            }
                		}
                	})
                }
                
                $("#course").on("change", function(){
                	var data = $("#course").val();
                    $("#teacher").html("<option value=''>请选择</option>");
                    if (data) {
                        $.ajax({
                            url: "../teach_queryTeacher.action",
                            type: "POST", //需改为POST,传数据
                            data: {
                                teachId: data,
                            },
                            success: function(data) {
                                var obj = JSON.parse(data);
                                for (var i in obj) {
                                    var option = document.createElement("option");
                                    $(option).val(obj[i].teacher.teacherNumber);
                                    $(option).text(obj[i].teacher.teacherName);
                                    $(option).appendTo($("#teacher"));
                                }
                            }
                        })
                    }
                })
                    //事件---添加课程
                $("#toAddCourseBtn").on("click", function() {
                	var data1 = $("#zhuanye1").val();
                	var data2 = $("#term").val();
                	var data3 = $("#course").val();
                	var data4 = $("#teacher").val();
                	if (data1 && data2 && data3 && data4){
                		$.ajax({
                            url: "../specialtycourse_addSpecialtycourse.action",
                            type: "POST", //需改为POST,传数据
                            data: {
                                term: data2,
                                specialtyId: data1,
                                courseId: data3,
                                teacherId: data4
                            },
                            success: function(data) {
                                alert("添加成功！！！");
                                $.ajax({
                            		url:"../specialtycourse_querySpecialtycourse.action",
                            		type:"POST",
                            		data: {
                            			specialtyCourseId: $("#zhuanye1").val(),
                            			term: $("#term").val()
                            		},
                            		success:function(data){
                            			createTable(data);
                            		}
                            	})
                            }
                        })
                	}
                })
                    //事件---删除课程
                $("tbody").on("click", ".delCourseBtn", function() {
                    $.ajax({
                        url: "../specialtycourse_deleteSpecialtycourse.action",
                        type: "POST",
                        data: {
                        	specialtyCourseId: $(this).parent().siblings("td[name='courseId']").attr("data-id")
                        },
                        success: function(data) {
                        	alert("删除成功！！！");
                        	$.ajax({
                        		url:"../specialtycourse_querySpecialtycourse.action",
                        		type:"POST",
                        		data: {
                        			specialtyCourseId: $("#zhuanye1").val(),
                        			term: $("#term").val()
                        		},
                        		success:function(data){
                        			createTable(data);
                        		}
                        	})
                        },
                        error:function(){
                            alert("删除失败!!!");
                        }
                    })
                })

            }
        })
        .state('addTeacher', {
            url: '/addTeacher',
            templateUrl: 'tpls/trainingPS/admin-addTeacher.html',
            controller: function($rootScope) {
            	$("#identityId").on("blur", function(){
            		if ($("#identityId").val()){
            			$.ajax({
                    		url:"../teacher_judgeTeacher.action",
                    		type:"POST",
                    		data: {
                    			identityId: $("#identityId").val()
                    		},
                    		success:function(data){
                    			if (data != ""){
                    				alert(data);
                    				$("#identityId").val("");
                    				$("span[name='identityIdError']").text("");
                    			}
                    		}
                    	})
            		}
            	})
                $("#addTeacherBtn").on('click', function() {
                    var ok=true;
                    if(!$("#teacherName").val()){
                        $("span[name='teacherNameError']").text("不能为空!!!");
                        ok=false;
                    } else {
                    	$("span[name='teacherNameError']").text("");
                    }
                    if(!$("#identityId").val()){
                        $("span[name='identityIdError']").text("不能为空!!!");
                        ok=false;
                    } else {
                    	$("span[name='identityIdError']").text("");
                    }
                    if($("#identityId").val() && $("#identityId").val().length < 6) {
                    	$("span[name='identityIdError']").text("身份证号错误!!!");
                        ok=false;
                    }
                    if(ok){
                    	$.ajax({
                    		url:"../teacher_addTeacher.action",
                    		type:"POST",
                    		data: {
                    			teacherName: $("#teacherName").val(),
                    			identityId: $("#identityId").val()
                    		},
                    		success:function(data){
                    			alert("添加成功！！！");
                    			window.location.reload(true);
                    		},
                            error:function(){
                                alert("添加失败!!!");
                            }
                    	})
                    }
                })
            }
        })
        .state('doTeaching', {
            url: '/doTeaching',
            templateUrl: 'tpls/trainingPS/admin-doTeaching.html',
            controller: function($rootScope) {
                //预加载---教师授课;
                function onLoading(){
                    $rootScope.paging("ul.pagination",{
                        numUrl:"../teach_queryTeacherCourseNumber.action",
                        dataUrl:"../teach_queryTeacherCourse.action",
                        data:{
                        }
                    }, loadDoTeachingTbody);
                }
                onLoading();
                function loadDoTeachingTbody(obj) {
                    $(".doTeachingTbody").html("");
                    for (var i = 0; i < obj.length; i++){
                    	var $tr = $("<tr></tr>");
                    	var course = "";
                    	for (var j = 0; j < obj[i].courses.length; j++){
                    		course += obj[i].courses[j].courseName + " ";
                    	}
                    	$tr.append("<td name='teacherId' data-id=" + 
                        		obj[i].teacher.teacherId + ">" + obj[i].teacher.teacherNumber + "</td>" +
                                "<td name='teacherName'>" + obj[i].teacher.teacherName + "</td>" +
                                "<td name='teacherCourse' data-id=" + obj[i].available + ">" 
                                + course.trim() + "</td>" +
                                "<td name='teacherDo'><input class='modTeaching' " +
                                "type='button' value='修改教授课程' /></td>");
                    	$(".doTeachingTbody").append($tr);
                    }
                }
                /*//事件--搜索按钮事件
                $("#toSearchingBtn").on("click",function(){
                    var searchData=$("#searchInput").val();
                    //TODO--
                    
                })*/
                /*事件--根据老师设定 modInfor 页面*/
                $(".doTeachingTbody").on("click", ".modTeaching", function() {
                    if ($(".modInfor").css("display") == "none") {
                        $("#btnBox1").css("display","none");
                        $(".viewArea1").css("visibility","hidden");
                        $(".modInfor").show(300);
                    }
                    var teacherName = $(this).parent().siblings("td[name='teacherName']").text();
                    $("h3").text(teacherName);
                    var teacherNumber = $(this).parent().siblings("td[name='teacherId']").text();
                    $("h3").attr("data-id", teacherNumber);
                    //预加---隐藏的modInfor页面
                    var courses = $(this).parent().siblings("td[name='teacherCourse']").text();
                    var available = $(this).parent().siblings("td[name='teacherCourse']").attr("data-id");
                    loadModInfor(courses, available);
                })

                /*modInfor 事件*/
                $("li[name='toCompulsory']").on("click", function() {
                    if ($("div[name='Compulsory']").css("display") == "none") {
                        $("div[name='Elective']").css("display","none");
                        $("div[name='Compulsory']").css("display","block");

                        $(this).addClass("active");
                        $("li[name='toElective").removeClass("active");

                    }
                })
                $("li[name='toElective").on("click", function() {
                    if ($("div[name='Elective']").css("display") == "none") {
                        $("div[name='Compulsory']").css("display","none");
                        $("div[name='Elective']").css("display","block");

                        $(this).addClass("active");
                        $("li[name='toCompulsory']").removeClass("active");
                    }
                })

                $("#course_list").on("change","input[name='course']", function() {
                    //console.log($(this).parent().text()); //课程名称
                    //console.log($(this).val()) //checkbox 的value;
                    //console.log($(this).prop("checked"));
                    if ($(this).prop("checked")) { //该checkbox 选中
                        $(".courseNameP").append('<span value="' + $(this).val() + '"">' + $(this).parent().text() + '<span>');
                    } else { //该checkbox 未选中
                        $(".courseNameP span[value='" + $(this).val() + "']").remove();
                    }
                })

                
                //返回按钮
                $("#returnBtn").on("click", function() {
                    if ($(".modInfor").css("display") != "none") {
                        $("#btnBox1").css("display","block");
                        $(".modInfor").hide(300);
                        $(".viewArea1").css("visibility","visible");
                    }
                    onLoading();
                })
                $("#subTeachingBtn").on("click", function() {
                	var courseId = "";
                	$(".courseNameP span").each(function(){
                		if ($(this).attr("value")){
                			courseId = courseId + $(this).attr("value") + " ";
                		}
                	})
                	courseId = courseId.trim();
                    $.ajax({
                		url:"../teach_updateTeacherCourse.action",
                		type:"POST",
                		data:{
                			teacherNumber: $("h3").attr("data-id"),
                			courseId: courseId
                		},
                		success:function(data){
                			alert("修改成功！！！");
                            if ($(".modInfor").css("display") != "none") {
                                $(".modInfor").hide(300);
                                $(".viewArea1").css("visibility","visible");
                            }
                            onLoading();
                		},
                        error:function(){
                            alert("修改失败!!!");
                        }
                	})
                })

                function loadModInfor(courses, available) {
            		resetModInfor(courses, available);
                    loadCompulsoryCourse(courses, available);
                    loadElectiveCourse(courses, available);
                }

                function loadCompulsoryCourse(courses, available) {
                    $(".courseDiv[name='Compulsory']").html(""); //清空
                    $.ajax({
                        url:"../course_queryCourseType.action",
                        type:"POST",
                        data:{
                        	courseType: "必修课"
                        },
                        success:function(data){
                        	//if-success
                        	var obj = JSON.parse(data);
                        	var course = courses.split(" ");
                            for (var i = 0; i < obj.length; i++) {
                                var ele = $("<label><input name='course' " +
                                		"type='checkbox' value=" + obj[i].courseId + " />" 
                                		+ obj[i].courseName + "</label>");
                                var j = $.inArray(obj[i].courseName, course);
                                if (j != -1){
                                	$(ele).find("input").attr("checked", true);
                                	if (available.substr(j, 1) == "1"){
                                		$(ele).find("input").attr("disabled", "disabled");
                                	}
                                	$(".courseNameP").append('<span value="' + obj[i].courseId + 
                                			'"">' + obj[i].courseName + '<span>');
                                }
                                $(".courseDiv[name='Compulsory']").append(ele);
                            }
                        }
                    })   
                }

                function loadElectiveCourse(courses, available) {
                    $(".courseDiv[name='Elective']").html(""); //清空
                    $.ajax({
                        url:"../course_queryCourseType.action",
                        type:"POST",
                        data:{
                        	courseType: "选修课"
                        },
                        success:function(data){
                        	//if-success
                        	var obj = JSON.parse(data);
                        	var course = courses.split(" ");
                            for (var i = 0; i < obj.length; i++) {
                            	var ele = $("<label><input class='course' name='course' " +
                                		"type='checkbox' value=" + obj[i].courseId + " />" 
                                		+ obj[i].courseName + "</label>");
                            	var j = $.inArray(obj[i].courseName, course)
                                if (j != -1){
                                	$(ele).find("input").attr("checked", true);
                                	if (available.substr(j, 1) == "1"){
                                		$(ele).find("input").attr("disabled", "disabled");
                                	}
                                	$(".courseNameP").append('<span value="' + obj[i].courseId + 
                                			'"">' + obj[i].courseName + '<span>');
                                }
                                $(".courseDiv[name='Elective']").append(ele);
                            }
                        }
                    }) 
                }

                //ModInfor 所有checkbox 全部都不选中 并清空courseNameP
                function resetModInfor() {
                    $("#course_list input[type='checkbox']").attr("checked", false);
                    $(".courseNameP").html("");
                }
            }
        })
        .state('teacherState', {
            url: '/teacherState',
            templateUrl: 'tpls/trainingPS/admin-teacherState.html',
            controller: function($rootScope) {
                //预加载---教师授课;
                function onLoading(){
                    $rootScope.paging("ul.pagination",{
                        numUrl:"../teacher_queryAllTeacherNumber.action",
                        dataUrl:"../teacher_queryAllTeacher.action",
                        data:{
                        }
                    }, loadDoTeachingTbody);
                }
                onLoading();
                function loadDoTeachingTbody(obj) {
                    $(".teacherInforTbody").html("");
                    for (var i = 0; i < obj.length; i++){
                            $(".teacherInforTbody").append('<tr>'
                            		+'<td name="teacherIDCard">' + obj[i].identityId + '</td>'
                            		+'<td name="teacherName">' + obj[i].teacherName + '</td>'
                            		+'<td name="teacherAccount">' + obj[i].teacherNumber + '</td>'
                            		+'<td name="teacherPassword">' + obj[i].password + '</td>'
                            		+'</tr>');
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
