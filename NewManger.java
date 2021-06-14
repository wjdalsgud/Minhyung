import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
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

public class NewManager extends JFrame {
	String did1=null;

	NewManager() {
		JFrame frame = new JFrame();

		ImagePanel Background= new ImagePanel(new ImageIcon("./image/Clock.jpg").getImage());
		frame.getContentPane().add(Background,BorderLayout.NORTH);
		frame.setTitle("회원정보수정");

		Background.setLayout(null);

		JLabel MainLabel = new JLabel(" 회원 정보 수정");
		MainLabel.setLayout(null);
		MainLabel.setBounds(66, 10, 173, 27);
		Background.add(MainLabel);


		JLabel pass = new JLabel("비밀번호");
		pass.setLayout(null);
		pass.setBounds(41, 139, 83, 15);
		Background.add(pass);

		JLabel phone = new JLabel("전화번호");
		phone.setLayout(null);
		phone.setBounds(41, 173, 67, 15);
		Background.add(phone);

		JPasswordField  pass_txt = new JPasswordField();
		pass_txt.setLayout(null);
		pass_txt.setBounds(93, 136, 96, 21);
		Background.add(pass_txt);
		pass_txt.setColumns(10);

		JTextField phone_txt = new JTextField();
		phone_txt.setLayout(null);
		phone_txt.setBounds(93, 167, 96, 21);
		Background.add(phone_txt);
		phone_txt.setColumns(10);

		JButton Back_btn = new JButton("뒤로가기");
		 Back_btn.setLayout(null);
		 Back_btn.setBounds(12, 210, 112, 43);
		 Background.add(Back_btn);
		 Back_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ManagerMain();

			}

		 });


		 JButton Sing_up =new JButton("회원수정");
			Sing_up.setLayout(null);
			Sing_up.setBounds(144, 210, 112, 43);
			Background.add(Sing_up);
			Sing_up.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					String id,pwd,phone;
					pwd=pass_txt.getText();
					phone=phone_txt.getText();
					Connection conn = null; // DB연결된 상태(세션)을 담은 객체
				    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
				    PreparedStatement pstm1 = null;  // SQL 문을 나타내는 객체
				    PreparedStatement spstm=null;
				    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
				    String sql="update 회원 set PW = '"+pwd+"', PHONE ='"+phone+"' where ID = '"+Manager3.did123+"' ";
				    String select="select ID from 회원";
					String user = "minhyung"; 
				    String pw = "1234";
				    String url = "jdbc:oracle:thin:@localhost:1521:xe";	 
				    String did = null;
				    try {
				    Class.forName("oracle.jdbc.driver.OracleDriver");      
				    conn = DriverManager.getConnection(url, user, pw);	       
					spstm=conn.prepareStatement(sql);
					int r= spstm.executeUpdate();
				    	JOptionPane.showMessageDialog(null, "회원수정이 완료되었습니다","Message",JOptionPane.INFORMATION_MESSAGE);
						frame.setVisible(false);	
						new ManagerMain();


				     } catch (SQLException me1) {
							System.out.println("sql오류");      
							me1.printStackTrace();
				    }catch(Exception me) {
				    	System.out.println("오류");
				    }
				    finally{
				        // DB 연결을 종료한다.
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
		frame.setSize(300,300);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}

}
