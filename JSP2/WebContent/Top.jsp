<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- TOP -->
	<table width="800">
		<tr height="100">
			<!-- 로고이미지 -->
			<td colspan="2" align="center">
				<img alt="로고" src="img/logo.jpg" width="200" height="70" />
			</td>
			<td colspan="4" align="center">
				<font size="10">낭만 캠핑</font>
			</td>
		</tr>
		<tr height="50">
			<td width="130" align="center"> 텐트 </td>
			<td width="130" align="center"> 의자 </td>
			<td width="130" align="center"> 식기류 </td>
			<td width="130" align="center"> 침낭 </td>
			<td width="130" align="center"> 테이블 </td>
			<td width="130" align="center"> 화롯불 </td>
			<td width="130" align="center"> <%= request.getParameter("id") %> </td>
		</tr>
	</table>
</body>
</html>