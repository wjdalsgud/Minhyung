import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Reservation1 extends JFrame{
JCheckBox[] place=new JCheckBox[Login.pctotal];
String[] name = new String [Login.pctotal];
JCheckBox did2 = null;
	Reservation1() {
		Connection conn5 = null; // DB연결된 상태(세션)을 담은 객체
	    PreparedStatement pstm5 = null;  // SQL 문을 나타내는 객체
	    ResultSet rs5 = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
	    String select5="select*from 피시방좌석";
		String user = "sys as sysdba"; 
        String pw = "Gksmf1238";
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";	 
        String did=null;
        int dpc=0;
		try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");      
	        conn5 = DriverManager.getConnection(url, user, pw);	       
	    	
		for(int n=0; n<Login.pctotal; n++) {
			name[n] = (n+1)+"번째 자리";
		}
		 setTitle("예약화면");
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 Container c=getContentPane();
		 c.setLayout(new FlowLayout(FlowLayout.LEFT,5,40));
		 ButtonGroup bg=new ButtonGroup();
		 pstm5=conn5.prepareStatement(select5);
	    	rs5=pstm5.executeQuery();
	    	int i=0;
		 while(rs5.next()) {
			 String id5 = rs5.getString("PC");
             int pw5 = rs5.getInt("SEAT");
             int seat5 = rs5.getInt("예약유무");
			 place[i]=new JCheckBox(name[i]);
			 place[i].setBorderPainted(true);
			 if(seat5==1) {
				 place[i].setEnabled(false);
			 }else {
				 place[i].setEnabled(true);
			 }
			 bg.add(place[i]);
			 c.add(place[i]);
			 i++;
		 }
		 JButton b1=new JButton("뒤로가기");
		 b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new LoginNextMain();	
			}
		 });
		 JButton b2=new JButton("예약하기");
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
			        int seat=0;
			        int dpc=0;
			        try {
				        Class.forName("oracle.jdbc.driver.OracleDriver");      
				        conn = DriverManager.getConnection(url, user, pw);	       
				    	spstm=conn.prepareStatement(select);
				    	rs=spstm.executeQuery();
						for(seat=0; seat<place.length;seat++) {
							if(place[seat].isSelected()) {
					            String SQL="UPDATE 예약좌석 SET SEAT="+(seat+1)+" WHERE ID='"+Login.lid+"'";
					            String SQL1="UPDATE 피시방좌석 SET 예약유무=1 WHERE SEAT="+(seat+1)+"";
					            pstm = conn.prepareStatement(SQL);
					            pstm1 = conn.prepareStatement(SQL1);
					        	int r= pstm.executeUpdate();
					        	int r1=pstm1.executeUpdate();
								JOptionPane.showMessageDialog(null, "선택하신 "+(seat+1)+"번 자리가 예약되었습니다","Message",JOptionPane.INFORMATION_MESSAGE);                 
								setVisible(false);
								new LoginNextMain();	
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
			
		 JPanel a= new JPanel();
		 a.setLayout(new FlowLayout(FlowLayout.CENTER,70,40));
		 a.add(b1);
		 a.add(b2);
		 c.add(a);

		setVisible(true);
		setSize(2400,2400);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		 finally{
	            // DB 연결을 종료한다.
	            try{
	                if ( rs5 != null ){rs5.close();}   
	                if ( pstm5 != null ){pstm5.close();}   
	                if ( conn5 != null ){conn5.close(); }
	            }catch(Exception me){
	                throw new RuntimeException(me.getMessage());
	            }	        
	        }
	}
}
