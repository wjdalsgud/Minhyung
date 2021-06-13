import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Login extends JFrame {
	static String lid=" ";
	static String lpw=" ";
	static int pctotal=0;
	Login() {
		JFrame frame = new JFrame();
		ImagePanel Background= new ImagePanel(new ImageIcon("C:/Users/pc/eclipse-workspace/PC Reservation ManageMent Program/image/Wallpaper.jpg").getImage());
		frame.getContentPane().add(Background,BorderLayout.NORTH);
		frame.setTitle("로그인");
		
		Background.setLayout(null);
		
		
		JLabel MainLabel = new JLabel("PC Reservation ManageMent Program");
		MainLabel.setLayout(null);
		MainLabel.setBounds(247, 10, 301, 45);
		MainLabel.setFont(new Font("HY수평선M",Font.BOLD,15));
		MainLabel.setForeground(Color.white);
		Background.add(MainLabel);
		
		JLabel ID = new JLabel(" ID :");
		ID.setLayout(null);
		ID.setBounds(275, 79, 105, 36);
		ID.setForeground(Color.BLACK);
		Background.add(ID);
		
		JTextField ID_textField = new JTextField();
		ID_textField.setBounds(307, 87, 122, 21);
		Background.add(ID_textField);
		ID_textField.setColumns(10);
		
		JLabel PW= new JLabel("PassWord :");
		PW.setLayout(null);
		PW.setBounds(233, 115, 105, 26);
		PW.setForeground(Color.BLACK);
		Background.add(PW);
		
		
		JPasswordField txtPass = new JPasswordField();
		txtPass.setLayout(null);
		txtPass.setBounds(307, 118, 122, 21);
		Background.add(txtPass);
		txtPass.setColumns(10);
		
		JButton ID_btn = new JButton("아이디 찾기");
		ID_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		ID_btn.setBackground(Color.cyan);
		ID_btn.setForeground(Color.MAGENTA);
		ID_btn.setBounds(156, 192, 105, 67);
		Background.add(ID_btn);
		ID_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new IdSearch();
				
			}
		
		});
		
		JButton PW_btn = new JButton("비밀번호찾기");
		PW_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		PW_btn.setBackground(Color.cyan);
		PW_btn.setForeground(Color.MAGENTA);
		PW_btn.setBounds(301, 192, 128, 67);
		Background.add(PW_btn);
		PW_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new PwSearch();
			}
		});
		JButton Signup_btn= new JButton("회원가입");
		Signup_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		Signup_btn.setBackground(Color.CYAN);
		Signup_btn.setForeground(Color.MAGENTA);
		Signup_btn.setBounds(443, 192, 105, 67);
		Background.add(Signup_btn);
		Signup_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new NewID();
				
			}
			
		});
		
		JButton Login_btn= new JButton("로그인");
		Login_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		Login_btn.setBackground(Color.CYAN);
		Login_btn.setForeground(Color.MAGENTA);
		Login_btn.setBounds(156, 312, 105, 67);
		Background.add(Login_btn);
		Login_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String id,pwd;
				id=ID_textField.getText();
				pwd=txtPass.getText();
				Connection conn = null; // DB연결된 상태(세션)을 담은 객체
			    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
			    PreparedStatement spstm=null;
			    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
			    String select="select ID,PW from 회원";
			    String select1="select*from 피시방 where pc=1";
				String user = "minhyung"; 
		        String pw = "1234";
		        String url = "jdbc:oracle:thin:@localhost:1521:xe";	 
		        String did=null;
		        String dpw=null;
		        try {
		        Class.forName("oracle.jdbc.driver.OracleDriver");      
		        conn = DriverManager.getConnection(url, user, pw);	       
		    	spstm=conn.prepareStatement(select);
		    	pstm=conn.prepareStatement(select1);
		    	rs=spstm.executeQuery();
		    	while(rs.next()) {
		    		did= rs.getString("ID");
		    		dpw= rs.getString("PW");
		    		if(did.equals(id)&&dpw.equals(pwd)) {
		    			lid=id;
		    			lpw=pwd;
		                pstm = conn.prepareStatement(select1);
		                rs = pstm.executeQuery();
		                while(rs.next()){
		            	pctotal=rs.getInt("total"); 		    			
		                }
		    			JOptionPane.showMessageDialog(null,"Login Success");
		    			frame.setVisible(false);
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
		
		JButton Manager_btn= new JButton("관리");
		Manager_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		Manager_btn.setBackground(Color.CYAN);
		Manager_btn.setForeground(Color.MAGENTA);
		Manager_btn.setBounds(301, 312, 105, 67);
		Background.add(Manager_btn);
		Manager_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new Manager();
				
			}
			
		});
		
		JButton Exit_btn = new JButton("종료");
		Exit_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		Exit_btn.setBackground(Color.CYAN);
		Exit_btn.setForeground(Color.MAGENTA);
		Exit_btn.setBounds(443, 312, 105, 67);
		Background.add(Exit_btn);
		Exit_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				System.exit(0);
				
			}
			
		});
		
		
		frame.setSize(800,480);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
