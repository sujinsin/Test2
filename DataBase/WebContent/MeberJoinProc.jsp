<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	
	//취미 부분은 별도로 읽어들여 다시 빈클래스에 저장해야한다. 
	String[] hobby = request.getParameterValues("hobby");
	
	// 배열의 있는 내용을 하나의 스트링으로 저장해야함. 
	
	String texthobby =  "";
	
	for(int i = 0 ; i < hobby.length; i ++) {
		
		if(i < hobby.length - 1)
			texthobby = hobby[i] + ", ";
		else
			texthobby = hobby[i];
	}
%>

	<!-- useBean을 이용하여 한꺼번에 데이터를 받아옴  -->
	
	<jsp:useBean id="mbean" class="model.MemberBean">
		<jsp:setProperty name="mbean" property="*" /><!-- 맵핑 시키시오 -->
	</jsp:useBean>
	<%
		mbean.setHobby(texthobby); //기존 취미는 주소번지가 저장되기에 위에 배열의 내용을 하나의 스트링으로 저장한 변수를 다시 입력해주면 됨. 아니면 하비가 이상하ㅡㄴ 값으로 들어감. 
	%>
	
	<h2>당신의 아이디 = <%= mbean.getId() %></h2>
	<h2>당신의 취미는 = <%= mbean.getHobby() %></h2>
</body>
</html>