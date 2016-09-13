<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, 
                                     user-scalable=no" ">
	<title>新生入学管理系统</title>
	<link defer rel="stylesheet " href="<%=path %>/css/reset.css ">
	<link defer rel="stylesheet " href="<%=path %>/css/bootstrap.min.css ">
	<link defer rel="stylesheet " href="<%=path %>/css/nav_css.css "/>
	<link defer rel="stylesheet" href="<%=path %>/css/systemCSS/commonCSS.css">	
	<link defer rel="stylesheet " type="text/css " href="<%=path %>/css/systemCSS/comeMS.css ">
	<script src="<%=path %>/js/angular-1.3.0.js "></script>
    <script src="<%=path %>/js/angular-ui-router.js "></script>
	<script defer src="<%=path %>/js/jquery.min.js "></script>
	<script defer src="<%=path %>/js/router-System/student-ComeMS.js "></script>
	<script defer src="<%=path %>/js/nav_js.js "></script>
</head>
<body>
<div class="page_nav ">
	<p class="page_title ">新生入学管理系统</p>
		<ul class="right_ul ">
			<li id="user_li ">
			<a>
				<span class="user_stuid "><s:property value="#session.user.studentNumber"/></span>
				<span class="user_name "><s:property value="#session.user.name"/></span>
				<span id="icon_btn"></span>
			</a>
				
			</li>
			<ul class="hide_ul ">
				<li><a class="toSelSys " href="../student_chooseSystem.action">选择其他系统</a></li>
				<li><a class="toSelSys" href="../login_logoff.action">注销</a></li>
			</ul>
		</ul>	
</div>
<br>
<div ng-app="routerApp" class="container ">
	<div class="col-md-3 ">
		<ul  class="nav_ul ">
    		<li class="nav_li "><a ui-sref="home ">新生须知</a></li>
    		<li class="nav_li "><a class="unfold_ul ">选择宿舍<i></i></a>
				<ul class="nav_hide_ul ">
					<li><a ui-sref="selectBed ">选择宿舍</a></li>
					<li><a ui-sref="selectResult ">选择结果</a></li>
				</ul>
    		</li>
    		<li class="nav_li "><a class="unfold_ul ">网上交费<i></i></a>
				<ul class="nav_hide_ul ">
					<li><a ui-sref="allExpenses ">各项费用</a></li>
					<li><a ui-sref="paying ">交费</a></li>
					<li><a ui-sref="bill ">收费单</a></li>
				</ul>
    		</li>
    		<li class="nav_li "><a ui-sref="printForm ">打印报道单</a></li>
    		<!-- <li class="nav_li "><a class="unfold_ul ">体检医保<i></i></a>
				<ul class="nav_hide_ul ">
					<li><a ui-sref="appointment ">体检预约</a></li>
					<li><a ui-sref="healthCare ">医保办理</a></li>
				</ul>
    		</li> -->
		</ul>
	</div>
	<div class="col-md-9 ">
		<div ui-view=" " class="right_context ">
		</div>
	</div>
</div>

</body>
</html>
