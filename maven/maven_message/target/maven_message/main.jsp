<%@ page import="com.pw.pojo.User" %>
<%@ page import="com.pw.pojo.PageEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>海文在线短信平台</title>
    <link type="text/css" rel="stylesheet" href="css/sms.css" />
    <link type="text/css" rel="stylesheet" href="css/semantic.css" />
    <link type="text/css" rel="stylesheet" href="css/alertify.css" />
    <script src="${pageContext.request.contextPath}/scripts/jquery.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/alertify.js" type="text/javascript"></script>
    <script>
        $(function () {
            $(".del").click(function () {
                var id = this.id; //消息id
                //alert("id="+id);
                alertify.confirm("是否確定刪除?",
                    function () {
                        window.location = "${pageContext.request.contextPath}/msg.do?param=delMsg&id=" + id;
                    },
                    function () {
                        alertify.error('取消');
                }).set('labels', {ok: '确认', cancel: '取消'}).set('reverseButtons', false);
                return false;//阻止超链接的默认行为
            })
        })

        function go(pageNo) {
            window.location("${pageContext.request.contextPath}/msg.do?param=queryAllMsgs&pageSize=5&pageNo="+pageNo);
            return false;
        }
    </script>
</head>
<body>
<div id="main">
    <div class="mainbox">
        <div class="title myMessage png"></div>
        <%@include file="menu.jsp" %>
        <!--错误信息  -->
        <div id="error"></div>
        <!--短消息列表  -->
        <div class="content messageList">
            <ul>
                <%--取出收到的所有短消息,进行遍历--%>
                <c:forEach items="${requestScope.pageEntity.list}" var="msg">
                    <li class=${msg.state==1?"unReaded":""}>
                        <em>${msg.msg_create_date}</em>
                        <em><a href="${pageContext.request.contextPath}/user.do?param=queryAllUsers&sendid=${msg.sendid}">回信</a></em>
                        <em><a id="${msg.id}" href="#" class="del">删除</a></em>
                        <p>
                            <strong>${msg.title}</strong>
                            <a href="${pageContext.request.contextPath}/msg.do?param=showMsgById&id=${msg.id}">
                                <c:choose> <%--when跟在choose里面，不然没有choose会报错--%>
                                    <c:when test="${fn:length(msg.msgcontent)>8}">
                                        ${fn:substring(msg.msgcontent,0 ,8)}...
                                    </c:when>
                                    <c:otherwise>
                                        ${msg.msgcontent}
                                    </c:otherwise>
                                </c:choose>
                            </a>
                        </p>
                    </li>
                </c:forEach>

            </ul>
        </div>
        <!--分页栏 -->
        <div align="center" style="margin-top:10px">
            <a href="javascript:go(1)">首页</a>&nbsp;&nbsp;&nbsp;
            <a href="javascript:go(${requestScope.pageEntity.prePage})">上一页</a>&nbsp;&nbsp;
            <a href="javascript:go(${requestScope.pageEntity.nextPage})">下一页</a>&nbsp;&nbsp;
            ${requestScope.pageEntity.pageNo}/${requestScope.pageEntity.totalPage}&nbsp;&nbsp;
            <a href="javascript:go(${requestScope.pageEntity.totalPage})">最后一页</a>
        </div>
    </div>
</div>
</body>
</html>
