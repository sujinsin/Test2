package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO { //데이터 접근 객체 
	   private Connection conn;
	   private PreparedStatement pstmt;
	   private ResultSet rs;

       public UserDAO () {
        	
	        try {
	            String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
	            String DB_USER = "User1";
	            String DB_PASSWORD = "User1";

	            // 드라이버를 로딩한다.
	            Class.forName("oracle.jdbc.driver.OracleDriver");

	            // 데이터베이스의 연결을 설정한다.
	            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        }

        public int login(String userID, String userPassword) {

	    	String query = "SELECT userPassword FROM User1 WHERE userID = ?";
	        try {
	
	            // Statement를 가져온다.
	            pstmt = conn.prepareStatement(query);
	
	            pstmt.setString(1, userID);
	            // SQL문을 실행한다.
	            rs = pstmt.executeQuery();
	
	            while(rs.next()) { 
	            	if(rs.getString(1).equals(userPassword))
	            		return 1; //로그인성공
	            	else 
	            		return 0; //비밀번호 불일치
	            }
	            return -1; // 아이디가 없음
	        } catch ( Exception e ) {
	            e.printStackTrace();
	        } finally {
	            try {
	                // ResultSet를 닫는다.
	                rs.close();
	                // Statement를 닫는다.
	                pstmt.close();
	                // Connection를 닫는다.
	                conn.close();
	            } catch ( SQLException e ) {}
	        }
	        return -2; // 데이터베이스 오류
		}	
        
        
        public int join(User user) {
        	String SQL="INSERT INTO User1 VALUES(?,?,?,?,?)";
        	try {
        		pstmt = conn.prepareStatement(SQL);
        		pstmt.setString(1, user.getUserID());
        		pstmt.setString(2, user.getUserPassword());
        		pstmt.setString(3, user.getUserName());
        		pstmt.setString(4, user.getUserGender());
        		pstmt.setString(5, user.getUserEmail());
        		return pstmt.executeUpdate();
        		
        	}catch(Exception e) {
        		e.printStackTrace();
        	}finally {
        		try {
        			pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        	return -1; // 데이터베이스 오류 
        	
        }
	} 