<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
	<c:choose>
		<c:when test='${msg=="addMember"}'>
			<script>
				window.onload = function() {
					alert("회원을 등록했습니다.");
				}
			</script>
		</c:when>
		<c:when test='${msg=="modified"}'>
			<script>
				window.onload = function() {
					alert("회원 정보를 수정했습니다.");
				}
			</script>
		</c:when>
		<c:when test='${msg=="deleted"}'>
			<script>
				window.onload = function() {
					alert("회원 정보를 삭제했습니다.");
				}
			</script>
		</c:when>
	</c:choose>
	<meta charset="UTF-8">
	<title>회원 정보 출력창</title>
	<style>
		.cls1 {
		font-size : 40px;
		text-align : center;
		}
		
		.cls2 {
		font-size : 20px;
		text-align : center;
		}
	</style>
</head>
<body>
	<p class="cls1">회원정보</p>
	<table align="center" border="1">
		<tr align = "center" bgcolor="lightgreen">
			<td width="7%"><b>아이디</b></td>
			<td width="7%"><b>비밀번호</b></td>
			<td width="7%"><b>이름</b></td>
			<td width="7%"><b>이메일</b></td>
			<td width="7%"><b>가입일</b></td>
			<td width="7%"><b>수정</b></td>
			<td width="7%"><b>삭제</b></td>
		</tr>
	</table>

</body>
</html>
