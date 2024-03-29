import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
public class LoginNextMain extends JFrame{
    static int dseat= 0;
LoginNextMain(){
	
	JFrame frame = new JFrame();
	ImagePanel Background= new ImagePanel(new ImageIcon("./image/Wallpaper.jpg").getImage());
	frame.getContentPane().add(Background,BorderLayout.NORTH);
	frame.setTitle("메인 화면");
	
	Background.setLayout(null);
	
	JLabel MainLabel = new JLabel("PC방 좌석 예약 프로그램");
	MainLabel.setLayout(null);
	MainLabel.setBounds(261, 10, 176, 44);
	MainLabel.setFont(new Font("HY수평선M",Font.BOLD,15));
	MainLabel.setForeground(Color.WHITE);
	Background.add(MainLabel);
	
	JButton btnNewButton_1 = new JButton("예약하기");
	btnNewButton_1.setLayout(null);
	btnNewButton_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
	btnNewButton_1.setBackground(Color.LIGHT_GRAY);
	btnNewButton_1.setForeground(Color.BLUE);
	btnNewButton_1.setBounds(233, 53, 205, 34);
	Background.add(btnNewButton_1);
	btnNewButton_1.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			Connection conn = null; // DB연결된 상태(세션)을 담은 객체
		    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
		    PreparedStatement spstm=null;
		    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
		    String select="select*from 예약좌석";
			String user = "minhyung"; 
	        String pw = "1234";
	        String url = "jdbc:oracle:thin:@localhost:1521:xe";	 
	        String did=null;
	        int dpc=0;
	        try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");      
	        conn = DriverManager.getConnection(url, user, pw);	       
	    	spstm=conn.prepareStatement(select);
	    	rs=spstm.executeQuery();
	    	while(rs.next()) {
				did= rs.getString("ID");
				dpc= rs.getInt("PC");
				dseat= rs.getInt("SEAT");
				if(did.equals(Login.lid)) {
					break;
				}
	    	}
	    	if(did.equals(Login.lid)&&dseat!=0) {
				JOptionPane.showMessageDialog(null, "이미 예약된 정보가 있습니다. 예약 취소 후 사용해 주시기 바랍니다","Message",JOptionPane.ERROR_MESSAGE);
			}
	    	else {
				setVisible(false);
				new Reservation1();
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
	                if ( pstm != null ){pstm.close();}   
	                if ( conn != null ){conn.close(); }
	            }catch(Exception me){
	                throw new RuntimeException(me.getMessage());
	            }	        
	        }			
		}
		
	});
	JButton btnNewButton_1_1_1 = new JButton("예약취소하기");
	btnNewButton_1_1_1.setLayout(null);
	btnNewButton_1_1_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
	btnNewButton_1_1_1.setBackground(Color.LIGHT_GRAY);
	btnNewButton_1_1_1.setForeground(Color.BLUE);
	btnNewButton_1_1_1.setBounds(233, 115, 205, 34);
	Background.add(btnNewButton_1_1_1);
	btnNewButton_1_1_1.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			Connection conn = null; // DB연결된 상태(세션)을 담은 객체
		    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
		    PreparedStatement spstm=null;
		    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
		    String select="select*from 예약좌석";
			String user = "minhyung"; 
	        String pw = "1234";
	        String url = "jdbc:oracle:thin:@localhost:1521:xe";	 
	        String did=null;
	        int dpc=0;
	        try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");      
	        conn = DriverManager.getConnection(url, user, pw);	       
	    	spstm=conn.prepareStatement(select);
	    	rs=spstm.executeQuery();
	    	while(rs.next()) {
				did= rs.getString("ID");
				dpc= rs.getInt("PC");
				dseat= rs.getInt("SEAT");
				if(did.equals(Login.lid)&&dseat==0) {
					JOptionPane.showMessageDialog(null, "예약된 좌석이 없습니다. 예약 후 사용해 주시기 바랍니다","Message",JOptionPane.ERROR_MESSAGE);
					break;
				}
            	if(did.equals(Login.lid)&&dseat!=0) {
					frame.setVisible(false);
					new Reservation2();
				}
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
	
	
	JButton c = new JButton("예약한 좌석 확인하기");
	c.setLayout(null);
	c.setCursor(new Cursor(Cursor.HAND_CURSOR));
	c.setBackground(Color.LIGHT_GRAY);
	c.setForeground(Color.BLUE);
	c.setBounds(233, 185, 205, 34);
	Background.add(c);
	c.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			Connection conn = null; // DB연결된 상태(세션)을 담은 객체
		    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
		    PreparedStatement spstm=null;
		    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
		    String select="select*from 예약좌석";
			String user = "minhyung"; 
	        String pw = "1234";
	        String url = "jdbc:oracle:thin:@localhost:1521:xe";	 
	        String did=null;
	        int dpc=0;
	        try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");      
	        conn = DriverManager.getConnection(url, user, pw);	       
	    	spstm=conn.prepareStatement(select);
	    	rs=spstm.executeQuery();
	    	while(rs.next()) {
				did= rs.getString("ID");
				dpc= rs.getInt("PC");
				dseat= rs.getInt("SEAT");
				if(did.equals(Login.lid)) {
					break;
				}
	    	}
				frame.setVisible(false);
				new Reservation3();
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
	
	JButton seat = new JButton("좌석 현황");
	seat.setLayout(null);
	seat.setCursor(new Cursor(Cursor.HAND_CURSOR));
	seat.setBackground(Color.LIGHT_GRAY);
	seat.setForeground(Color.BLUE);
	seat.setBounds(233, 251, 205, 34);
	Background.add(seat);
	seat.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			new Reservation4();
			
		}
		
	});
	
	JButton logout = new JButton("로그아웃");
	 logout.setLayout(null);
	 logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
	 logout.setBackground(Color.LIGHT_GRAY);
	 logout.setForeground(Color.BLUE);
	 logout.setBounds(233, 327, 205, 34);
	 Background.add(logout);
	 logout.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			new Login();
			
		}
		 
	 });
	
	
	JButton exit = new JButton("종료");
	exit.setLayout(null);
	exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
	 exit.setBackground(Color.LIGHT_GRAY);
	 exit.setForeground(Color.BLUE);
	 exit.setBounds(233, 399, 205, 34);
	 Background.add(exit);
	 exit.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			System.exit(0);
			
		}
		 
	 });
	
	 	frame.setSize(800,480);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
	
	

	
	}
}
