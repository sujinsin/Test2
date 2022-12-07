<%@page import="model.MemberDAO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
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
			texthobby += hobby[i] + ", ";
		else
			texthobby += hobby[i];
	}
%>

	<!-- useBean을 이용하여 한꺼번에 데이터를 받아옴  -->
	
	<jsp:useBean id="mbean" class="model.MemberBean">
		<jsp:setProperty name="mbean" property="*" /><!-- 맵핑 시키시오 -->
	</jsp:useBean>
	<%
		mbean.setHobby(texthobby); //기존 취미는 주소번지가 저장되기에 위에 배열의 내용을 하나의 스트링으로 저장한 변수를 다시 입력해주면 됨. 아니면 하비가 이상하ㅡㄴ 값으로 들어감. 
	
		// 데이터 베이스 클래스 객체 생성 
		
		MemberDAO mDAO = new MemberDAO();
		int result = mDAO.insertMember(mbean);
		
		
		if(result > 0) {
			out.write(" 회원가입이 완료 되었습니다. ");
		}else {
			out.write(" 회원가입에 실패하셨습니다. ");
		}
	%>
	
</body>
</html>