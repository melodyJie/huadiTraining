<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>使用学生综合服务系统</title>
    <link rel="stylesheet" href="<%=path %>/css/css.css">
    <link rel="stylesheet" href="<%=path %>/css/L-animation.css">
    <script defer src="<%=path %>/js/selectSystem-js/L-animation.js"></script>
    <script defer src="<%=path %>/js/jquery.min.js"></script>
</head>

<body>
    <audio id="ballmusic" src="<%=path %>/music/tishi.mp3">
        Your browser does not support the audio element.
    </audio>
    <div class="ballBox clearfix">
        <ul id="list-product" class="pr">
            <li id="li-0" class="pr hidden">
                <a href="jsp/studentComeMS-student.jsp">
                    <div id="L-0" class="L">
                        <p>新生入学系统</p>
                    </div>
                </a>
                <div class="L-shadow"></div>
            </li>
            <li id="li-1" class="pr hidden">
                <a href="jsp/trainingPS-student.jsp">
                    <div id="L-1" class="L">
                        <p>教务管理系统</p>
                    </div>
                </a>
                <div class="L-shadow"></div>
            </li>
            <li id="li-2" class="pr hidden">
                <a href="jsp/roomMS-student.jsp">
                    <div id="L-2" class="L">
                        <p>宿舍管理</p>
                    </div>
                </a>
                <div class="L-shadow"></div>
            </li>
            <li id="li-3" class="pr hidden">
                <a href="jsp/chargeMS-student.jsp">
                    <div id="L-3" class="L">
                        <p>收费管理</p>
                    </div>
                </a>
                <div class="L-shadow"></div>
            </li>
            <li id="li-4" class="pr">
                <div id="L-4" class="L">
                    <p>----系统</p>
                </div>
                <div class="L-shadow"></div>
            </li>
        </ul>
    </div>
</body>

</html>
