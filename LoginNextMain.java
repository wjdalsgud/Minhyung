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
	ImagePanel Background= new ImagePanel(new ImageIcon("C:/Users/pc/eclipse-workspace/PC Reservation ManageMent Program/image/Wallpaper.jpg").getImage());
	frame.getContentPane().add(Background,BorderLayout.NORTH);
	frame.setTitle("메인");
	
	Background.setLayout(null);
	
	JLabel MainLabel = new JLabel("PC방 좌석 예약 프로그램");
	MainLabel.setLayout(null);
	MainLabel.setBounds(265, 10, 280, 68);
	MainLabel.setFont(new Font("HY수평선",Font.BOLD,18));
	MainLabel.setForeground(Color.WHITE);
	Background.add(MainLabel);
	
	JButton ReserVation_btn = new JButton("예약하기");
	ReserVation_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	ReserVation_btn.setBackground(Color.LIGHT_GRAY);
	ReserVation_btn.setForeground(Color.blue);
	ReserVation_btn.setLayout(null);
	ReserVation_btn.setBounds(244, 75, 224, 41);
	Background.add(ReserVation_btn);
	ReserVation_btn.addActionListener(new ActionListener() {

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
				frame.setVisible(false);
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
	
	JButton Cancel_reservation_btn = new JButton("예약취소하기");
	Cancel_reservation_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	Cancel_reservation_btn.setBackground(Color.LIGHT_GRAY);
	Cancel_reservation_btn.setForeground(Color.blue);
	Cancel_reservation_btn.setLayout(null);
	Cancel_reservation_btn.setBounds(244, 141, 224, 41);
	Background.add(Cancel_reservation_btn);
	Cancel_reservation_btn.addActionListener(new ActionListener() {

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
	
	JButton Confirm_btn = new JButton("예약한 좌석 확인하기");
	Confirm_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	Confirm_btn.setBackground(Color.LIGHT_GRAY);
	Confirm_btn.setForeground(Color.blue);
	Confirm_btn.setLayout(null);
	Confirm_btn.setBounds(244, 205, 224, 41);
	Background.add(Confirm_btn);
	Confirm_btn.addActionListener(new ActionListener() {

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
	
	JButton Seat_Info_btn = new JButton("좌석 현황");
	Seat_Info_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	Seat_Info_btn.setBackground(Color.LIGHT_GRAY);
	Seat_Info_btn.setForeground(Color.blue);
	Seat_Info_btn.setLayout(null);
	Seat_Info_btn.setBounds(244, 265, 224, 41);
	Background.add(Seat_Info_btn);
	Seat_Info_btn.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			new Reservation4();
		}
		
	});
	
	JButton Logout_btn = new JButton("로그아웃");
	Logout_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	Logout_btn.setBackground(Color.LIGHT_GRAY);
	Logout_btn.setForeground(Color.blue);
	Logout_btn.setLayout(null);
	Logout_btn.setBounds(244, 332, 224, 41);
	Background.add(Logout_btn);
	Logout_btn.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			new Login();
			
		}
		
	});
	
	JButton Exit_btn = new JButton("종료");
	Exit_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	Exit_btn.setBackground(Color.LIGHT_GRAY);
	Exit_btn.setForeground(Color.BLUE);
	Exit_btn.setLayout(null);
	Exit_btn.setBounds(244, 392, 224, 41);
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
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
	}
}
