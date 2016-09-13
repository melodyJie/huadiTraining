var routerApp = angular.module('routerApp', ['ui.router']);
routerApp.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/notice');
    $stateProvider
        .state('notice', {
            url: '/notice',
            templateUrl: 'tpls/comeMS/admin-notice.html',
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
        .state('setNotice', {
            url: '/setNotice',
            templateUrl: 'tpls/comeMS/admin-setNotice.html',
            controller: function($scope, $http) {
                $(function() {
                    $.ajax({
                        url: "../register_attainNotice.action",
                        type: "GET",
                        success: function(data) {
                            $("#setNotice").val(data);
                        }
                    })
                    $("#setNoticeBtn").on("click", function() {
                        $.ajax({
                            url: "../register_addNotice.action",
                            type: "POST",
                            data: {
                                notice: $("#setNotice").val()
                            },
                            success: function(data) {
                                alert(data);
                            }
                        })
                    })
                })
            }

        })
        .state('addStudent', {
            url: '/addStudent',
            templateUrl: 'tpls/comeMS/admin-addStudent.html',
            controller: function($rootScope, $scope) {
                $rootScope.setting1();
                $("#identity").on("blur", function(){
                	if ($("#identity").val()){
                		$.ajax({
                            url: "../student_judgeStudent.action",
                            type: "POST",
                            data: {
                                identityId: $("#identity").val()
                            },
                            success: function(data) {
                            	if (data != ""){
                            		alert(data);
                            		$("#identity").val("");
                            		$("span[name='identityError']").text("");
                            	}
                            }
                        })
                	}
                })
                //确定发送数据
                $("#addStudent").on("click", function() {
                    var ok = true;
                    if (!$("#name").val()) {
                        $("span[name='nameError']").text("不能为空!!!");
                        ok = false;
                    } else {
                    	$("span[name='nameError']").text("");
                    }
                    if (!$("#identity").val()) {
                        $("span[name='identityError']").text("不能为空!!!");
                        ok = false;
                    } else {
                    	$("span[name='identityError']").text("");
                    }
                    if ($("#identity").val() && $("#identity").val().length < 6) {
                        $("span[name='identityError']").text("身份证号错误!!!");
                        ok = false;
                    }
                    if (!$("#class").val()) {
                        $("span[name='classError']").text("不能为空!!!");
                        ok = false;
                    } else {
                    	$("span[name='classError']").text("");
                    }
                    if (ok) {
                        $.ajax({
                            url: "../student_addStudent.action",
                            type: "POST",
                            data: {
                                name: $("#name").val(),
                                identityId: $("#identity").val(),
                                sex: $("#sex :selected").text(),
                                year: $("#nianjiselect").find("option:checked").text(),
                                password: $("#class :selected").text()
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
        .state('doStudent', {
            url: '/doStudent',
            templateUrl: 'tpls/comeMS/admin-doStudent.html',
            controller: function($rootScope) {
                function onLoading() {
                    $rootScope.paging("ul.pagination",{
                        numUrl:"../student_queryAllStudentsNumber.action",
                        dataUrl:"../student_queryAllStudents.action",
                        data:{
                            nianji:$("#nianji1").val(),
                            xueyuan:$("#xueyuan1").val(),
                            zhuanye:$("#zhuanye1").val(),
                            banji:$("#banji1").val()
                        }
                    }, loadDoStudent);
                }
                onLoading();

                function loadDoStudent(obj) {
                    $(".doStudentTbody").html("");
                    console.log(obj)
                    for (var i in obj) {
                        var $tr = $("<tr></tr>");
                        $tr.append("<td><input class='bigRadio' type='radio' name='studentInfor' /></td>")
                            .append("<td name='stuID'>" + obj[i].studentNumber + "</td>")
                            .append("<td name='stuName'>" + obj[i].name + "</td>")
                            .append("<td name='stuSex'>" + obj[i].sex + "</td>")
                            .append("<td name='stuIDCard'>" + obj[i].identityId + "</td>")
                            .append("<td name='stuXueyuan' data-id=" + obj[i].studentclass.specialty.college.collegeId + ">" 
                            		+ obj[i].studentclass.specialty.college.collegeName + "</td>")
                            .append("<td name='stuZhuanye' data-id=" + obj[i].studentclass.specialty.specialtyId + ">" 
                            		+ obj[i].studentclass.specialty.specialtyName + "</td>")
                            .append("<td name='stuBanji'>" + obj[i].studentclass.classNumber + "</td>");
                        $(".doStudentTbody").append($tr);
                    }
                }
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
                            $(option).appendTo($(".xueyuan"));
                        }
                    }
                });
                //选完学院 加载专业下拉菜单
                $("#xueyuan1").on("change", function() {
                    var data = $(".xueyuan").val();
                    $("#zhuanye1").html("<option value=''>请选择</option>");
                    $("#banji1").html("<option value=''>请选择</option>");
                    if (data) {
                        $.ajax({
                            url: "../specialty_querySpecialty.action",
                            type: "POST",
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
                    //TODO---分页1
                    onLoading();
                });
                //选完专业和入学年份 加载班级下拉菜单
                $(".zhuanye").on("change", function() {
                    //console.log($(".zhuanye").val());
                    var data1 = $(".zhuanye").val();
                    var data2 = $(".nianji").val();
                    $(".banji").html("<option value=''>请选择</option>");
                    console.log([data1,data2]);
                    if (data1&&data2) {
                        $.ajax({
                            url: "../studentclass_queryClass.action",
                            type: "POST", //需改为POST,传数据
                            data: {
                                classId: data1,
                                year:data2
                            },
                            success: function(data) {
                                var obj = JSON.parse(data);
                                for (var i in obj) {
                                    var option = document.createElement("option");
                                    $(option).val(obj[i].classId);
                                    $(option).text(obj[i].classNumber);
                                    $(option).appendTo($(".banji"));
                                }
                                
                            }
                        })

                    }
                    onLoading();
                });
                $(".nianji").on("change", function() {
                    //console.log($(".zhuanye").val());
                    var data1 = $(".zhuanye").val();
                    var data2 = $(".nianji").val();
                    $(".banji").html("<option value=''>请选择</option>");
                    if (data1&&data2) {
                        $.ajax({
                            url: "../studentclass_queryClass.action",
                            type: "POST", //需改为POST,传数据
                            data: {
                                classId: data1,
                                year:data2
                            },
                            success: function(data) {
                                var obj = JSON.parse(data);
                                for (var i in obj) {
                                    var option = document.createElement("option");
                                    $(option).val(obj[i].classId);
                                    $(option).text(obj[i].classNumber);
                                    $(option).appendTo($(".banji"));
                                }
                                
                            }
                        })
                    }
                    onLoading();
                });
                 $("#banji1").on("change",function(){
                    //重新加载
                    onLoading();
                })


                //返回按钮
                $("#returnBtn").on("click", function() {
                        if ($(".modInfor").css("display") != "none") {
                            $(".modInfor").hide(300);
                            $(".viewArea1").css("visibility","visible");
                        }
                        $(".btnBox").show(300);
                        $("#head").show(300);
                        $("input[name='studentInfor']:checked").attr("checked", false);
                    })
                    //提交按钮
                $("#submitUpdate").on("click", function() {
                        $.ajax({
                            url: "../student_updateStudent.action",
                            type: "POST",
                            data: {
                                name: $("#name").val(),
                                sex: $("#sex :selected").text(),
                                identityId: $("#identity").val(),
                                studentNumber: $("input[name='studentInfor']:checked").parent().next().text(),
                                password: $("#banji2 :selected").text()
                            },
                            success: function(data) {
                                alert("修改成功！！！");
                                if ($(".modInfor").css("display") != "none") {
                                    $(".modInfor").hide(300);
                                    $(".viewArea1").css("visibility","visible");
                                }
                                $(".btnBox").show(300);
                                $("#head").show(300);
                                $("input[name='studentInfor']:checked").attr("checked", false);
                                onLoading();
                            }
                        })
                    })
                    //删除按钮
                $("#delBtn").on("click", function() {
                        var $checked = $("input[name='studentInfor']:checked").parent().parent();
                        if ($checked.html()) {
                            var stuID = $checked.find("td[name='stuID']").text();
                            var stuName = $checked.find("td[name='stuName']").text();
                            var stuSex = $checked.find("td[name='stuSex']").text();
                            var stuIDCard = $checked.find("td[name='stuIDCard']").text();
                            var stuXueyuan = $checked.find("td[name='stuXueyuan']").text();
                            var stuXueyuanV = $checked.find("td[name='stuXueyuan']").attr("data-id");
                            var stuZhuanye = $checked.find("td[name='stuZhuanye']").text();
                            var stuZhuanyeV = $checked.find("td[name='stuZhuanye']").attr("data-id");
                            var stuBanji = $checked.find("td[name='stuBanji']").text();

                            $.ajax({
                                url: "../student_deleteStudent.action",
                                type: "POST",
                                data: {
                                    studentNumber: stuID
                                },
                                success: function(data) {
                                    alert("删除成功！！！");
                                    onLoading();
                                },
                                error:function(){
                                    alert("失败失败!!");
                                }
                            })
                        } else {
                            alert("请选择!!!");
                        }
                    })
                    //修改按钮
                $("#modBtn").on("click", function() {

                    var $checked = $("input[name='studentInfor']:checked").parent().parent();
                    if ($checked.html()) {
                        if ($(".modInfor").css("display") == "none") {
                            $(".modInfor").show(300);
                            $(".viewArea1").css("visibility","hidden");
                        }
                        $(".btnBox").hide(300);
                        $("#head").hide(300);
                        var stuID = $checked.find("td[name='stuID']").text();
                        var stuName = $checked.find("td[name='stuName']").text();
                        var stuSex = $checked.find("td[name='stuSex']").text();
                        var stuIDCard = $checked.find("td[name='stuIDCard']").text();
                        var stuXueyuan = $checked.find("td[name='stuXueyuan']").text();
                        var stuXueyuanV = $checked.find("td[name='stuXueyuan']").attr("data-id");
                        var stuZhuanye = $checked.find("td[name='stuZhuanye']").text();
                        var stuZhuanyeV = $checked.find("td[name='stuZhuanye']").attr("data-id");
                        var stuBanji = $checked.find("td[name='stuBanji']").text();

                        $(".modInfor").find("input[name='fi-name']").val(stuName);
                        $(".modInfor").find("select[name='fi-sex'] option").each(function() {
                            if ($(this).text() == stuSex) {
                                $(this).attr("selected", true)
                            }
                        });
                        $(".modInfor").find("input[name='fi-idcard']").val(stuIDCard);;
                        //事件--选完学院 加载专业下拉菜单
                        $("#xueyuan2").off("change").on("change", function() {
                            //console.log($(".xueyuan").val());
                            var data = $("#xueyuan2").val();
                            $("#zhuanye2").html("<option value=''>请选择</option>");
                            $("#banji2").html("<option value=''>请选择</option>");
                            if (data) {
                                $.ajax({
                                    url: "../specialty_querySpecialty.action",
                                    type: "POST",
                                    data: {
                                        specialtyId: data
                                    },
                                    success: function(data) {
                                        var obj = JSON.parse(data);
                                        for (var i in obj) {
                                            var option = document.createElement("option");
                                            $(option).val(obj[i].specialtyId);
                                            $(option).text(obj[i].specialtyName);
                                            $(option).appendTo($("#zhuanye2"));
                                        }
                                    }
                                })
                            }
                        });
                        //事件--选完专业 加载班级下拉菜单
                        $("#zhuanye2").off("change").on("change", function() {
                            var data = $("#zhuanye2").val();
                            $("#banji2").html("<option value=''>请选择</option>");
                            if (data) {
                                $.ajax({
                                    url: "../studentclass_queryClass.action",
                                    type: "POST", //需改为POST,传数据
                                    data: {
                                        classId: data
                                    },
                                    success: function(data) {
                                        var obj = JSON.parse(data);
                                        for (var i in obj) {
                                            var option = document.createElement("option");
                                            $(option).val(obj[i].classId);
                                            $(option).text(obj[i].classNumber);
                                            $(option).appendTo($("#banji2"));
                                        }
                                    }
                                })
                            }
                        });
                        //获取学院
                        $.ajax({
                            url: "../college_queryCollege.action",
                            type: "GET",
                            success: function(data) {
                                $("#xueyuan2").html("<option value=''>请选择</option>");
                                var obj = JSON.parse(data);
                                for (var i in obj) {
                                    var option = document.createElement("option");
                                    $(option).val(obj[i].collegeId);
                                    $(option).text(obj[i].collegeName);
                                    $(option).appendTo($("#xueyuan2"));
                                }
                                //设定当前值
                                $(".modInfor").find("select[name='fi-xueyuan'] option").each(function() {
                                    if ($(this).text() == stuXueyuan) {
                                        $(this).attr("selected", true)
                                    }
                                });
                                //获取当前学院的专业 并 加载到select标签
                                $.ajax({
                                    url: "../specialty_querySpecialty.action",
                                    type: "POST", //需改为POST,传数据
                                    data: {
                                        specialtyId: stuXueyuanV
                                    },
                                    success: function(data) {
                                        $("#zhuanye2").html("<option value=''>请选择</option>");
                                        $("#banji2").html("<option value=''>请选择</option>");
                                        var obj = JSON.parse(data);
                                        for (var i in obj) {
                                            var option = document.createElement("option");
                                            $(option).val(obj[i].specialtyId);
                                            $(option).text(obj[i].specialtyName);
                                            $(option).appendTo($("#zhuanye2"));
                                        }
                                        //设定当前值
                                        $(".modInfor").find("select[name='fi-zhuanye'] option").each(function() {
                                            if ($(this).text() == stuZhuanye) {
                                                $(this).attr("selected", true)
                                            }
                                        });
                                        //根据专业加载当前专业的班级
                                        $.ajax({
                                            url: "../studentclass_queryClass.action",
                                            type: "POST", //需改为POST,传数据
                                            data: {
                                                classId: stuZhuanyeV
                                            },
                                            success: function(data) {
                                                $("#banji2").html("<option value=''>请选择</option>");
                                                var obj = JSON.parse(data);
                                                for (var i in obj) {
                                                    var option = document.createElement("option");
                                                    $(option).val(obj[i].classId);
                                                    $(option).text(obj[i].classNumber);
                                                    $(option).appendTo($("#banji2"));
                                                }
                                                //设定当前值
                                                $(".modInfor").find("select[name='fi-banji'] option").each(function() {
                                                    if ($(this).text() == stuBanji) {
                                                        $(this).attr("selected", true)
                                                    }
                                                });
                                                $(".modInfor").show(300);
                                            }
                                        })
                                    }
                                })

                            }
                        });
                    } else {
                        alert("请选择！！！");
                    }
                })
            }
        })
        .state('stuState', {
            url: '/stuState',
            templateUrl: 'tpls/comeMS/admin-stuState.html',
            controller: function($rootScope) {
                //预加载
                function onLoading(){
                    $rootScope.paging("ul.pagination",{
                        numUrl:"../student_queryAllStudentsNumber.action",
                        dataUrl:"../student_queryAllStudents.action",
                        data:{
                            nianji:$("#nianji1").val(),
                            xueyuan:$("#xueyuan1").val(),
                            zhuanye:$("#zhuanye1").val(),
                            banji:$("#banji1").val()
                        }
                    }, loadStuState);
                }
                function loadStuState(obj) {
                    $(".stuInforTbody").html("");
                    for(var i=0;i<obj.length;i++){
                        //TODO---litong
                        $(".stuInforTbody").append('<tr>'
                        		+'<td name="stuId">' + obj[i].studentId + '</td>'
                        		+'<td name="stuNum">' + obj[i].studentNumber + '</td>'
                        		+'<td name="stuName">' + obj[i].name + '</td>'
                        		+'<td name="stuIDCard">' + obj[i].identityId + '</td>'
                        		+'<td name="password">' + obj[i].password + '</td>'
                        		+'</tr>')
                    }
                }

                $.ajax({
                    url: "../college_queryCollege.action",
                    type: "GET",
                    success: function(data) {
                        $(".xueyuan").html("<option value=''>请选择</option>");
                        var obj = JSON.parse(data);
                        for (var i in obj) {
                            var option = document.createElement("option");
                            $(option).val(obj[i].collegeId);
                            $(option).text(obj[i].collegeName);
                            $(option).appendTo($(".xueyuan"));
                        }
                    }
                });
                //选完学院 加载专业下拉菜单
                $(".xueyuan").on("change", function() {
                    //console.log($(".xueyuan").val());
                    var data = $(".xueyuan").val();
                    $(".zhuanye").html("<option value=''>请选择</option>");
                    $(".banji").html("<option value=''>请选择</option>");
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
                                    $(option).appendTo($(".zhuanye"));
                                }
                            }
                        })
                    }
                    //重新加载
                    onLoading();
                });
                //选完专业和入学年份 加载班级下拉菜单
                $(".zhuanye").on("change", function() {
                    //console.log($(".zhuanye").val());
                    var data1 = $(".zhuanye").val();
                    var data2 = $(".nianji").val();
                    $(".banji").html("<option value=''>请选择</option>");
                    //console.log([data1,data2]);
                    if (data1&&data2) {
                        $.ajax({
                            url: "../studentclass_queryClass.action",
                            type: "POST", //需改为POST,传数据
                            data: {
                                classId: data1,
                                year:data2
                            },
                            success: function(data) {
                                var obj = JSON.parse(data);
                                for (var i in obj) {
                                    var option = document.createElement("option");
                                    $(option).val(obj[i].classId);
                                    $(option).text(obj[i].classNumber);
                                    $(option).appendTo($(".banji"));
                                }
                                
                            }
                        })
                    }
                    onLoading();
                });
                $(".nianji").on("change", function() {
                    //console.log($(".zhuanye").val());
                    var data1 = $(".zhuanye").val();
                    var data2 = $(".nianji").val();
                    $(".banji").html("<option value=''>请选择</option>");
                    if (data1&&data2) {
                        $.ajax({
                            url: "../studentclass_queryClass.action",
                            type: "POST", //需改为POST,传数据
                            data: {
                                classId: data1,
                                year:data2
                            },
                            success: function(data) {
                                var obj = JSON.parse(data);
                                for (var i in obj) {
                                    var option = document.createElement("option");
                                    $(option).val(obj[i].classId);
                                    $(option).text(obj[i].classNumber);
                                    $(option).appendTo($(".banji"));
                                }
                                
                            }
                        })
                    }
                    onLoading();
                });
                
                 $("#banji1").on("change",function(){
                    //重新加载
                    onLoading();
                })
                //预加载
                onLoading();
            }
        });
});

routerApp.run(function($rootScope) {
    //只能执行依次
    $rootScope.setting1 = function() {
        //预加载学院下拉菜单
        $.ajax({
            url: "../college_queryCollege.action",
            type: "GET",
            success: function(data) {
                $(".xueyuan").html("<option value=''>请选择</option>");
                var obj = JSON.parse(data);
                for (var i in obj) {
                    var option = document.createElement("option");
                    $(option).val(obj[i].collegeId);
                    $(option).text(obj[i].collegeName);
                    $(option).appendTo($(".xueyuan"));
                }
            }
        });
        //选完学院 加载专业下拉菜单
        $(".xueyuan").on("change", function() {
            //console.log($(".xueyuan").val());
            var data = $(".xueyuan").val();
            $(".zhuanye").html("<option value=''>请选择</option>");
            $(".banji").html("<option value=''>请选择</option>");
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
                            $(option).appendTo($(".zhuanye"));
                        }
                    }
                })
            }
        });
        /*//选完专业 加载班级下拉菜单
        $(".zhuanye").on("change", function() {
            //console.log($(".zhuanye").val());
            var data = $(".zhuanye").val();
            $(".banji").html("<option value=''>请选择</option>");
            if (data) {
                $.ajax({
                    url: "../studentclass_queryClass.action",
                    type: "POST", //需改为POST,传数据
                    data: {
                        classId: data
                    },
                    success: function(data) {
                        var obj = JSON.parse(data);
                        for (var i in obj) {
                            var option = document.createElement("option");
                            $(option).val(obj[i].classId);
                            $(option).text(obj[i].classNumber);
                            $(option).appendTo($(".banji"));
                        }
                    }
                })
            }
        });*/
        //选完专业和入学年份 加载班级下拉菜单
        $(".zhuanye").on("change", function() {
            //console.log($(".zhuanye").val());
            var data1 = $(".zhuanye").val();
            var data2 = $(".nianji").val();
            $(".banji").html("<option value=''>请选择</option>");
            console.log([data1,data2]);
            if (data1&&data2) {
                $.ajax({
                    url: "../studentclass_queryClass.action",
                    type: "POST", //需改为POST,传数据
                    data: {
                        classId: data1,
                        year:data2
                    },
                    success: function(data) {
                        var obj = JSON.parse(data);
                        for (var i in obj) {
                            var option = document.createElement("option");
                            $(option).val(obj[i].classId);
                            $(option).text(obj[i].classNumber);
                            $(option).appendTo($(".banji"));
                        }
                    }
                })
            }
        });
        $(".nianji").on("change", function() {
            //console.log($(".zhuanye").val());
            var data1 = $(".zhuanye").val();
            var data2 = $(".nianji").val();
            $(".banji").html("<option value=''>请选择</option>");
            if (data1&&data2) {
                $.ajax({
                    url: "../studentclass_queryClass.action",
                    type: "POST", //需改为POST,传数据
                    data: {
                        classId: data1,
                        year:data2
                    },
                    success: function(data) {
                        var obj = JSON.parse(data);
                        for (var i in obj) {
                            var option = document.createElement("option");
                            $(option).val(obj[i].classId);
                            $(option).text(obj[i].classNumber);
                            $(option).appendTo($(".banji"));
                        }
                    }
                })
            }
        });
    }


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
