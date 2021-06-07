import java.awt.Container;
import java.awt.FlowLayout;
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

public class Manage3 extends JFrame{

	Manage3(){
		Container c= getContentPane();
		setTitle("회원 수정");
		JPanel a= new JPanel();
		JPanel a1= new JPanel();
		JPanel a2= new JPanel();
		JPanel a3= new JPanel();
		a.setLayout(new FlowLayout());
		a1.setLayout(new FlowLayout());
		a2.setLayout(new FlowLayout());
		a3.setLayout(new FlowLayout());
		JLabel ab=new JLabel("               회원 수정            ");
		JLabel ab1=new JLabel("수정할 회원 아이디를 입력해주세요");
		JLabel ab2= new JLabel("ID");
		JTextField abc= new JTextField(15);
		JButton abcd= new JButton("뒤로가기");
		 abcd.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new ManagerMain();	
			}
		 });
		JButton abcd1=new JButton("회원 수정");
		abcd1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				String id,phone;
				phone=abc.getText();
				Connection conn = null; // DB연결된 상태(세션)을 담은 객체
			    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
			    PreparedStatement spstm=null;
			    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
			    String select="select ID,PHONE from 회원";
				String user = "sys as sysdba"; 
		        String pw = "Gksmf1238";
		        String url = "jdbc:oracle:thin:@localhost:1521:orcl";	 
		        String did=null;
		        String dphone=null;
		        String dpw= null;
		        try {
		        Class.forName("oracle.jdbc.driver.OracleDriver");      
		        conn = DriverManager.getConnection(url, user, pw);	       
		    	spstm=conn.prepareStatement(select);
		    	rs=spstm.executeQuery();
		    	while(rs.next()) {
		    		did=rs.getString("ID");
		    		dphone= rs.getString("PHONE");
		    		if(dphone.equals(phone)) {
		    			JOptionPane.showMessageDialog(null, "ID : "+did,"Message",JOptionPane.INFORMATION_MESSAGE);
		    			break;
		    		}
		    	}
		    	if(!dphone.equals(phone)) {
		    		JOptionPane.showMessageDialog(null, "아이디를 잘못 입력했습니다","Message",JOptionPane.ERROR_MESSAGE);
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
		c.setLayout(new FlowLayout());
		a.add(ab);
		a1.add(ab1);
		a2.add(ab2);
		a2.add(abc);
		a3.add(abcd);
		a3.add(abcd1);
		c.add(a);
		c.add(a1);
		c.add(a2);
		c.add(a3);
		setSize(300,200);
		setVisible(true);
	}	
}