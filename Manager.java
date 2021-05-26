import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Manager extends JFrame{
	Manager(){
	setTitle("관리자 로그인 화면");
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 Container c=getContentPane();
	 JPanel q1= new JPanel();
	 JLabel j=new JLabel("관리자 로그인 화면");
	 q1.add(j);
	 JLabel j1=new JLabel("  아 이 디 ");
	 q1.add(j1);
	 JTextField jt= new JTextField(18);
	 q1.add(jt);
	 q1.add(new JLabel(" 비밀번호"));
	 JTextField jt1=new JTextField(18);
	 q1.add(jt1);
	 JButton b1=new JButton("로그인");
	 b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String id,pwd;
				id=jt.getText();
				pwd=jt1.getText();
				Connection conn = null; // DB연결된 상태(세션)을 담은 객체
			    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
			    PreparedStatement spstm=null;
			    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
			    String select="select ID,PW from 관리자";
				String user = "sys as sysdba"; 
		        String pw = "Gksmf1238";
		        String url = "jdbc:oracle:thin:@localhost:1521:orcl";	 
		        String did=null;
		        String dpw=null;
		        try {
		        Class.forName("oracle.jdbc.driver.OracleDriver");      
		        conn = DriverManager.getConnection(url, user, pw);	       
		    	spstm=conn.prepareStatement(select);
		    	rs=spstm.executeQuery();
		    	while(rs.next()) {
		    		did= rs.getString("ID");
		    		dpw= rs.getString("PW");
		    		if(did.equals(id)&&dpw.equals(pwd)) {
		    			System.out.println("로그인에 성공했습니다");
						setVisible(false);
						new ManagerMain();	
		    			break;
		    		}
		    	}
		    	if(!did.equals(id)||!dpw.equals(pwd)) {
		    		JOptionPane.showMessageDialog(null, "아이디나 비밀번호를 잘못 입력했습니다","Message",JOptionPane.ERROR_MESSAGE);
				}
		         } catch (SQLException be) {
		        	 System.out.println("sql오류");
		        	 be.printStackTrace();
		        }catch(Exception ne) {
		        	System.out.println("오류");
		        }
		        finally{
		            // DB 연결을 종료한다.
		            try{
		                if ( rs != null ){rs.close();}   
		                if ( pstm != null ){pstm.close();}   
		                if ( conn != null ){conn.close(); }
		            }catch(Exception me){
		                throw new RuntimeException(me.getMessage());
		            }
		            
		        }
		}
	 });
	 JButton abcd= new JButton("뒤로가기");
	 abcd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login();	
		}
	 });
	 c.setLayout(new BorderLayout());
	 q1.add(b1);
	 c.add(q1);
	 q1.add(abcd);
	 setSize(300,400);
	 setVisible(true);
	 
}
}
