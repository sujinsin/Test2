package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//오라클 데이터 베이스에 연결하고 SELECT, INSERT, UPDATE, DELETE 를 실행해주는 클래스다. 

public class MemberDAO {
	
	String id= "JSP2"; //접속아이디
	String pass = "JSP2"; 
	String url = "jdbc:oracle:thin:@localhost:1521:XE"; //접속 URL
	
	Connection con; //데이터 베이스에 접근할 수 있도록 설정 
	PreparedStatement pstmt; //데이터 베이스에서 쿼리를 실행시켜주는 객체 
	ResultSet rs; // 데이터 베이스의 테이블의 결과를 리턴받아 자바에 저장해주는 객체 
	
	// 데이터 베이스에 접근할 수 있도록 도와주는 메소드 
	public void getCon() {
		try {
			// 1. 해당 데이터 베이스를 사용한다고 선언 (클래스를 등록 = 오라클용을 사용)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. 해당 데이터 베이스에 접속 
			con = DriverManager.getConnection(url,id,pass);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int insertMember(MemberBean m) {
		String query = "insert into Member values(?,?,?,?,?,?,?,?)";
		int result = 0;
		try {
			getCon();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPass1());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getTel());
			pstmt.setString(5, m.getHobby());
			pstmt.setString(6, m.getJob());
			pstmt.setString(7, m.getAge());
			pstmt.setString(8, m.getInfo());
			
			result= pstmt.executeUpdate();
			
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(result > 0 ) {
					con.commit();
				}else {
					con.rollback();
				}
				
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return -1; //데이터 오류 
	}
	
}
