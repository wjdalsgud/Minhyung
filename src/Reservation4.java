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

public class Reservation4 extends JFrame{
JCheckBox[] place=new JCheckBox[Login.pctotal];
String[] name = new String [Login.pctotal];
JCheckBox did2 = null;
	Reservation4() {
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
			 if(i==Login.pctotal) {
				 break;
			 }
		 }
		 JButton b1=new JButton("뒤로가기");
		 b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new LoginNextMain();	
			}
		 });
		 
			
		 JPanel a= new JPanel();
		 a.setLayout(new FlowLayout(FlowLayout.CENTER,70,40));
		 a.add(b1);
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