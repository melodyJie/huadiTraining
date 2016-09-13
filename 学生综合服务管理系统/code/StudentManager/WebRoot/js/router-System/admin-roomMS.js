var routerApp = angular.module('routerApp', ['ui.router']);
routerApp.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/notice');
    $stateProvider
        .state('notice', {
            url: '/notice',
            templateUrl: 'tpls/roomMS/admin-notice.html',
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
        .state('setNotice', {
            url: '/setNotice',
            templateUrl: 'tpls/roomMS/admin-setNotice.html',
            controller: function($scope, $http) {
                $(function() {
                    $.ajax({
                        url: "../notice_attainNotice.action",
                        type: "POST",
                        data: {
                        	title: "room"
                        },
                        success: function(data) {
                            $("#setNotice").val(data);
                        }
                    })
                    $("#setNoticeBtn").on("click", function() {
                        $.ajax({
                            url: "../notice_addNotice.action",
                            type: "POST",
                            data: {
                            	title: "room",
                                content: $("#setNotice").val()
                            },
                            success: function(data) {
                                alert(data);
                            }
                        })
                    })
                })
            }
        })
        .state('seeHealthScore', {
            url: '/seeHealthScore',
            templateUrl: 'tpls/roomMS/admin-seeHealthScore.html',
            controller: function($rootScope) {
                $(function() {
                    //dateInput 设置
                    jeDate({
                        dateCell: ".dateInput",
                        format: "YYYY-MM-DD",
                        isTime: false,
                        minDate: "2016-1-1 00:00:00",
                        maxDate: "2099-12-31 23:59:59",
                        festival: true
                    });
                    //给tbody的修改按钮添加事件
                    $(".seeHealthScoreTbody").on("click", ".modHealthScoreBtn", function() {
                        var $this = $(this);
                        if ($this.val() == "修改") {
                            $this.parent().siblings("td").each(function() { // 获取当前行的其他单元格
                                if ($(this).attr("name") == "score") {
                                    $(this).html("<input class='f0to100Input' type='text' value='" + $(this).text() + "'>");

                                }
                                //时间应该不能改
                                /*if($(this).attr("name")=="date"){
                                    $(this).html("<input class='dateInput' type='text' value='" + $(this).text() + "'>");
                                }*/
                            });
                            $this.val("确定");
                        } else if ($this.val() == "确定") {
                            //dataJson用来存储数据
                            var dataJson = {};
                            dataJson["roomscoreId"] = $this.parent().siblings("td[name='roomNumber']").attr("data-id");
                            dataJson["roomNumber"] = $this.parent().siblings("td[name='roomNumber']").text();
                            $this.parent().siblings("td").each(function(index) { // 获取当前行的其他单元格
                                    obj_text = $(this).find("input:text"); //获取文本框
                                    if (obj_text.length) {
                                        //TODO--dataJson用来存储数据
                                        dataJson[$(this).attr("name")] = obj_text.val();
                                    }
                                })
                                //test
                            console.log(dataJson);
                            //这段代码应该在success 里面
                            $this.parent().siblings("td").each(function(index) { // 获取当前行的其他单元格
                                var obj_text = $(this).find("input:text"); //获取文本框
                                if (obj_text.length) {
                                    $(this).html(obj_text.val());
                                }
                            })
                            $this.val("修改");
                            $.ajax({
                                url: "../roomscore_updateRoomscore.action",
                                data: dataJson,
                                type: "POST",
                                success: function(result) {
                                    /*if (parseInt(result)) {
                                        $this.parent().siblings("td").each(function(index) { // 获取当前行的其他单元格
                                            var obj_text = $(this).find("input:text"); //获取文本框
                                            if (obj_text.length) {
                                                $(this).html(obj_text.val());
                                            }
                                        })
                                    } else {
                                        alert("申请成功,但修改失败...");
                                        //TODO--重新加载展示页面(局部)
                                    }*/
                                },
                                error: function(result, state) {
                                    alert(state);
                                }
                            })
                        } else {
                            alert("error....");
                        }
                    })

                    //预加载--楼栋
                    $rootScope.loadFloor();

                    //选中楼栋和日期后,提交按钮事件,加载卫生评分
                    $(".fsBtn").on("click", function() {
                            if ($("#date").val() == "" || $("#building :selected").val() == "") {
                                alert("请选择楼栋和日期！！！");
                            } else {
                                $rootScope.paging("ul.pagination", {
                                    numUrl: "../roomscore_queryAllScoreNumber.action",
                                    dataUrl: "../roomscore_queryAllScore.action",
                                    data: {
                                        scoreDate: $("#date").val(),
                                        roomNumber: $("#building :selected").val()
                                    }
                                }, loadSeeScore);
                            }
                        })
                        //0-100Input标签限制事件
                    $(".seeHealthScoreTbody").on("change", ".f0to100Input", function() {
                            if (!/(^0$)|(^100$)|(^\d{1,2}$)/.test($(this).val())) {
                                $(this).val("");
                                alert('输入格式不正确!');
                            }
                        })
                        //根据楼栋和日期加载为什评分
                    function loadSeeScore(obj) {
                        $(".seeHealthScoreTbody").html("");
                        for (var i = 0; i < obj.length; i++) {
                            var tr = document.createElement("tr");
                            var td_roomNum = document.createElement("td");
                            $(td_roomNum).attr("name", "roomNumber");
                            $(td_roomNum).attr("data-id", obj[i].roomgradeId);
                            //TODO--设定<td name="roomNum"> 标签的 数值
                            $(td_roomNum).html(obj[i].room.roomNumber);
                            tr.appendChild(td_roomNum);

                            var td_allScore = document.createElement("td");
                            $(td_allScore).attr("name", "score");
                            //TODO--设定<td name="allScore"> 标签的 数值
                            $(td_allScore).html(obj[i].score);
                            tr.appendChild(td_allScore);

                            var td_date = document.createElement("td");
                            $(td_date).attr("name", "scoreDate");
                            //TODO--设定<td name="date"> 标签的 数值
                            $(td_date).html(obj[i].scoreDate);
                            tr.appendChild(td_date);

                            var td_caozuo = document.createElement("td");
                            var mod_input = document.createElement("input")
                                //$(mod_input).attr("","");
                            $(mod_input).attr("type", "button");
                            $(mod_input).attr("class", "modHealthScoreBtn");
                            $(mod_input).attr("value", "修改");
                            td_caozuo.appendChild(mod_input);
                            tr.appendChild(td_caozuo);
                            $(".seeHealthScoreTbody").append(tr)
                        }
                    }

                })
            }
        })
        .state('setHealthScore', {
            url: '/setHealthScore',
            templateUrl: 'tpls/roomMS/admin-setHealthScore.html',
            controller: function($rootScope) {
                $(function() {
                    //dateInput 设置
                    jeDate({
                        dateCell: ".dateInput",
                        format: "YYYY-MM-DD",
                        isTime: false,
                        minDate: "2016-1-1 00:00:00",
                        maxDate: "2099-12-31 23:59:59",
                        festival: true
                    });
                    //预加载--楼栋
                    $rootScope.loadFloor(); //TODO--填写parentElement


                    $(".fsBtn").on("click", function() {
                        loadSetScore(".setHealthScoreTbody");
                    })

                    function loadSetScore(parentElement) {
                        if ($("#date").val() == "" || $("#building :selected").val() == "") {
                            alert("请选择楼栋和日期");
                        } else {
                            //预加载
                            $.ajax({
                                url: "../roomscore_queryNoScoreRoom.action",
                                type: "POST",
                                data: {
                                    scoreDate: $("#date").val(),
                                    roomNumber: $("#building :selected").val()
                                },
                                success: function(data) {
                                    var obj = JSON.parse(data);
                                    //if-succsee ~~~~ 根据数值加载到tr - td 里去
                                    $(parentElement).html(""); //清空
                                    var data_length = obj.length; //数据长度
                                    for (var i = 0; i < data_length; i++) {
                                        var tr = document.createElement("tr");
                                        var td_roomNum = document.createElement("td");
                                        $(td_roomNum).attr("name", "roomNumber");
                                        $(td_roomNum).attr("data-id", obj[i].roomId);
                                        //TODO--设定<td name="roomNum"> 标签的 数值
                                        $(td_roomNum).html(obj[i].roomNumber);
                                        tr.appendChild(td_roomNum);

                                        var td_allScore = document.createElement("td");
                                        $(td_allScore).attr("name", "score");
                                        var score_input = document.createElement("input");
                                        $(score_input).attr("type", "text");
                                        $(score_input).attr("placeholder", "0至100以内的整数");
                                        $(score_input).attr("class", "f0to100Input");
                                        td_allScore.appendChild(score_input);
                                        tr.appendChild(td_allScore);

                                        /*var td_date = document.createElement("td");
                                        $(td_date).attr("name", "date");
                                        var date_input = document.createElement("input");
                                        $(date_input).attr("type", "text");
                                        $(date_input).attr("class", "dateInput");
                                        td_date.appendChild(date_input);
                                        tr.appendChild(td_date);*/

                                        var td_caozuo = document.createElement("td");
                                        var sub_input = document.createElement("input");
                                        //$(mod_input).attr("","");
                                        $(sub_input).attr("type", "button");
                                        $(sub_input).attr("class", "subHealthScoreBtn");
                                        $(sub_input).attr("value", "提交");
                                        td_caozuo.appendChild(sub_input);
                                        tr.appendChild(td_caozuo);
                                        $(parentElement).append(tr);
                                    }
                                }
                            })
                        }
                    }
                    //0-100Input标签限制事件
                    $(".setHealthScoreTbody").on("change", ".f0to100Input", function() {
                        if (!/(^0$)|(^100$)|(^\d{1,2}$)/.test($(this).val())) {
                            $(this).val("");
                            alert('输入格式不正确!');
                        }
                    })
                    $(".setHealthScoreTbody").on("click", ".subHealthScoreBtn", function() {
                        var roomscoreId = $(this).parent().parent().find("td[name='roomNumber']").attr("data-id");
                        var score = $(this).parent().siblings("td[name='score']").find("input:text").val();
                        if (score == "") {
                            alert("请输入分数！！！");
                        } else {
                            $.ajax({
                                url: "../roomscore_savaRoomscore.action",
                                type: "POST",
                                data: {
                                    roomscoreId: roomscoreId,
                                    score: score,
                                    scoreDate: $("#date").val()
                                },
                                success: function(data) {
                                    alert("保存成功！！！");
                                    loadSetScore(".setHealthScoreTbody");
                                }
                            })
                        }
                    })

                })
            }

        })
        .state('manRepairRecord', {
            url: '/manRepairRecord',
            templateUrl: 'tpls/roomMS/admin-manRepairRecord.html',
            controller: function($rootScope) {
                //预加载--楼栋select
                $rootScope.loadFloor(""); //TODO--填写parentElement
                //预加载--宿舍号select
                $rootScope.loadRoomNum(""); //TODO--填写parentElement

                //楼栋改变
                $("#building").on("change", function() {
                    loadRepairRecord();
                })

                //宿舍号改变
                $("#room").on("change", function() {
                    loadRepairRecord();
                })

                //提交
                $(".manRepairRecordTbody").on("click", ".subRepairRecordBtn", function() {
                    $.ajax({
                        url: "../maintenance_updateMaintenance.action",
                        type: "POST",
                        data: {
                            maintenanceId: $(this).parent().parent().find("td[name='recordId']").text(),
                            state: $(this).parent().parent().find("select option:selected").text()
                        },
                        success: function(data) {
                            alert("修改成功！！！");
                            loadRepairRecord();
                        }
                    })
                })

                //预加载--待处理维修记录
                loadRepairRecord();

                function loadRepairRecord() {
                    /*$.ajax({
                        url: "../maintenance_queryAllMaintenance.action",
                        type: "POST",
                        data: {
                            handle: $("#building :selected").val(),
                            note: $("#room :selected").val(),
                            state: "审核中 处理中"
                        },
                        success: function(data) {
                            repairRecord(data);
                        }
                    })*/
                    $rootScope.paging("ul.pagination", {
                        numUrl: "../maintenance_queryAllMaintenanceNumber.action",
                        dataUrl: "../maintenance_queryAllMaintenance.action",
                        data: {
                            handle: $("#building :selected").val(),
                            note: $("#room :selected").val(),
                            state: "审核中 处理中"
                        }
                    }, repairRecord);
                }

                function repairRecord(obj) {
                    $(".manRepairRecordTbody").html(""); //清空
                    for (var i = 0; i < obj.length; i++) {
                        var tr = document.createElement("tr");
                        //-记录id  列
                        var td_recordId = document.createElement("td");
                        $(td_recordId).attr("name", "recordId");
                        //TODO--设定<td name="recordId"> 标签的 数值
                        $(td_recordId).html(obj[i].maintenanceId);
                        tr.appendChild(td_recordId);

                        //-楼栋 列
                        var td_floor = document.createElement("td");
                        $(td_floor).attr("name", "floor");
                        //TODO--设定<td name="floor"> 标签的 数值
                        $(td_floor).html(obj[i].room.building.buildingName);
                        tr.appendChild(td_floor);

                        //-宿舍号 列
                        var td_roomNum = document.createElement("td");
                        $(td_roomNum).attr("name", "roomNum");
                        //TODO--设定<td name="roomNum"> 标签的 数值
                        $(td_roomNum).html(obj[i].room.roomNumber);
                        tr.appendChild(td_roomNum);

                        //-物品 列
                        var td_goods = document.createElement("td");
                        $(td_goods).attr("name", "goods");
                        //TODO--设定<td name="goods"> 标签的 数值
                        $(td_goods).html(obj[i].handle);
                        tr.appendChild(td_goods);

                        //-详情 列
                        var td_details = document.createElement("td");
                        $(td_details).attr("name", "details");
                        //TODO--设定<td name="details"> 标签的 数值
                        $(td_details).html(obj[i].note);
                        tr.appendChild(td_details);

                        //-报修时间 列
                        var td_rTime = document.createElement("td");
                        $(td_rTime).attr("name", "rTime");
                        //TODO--设定<td name="rTime"> 标签的 数值
                        $(td_rTime).html(obj[i].maintenanceDate);
                        tr.appendChild(td_rTime);

                        //-状态 列
                        var td_state = document.createElement("td");
                        $(td_state).attr("name", "state");
                        $(td_state).attr("class", "state");
                        var td_state_select = document.createElement("select");

                        var td_state_select_option1 = document.createElement("option");
                        //TODO--设定-带查看-选项的value值
                        $(td_state_select_option1).attr("value", "审核中");
                        $(td_state_select_option1).html("审核中");
                        td_state_select.appendChild(td_state_select_option1);

                        var td_state_select_option2 = document.createElement("option");
                        //TODO--设定-带查看-选项的value值
                        $(td_state_select_option2).attr("value", "处理中");
                        $(td_state_select_option2).html("处理中");
                        td_state_select.appendChild(td_state_select_option2);

                        var td_state_select_option3 = document.createElement("option");
                        //TODO--设定-带查看-选项的value值
                        $(td_state_select_option3).attr("value", "已维修");
                        $(td_state_select_option3).html("已维修");
                        td_state_select.appendChild(td_state_select_option3);

                        td_state.appendChild(td_state_select);
                        tr.appendChild(td_state);

                        //-操作 列
                        var td_caozuo = document.createElement("td");
                        var sub_input = document.createElement("input");
                        //$(mod_input).attr("","");
                        $(sub_input).attr("type", "button");
                        $(sub_input).attr("class", "subRepairRecordBtn");
                        $(sub_input).attr("value", "提交");
                        td_caozuo.appendChild(sub_input);
                        tr.appendChild(td_caozuo);
                        $(".manRepairRecordTbody").append(tr);
                    }
                    for (var i = 0; i < obj.length; i++) {
                        $(".state:eq(" + i + ")").find("option[value=" + obj[i].state + "]").attr("selected", true);
                    }
                }
            }
        })
        .state('seeAllRepairRecord', {
            url: '/seeAllRepairRecord',
            templateUrl: 'tpls/roomMS/admin-seeAllRepairRecord.html',
            controller: function($rootScope) {
                //预加载--楼栋select
                $rootScope.loadFloor(""); //TODO--填写parentElement
                //预加载--宿舍号select
                $rootScope.loadRoomNum(""); //TODO--填写parentElement

                //楼栋改变
                $("#building").on("change", function() {
                    loadseeAllRepairRecord();
                })

                //宿舍号改变
                $("#room").on("change", function() {
                    loadseeAllRepairRecord();
                })

                //预加载--待处理维修记录
                loadseeAllRepairRecord();

                function loadseeAllRepairRecord() {
                    /*$.ajax({
                        url: "../maintenance_queryAllMaintenance.action",
                        type: "POST",
                        data: {
                            handle: $("#building :selected").val(),
                            note: $("#room :selected").val(),
                            state: "已维修"
                        },
                        success: function(data) {
                             repairRecord(data);
                        }
                    })*/
                    $rootScope.paging("ul.pagination", {
                        numUrl: "../maintenance_queryAllMaintenanceNumber.action",
                        dataUrl: "../maintenance_queryAllMaintenance.action",
                        data: {
                            handle: $("#building :selected").val(),
                            note: $("#room :selected").val(),
                            state: "已维修"
                        }
                    }, repairRecord);

                }

                function repairRecord(obj) {
                    $(".seeAllRepairRecordTbody").html(""); //清空
                    for (var i = 0; i < obj.length; i++) {
                        var tr = document.createElement("tr");
                        //-记录id  列
                        var td_recordId = document.createElement("td");
                        $(td_recordId).attr("name", "recordId");
                        //TODO--设定<td name="recordId"> 标签的 数值
                        $(td_recordId).html(obj[i].maintenanceId);
                        tr.appendChild(td_recordId);

                        //-楼栋 列
                        var td_floor = document.createElement("td");
                        $(td_floor).attr("name", "floor");
                        //TODO--设定<td name="floor"> 标签的 数值
                        $(td_floor).html(obj[i].room.building.buildingName);
                        tr.appendChild(td_floor);

                        //-宿舍号 列
                        var td_roomNum = document.createElement("td");
                        $(td_roomNum).attr("name", "roomNum");
                        //TODO--设定<td name="roomNum"> 标签的 数值
                        $(td_roomNum).html(obj[i].room.roomNumber);
                        tr.appendChild(td_roomNum);

                        //-物品 列
                        var td_goods = document.createElement("td");
                        $(td_goods).attr("name", "goods");
                        //TODO--设定<td name="goods"> 标签的 数值
                        $(td_goods).html(obj[i].handle);
                        tr.appendChild(td_goods);

                        //-详情 列
                        var td_details = document.createElement("td");
                        $(td_details).attr("name", "details");
                        //TODO--设定<td name="details"> 标签的 数值
                        $(td_details).html(obj[i].note);
                        tr.appendChild(td_details);

                        //-报修时间 列
                        var td_rTime = document.createElement("td");
                        $(td_rTime).attr("name", "rTime");
                        //TODO--设定<td name="rTime"> 标签的 数值
                        $(td_rTime).html(obj[i].maintenanceDate);
                        tr.appendChild(td_rTime);

                        //-状态 列
                        var td_state = document.createElement("td");
                        $(td_state).attr("name", "state");
                        //TODO--设定<td name="state"> 标签的 数值
                        $(td_state).html(obj[i].state);
                        tr.appendChild(td_state);

                        //-操作 列 ---暂时不加操作吧
                        /*var td_caozuo = document.createElement("td");
                        var sub_input = document.createElement("input");
                        //$(mod_input).attr("","");
                        $(sub_input).attr("type", "button");
                        $(sub_input).attr("class", "subRepairRecordBtn");
                        $(sub_input).attr("value", "提交");
                        td_caozuo.appendChild(sub_input);
                        tr.appendChild(td_caozuo);*/
                        $(".seeAllRepairRecordTbody").append(tr);
                    }
                }
            }
        })
        .state('manStayRecord', {
            url: '/manStayRecord',
            templateUrl: 'tpls/roomMS/admin-manStayRecord.html',
            controller: function($rootScope) {
            	loadMainP();
            	function loadMainP(){
            		$rootScope.paging("ul.pagination", {
                        numUrl: "../application_queryAllApplicationNumber.action",
                        dataUrl: "../application_queryAllApplication.action",
                        data: {
                            state: "审核中"
                        }
                    }, loadStayRecord);
            	}
                

                function loadStayRecord(obj) {
                    $(".manStayRecordTbody").html(""); //清空
                    var data_length = obj.length; //数据长度
                    for (var i = 0; i < data_length; i++) {
                        var tr = document.createElement("tr");
                        //-记录id  列
                        var td_recordId = document.createElement("td");
                        $(td_recordId).attr("name", "recordId");
                        //TODO--设定<td name="recordId"> 标签的 数值
                        $(td_recordId).html(obj[i].applicationId);
                        tr.appendChild(td_recordId);

                        //-学生姓名 列
                        var td_stuName = document.createElement("td");
                        $(td_stuName).attr("name", "stuName");
                        //TODO--设定<td name="stuName"> 标签的 数值
                        $(td_stuName).html(obj[i].student.name);
                        tr.appendChild(td_stuName);

                        //-学生学号 列
                        var td_stuNum = document.createElement("td");
                        $(td_stuNum).attr("name", "stuNum");
                        //TODO--设定<td name="stuNum"> 标签的 数值
                        $(td_stuNum).html(obj[i].student.studentNumber);
                        tr.appendChild(td_stuNum);

                        //-开始时间 列
                        var td_staTime = document.createElement("td");
                        $(td_staTime).attr("name", "staTime");
                        //TODO--设定<td name="staTime"> 标签的 数值
                        $(td_staTime).html(obj[i].beginDate);
                        tr.appendChild(td_staTime);

                        //-结束时间 列
                        var td_endTime = document.createElement("td");
                        $(td_endTime).attr("name", "endTime");
                        //TODO--设定<td name="endTime"> 标签的 数值
                        $(td_endTime).html(obj[i].endDate);
                        tr.appendChild(td_endTime);

                        //-详细原因 列
                        var td_details = document.createElement("td");
                        $(td_details).attr("name", "details");
                        //TODO--设定<td name="details"> 标签的 数值
                        $(td_details).html(obj[i].reason);
                        tr.appendChild(td_details);

                        //-操作 列 ---暂时不加操作吧
                        var td_caozuo = document.createElement("td");
                        var agree_input = document.createElement("input");
                        $(agree_input).attr("type", "button");
                        $(agree_input).attr("class", "agrStayRecordBtn");
                        $(agree_input).attr("value", "同意");
                        td_caozuo.appendChild(agree_input);

                        var disagree_input = document.createElement("input");
                        $(disagree_input).attr("type", "button");
                        $(disagree_input).attr("class", "disStayRecordBtn");
                        $(disagree_input).attr("value", "拒绝");
                        td_caozuo.appendChild(disagree_input);
                        tr.appendChild(td_caozuo);
                        $(".manStayRecordTbody").append(tr);
                    }
                }
                //添加事件--同意按钮
                $(".manStayRecordTbody").on("click", ".agrStayRecordBtn", function() {
                    //根据 td的name来获取td 进而取其值
                    var data = $(this).parent().siblings("td[name='recordId']").text();
                    $.ajax({
                        url: "../application_updateApplication.action",
                        type: "POST",
                        data: {
                            applicationId: data,
                            state: "已同意"
                        },
                        success: function(data) {
                            alert("同意成功！！！");
                            loadMainP();
                        },
                        error:function(){
                            alert("同意失败!!!");
                        }
                    })
                })

                //添加事件--拒绝按钮
                $(".manStayRecordTbody").on("click", ".disStayRecordBtn", function() {
                    var data = $(this).parent().siblings("td[name='recordId']").text();
                    $.ajax({
                        url: "../application_updateApplication.action",
                        type: "POST",
                        data: {
                            applicationId: data,
                            state: "已拒绝"
                        },
                        success: function(data) {
                            alert("修改成功！！！");
                            loadMainP();
                        },
                        error:function(){
                            alert("拒绝失败!!!");
                        }
                    })
                })
            }
        })
        .state('seeAllStayRecord', {
            url: '/seeAllStayRecord',
            templateUrl: 'tpls/roomMS/admin-seeAllStayRecord.html',
            controller: function($rootScope) {
                $(".searchFBtn").on("click", function() {
                    /*if ($("#name").val() == "" && $("#number").val() == "") {
                        alert("请输入姓名或学号！！！");
                    } else {
                        loadSeeAllStayRecord()
                    }*/
                    loadSeeAllStayRecord()
                })
                loadSeeAllStayRecord();

                function loadSeeAllStayRecord() {
                    /*$.ajax({
                        url: "../application_queryAllApplication.action",
                        type: "POST",
                        data: {
                            reason: $("#name").val() + " " + $("#number").val(),
                            state: "已同意 已拒绝"
                        },
                        success: function(data) {
                            //alert(data);
                            seeAllStayRecord(data);
                        }
                    })*/
                    $rootScope.paging("ul.pagination", {
                        numUrl: "../application_queryAllApplicationNumber.action",
                        dataUrl: "../application_queryAllApplication.action",
                        data: {
                            reason: $("#name").val() + " " + $("#number").val(),
                            state: "已同意 已拒绝"
                        }
                    }, seeAllStayRecord);
                }

                function seeAllStayRecord(obj) {
                    $(".seeAllStayRecordTbody").html(""); //清空
                    for (var i = 0; i < obj.length; i++) {
                        var tr = document.createElement("tr");
                        //-记录id  列
                        var td_recordId = document.createElement("td");
                        $(td_recordId).attr("name", "recordId");
                        //TODO--设定<td name="recordId"> 标签的 数值
                        $(td_recordId).html(obj[i].applicationId);
                        tr.appendChild(td_recordId);

                        //-学生姓名 列
                        var td_stuName = document.createElement("td");
                        $(td_stuName).attr("name", "stuName");
                        //TODO--设定<td name="stuName"> 标签的 数值
                        $(td_stuName).html(obj[i].student.name);
                        tr.appendChild(td_stuName);

                        //-学生学号 列
                        var td_stuNum = document.createElement("td");
                        $(td_stuNum).attr("name", "stuNum");
                        //TODO--设定<td name="stuNum"> 标签的 数值
                        $(td_stuNum).html(obj[i].student.studentNumber);
                        tr.appendChild(td_stuNum);

                        //-开始时间 列
                        var td_staTime = document.createElement("td");
                        $(td_staTime).attr("name", "staTime");
                        //TODO--设定<td name="staTime"> 标签的 数值
                        $(td_staTime).html(obj[i].beginDate);
                        tr.appendChild(td_staTime);

                        //-结束时间 列
                        var td_endTime = document.createElement("td");
                        $(td_endTime).attr("name", "endTime");
                        //TODO--设定<td name="endTime"> 标签的 数值
                        $(td_endTime).html(obj[i].endDate);
                        tr.appendChild(td_endTime);

                        //-详细原因 列
                        var td_details = document.createElement("td");
                        $(td_details).attr("name", "details");
                        //TODO--设定<td name="details"> 标签的 数值
                        $(td_details).html(obj[i].reason);
                        tr.appendChild(td_details);

                        //-状态 列
                        var td_state = document.createElement("td");
                        $(td_state).attr("name", "state");
                        //TODO--设定<td name="state"> 标签的 数值
                        $(td_state).html(obj[i].state);
                        tr.appendChild(td_state);

                        $(".seeAllStayRecordTbody").append(tr);
                    }
                }
            }
        });

});
routerApp.run(function($rootScope) {
    //---通用方法---

    //---预加载楼栋
    $rootScope.loadFloor = function(parentElement) {
            $.ajax({
                url: "../building_queryBuilding.action",
                type: "GET",
                success: function(data) {
                    //                  var strs = new Array();
                    //                    strs = data.split(" ");
                    //                    for (i = 0; i < strs.length; i++) {
                    //                        $("#building").append(
                    //                            "<option value='" + strs[i] + "'>" + strs[i] + "</option>");
                    //                    }
                    var obj = JSON.parse(data);
                    for (var i = 0; i < obj.length; i++) {
                        $("#building").append(
                            "<option value='" + obj[i].buildingId + "'>" + obj[i].buildingName + "</option>");
                    }
                }
            })
        }
        //---预加载宿舍号
    $rootScope.loadRoomNum = function(parentElement) {
        $("#building").on("change", function() {
            var $building = $("#building option:selected");
            $("#room option").remove();
            $("#room").append("<option value=''>请选择</option>")
            if ($building.val() != "") {
                $.ajax({
                    url: "../room_queryRooms.action",
                    type: "POST",
                    data: {
                        roomNumber: $building.val()
                    },
                    // dataType:"JSON",
                    success: function(data) {
                        var obj = JSON.parse(data);
                        for (var i = 0; i < obj.length; i++) {
                            $("#room").append(
                                "<option value='" + obj[i].id + "'>" + obj[i].value + "</option>");
                        }
                    },
                    error: function(e1, e2) {
                        alert([e1, e2]);
                    }
                })
            }
        })
    }
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
