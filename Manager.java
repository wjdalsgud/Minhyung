import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Manager extends JFrame{
	Manager(){
		JFrame frame = new JFrame();
		ImagePanel Background= new ImagePanel(new ImageIcon("C:/Users/pc/eclipse-workspace/PC Reservation ManageMent Program/image/Tree.jpg").getImage());
		frame.getContentPane().add(Background,BorderLayout.NORTH);
		frame.setTitle("관리자 로그인 화면");
		
		Background.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("관리자 로그인");
		lblNewLabel.setLayout(null);
		lblNewLabel.setBounds(82, 28, 125, 30);
		lblNewLabel.setFont(new Font("HY수평선M",Font.BOLD,15));
		lblNewLabel.setForeground(Color.WHITE);
		Background.add(lblNewLabel);
		
		JLabel ID = new JLabel("ID");
		ID.setLayout(null);
		ID.setBounds(28, 87, 50, 15);
		ID.setForeground(Color.WHITE);
		Background.add(ID);
		
		JLabel Pass = new JLabel("Password");
		Pass.setLayout(null);
		Pass.setBounds(12, 128, 77, 15);
		Pass.setForeground(Color.WHITE);
		Background.add(Pass);
		
		JTextField ID_TXT = new JTextField();
		ID_TXT.setBounds(73, 84, 96, 21);
		Background.add(ID_TXT);
		ID_TXT.setColumns(18);
		
		JPasswordField Pass_TXT = new JPasswordField();
		 Pass_TXT.setBounds(73, 125, 96, 21);
		 Background.add(Pass_TXT);
		 Pass_TXT.setColumns(18);
	
		
		 JButton Login = new JButton("로그인");
		 Login.setLayout(null);
		 Login.setCursor(new Cursor(Cursor.HAND_CURSOR));
		 Login.setBackground(Color.lightGray);
		 Login.setForeground(Color.BLUE);
		 Login.setBounds(12, 200, 91, 23);
		 Background.add(Login);
		 Login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String id,pwd;
				id=ID_TXT.getText();
				pwd=Pass_TXT.getText();
				Connection conn = null; // DB연결된 상태(세션)을 담은 객체
			    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
			    PreparedStatement spstm=null;
			    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
			    String select="select ID,PW from 관리자";
				String user = "minhyung"; 
		        String pw = "1234";
		        String url = "jdbc:oracle:thin:@localhost:1521:xe";	 
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
						frame.setVisible(false);
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
		
		JButton b = new JButton("뒤로가기");
		b.setLayout(null);
		b.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b.setBackground(Color.LIGHT_GRAY);
		b.setForeground(Color.BLUE);
		b.setBounds(139, 200, 91, 23);
		Background.add(b);
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new Login();
				
			}
			
		});
		
		JButton sign = new JButton("회원가입");
		sign.setLayout(null);
		sign.setCursor(new Cursor(Cursor.HAND_CURSOR));
		sign.setBackground(Color.LIGHT_GRAY);
		sign.setForeground(Color.BLUE);
		sign.setBounds(82, 243, 91, 23);
		Background.add(sign);
		sign.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ManagerID();
				
			}
			
		});
		frame.setSize(300,400);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
