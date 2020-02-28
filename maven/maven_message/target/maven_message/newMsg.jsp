<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>>
<html>
<head>
	<title>海文 在线短信平台</title>
	<link type="text/css" rel="stylesheet" href="css/sms.css" />
	<script src="${pageContext.request.contextPath}/scripts/jquery.js" type="text/javascript"></script>
	<script>

	</script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/msg.do?param=sendMsg" method="post">
		<div id="main">
			<div class="mainbox">
				<%@include file="menu.jsp" %>
				<div class="content">
					<div class="message">
						<div class="tmenu">
							<ul class="clearfix">
								<li>发送给： <select name="toUser">
									<c:choose>
										<c:when test="${requestScope.sendid == null}">
											<c:forEach items="${requestScope.uList}" var="user">
												<c:if test="${requestScope.sendid != user.id}">
													<option value="${user.id}">${user.name}</option>
												</c:if>
											</c:forEach>
										</c:when>
										<c:otherwise><%-- 回信时只显示回信对象的名字 --%>
											<c:forEach items="${requestScope.uList}" var="user">
												<c:if test="${requestScope.sendid == user.id}">
													<option selected="selected" value="${user.id}">${user.name}</option>
												</c:if>
											</c:forEach>
										</c:otherwise>
									</c:choose>>
								</select>
								</li>
								<li>标题：<input type="text" name="title" id="title" /></li>
							</ul>
						</div>
						<div class="view">
							<textarea name="content" id="content"></textarea>
							<div class="send">
								<input type="submit" name="submit" value=" " id="btn_send"/>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
