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

public class Reservation2 extends JFrame{
	int ab=LoginNextMain.dseat;
		Reservation2(){
			 setTitle("예약취소화면");
			 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 Container c=getContentPane();
			
			 JLabel jl=new JLabel("현재 예약된 좌석은 "+LoginNextMain.dseat+"번입니다. 예약을 취소하시겠습니까?");
			 
			 JButton b1=new JButton("뒤로가기");
			 b1.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						new LoginNextMain();	
				}
			 });
			 JButton b2=new JButton("예약취소하기");
			 b2.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						Connection conn = null; // DB연결된 상태(세션)을 담은 객체
					    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
					    PreparedStatement pstm1 = null;  // SQL 문을 나타내는 객체
					    PreparedStatement spstm=null;
					    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
					    String select="select*from 예약좌석";
						String user = "sys as sysdba"; 
				        String pw = "Gksmf1238";
				        String url = "jdbc:oracle:thin:@localhost:1521:orcl";	 
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
								setVisible(false);
								new LoginNextMain();	
				            }catch(Exception me){
				                throw new RuntimeException(me.getMessage());
				            }	        
				        }			
				}
			 });
			 JPanel a= new JPanel();
			 a.setLayout(new FlowLayout(FlowLayout.CENTER,70,40));
			 a.add(jl);
			 a.add(b1);
			 a.add(b2);
			 c.add(a);

			setVisible(true);
			setSize(400,400);
		}
}

