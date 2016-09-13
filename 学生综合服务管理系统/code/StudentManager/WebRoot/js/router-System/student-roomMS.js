var routerApp = angular.module('routerApp', ['ui.router']);
routerApp.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/home');
    $stateProvider.state('home', {
            url: '/home',
            templateUrl: 'tpls/roomMS/student-home.html',
            controller: function($scope, $http) {
                $(function() {
                	$.ajax({
                        url: "../notice_attainNotice.action",
                        type: "POST",
                        data: {
                        	title: "room"
                        },
                        success: function(data) {
                            $("#notice").html(data);
                        }
                    })
                })
            }
        })
        .state('seeScore', {
            url: '/seeScore',
            templateUrl: 'tpls/roomMS/student-seeScore.html',
            controller: function($rootScope) {
                function loadSeeScoreTbody(score){
                    $(".seeScoreTbody").html("");
                    for (var i = 0; i < score.length; i++) {
                        $(".seeScoreTbody").append('<tr>'
                                                        +'<td>'+score[i].room.roomNumber +'</td>'
                                                        +'<td>'+score[i].score +'</td>'
                                                        +'<td>'+score[i].scoreDate +'</td>'
                                                    +'</tr>');
                    }
                }
                $rootScope.paging("ul.pagination", "../roomscore_queryScoreNumber.action", "../roomscore_queryScore.action", loadSeeScoreTbody);
            }

        })
        .state('repair', {
            url: '/repair',
            templateUrl: 'tpls/roomMS/student-repair.html',
            controller: function($scope, $http) {
                $(function() {
                    $("#submit").on("click", function() {
                        var $handle = $(".textInput:first");
                        var $note = $(".areaInput:first");
                        if ($handle.val() == "" || $note.val() == "") {
                            alert("请填写信息！！！");
                        } else {
                            $.ajax({
                                url: "../maintenance_addMaintenance.action",
                                type: "POST",
                                data: {
                                    //maintenanceId: $roomNumber.val(),
                                    handle: $handle.val(),
                                    note: $note.val()
                                },
                                // dataType:"JSON",
                                success: function(data) {
                                    alert(data);
                                    window.location.reload(true);
                                },
                                error: function(e1, e2) {
                                    alert([e1, e2]);
                                }
                            })
                        }
                    });
                });
            }
        })
        .state('seeProgress', {
            url: '/seeProgress',
            templateUrl: 'tpls/roomMS/student-seeProgress.html',
            controller: function($rootScope) {
                $("#cancelMaintenance").on("click", function() {
                    var $maintenanceId = $(".bigRadio:checked").parent().next();
                    $.ajax({
                        url: "../maintenance_cancelMaintenance.action",
                        type: "POST",
                        data: {
                            maintenanceId: $maintenanceId.text(),
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

                function loadSeeProgressTbody(baoxiu){
                    $(".seeProgressTbody").html("");
                    for (var i = 0; i < baoxiu.length; i++) {
                    	$tr = $("<tr></tr>");
                    	if (baoxiu[i].state == "已维修"){
                        	$tr.append('<td>'
                                    +'<input class="bigRadio" type="radio" name="course" disabled="disabled" />'
                                    +'</td>');
                        } else {
                        	$tr.append('<td>'
                                    +'<input class="bigRadio" type="radio" name="course" />'
                                    +'</td>');
                        }
                        $tr.append('<td>'+baoxiu[i].maintenanceId+'</td>'
                        		+'<td>'+baoxiu[i].room.roomNumber+'</td>'
                        		+'<td>'+baoxiu[i].handle+'</td>'
                        		+'<td>'+baoxiu[i].note+'</td>'
                        		+'<td>'+baoxiu[i].maintenanceDate+'</td>'
                        		+'<td>'+baoxiu[i].state+'</td>');
                        $(".seeProgressTbody").append($tr);
                    }
                }
                $rootScope.paging("ul.pagination", "../maintenance_queryMaintenanceNumber.action", "../maintenance_queryMaintenance.action", loadSeeProgressTbody);
            }

        })
        .state('applyStay', {
            url: '/applyStay',
            templateUrl: 'tpls/roomMS/student-applyStay.html',
            controller: function($scope, $http) {
                $(function() {
                    jeDate({
                        dateCell: "#dateInputS",
                        format: "YYYY-MM-DD",
                        isTime: false,
                        minDate: jeDate.now(0),
                        maxDate: '2099-06-30', //最大日期
                        festival: true
                    });
                    jeDate({
                        dateCell: "#dateInputE",
                        format: "YYYY-MM-DD",
                        isTime: false,
                        minDate: jeDate.now(0),
                        maxDate: '2099-06-30', //最大日期
                        festival: true
                    });
                    //
                    $("#dateInputS").on("change", function() {
                        if ($("#dateInputS").val() && $("#dateInputE").val()) {
                            var arryS = $("#dateInputS").val().split("-");
                            console.log(arryS);
                            console.log($("#dateInputE").val());
                        }
                    })
                    $("#dateInputE").on("change", function() {
                        if ($("#dateInputS").val() && $("#dateInputE").val()) {
                            var arryE = $("#dateInputE").val().split("-");
                            console.log(arryE);
                            console.log($("#dateInputE").val());
                        }
                    })

                    $("#submitApplication").on("click", function() {
                        var $beginDate = $("#dateInputS");
                        var $endDate = $("#dateInputE");
                        var $reason = $(".areaInput:first");
                        if ($beginDate.val() == "" || $endDate.val() == "") {
                            alert("请选择留校时间！！！");
                        } else if ($reason.val() == "") {
                            alert("请注明原因！！！");
                        } else {
                            $.ajax({
                                url: "../application_addApplication.action",
                                type: "POST",
                                data: {
                                    beginDate: $beginDate.val(),
                                    endDate: $endDate.val(),
                                    reason: $reason.val()
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
                        }
                    })
                });
                $http.get("").success(function() {});
            }
        })
        .state('seeAProgress', {
            url: '/seeAProgress',
            templateUrl: 'tpls/roomMS/student-seeAProgress.html',
            controller: function($rootScope) {
                $(function() {
                    $("#cancelApplication").on("click", function() {
                        var $applicationId = $(".bigRadio:checked").parent().next();
                        $.ajax({
                            url: "../application_cancelApplication.action",
                            type: "POST",
                            data: {
                                applicationId: $applicationId.text(),
                            },
                            //dataType:"JSON",
                            success: function(data) {
                                alert("取消成功！！！");
                                window.location.reload(true);
                            },
                            error: function(e1, e2) {
                                alert([e1, e2]);
                            }
                        })
                    });
                });

                function loadSeeAProgressTbody(liuxiao){
                    $(".seeAProgressTbody").html("");
                    for (var i = 0; i < liuxiao.length; i++) {
                    	$tr = $("<tr></tr>");
                    	if (liuxiao[i].state == "审核中"){
                        	$tr.append('<td>'
                                    +'<input class="bigRadio" type="radio" name="stay" />'
                                    +'</td>');
                        } else {
                        	$tr.append('<td>'
                                    +'<input class="bigRadio" type="radio" name="stay" disabled="disabled" />'
                                    +'</td>');
                        }
                        $tr.append('<td>'+liuxiao[i].applicationId+'</td>'
                        		+'<td>'+liuxiao[i].student.name+'</td>'
                        		+'<td>'+liuxiao[i].beginDate+'</td>'
                        		+'<td>'+liuxiao[i].endDate+'</td>'
                        		+'<td>'+liuxiao[i].reason+'</td>'
                        		+'<td>'+liuxiao[i].state+'</td>');
                        $(".seeAProgressTbody").append($tr);
                    }
                }
                $rootScope.paging("ul.pagination", "../application_queryApplicationNumber.action", "../application_queryApplication.action", loadSeeAProgressTbody);
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
                data: {
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
