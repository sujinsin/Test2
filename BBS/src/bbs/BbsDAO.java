package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BbsDAO {
	private Connection conn;
	private ResultSet rs;

    public BbsDAO () {
     	
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
    
    
    public int write(String bbsTitle, String userID, String bbsContent) {
    	PreparedStatement pstmt = null;
    	String SQL = "INSERT INTO BBS VALUES(BID.NEXTVAL,?,?,SYSDATE,?,?)";
   	 try {
			pstmt = conn.prepareStatement(SQL);
         // Statement를 가져온다.
			pstmt.setString(1, bbsTitle);
			pstmt.setString(2, userID);
			pstmt.setString(3, bbsContent);
			pstmt.setInt(4, 1);
         // SQL문을 실행한다.
			int result = pstmt.executeUpdate();
			conn.commit();
			return result;
     } catch ( Exception e ) {
         e.printStackTrace();
         if(conn != null) {
        	 try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
         }
         
     } finally {
    	 try {
    		 if(pstmt != null) {pstmt.close();}
    		 if(conn != null) {conn.close();}
    	 } catch (Exception e){
    		 e.printStackTrace();
    	 }
     }
     return -1; // 데이터베이스 오류
    	
    }
    
    public int getNext() {
    	String SQL = "SELECT bbsID FROM BBS ORDER BY bbsID DESC";
    	try {
    		PreparedStatement pstmt = conn.prepareStatement(SQL);
    		rs = pstmt.executeQuery();
    		if(rs.next()) {
    			return rs.getInt(1) + 1;	
    		}
    		return 1;
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return -1; // 데이터베이스 오류 
    }
    
    public ArrayList<Bbs> getList(int pageNumber) {
    	String SQL ="SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1 AND ROWNUM <= 10 ORDER BY bbsID DESC";
    	ArrayList<Bbs> list = new ArrayList<Bbs>();
    	try {
        	PreparedStatement pstmt = conn.prepareStatement(SQL);
        	pstmt.setInt(1, getNext() - (pageNumber -1) * 10 );
        	rs = pstmt.executeQuery();
        	while(rs.next()) {
        		Bbs bbs = new Bbs();
        		bbs.setBbsID(rs.getInt(1));
        		bbs.setBbsTitle(rs.getString(2));
        		bbs.setUserID(rs.getString(3));
        		bbs.setBbsDate(rs.getString(4));
        		bbs.setBbsContent(rs.getString(5));
        		bbs.setBbsAvailable(rs.getInt(6));
        		list.add(bbs);
        	}  		
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    	return list; //데이터 베이스 오류 
    }
    
    public boolean nextPage(int pageNumber) {
    	String SQL ="SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1";
    	try {
        	PreparedStatement pstmt = conn.prepareStatement(SQL);
        	pstmt.setInt(1, getNext() - (pageNumber -1) * 10 );
        	rs = pstmt.executeQuery();
        	if(rs.next()) {
        		return true;
        	}  		
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    	return false; //데이터 베이스 오류 
    }
    
    public Bbs getBbs(int bbsID) {
    	String SQL ="SELECT * FROM BBS WHERE bbsID = ?";
    	try {
        	PreparedStatement pstmt = conn.prepareStatement(SQL);
        	pstmt.setInt(1, bbsID);
        	rs = pstmt.executeQuery();
        	if(rs.next()) {
        		Bbs bbs = new Bbs();
        		bbs.setBbsID(rs.getInt(1));
        		bbs.setBbsTitle(rs.getString(2));
        		bbs.setUserID(rs.getString(3));
        		bbs.setBbsDate(rs.getString(4));
        		bbs.setBbsContent(rs.getString(5));
        		bbs.setBbsAvailable(rs.getInt(6));
        		return bbs;
        	}  		
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null; //데이터 베이스 오류 
    }
    public int update(int bbsID, String bbsTitle, String bbsContent) {
    	PreparedStatement pstmt = null;
    	String SQL = "UPDATE BBS SET bbsTitle = ?, bbsContent = ? Where bbsID = ?";
	   	try {
				pstmt = conn.prepareStatement(SQL);
	         // Statement를 가져온다.
				pstmt.setString(1, bbsTitle);
				pstmt.setString(2, bbsContent);
				pstmt.setInt(3, bbsID);
				int result = pstmt.executeUpdate();
				conn.commit();
				return result;
	     } catch ( Exception e ) {
	         e.printStackTrace();
	         if(conn != null) {
	        	 try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	         }
	         
	     } finally {
	    	 try {
	    		 if(pstmt != null) {pstmt.close();}
	    		 if(conn != null) {conn.close();}
	    	 } catch (Exception e){
	    		 e.printStackTrace();
	    	 }
	     }
	   	return -1; // 데이터베이스 오류
    	
    }
    
    public int delete(int bbsID) {
    	PreparedStatement pstmt = null;
    	String SQL = "UPDATE BBS SET bbsAvailable = 0 Where bbsID = ?";
	   	try {
				pstmt = conn.prepareStatement(SQL);
	         // Statement를 가져온다.
				pstmt.setInt(1, bbsID);
				int result = pstmt.executeUpdate();
				conn.commit();
				return result;
	     } catch ( Exception e ) {
	         e.printStackTrace();
	         if(conn != null) {
	        	 try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	         }
	         
	     } finally {
	    	 try {
	    		 if(pstmt != null) {pstmt.close();}
	    		 if(conn != null) {conn.close();}
	    	 } catch (Exception e){
	    		 e.printStackTrace();
	    	 }
	     }
	   	return -1; // 데이터베이스 오류
    }
}
