var routerApp = angular.module('routerApp', ['ui.router']);
routerApp.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/notice');
    $stateProvider
        .state('notice', {
            url: '/notice',
            templateUrl: 'tpls/chargeMS/admin-notice.html',
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
        .state('setNotice', {
            url: '/setNotice',
            templateUrl: 'tpls/chargeMS/admin-setNotice.html',
            controller: function($scope, $http) {
                $(function() {
                	$.ajax({
                        url: "../notice_attainNotice.action",
                        type: "POST",
                        data: {
                        	title: "charge"
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
                            	title: "charge",
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
        .state('allCost', {
            url: '/allCost',
            templateUrl: 'tpls/chargeMS/admin-allCost.html',
            controller: function($rootScope) {
                //预加载---allCost 表格
                $rootScope.paging("ul#pagination1",{
                    numUrl:"../fee_queryFeeNumber.action",
                    dataUrl:"../fee_queryFee.action",
                    data:{
                        state: "已发布"
                    }
                }, loadAllCost);

                //事件---seeFees
                $(".allCostTbody").on("click", ".seeFeesBtn", function() {

                        if ($(".modInfor").css("display") == "none") {
                            $("#pTable").parent().css("visibility", "hidden");
                            $(".modInfor").show(300);
                        }
                        //预加载---根据缴费项id 加载 详细缴费标准
                        var data = {
                            "costId": $(this).parent().siblings("td[name='costId']").text(),
                            "costName": $(this).parent().siblings("td[name='costName']").text(),
                            "type": $(this).parent().siblings("td[name='classifiedType']").text()
                        }
                        $("#costIdSpan").text($(this).parent().siblings("td[name='costId']").text());
                        $("#costNameSpan").text($(this).parent().siblings("td[name='costName']").text());
                        $("#costTypeSpan").text($(this).parent().siblings("td[name='classifiedType']").text());
                        loadCostFees(data);
                    })
                    //事件---returnBtn 返回到主页面
                $("#returnBtn").on("click", function() {
                    if ($(".modInfor").css("display") == "block") {
                        $(".modInfor").hide(300);
                        $("#pTable").parent().css("visibility", "visible");
                    }
                })

                //方法---预加载allCost
                function loadAllCost(obj) {
                	$(".allCostTbody").html(""); //清零 会报错 无影响
                    for (var i = 0; i < obj.length; i++) {
                        $(".allCostTbody").append('<tr>' 
                        		+ '<td name="costId">' + obj[i].feeId + '</td>' 
                        		+ '<td name="costName">' + obj[i].feeName + '</td>' 
                        		+ '<td name="classifiedType">' + obj[i].type + '</td>' 
                        		+ '<td name="pubDate">' + obj[i].publishDate + '</td>' 
                        		+ '<td name="seeFees">' 
                        		+ '<input class="seeFeesBtn" type="button" value="查看" />' 
                        		+ '</td>' + '</tr>');
                    }
                }

                ///方法---根据缴费项id 加载 详细缴费标准
                function loadCostFees(data) {
                    $("#costNameTitle").html(data.costName);
                    var feeId = data.costId;
                    var types = data.type.substr(1).split("+");
                    /**根据 缴费项划分类型 分为 按学院,专业,班级,年级,学院+年级,专业+年级 
                     * 加载 thead
                     */
                    $(".feesThead").html("");
                    var $tr = $("<tr></tr>")
                    for (var i = 0; i < types.length; i++){
                    	$tr.append("<td>" + types[i] + "</td>");
                    }
                    $tr.append("<td>金额</td>");
                    $(".feesThead").append($tr);

                    $rootScope.paging("ul#pagination2",{
                        numUrl:"../feetype_queryFeetypeNumber.action",
                        dataUrl:"../feetype_queryFeetype.action",
                        data:{
                            feetypeId: feeId
                        }
                    }, loadCostFeesTbody);
                    /*$.ajax({
                        url:"../feetype_queryFeetype.action",
                        type:"POST",
                        data:{
                        	feetypeId: feeId
                        },
                        success:function(data){
                        	//if-success
                        	var obj = JSON.parse(data);
                            //加载完 thead后 加载tbody
                            var data_length = obj.length;
                            $(".feesTbody").html("");
                            for (var i = 0; i < data_length; i++) {
                            	var $tr = $("<tr></tr>")
                            	$tr.append("<td>" + obj[i].id + "</td>");
                            	if (types.length > 1){
                            		$tr.append("<td>" + obj[i].type + "</td>");
                            	}
                            	$tr.append("<td>" + obj[i].value + "</td>");
                                $(".feesTbody").append($tr);
                            }
                        }
                    })*/
                }
                function loadCostFeesTbody(obj){
                    $(".feesTbody").html("");
                    var types = $("#costTypeSpan").text().substr(1).split("+");
                    for (var i = 0; i <obj.length; i++) {
                        var $tr = $("<tr></tr>")
                        $tr.append("<td>" + obj[i].id + "</td>");
                        if (types.length > 1){
                            $tr.append("<td>" + obj[i].type + "</td>");
                        }
                        $tr.append("<td>" + obj[i].value + "</td>");
                        $(".feesTbody").append($tr);
                    }
                }
            }
        })
        .state('addCost', {
            url: '/addCost',
            templateUrl: 'tpls/chargeMS/admin-addCost.html',
            controller: function() {
                //mainView start
            	$("#costNameInput").on("blur", function(){
            		if ($("#costNameInput").val()) {
            			$.ajax({
                			url:"../fee_validateFee.action",
                			type:"POST",
                			data:{
                				feeName: $(this).val()
                			},
                			success:function(data){
                				if (data != ""){
                					alert(data);
                					$("#costNameInput").val("");
                					$("span.error[name='costNameError']").html("");
                				}
                			}
                		})
            		}
            	})
                
                $("#addCostSubmit").on("click", function() {
                    if ($("#costNameInput").val()) {
                        var selfData = {
                            "name": $("#costNameInput").val(),
                            "type": $("#costTypeSelect option:selected").text()
                        }
                        //submit
                       $.ajax({
                           url:"../fee_addFee.action",
                           type:"POST",
                           data:{
                           	feeName: selfData.name,
                           	type: selfData.type
                           },
                           success:function(data){
                        	   alert("添加成功！！！");
                        	   window.location.reload(true);
                           }
                       })
                    } else {
                    	$("span.error[name='costNameError']").html("不能为空！！！");
                    }
                })
                //mainView end
                
            }
        })
        .state('delCost', {
            url: '/delCost',
            templateUrl: 'tpls/chargeMS/admin-delCost.html',
            controller: function($rootScope) {
                loadDelCost();

                //事件---delCostBtn
                $(".delCostTbody").on("click", ".delCostBtn", function() {
                        $.ajax({
                            url:"../fee_deleteFee.action",
                            type:"POST",
                            data:{
                            	feeId: $(this).parent().siblings("td[name='costId']").text()
                            },
                            success:function(data){
                            	alert("删除成功！！！");
                            	loadDelCost();
                            },
                            error:function(e1,e2){
                                alert("删除失败!!!");
                            }
                        })
                    })
                    //方法---预加载 主页面
                function loadDelCost() {
                    /*$.ajax({
                        url:"../fee_queryFee.action",
                        type:"POST",
                        data:{
                        	state: "未发布"
                        },
                        success:function(data){
                        	//if-success
                        	var obj = JSON.parse(data);
                            var data_length = obj.length;
                            for (var i = 0; i < data_length; i++) {
                                $(".delCostTbody").append('<tr>' 
                                		+ '<td name="costId">' + obj[i].id + '</td>' 
                                		+ '<td name="costName">' + obj[i].feeName + '</td>' 
                                		+ '<td name="costType">' + obj[i].type + '</td>' 
                                		+ '<td>' 
                                		+ '<input class="delCostBtn" type="button" value="删除" />' 
                                		+ '</td>' + '</tr>');
                            }
                        }
                    })*/
                    $rootScope.paging("ul.pagination",{
                        numUrl:"../fee_queryFeeNumber.action",
                        dataUrl:"../fee_queryFee.action",
                        data:{
                            state: "未发布"
                        }
                    }, loadDelCostTbody);
                }
                function loadDelCostTbody(obj) {
                    $(".delCostTbody").html(""); //清零 会报错 无影响
                    for (var i = 0; i < obj.length; i++) {
                        $(".delCostTbody").append('<tr>' 
                                + '<td name="costId">' + obj[i].feeId + '</td>' 
                                + '<td name="costName">' + obj[i].feeName + '</td>' 
                                + '<td name="costType">' + obj[i].type + '</td>' 
                                + '<td>' 
                                + '<input class="delCostBtn" type="button" value="删除" />' 
                                + '</td>' + '</tr>');
                    }
                }
            }
        })
        .state('pubCost', {
            url: '/pubCost',
            templateUrl: 'tpls/chargeMS/admin-pubCost.html',
            controller: function($rootScope) {
                //预加载---allCost 表格
                loadToPubCost();

                //事件---seeFees
                $(".allCostTbody").on("click", ".seeFeesBtn", function() {
                    if ($(".modInfor").css("display") == "none") {
                        $("#pTable").parent().css("visibility", "hidden");
                        $(".modInfor").show(300);
                    }
                    //预加载---根据缴费项id 加载 详细缴费标准
                    var data = {
                        "costId": $(this).parent().siblings("td[name='costId']").text(),
                        "costName": $(this).parent().siblings("td[name='costName']").text(),
                        "costType":$(this).parent().siblings("td[name='costType']").text()
                    }
                    //console.log(data);
                    loadCostFees(data);
                })
                //事件---subFeesBtn
                $(".allCostTbody").on("click", ".pubFeesBtn", function() {
                    /*发布缴费项*/
                    $.ajax({
                        url:"../fee_updateFee.action",
                        type:"POST",
                        data:{
                        	feeId: $(this).parent().siblings("td[name='costId']").text()
                        },
                        success:function(data){
                        	alert("发布成功！！！");
                        	loadToPubCost();
                        }
                    })
                })
                //事件---returnBtn 返回到主页面
                $("#returnBtn").on("click", function() {
                    if ($(".modInfor").css("display") == "block") {
                        $(".modInfor").hide(300);
                        $("#pTable").parent().css("visibility", "visible");
                    }
                    $(".addStandardDiv").hide(300);
                })

                //方法---预加载allCost
                function loadToPubCost() {
                	/*$(parentElement).html(""); //清零 会报错 无影响
                    $.ajax({
                        url:"../fee_queryFee.action",
                        type:"POST",
                        data:{
                        	state: "未发布"
                        },
                        success:function(data){
                        	//if-success
                        	var obj = JSON.parse(data);
                            var data_length = obj.length;
                            for (var i = 0; i < data_length; i++) {
                                $(parentElement).append('<tr>' 
                                		+ '<td name="costId">' + obj[i].id + '</td>' 
                                		+ '<td name="costName">' + obj[i].feeName + '</td>' 
                                		+ '<td name="costType">' + obj[i].type + '</td>'
                                		+ '<td name="seeFees">' 
                                        + '<input class="seeFeesBtn" type="button" value="查看" />' 
                                        + '</td>' 
                                        + '<td name="pubFees">' 
                                        + '<input class="pubFeesBtn" type="button" value="发布" />' 
                                        + '</td>'
                                        + '</tr>');
                            }
                        }
                    })*/
                    $rootScope.paging("ul#pagination1",{
                        numUrl:"../fee_queryFeeNumber.action",
                        dataUrl:"../fee_queryFee.action",
                        data:{
                            state: "未发布"
                        }
                    }, loadToPubCostTbody);
                }
                function loadToPubCostTbody(obj) {
                    $(".allCostTbody").html(""); //清零 会报错 无影响
                    for (var i = 0; i < obj.length; i++) {
                        $(".allCostTbody").append('<tr>' 
                                + '<td name="costId">' + obj[i].feeId + '</td>' 
                                + '<td name="costName">' + obj[i].feeName + '</td>' 
                                + '<td name="costType">' + obj[i].type + '</td>'
                                + '<td name="seeFees">' 
                                + '<input class="seeFeesBtn" type="button" value="查看" />' 
                                + '</td>' 
                                + '<td name="pubFees">' 
                                + '<input class="pubFeesBtn" type="button" value="发布" />' 
                                + '</td>'
                                + '</tr>');
                    }
                }

                ///方法---根据缴费项id 加载 详细缴费标准
                function loadCostFees(data) {
                    $("#costIdSpan").html(data.costId);
                    $("#costNameSpan").html(data.costName);
                    $("#costTypeSpan").html(data.costType);
                    var feeId = data.costId;
                    var types = data.costType.substr(1).split("+");
                    /**根据 缴费项划分类型 分为 按学院,专业,班级,年级,学院+年级,专业+年级 
                     * 加载 thead
                     */
                    $(".feesThead").html("");
                    var $tr = $("<tr></tr>")
                    for (var i = 0; i < types.length; i++){
                    	$tr.append("<td>" + types[i] + "</td>");
                    }
                    $tr.append("<td>金额</td>");
                    $(".feesThead").append($tr);
                    /*$.ajax({
                        url:"../feetype_queryFeetype.action",
                        type:"POST",
                        data:{
                        	feetypeId: feeId,
                        },
                        success:function(data){
                        	//if-success
                        	var obj = JSON.parse(data);
                            //加载完 thead后 加载tbody 
                            var data_length = obj.length;
                            $(".feesTbody").html("");
                            for (var i = 0; i < data_length; i++) {
                            	var $tr = $("<tr></tr>")
                            	$tr.append("<td>" + obj[i].id + "</td>");
                            	if (types.length > 1){
                            		$tr.append("<td>" + obj[i].type + "</td>");
                            	}
                            	$tr.append("<td>" + obj[i].value + "</td>");
                                $(".feesTbody").append($tr);
                            }
                        }
                    })*/
                    $rootScope.paging("ul#pagination2",{
                        numUrl:"../feetype_queryFeetypeNumber.action",
                        dataUrl:"../feetype_queryFeetype.action",
                        data:{
                            feetypeId: feeId,
                        }
                    }, loadCostFeesTbody);
                }
                function loadCostFeesTbody(obj) {
                    var types = $("#costTypeSpan").html().substr(1).split("+");
                    $(".feesTbody").html("");
                    for (var i = 0; i < obj.length; i++) {
                        var $tr = $("<tr></tr>")
                        $tr.append("<td>" + obj[i].id + "</td>");
                        if (types.length > 1){
                            $tr.append("<td>" + obj[i].type + "</td>");
                        }
                        $tr.append("<td>" + obj[i].value + "</td>");
                        $(".feesTbody").append($tr);
                    }
                }


                //制定标准 start
                //添加按钮--弹出添加标准页面
                $("#addStandardBtn").on("click",function(){
                    //获取缴费项的分类类型
                    //根据根类类型将 对应的 li 显示出来
                    $(".addStandardDiv li.hideLi").css("display","none");
                    var costType=$("#costTypeSpan").text();
                    var types = costType.substr(1).split("+");
                    if($.inArray("年级", types) != -1){
                        $("li.nianjiLi").css("display","block");
                    }
                    if($.inArray("学院", types) != -1){
                        $("li.xueyuanLi").css("display","block");
                    }
                    if($.inArray("专业", types) != -1){
                        $("li.zhuanyeLi").css("display","block");
                    }
                    if($.inArray("班级", types) != -1){
                        $("li.banjiLi").css("display","block");
                    }
                    $(".addStandardDiv").show(300);
                    $("#xueyuanSelect option:first").prop("selected", "selected");
                    $("#zhuanyeSelect option:first").prop("selected", "selected");
                    $("#banjiSelect option:first").prop("selected", "selected");
                    $("#nianjiSelect option:first").prop("selected", "selected");
                })
                //
                $("#addCostStandarSubmit").on("click",function(){
                    var ok=true;
                    var costType=$("#costTypeSpan").text();
                    if(!$("#costMoneyInput").val()){
                        $("#costMoneyInput").siblings("span.error").text("非空！");
                        ok=false;
                    }
                    if(ok){
                        $.ajax({
                            url:"../feetype_addFeetype.action",
                            type:"POST",
                            data:{
                            	feetypeId: $("#costIdSpan").html(),
                            	grade: $("#nianjiSelect").val(),
                            	collegeId: $("#xueyuanSelect").val(),
                            	specialtyId: $("#zhuanyeSelect").val(),
                            	studentclassId: $("#banjiSelect").val(),
                            	value: $("#costMoneyInput").val()
                            },
                            success:function(data){
                            	if (data == ""){
                            		//if-success
                                    $(".addStandardDiv").hide(300);
                                    //reload costFees 页面
                                    var data1 = {
                                        "costId": $("#costIdSpan").html(),
                                        "costName": $("#costNameSpan").html(),
                                        "costType":$("#costTypeSpan").html()
                                    }
                                    loadCostFees(data1);
                            	} else {
                            		alert(data);
                            	}
                            	$("#costMoneyInput").val("");
                            }
                        })
                    }
                })
                //
                $("#canselCostStandarSubmit").on("click",function(){
                    $(".addStandardDiv").hide(300);
                })
                //金额为整数
                //事件---只能输入数字
                $(".onlyNum").on("keyup", function() {
                    $(this).val($(this).val().replace(/[^0-9]/g, ''));
                })
                $(".onlyNum").on("afterpaste", function() {
                    $(this).val($(this).val().replace(/[^0-9]/g, ''));
                })
                
                //预加载学院下拉菜单
                $.ajax({
                    url: "../college_queryCollege.action",
                    type: "GET",
                    success: function(data) {
                        $("#xueyuanSelect").html("");
                        var obj = JSON.parse(data);
                        for (var i in obj){
                        	var option = document.createElement("option");
                            $(option).val(obj[i].collegeId);
                            $(option).text(obj[i].collegeName);
                            $(option).appendTo($("#xueyuanSelect")); 
                        }
                    }
                });
                
                //预加载专业下拉菜单
                $("#zhuanyeSelect").html("");
                $.ajax({
                    url: "../specialty_queryAllSpecialty.action",
                    type: "POST", //需改为POST,传数据
                    success: function(data) {
                    	var obj = JSON.parse(data);
                        for (var i in obj){
                        	var option = document.createElement("option");
                            $(option).val(obj[i].specialtyId);
                            $(option).text(obj[i].specialtyName);
                            $(option).appendTo($("#zhuanyeSelect")); 
                        }
                    }
                })
                
                //预加载班级下拉菜单
                $("#banjiSelect").html("");
                $.ajax({
                	url: "../studentclass_queryAllClass.action",
                	type: "POST", //需改为POST,传数据
                	success: function(data) {
                		var obj = JSON.parse(data);
                		for (var i in obj){
                			var option = document.createElement("option");
                			$(option).val(obj[i].classId);
                			$(option).text(obj[i].classNumber);
                			$(option).appendTo($("#banjiSelect")); 
                		}
                	}
                })
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
                    console.log(nownum);
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
                    console.log(nownum);
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
                //console.log(num+"页")
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
                    console.log(data);
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
