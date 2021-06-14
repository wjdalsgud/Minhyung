import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date nowDate = new Date(); 
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd"); 
		String NowDate = simpleDateFormat.format(nowDate);     
		Connection conn = null; // DB연결된 상태(세션)을 담은 객체
	    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
	    PreparedStatement pstm1=null;
	    PreparedStatement pstm2=null;
	    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
	    String select="select*from 피시방";
		String user = "minhyung"; 
        String pw = "1234";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";	 
        String newdate=" ";
		try {
			 Class.forName("oracle.jdbc.driver.OracleDriver");      
		        conn = DriverManager.getConnection(url, user, pw);	   
                pstm = conn.prepareStatement(select);
                rs = pstm.executeQuery();
           while(rs.next()){
                String pc = rs.getString("PC");
                int total = rs.getInt("TOTAL");
                newdate = rs.getString("날짜");
            }
        if(!NowDate.equals(newdate)) {
         String SQL="UPDATE 예약좌석 SET SEAT=0";
          String SQL1="UPDATE 피시방좌석 SET 예약유무=0";         
         String SQL2="UPDATE 피시방 SET 날짜="+NowDate+"";  
            pstm = conn.prepareStatement(SQL);            
            pstm1 = conn.prepareStatement(SQL1);
            pstm2 = conn.prepareStatement(SQL2);
        	int r= pstm.executeUpdate();
        	int r1= pstm1.executeUpdate();
        	int r2= pstm2.executeUpdate();
        }

        new Login();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			 try{
	                if ( rs != null ){rs.close();}   
	                if ( pstm != null ){pstm.close();}   
	                if ( pstm1 != null ){pstm.close();}  
	                if ( pstm2 != null ){pstm.close();}   
	                if ( conn != null ){conn.close(); }
	            }catch(Exception me){
	                throw new RuntimeException(me.getMessage());
	            }	
		}
	}
}
