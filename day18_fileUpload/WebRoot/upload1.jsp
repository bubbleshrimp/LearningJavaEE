<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'upload1.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<form enctype="multipart/form-data"  method="post" action="${pageContext.request.contextPath }/servlet/uploadServlet">
		用户名：<input type="text" name="userName" placeholder="userName" /><br>
		文件一：<input type="file" name="file1" placeholder="file1" /><br>
		文件二：<input type="file" name="file2" placeholder="file2" /><br> 
		<input type="submit" value="提交" />
	</form>
</body>
</html>
