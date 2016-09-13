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
                                     user-scalable=no">
	<title>费用管理-管理员</title>
	<link defer rel="stylesheet" href="<%=path %>/css/reset.css">
	<link defer rel="stylesheet " href="<%=path %>/css/bootstrap.min.css ">
	<link defer rel="stylesheet" href="<%=path %>/css/nav_css.css"/>
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/systemCSS/chargeMS.css">
	<link defer rel="stylesheet" href="<%=path %>/css/systemCSS/commonCSS.css">	
	<script src="<%=path %>/js/angular-1.3.0.js"></script>
    <script src="<%=path %>/js/angular-ui-router.js"></script>
	<script defer src="<%=path %>/js/jquery.min.js"></script>
	<script defer src="<%=path %>/js/router-System/admin-chargeMS.js"></script>
	<script defer src="<%=path %>/js/nav_js.js"></script>
</head>
<body>
<div class="page_nav">
	<p class="page_title">费用管理-管理员</p>
		<ul class="right_ul ">
			<li id="user_li">
			<a>
				<span class="user_stuid"><s:property value="#session.user.username"/></span>
                    <span class="user_name"><s:property value="#session.user.managerName"/></span>
                    <span id="icon_btn"></span>
			</a>
				
			</li>
			<ul class="hide_ul ">
				<li><a class="toSelSys" href="../manager_chooseSystem.action">选择其他系统</a></li>
				<li><a class="toSelSys" href="../login_logoff.action">注销</a></li>
			</ul>
		</ul>	
</div>
<br>
<div ng-app="routerApp" class="container ">
	<div class="col-md-3 ">
		<ul  class="nav_ul">
			<li class="nav_li"><a class="unfold_ul ">公告管理<i></i></a>
                <ul class="nav_hide_ul ">
                    <li><a ui-sref="notice">查看公告</a></li>
                    <li><a ui-sref="setNotice">设置公告</a></li>
                </ul>
            </li>
    		<li class="nav_li"><a ui-sref="allCost">查看已发布缴费项</a></li>
			<li class="nav_li"><a ui-sref="addCost">添加缴费项</a></li>
			<li class="nav_li"><a ui-sref="delCost">删除缴费项</a></li>
			<li class="nav_li"><a ui-sref="pubCost">发布缴费项</a></li>
		</ul>
	</div>
	<div class="col-md-9">
		<div ui-view="" class="right_context">
		</div>
	</div>
</div>

</body>
</html>
