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
    <title>管理宿舍</title>
    <link defer rel="stylesheet " href="<%=path %>/css/reset.css ">
    <link defer rel="stylesheet " href="<%=path %>/css/bootstrap.min.css ">
    <link defer rel="stylesheet " href="<%=path %>/css/nav_css.css " />
    <link defer rel="stylesheet " type="text/css " href="<%=path %>/css/systemCSS/roomMS.css ">
    <link defer rel="stylesheet" href="<%=path %>/css/systemCSS/commonCSS.css">
    <script src="<%=path %>/js/angular-1.3.0.js "></script>
    <script src="<%=path %>/js/angular-ui-router.js "></script>
    <script defer src="<%=path %>/js/jquery.min.js "></script>
    <script defer src="<%=path %>/js/jedate/jedate.js "></script>
    <script defer src="<%=path %>/js/router-System/admin-roomMS.js "></script>
    <script defer src="<%=path %>/js/nav_js.js "></script>
</head>

<body>
    <div class="page_nav">
        <p class="page_title">宿舍管理系统</p>
        <ul class="right_ul">
            <li id="user_li">
                <a>
                    <span class="user_stuid"><s:property value="#session.user.username"/></span>
                    <span class="user_name"><s:property value="#session.user.managerName"/></span>
                    <span id="icon_btn"></span>
                </a>
            </li>
            <ul class="hide_ul ">
                <li><a class="toSelSys " href="../manager_chooseSystem.action">选择其他系统</a></li>
                <li><a class="toSelSys" href="../login_logoff.action">注销</a></li>
            </ul>
        </ul>
    </div>
    <br>
    <div ng-app="routerApp"  class="container ">
        <div class="col-md-3">
            <ul class="nav_ul">
                <li class="nav_li"><a class="unfold_ul ">公告管理<i></i></a>
                    <ul class="nav_hide_ul ">
                        <li><a ui-sref="notice">查看公告</a></li>
                        <li><a ui-sref="setNotice">设置公告</a></li>
                    </ul>
                </li>
                <li class="nav_li "><a class="unfold_ul ">卫生评分管理<i></i></a>
                    <ul class="nav_hide_ul ">
                        <li><a ui-sref="seeHealthScore">查看卫生评分</a></li>
                        <li><a ui-sref="setHealthScore">设置卫生评分</a></li>
                    </ul>
                </li>
                <li class="nav_li "><a class="unfold_ul ">维修申请记录<i></i></a>
                    <ul class="nav_hide_ul ">
                        <li><a ui-sref="manRepairRecord">处理维修记录</a></li>
                        <li><a ui-sref="seeAllRepairRecord">查看所有记录</a></li>
                    </ul>
                </li>
                <li class="nav_li "><a class="unfold_ul ">留校申请记录<i></i></a>
                    <ul class="nav_hide_ul ">
                        <li><a ui-sref="manStayRecord">处理留校申请</a></li>
                        <li><a ui-sref="seeAllStayRecord">查看所有记录</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="col-md-9 ">
            <div ui-view=" " class="right_context ">
            </div>
        </div>
    </div>
</body>

</html>
