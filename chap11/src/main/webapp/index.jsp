<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인덱스</title>
</head>
<body>
<% 
/* response.sendRedirect("BoardServlet?command=board_list"); */
response.sendRedirect("http://localhost:8090/chap11/BoardServlet?command=board_list");
%>
</body>
</html>