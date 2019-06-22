<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="/static/login/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="/static/login/css/demo.css" />
    <!--必要样式-->
    <link rel="stylesheet" type="text/css" href="/static/login/css/component.css" />
    <script>
        // 判断是登录账号和密码是否为空,无法组织，解决不了
        function check(){
            var username = $("#username").val();
            var password = $("#password").val();
            if(username=="" || password==""){
                alert("账号或密码不能为空！");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <div class="container demo-1">
        <div class="content">
            <div id="large-header" class="large-header">
                <canvas id="demo-canvas"></canvas>
                <div class="logo_box">
                    <h3>欢迎你</h3>
                    <font color="red"><h4 align="center">${msg}</h4></font>
                    <form action="${request.getContextPath()}/login" method="post" onsubmit="return check()" autocomplete="off">
                        <div class="input_outer">
                            <span class="u_user"></span>
                            <input id="username" name="username" class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入账户">
                        </div>
                        <div class="input_outer">
                            <span class="us_uer"></span>
                            <input id="password" name="password" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;" type="password" placeholder="请输入密码">
                        </div>
                        <div class="mb2"><input id="submit" type="submit" class="act-but submit" style="outline: none;color: #FFFFFF;border:0;width: 330px;" value="登录"></div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="/static/login/js/jquery.min.js"></script>
    <script src="/static/login/js/TweenLite.min.js"></script>
    <script src="/static/login/js/EasePack.min.js"></script>
    <script src="/static/login/js/rAF.js"></script>
    <script src="/static/login/js/demo-1.js"></script>
</body>
</html>
