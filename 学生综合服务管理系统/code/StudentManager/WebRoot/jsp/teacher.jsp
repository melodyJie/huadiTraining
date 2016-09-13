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
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>教务管理系统-管理员</title>
    <link defer rel="stylesheet" href="<%=path %>/css/reset.css">
    <link defer rel="stylesheet " href="<%=path %>/css/bootstrap.min.css ">
    <link defer rel="stylesheet" href="<%=path %>/css/nav_css.css" />
    <link defer rel="stylesheet" href="<%=path %>/css/systemCSS/commonCSS.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/systemCSS/teacherMS.css">
    <script src="<%=path %>/js/angular-1.3.0.js"></script>
    <script src="<%=path %>/js/angular-ui-router.js"></script>
    <script src="<%=path %>/js/jquery.min.js"></script>
    <script defer src="<%=path %>/js/jedate/jedate.js"></script>
    <script defer src="<%=path %>/js/router-System/teacherMS.js"></script>
    <script defer src="<%=path %>/js/nav_js.js"></script>
</head>

<body>
    <div class="page_nav">
        <p class="page_title">教师专用系统</p>
        <ul class="right_ul">
            <li id="user_li">
                <a>
                    <span class="user_stuid "><s:property value="#session.user.teacherNumber"/></span>
					<span class="user_name "><s:property value="#session.user.teacherName"/></span>
					<span id="icon_btn"></span>
                </a>
            </li>
            <ul class="hide_ul">
                <li><a class="toSelSys" href="../login_logoff.action">注销</a></li>
            </ul>
        </ul>
    </div>
    <br>
    <div ng-app="routerApp" class="container">
        <div class="col-md-3 ">
            <ul class="nav_ul">
                <li class="nav_li"><a ui-sref="seeStudyPlan">查看开课计划</a></li>
                <li class="nav_li "><a class="unfold_ul ">录入成绩<i></i></a>
                    <ul class="nav_hide_ul ">
                        <li class="nav_li"><a ui-sref="entryCompulsoryScore">录入必修成绩</a></li>
                        <li class="nav_li"><a ui-sref="entryElectivesScore">录入选修成绩</a></li>
                    </ul>
                </li>
                <li class="nav_li "><a class="unfold_ul ">查看录入结果<i></i></a>
                    <ul class="nav_hide_ul ">
                        <li class="nav_li"><a ui-sref="seeCompulsoryScore">必修录入结果</a></li>
                        <li class="nav_li"><a ui-sref="seeElectivesScore">选修录入结果</a></li>
                    </ul>
                </li>
                
            </ul>
        </div>
        <div class="col-md-9 ">
            <div ui-view="" class="right_context">
            </div>
        </div>
    </div>
</body>

</html>
