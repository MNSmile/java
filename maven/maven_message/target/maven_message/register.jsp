<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>海文 在线短信平台</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/sms.css">
    <script src="${pageContext.request.contextPath}/scripts/jquery.js" type="text/javascript"></script>
    <script>
        var $name, $pwd, $affirm, $email, $error;
        $(function () {
            $name = $("#name");
            $pwd = $("#pwd");
            $affirm = $("#affirm");
            $email = $("#email");
            $error = $("#error");


            //填写用户名时，失去焦点即触发blur事件
            $("#name").blur(function () {

                $.get("lr.do",{
                    "param":"queryByUser",
                    "name":$(this).val()
                },function (data) {
                    if(data=="true") {
                        $("#error").html("此用户名已经注册").css("color","red");
                    } else {
                        $("#error").html("此用户名可用").css("color","green");
                    }
                });
            });

            $("#register").click(function () {
                if(validate()){
                    $.post("lr.do",{
                        "param":"register",
                        "name":$name.val(),
                        "pwd":$pwd.val(),
                        "email":$email.val()
                    },function (data) {
                        $("#error").html(data);
                    })
                }
            });

            //重置
            $("#reset").click(function () {
                $("#name").val("");
                $("#pwd").val("");
                $("#affirm").val("");
                $("#email").val("");
            })
        });

        /**
        *是否验证通过
        * */
        function validate() {
            if ($name.val() == "") {
                $error.css("color", "red").html("用户名不能为空");
                return false;//不用提交表单
            }
            if ($pwd.val() == "") {
                $error.css("color", "red").html("密码不能为空");
                return false;//不用提交表单
            }
            if ($affirm.val() == "") {
                $error.css("color", "red").html("确认密码不能为空");
                return false;//不用提交表单
            }

            if ($pwd.val() != $affirm.val()) {
                $error.css("color", "red").html("两次密码不一致");
                return false;//不用提交表单
            }
            if ($email.val() == "") {
                $error.css("color", "red").html("邮箱不能为空");
                return false;//不用提交表单
            }
            var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
            if (reg.test($email.val()) == false) {
                $error.css("color", "red").html("邮箱格式不正确");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <div id="regTitle" class="png"></div>
    <div id="regForm" class="userForm png">
       <%-- <form action="lr.do?param=register" method="post" id="rForm" onsubmit="return validate()">--%>
            <dl>
                <div id="error" >错误信息</div>
                <dt>用 户 名：</dt>
                <dd>
                    <input type="text" name="name" id="name"/>
                </dd>
                <dt>密 码：</dt>
                <dd>
                    <input type="password" name="pwd" id="pwd"/>
                </dd>
                <dt>确认密码：</dt>
                <dd>
                    <input type="password" name="affirm" id="affirm"/>
                </dd>
                <dt>邮 箱：</dt>
                <dd>
                    <input type="text" name="email" id="email"/>
                </dd>
            </dl>
            <div class="buttons">
                <input class="btn-reg png" type="submit" name="register" value=" " id="register"/>
                <input class="btn-reset png" type="reset" name="reset" value=" " id="reset"/>
            </div>
            <div class="goback">
                <a href="index.jsp" class="png">返回登录页</a>
            </div>
        <%--</form>--%>
    </div>
</body>
</html>
