<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>login</title>
    <link rel="stylesheet" href="<%=path %>/css/loginCss.css">
    <link rel="stylesheet" href="<%=path %>/css/moveYun.css">
    <script src="<%=path %>/js/jquery.min.js"></script>
    <script src="<%=path %>/js/jquery.validate.min.js"></script>
    <script src="<%=path %>/js/login-js/loginValidate.js"></script>
    <script src="<%=path %>/js/login-js/loginDrag.js"></script>
    <!-- <script src="<%=path %>/js/login-js/loginDongHua.js"></script> -->
    
</head>

<body>
    <img id="yunduo" src="images/yunduo.png" onselectstart="return false" ondragstart="return false;" oncontextmenu="return false;" >
    <div class="loginPanel" id="loginPanel">
        <div class="login_logo_web"></div>
        <form id="loginForm" method="post"  action="login_login.action">
            <p>
                <label for="user">用户名:</label>
                <input id="user" name="username" type="text" required minlength="3" value="请填写帐号" onFocus="if (value =='请填写帐号'){value =''}" onBlur="if (value ==''){value='请填写帐号';}">
            </p>
            <p>
                <label for="password">密码:</label>
                <input id="password" type="password" maxlength="12" name="password" required minlength="5">
                <div style="position: relative; margin-left: 80px; color: red"><s:actionmessage/></div>
            </p>
            <p>
                <label for="persontype">类型:</label>
                <select name="persontype" id="persontype">
                    <option value="staff" checked="checked">学 生</option>
                    <option value="administrator">管理员</option>
                    <option value="teacher">教师</option>
                </select>
            </p>
            <input id="submit" class="btn" type="submit" value="">
        </form>
    </div>
</body>

</html>
