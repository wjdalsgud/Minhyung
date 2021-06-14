import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
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
import javax.swing.JTextField;

public class Manager3 extends JFrame{
static String did123=" ";
    Manager3(){
    	JFrame frame = new JFrame();
		ImagePanel Background= new ImagePanel(new ImageIcon("C:/Users/pc/eclipse-workspace/PC Reservation ManageMent Program/image/Blue.jpg").getImage());
		frame.getContentPane().add(Background,BorderLayout.NORTH);
		frame.setTitle("회원 수정");
		
		Background.setLayout(null);
    	
    	JLabel Main = new JLabel("회원 수정");
		Main.setLayout(null);
		Main.setBounds(94, 10, 105, 35);
		Main.setFont(new Font("HT수평선M",Font.BOLD,13));
		Main.setForeground(Color.BLACK);
		Background.add(Main);
		
		JLabel a = new JLabel("관리자 아이디를 입력해주세요");
		a.setLayout(null);
		a.setForeground(Color.BLACK);
		a.setBounds(31, 41, 225, 35);
		Background.add(a);
		
		JLabel ID = new JLabel("ID");
		ID.setLayout(null);
		ID.setForeground(Color.BLACK);
		ID.setBounds(12, 70, 79, 35);
		Background.add(ID);
		
		JTextField ab = new JTextField();
		ab.setLayout(null);
		ab.setBounds(41, 77, 164, 21);
		Background.add(ab);
		ab.setColumns(15);
		
		JButton b = new JButton("뒤로가기");
		b.setLayout(null);
		b.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b.setBackground(Color.LIGHT_GRAY);
		b.setForeground(Color.BLUE);
		b.setBounds(31, 115, 105, 35);
		Background.add(b);
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ManagerMain();
				
			}
			
		});
		
		
		JButton Change = new JButton("아이디 변경");
		Change.setLayout(null);
		Change.setCursor(new Cursor(Cursor.HAND_CURSOR));
		Change.setBackground(Color.LIGHT_GRAY);
		Change.setForeground(Color.BLUE);
		Change.setBounds(149, 115, 105, 35);
		Background.add(Change);
		Change.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 String id;
	                id=ab.getText();
	                Connection conn = null; // DB연결된 상태(세션)을 담은 객체
	                PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
	                PreparedStatement spstm=null;
	                ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
	                String select="select ID,PHONE from 회원";
	                String user = "minhyung"; 
	                String pw = "1234";
	                String url = "jdbc:oracle:thin:@localhost:1521:xe";     
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
	                    if(did.equals(id)) {
	                    did123=id;
	                        setVisible(false);
	                        new NewManager();
	                        
	                        break;
	                    }
	                }
	                if(!did.equals(id)) {
	                    JOptionPane.showMessageDialog(null, "존재하지않는 아이디입니다.","Message",JOptionPane.ERROR_MESSAGE);
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
		
		
		
		
		frame.setSize(300,200);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	}
    }
