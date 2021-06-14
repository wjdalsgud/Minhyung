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

public class Reservation2 extends JFrame{
	int ab=LoginNextMain.dseat;
		Reservation2(){
		
			JFrame frame = new JFrame();
			ImagePanel Background= new ImagePanel(new ImageIcon("C:/Users/pc/eclipse-workspace/PC Reservation ManageMent Program/image/Space.jpg").getImage());
			frame.getContentPane().add(Background,BorderLayout.NORTH);
			frame.setTitle("예약취소화면");
			
			Background.setLayout(null);
			
			JLabel Cancel_seat = new JLabel("현재 예약된 좌석은 "+LoginNextMain.dseat+"번입니다.예약을 취소하시겠습니까?");
			Cancel_seat.setLayout(null);
			Cancel_seat.setBounds(22, 36, 402, 55);
			Cancel_seat.setFont(new Font("HY수평선",Font.BOLD,15));
			Cancel_seat.setForeground(Color.WHITE);
			Background.add(Cancel_seat);
			
			JButton Back_btn = new JButton("뒤로 가기");
			Back_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			Back_btn.setBackground(Color.LIGHT_GRAY);
			Back_btn.setForeground(Color.BLUE);
			Back_btn.setLayout(null);
			Back_btn.setBounds(52, 158, 128, 34);
			Background.add(Back_btn);
			
			Back_btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
					new LoginNextMain();
					
				}
				
			});
			JButton Cancel_seat_btn = new JButton("예약 취소하기");
			Cancel_seat_btn.setLayout(null);
			Cancel_seat_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			Cancel_seat_btn.setBackground(Color.LIGHT_GRAY);
			Cancel_seat_btn.setForeground(Color.BLUE);
			Cancel_seat_btn.setBounds(224, 158, 128, 34);
			Background.add(Cancel_seat_btn);
			Cancel_seat_btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Connection conn = null; // DB연결된 상태(세션)을 담은 객체
				    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
				    PreparedStatement pstm1 = null;  // SQL 문을 나타내는 객체
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
					String SQL="UPDATE 예약좌석 SET SEAT=0 WHERE ID='"+Login.lid+"'";
		            String SQL1="UPDATE 피시방좌석 SET 예약유무=0 WHERE SEAT="+ab+"";
		            //String SQL1="UPDATE 피시방좌석 SET 예약유무=0 WHERE SEAT=99";
		            pstm = conn.prepareStatement(SQL);
		            pstm1 = conn.prepareStatement(SQL1);
		        	int r= pstm.executeUpdate();
		        	int r1= pstm1.executeUpdate();

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
			            	JOptionPane.showMessageDialog(null, "예약이 취소되었습니다","Message",JOptionPane.INFORMATION_MESSAGE);
							frame.setVisible(false);
							new LoginNextMain();	
			            }catch(Exception me){
			                throw new RuntimeException(me.getMessage());
			            }	        
			        }		
				}
				
			});
			
			frame.setSize(450 ,450);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
		}
	}
