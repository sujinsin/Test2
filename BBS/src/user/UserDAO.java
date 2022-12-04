package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO { //������ ���� ��ü 
	   private Connection conn;
	   private PreparedStatement pstmt;
	   private ResultSet rs;

       public UserDAO () {
        	
	        try {
	            String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
	            String DB_USER = "User1";
	            String DB_PASSWORD = "User1";

	            // ����̹��� �ε��Ѵ�.
	            Class.forName("oracle.jdbc.driver.OracleDriver");

	            // �����ͺ��̽��� ������ �����Ѵ�.
	            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        }

        public int login(String userID, String userPassword) {

	    	String query = "SELECT userPassword FROM User1 WHERE userID = ?";
	        try {
	
	            // Statement�� �����´�.
	            pstmt = conn.prepareStatement(query);
	
	            pstmt.setString(1, userID);
	            // SQL���� �����Ѵ�.
	            rs = pstmt.executeQuery();
	
	            while(rs.next()) { 
	            	if(rs.getString(1).equals(userPassword))
	            		return 1; //�α��μ���
	            	else 
	            		return 0; //��й�ȣ ����ġ
	            }
	            return -1; // ���̵� ����
	        } catch ( Exception e ) {
	            e.printStackTrace();
	        } finally {
	            try {
	                // ResultSet�� �ݴ´�.
	                rs.close();
	                // Statement�� �ݴ´�.
	                pstmt.close();
	                // Connection�� �ݴ´�.
	                conn.close();
	            } catch ( SQLException e ) {}
	        }
	        return -2; // �����ͺ��̽� ����
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
        	return -1; // �����ͺ��̽� ���� 
        	
        }
	} 