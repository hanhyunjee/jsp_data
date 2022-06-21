<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--JSP페이지에성 ㅔ러가 발생하였을 때 보여줄 에러페이지 --%>
<%@page errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
0으로 나누는 것은 불능입니다
<%--강제로 예외를 발생시킴 --%>
<%=2/0%>
</body>
</html>