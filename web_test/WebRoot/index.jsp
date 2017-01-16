<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=utf-8"%>
<body>
    welcome to Eclipse for J2EE Developer Center,欢迎 哈哈哈！
    <br/>
    <%=new Date(System.currentTimeMillis()).toLocaleString() %>
    <br/>
    <a href="jsevent.html">JavaScript事件测试</a>
</body> 