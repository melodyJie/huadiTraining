<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<title>主页面</title>
	<link defer rel="stylesheet" href="<%=path %>/css/reset.css">
	<link defer rel="stylesheet " href="<%=path %>/css/bootstrap.min.css ">
	<link defer rel="stylesheet" href="<%=path %>/css/nav_css.css"/>
	<script src="<%=path %>/js/angular-1.3.0.js"></script>
    <script src="<%=path %>/js/angular-ui-router.js"></script>
	
	<script defer src="<%=path %>/js/jquery.min.js"></script>
	<script defer src="<%=path %>/js/nav_routing.js"></script>
	<script defer src="<%=path %>/js/nav_js.js"></script>
</head>
<body>
<div class="page_nav">
	<p class="page_title">学校需哦小</p>
</div>
<br>
<div ng-app="routerApp" class="container ">
	<div class="col-md-3 ">
		<ul  class="nav_ul">
    		<li class="nav_li"><a ui-sref="home">home</a></li>
    		<li class="nav_li"><a class="unfold_ul">Profile <i></i></a>
				<ul class="nav_hide_ul">
					<li><a ui-sref="test">1</a></li>
					<li>2</li>
					<li>3</li>
					<li>4</li>
				</ul>
    		</li>
    		<li class="nav_li"><a ui-sref="about">About</a></li>
		</ul>
	</div>
	<div class="col-md-9 ">
		<div ui-view="" class="right_context">
		</div>
	</div>
</div>

</body>
</html>
