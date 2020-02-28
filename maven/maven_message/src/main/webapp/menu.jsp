<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="menu">
    <span>当前用户：<a href="${pageContext.request.contextPath}/msg.do?param=queryAllMsgs&pageNo=1&pageSize=5">${sessionScope.user.name}</a></span>
    <span><a href="${pageContext.request.contextPath}/user.do?param=queryAllUsers">发短消息</a></span>
    <span><a href="${pageContext.request.contextPath}/user.do?param=logout">退出</a></span>
</div>
