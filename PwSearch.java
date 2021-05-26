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

public class PwSearch extends JFrame{

	PwSearch(){
		Container c= getContentPane();
		setTitle("비밀번호 찾기");
		JPanel a= new JPanel();
		JPanel a1= new JPanel();
		JPanel a2= new JPanel();
		JPanel a3= new JPanel();
		JPanel a4= new JPanel();
		a.setLayout(new FlowLayout());
		a1.setLayout(new FlowLayout());
		a2.setLayout(new FlowLayout());
		a3.setLayout(new FlowLayout());
		JLabel ab=new JLabel("             비밀번호 찾기           ");
		JLabel ab1=new JLabel("아이디와 전화번호를 입력해주세요");
		JLabel ab2= new JLabel("  아 이 디 ");
		JTextField abc= new JTextField(15);
		JLabel ab3= new JLabel("전화번호 ");
		JTextField abc12= new JTextField(15);
		JButton abcd= new JButton("뒤로가기");
		 abcd.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new Login();	
			}
		 });
		JButton abcd1=new JButton("비밀번호 검색");
		abcd1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

			    String id,phone;			    
				id= abc.getText();
				phone=abc12.getText();
				Connection conn = null; // DB연결된 상태(세션)을 담은 객체
			    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
			    PreparedStatement spstm=null;
			    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
			    String select="select ID,PW,PHONE from 회원";
				String user = "sys as sysdba"; 
			    String pw = "Gksmf1238";
			    String url = "jdbc:oracle:thin:@localhost:1521:orcl";	 
			    String did = null;
			    String dphone=null;
			    String pwd=null;
			    try {
			    Class.forName("oracle.jdbc.driver.OracleDriver");      
			    conn = DriverManager.getConnection(url, user, pw);	       
				spstm=conn.prepareStatement(select);
				rs=spstm.executeQuery();
				while(rs.next()) {
					did= rs.getString("ID");
					pwd=rs.getString("PW");
					dphone= rs.getString("PHONE");
					if(did.equals(id)&&dphone.equals(phone)) {
						JOptionPane.showMessageDialog(null, "비밀번호 : "+pwd,"Message",JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				}
			     } catch (SQLException me1) {
						System.out.println("sql오류");      
						me1.printStackTrace();
			    }catch(Exception me) {
			    	System.out.println("오류");
			    }
			    finally{
			        // DB 연결을 종료한다.
			    	if(!did.equals(id)) {
						JOptionPane.showMessageDialog(null, "아이디나 전화번호를 잘못 입력하셨습니다","Message",JOptionPane.ERROR_MESSAGE);
					}
			        try{
			            if ( rs != null ){rs.close();}   
			            if ( pstm != null ){pstm.close();}   
			            if ( conn != null ){conn.close(); }
			        }catch(Exception ne){
			            throw new RuntimeException(ne.getMessage());
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
		a4.add(ab3);
		a4.add(abc12);
		c.add(a);
		c.add(a1);
		c.add(a2);
		c.add(a4);
		c.add(a3);
		setSize(300,250);
		setVisible(true);
	}
	
}
