import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Font;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		ImagePanel Background= new ImagePanel(new ImageIcon("C:/Users/pc/eclipse-workspace/PC Reservation ManageMent Program/image/Wallpaper.jpg").getImage());
		frame.getContentPane().add(Background, BorderLayout.NORTH);
		frame.setTitle("로그인화면");
		
		JLabel ID_label= new JLabel("ID :");
		ID_label.setBounds(201, 99, 79, 39);
		Background.add(ID_label);
		
		JTextField ID_TextField = new JTextField(); // ID TextField
		ID_TextField.setBounds(225, 108, 96, 21);
		Background.add(ID_TextField);
		ID_TextField.setColumns(10);
		
		JLabel PW_label= new JLabel("PW :");
		PW_label.setBounds(355, 99, 72, 39);
		Background.add(PW_label);
		
		JTextField PW_TextField = new JTextField(); // PW TextField
		PW_TextField.setBounds(383, 108, 96, 21);
		Background.add(PW_TextField);
		PW_TextField.setColumns(10);
		
		JButton SearchID_btn= new JButton("아이디 찾기");
		SearchID_btn.setBounds(172, 194, 110, 54);
		Background.add(SearchID_btn);
		SearchID_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IdSearch();
			}
		});
		
		
		
		JButton SearchPW_btn= new JButton("비밀번호 찾기");
		SearchPW_btn.setBounds(327, 193, 113, 57);
		Background.add(SearchPW_btn);
		SearchPW_btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new PwSearch();
			}
		});
		
		JButton Signup_btn = new JButton("회원가입");
		Signup_btn.setBounds(470, 193, 118, 57);
		Background.add(Signup_btn);
		Signup_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NewID();
			}
		});
		
		JLabel MainLabel = new JLabel("PC방 좌석 예약 프로그램");
		MainLabel.setFont(new Font("HY수평선M", Font.BOLD, 14));
		MainLabel.setBounds(267, 10, 220, 61);
		Background.add(MainLabel);
		
		JButton Login_btn = new JButton("로그인");
		Login_btn.setBounds(172, 286, 110, 54);
		Background.add(Login_btn);
		Login_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id,pwd;
				id=ID_TextField.getText();
				pwd=PW_TextField.getText();
				Connection conn = null; // DB연결된 상태(세션)을 담은 객체
			    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
			    PreparedStatement spstm=null;
			    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
			    String select="select ID,PW from 회원";
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
		
		JButton Manage_btn = new JButton("관리");
		Manage_btn .setBounds(327, 286, 110, 54);
		Background.add(Manage_btn );
		Manage_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Manager();
			}
		});
		
		
		JButton Exit_btn = new JButton("종료");
		 Exit_btn.setBounds(478, 286, 110, 54);
		Background.add( Exit_btn);
		Exit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		frame.setSize(800, 480);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}
}	
		
		class ImagePanel extends JPanel {
			private Image img;
			
			public ImagePanel(Image img) {
				this.img = img;
				setSize(new Dimension(img.getWidth(null),img.getHeight(null)));
				setPreferredSize(new Dimension(img.getWidth(null),img.getHeight(null)));
				setLayout(null);
			}
			public  int getWidth() {
				return img.getWidth(null);
			}
			public int getHeight() {
				return img.getHeight(null);
			}
			public void paintComponent(Graphics g) {
				g.drawImage(img,0,0,null);
			}
		}
