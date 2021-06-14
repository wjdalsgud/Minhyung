import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class InsertSeat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null; // DB연결된 상태(세션)을 담은 객체
	    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
	    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
	    String sql="insert into 피시방좌석 values(?,?,?)";
	    String select="select ID from 회원";
		String user = "minhyung"; 
        String pw1 = "1234";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";	                

        try {
        	  Class.forName("oracle.jdbc.driver.OracleDriver");  
        	  conn = DriverManager.getConnection(url, user, pw1);	    
            // SQL 문장을 만들고 만약 문장이 질의어(SELECT문)라면
            // 그 결과를 담을 ResulSet 객체를 준비한 후 실행시킨다.
            String quary = "SELECT ID,PW,PHONE FROM 관리자";
            System.out.println("============================================");
            Scanner sc= new Scanner(System.in);
            String a=sc.nextLine();
            if(a.equals("select")) {

                pstm = conn.prepareStatement(quary);
                rs = pstm.executeQuery();
            while(rs.next()){
                String id = rs.getString("ID");
                String pw = rs.getString("PW");
                String phone = rs.getString("PHONE");
                String result = id+" "+pw+" "+ phone;
                System.out.println(result);
            }
            }
            else if(a.equals("insert")){
            	String SQL="insert into 피시방좌석 values(?,?,?)";
                pstm = conn.prepareStatement(SQL);
                for(int i=1;i<1001;i++) {
            	pstm.setInt(1, 1); 
            	pstm.setInt(2, i); 
            	pstm.setInt(3, 0);
            	int r= pstm.executeUpdate();
                }
            }

        } catch (Exception sqle) {
            System.out.println("SELECT문에서 예외 발생");
            sqle.printStackTrace();

        }finally{
            // DB 연결을 종료한다.
            try{
                if ( rs != null ){rs.close();}   
                if ( pstm != null ){pstm.close();}   
                if ( conn != null ){conn.close(); }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }

        }

}
	}
