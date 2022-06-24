<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>post방식에서 한글 깨짐 방지</h3>
<form method="post" action="jstlFmt_2.jsp">
	이름 : <input type="text" name="name">
	<input type="submit" value="전송">
</form>
</body>
</html>