<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	 <h2>이 페이지는 로그인 정보가 넘어오는 페이지 입니다. </h2>
	<% 
		request.setCharacterEncoding("UTF-8");/* post 방식의 한글처리  */
		
		String id = request.getParameter("id"); /* request 객체에 담ㅅ겨진 사용자 정보중 id만 추출  */
		
		//response.sendRedirect("ResponseRedirect.jsp");// 흐름제어 , 데이터를 가지고 가지 못함. 주소 자체가 변경이 되어 리퀘스트 객체에 있는 값은 넘어가지 못해서 null을 출력함. 
		
		//response.sendRedirect("ResponseRedirect.jsp?id="+id);
		
		
		
		
	%>
	
<%-- 	<jsp:forward page="ResponseRedirect.jsp" /><!-- 포워드 액션태그 :  다음페이지까지 데이터를 보내줌   --> --%>

		<jsp:forward page="ResponseRedirect.jsp" >
			<jsp:param value="mmmm" name="id" />
			<jsp:param value="010-3939-0000" name="phone" />
		</jsp:forward>
		<!-- 리퀘스트의 객체의 데이터를 유지하고 싶거나, 값을  임의로 변경하거나 할때 씀,  param 
			내페이지에서 보여지기 때문에 살아서 해당 페이지를 가지고 옴 
			내 페이지에 request가 살아있기때문에 값이 출력이 되는것 
			포워드는 : responseRedirect를 ResponseProc 에 포함을 시켜서 화면을 출력함. !!!! 
		-->
		
		

	
	<h3>아이디는  = <%= id %></h3>
</body>
</html>