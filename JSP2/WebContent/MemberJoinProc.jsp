<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h2>회원 정보 보기</h2>
		<%
			request.setCharacterEncoding("UTF-8");
		%>
		
		<!-- request로 넘어온 데이터를 자바 빈즈랑 맵핑 시켜주는 useBean  -->
		<!-- 객체생성 MemberBean mbean = new MemberBean(); 과 같음 -->
		<jsp:useBean id="mbean" class="bean.MemberBean">
			<jsp:setProperty name="mbean" property="*" /><!-- * = 자동으로 모든걸 매핑하라  -->
		</jsp:useBean><!-- jsp내용을 자바빈 클래스 (MemberBean의미) 에 데이터를 매핑(넣어준다.) -->
		
		<h2>당신의 아이디는 <jsp:getProperty property="id" name="mbean" /><h2>
		<h2>당신의 이메일은 <jsp:getProperty property="email" name="mbean" /><h2>
		<h2>당신의 전화번호는 <jsp:getProperty property="tel" name="mbean" /><h2>
		<h2>당신의 주소는 <jsp:getProperty property="address" name="mbean" /><h2>
		
		<%= mbean.getAddress() %>
	</div>
</body>
</html>