import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Manage1 extends JFrame{
	Container c;
	JTextArea ta;
	JButton s;
	JButton sa;
	JPanel j= new JPanel();
	Connection conn = null; // DB연결된 상태(세션)을 담은 객체
    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
    String sql="insert into 회원 values(?,?,?)";
    String select="select ID from 회원";
	String user = "sys as sysdba"; 
    String pw1 = "Gksmf1238";
    String url = "jdbc:oracle:thin:@localhost:1521:orcl";	  
    String result[] = new String[1000];
    String result1="";
    int i=0;
	Manage1(){
		
		try {
			
			ta= new JTextArea("\t   회원정보\n",35,35);

      	  Class.forName("oracle.jdbc.driver.OracleDriver");  
      	  conn = DriverManager.getConnection(url, user, pw1);	    
          // SQL 문장을 만들고 만약 문장이 질의어(SELECT문)라면
          // 그 결과를 담을 ResulSet 객체를 준비한 후 실행시킨다.
          String quary = "SELECT ID,PW,PHONE FROM 회원";
              pstm = conn.prepareStatement(quary);
              rs = pstm.executeQuery();
          while(rs.next()){
              String id = rs.getString("ID");
              String pw = rs.getString("PW");
              String phone = rs.getString("PHONE");
             result[i] ="  ID : "+id+"   PW : "+pw+"   PHONE : "+ phone+"\n";
             result1+=result[i];
        
              i++;
          }
		} catch (Exception sqle) {
            System.out.println("SELECT문에서 예외 발생");
            sqle.printStackTrace();
            
        }finally{
            // DB 연결을 종료한다.
            ta.append(result1);
            try{
                if ( rs != null ){rs.close();}   
                if ( pstm != null ){pstm.close();}   
                if ( conn != null ){conn.close(); }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
            
        }
		setTitle("회원관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c=getContentPane();
			
		JButton abcd= new JButton("뒤로가기");
		 abcd.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new ManagerMain();	
			}
		 });
		 
		c.setLayout(new BorderLayout());
		j.add(new JLabel("PC방에 가입한 회원 정보"));
	    j.add(ta);
	    j.add(new JScrollPane(ta));
	    j.add(abcd);
	    c.add(j);
	    setSize(500,1000);
	    setVisible(true);
	}

}
