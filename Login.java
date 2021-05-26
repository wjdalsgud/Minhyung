import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JFrame{
	
	
 Login(){

	 setTitle("로그인화면");
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 Container c=getContentPane();
	 JPanel q1= new JPanel();
	 JPanel q2=new JPanel();
	 	JPanel q3=new JPanel();
	 	JPanel q4= new JPanel();
	 	JPanel q5= new JPanel();
	 	JPanel im= new JPanel();
	 	im.setLayout(new FlowLayout(FlowLayout.CENTER));
	 	q1.setLayout(new FlowLayout(FlowLayout.CENTER));
	 	q2.setLayout(new FlowLayout(FlowLayout.CENTER));
	 	q3.setLayout(new FlowLayout(FlowLayout.CENTER));
	 	q4.setLayout(new FlowLayout(FlowLayout.CENTER));
	 	q5.setLayout(new FlowLayout(FlowLayout.CENTER));
	 	ImageIcon im1= new ImageIcon("./image/12.PNG");
	 	JLabel im2= new JLabel(im1);
	 	im.add(im2);
	 JLabel j=new JLabel("PC방 좌석 예약 프로그램");
	 q1.add(j);
	 JLabel j1=new JLabel("  아 이 디 ");
	 q2.add(j1);
	 JTextField jt= new JTextField(18);
	 q2.add(jt);
	 q3.add(new JLabel(" 비밀번호"));
	 JTextField jt1=new JTextField(18);
	 q3.add(jt1);
	 JButton b=new JButton("회원가입");
	 b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new NewID();	
		}
	 });
	 JButton b31=new JButton("아이디 찾기");
	 b31.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new IdSearch();
		}
	 });
	 q4.add(b31);
	 JButton b32=new JButton("비밀번호 찾기");
	 b32.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new PwSearch();	
		}
	 });
	 q4.add(b32);
	 q5.add(b);
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
			    String sql="insert into 회원 values(?,?,?)";
			    String select="select ID,PW from 회원";
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
						new LoginNextMain();	
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
	 q5.add(b1);
	 JButton b2=new JButton("종료");
	 b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			System.exit(0);
		}
	 });

	 JButton b3=new JButton("관리");
	 b3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Manager();
				
		}
	 });
	 
	 q5.add(b2);
	 q5.add(b3);
	 c.setLayout(new FlowLayout());
	 c.add(q1);
	 c.add(im);
	 c.add(q2);
	 c.add(q3);
	 c.add(q4);
	 c.add(q5);
	 setSize(300,400);
	 setVisible(true);
 }
	
	
}
