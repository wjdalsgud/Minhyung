import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Reservation1 extends JFrame{
JCheckBox[] place=new JCheckBox[100];
String[] name = new String [100];

JCheckBox did2 = null;
	Reservation1() {
		for(int n=0; n<100; n++) {
			name[n] = (n+1)+"번째 자리";
		}
		JTextField tf = new JTextField(10);
		 setTitle("예약화면");
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 Container c=getContentPane();
		 c.setLayout(new FlowLayout(FlowLayout.LEFT,5,40));
		 for(int i=0;i<place.length;i++) {
			 place[i]=new JCheckBox(name[i]);
			 place[i].setBorderPainted(true);
			 c.add(place[i]);
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
			 
				@SuppressWarnings("unlikely-arg-type")
				public void actionPerformed(ActionEvent e) {
					// String str = "";
					int count = 0;
					for(int seat=0; seat<place.length;seat++) {
						if(place[seat].isSelected()) {
							count++;
							if(count >1) {
								JOptionPane.showMessageDialog(b2 , "1개 이상 선택하실 수 없습니다","에러",JOptionPane.ERROR_MESSAGE);
							}
						}
						String id;
						id = tf.getText();
						Connection con = null;  // DB연결된 세션을 담은 객체
						PreparedStatement pstm =null; // SQL 문을 나타내는 객체
						PreparedStatement spstm = null; 
						ResultSet Result =null; // 쿼리문을 날린것에 반환값 담는 객체
						String sql = "insert into 좌석정보 values(?)";
						String select = "select 좌석 from 좌석정보";
						String user = "minhyung";
						String pw = "1234";
						String url = "jdbc:oracle:thin:@localhost:1521:xe";
						String did = null;
					
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						con = DriverManager.getConnection(url, user , pw);
						spstm = con.prepareStatement(select);
						Result = spstm.executeQuery();
						while(Result.next()) {
							did= Result.getString("좌석");
							if(did.equals(place[seat])) {
								JOptionPane.showMessageDialog(null, "이미 예약된 좌석입니다","에러",JOptionPane.ERROR_MESSAGE);
								break;
							}
						}
					}
					catch(SQLException message) {
						System.out.println("SQL 오류");
						message.printStackTrace();
					}
					catch(Exception message_1) {
						System.out.println("Error");
					}
					finally {
						if(!did.equals(place[seat])) {
							did2 = place[seat];
							JOptionPane.showMessageDialog(null, "예약 가능한 좌석입니다");
						}
						try {
							if(Result != null) {Result.close();}
							if(pstm != null) {pstm.close();}
							if(con != null) {con.close();}
												
					}
						catch(Exception ne) {
							throw new RuntimeException(ne.getMessage());
		}
	}
}
					setVisible(false);
					new LoginNextMain();	
					}
			
		 });
		 JPanel a= new JPanel();
		 a.setLayout(new FlowLayout(FlowLayout.CENTER,70,40));
		 a.add(b1);
		 a.add(b2);
		 c.add(a);

		setVisible(true);
		setSize(400,400);
	}
}
