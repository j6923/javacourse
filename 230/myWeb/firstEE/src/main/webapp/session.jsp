<%@page import = "java.text.SimpleDateFormat" %>
<%@page import ="java.util.Date"%>

<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>session.jsp</title>
</head>
<body>
技记ID: <%=session.getId() %><br>
技记 IS NEW : <%=session.isNew() %><br>
技记 弥辆荤侩矫埃: <%=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(session.getLastAccessedTime())) %>
</body>
</html>