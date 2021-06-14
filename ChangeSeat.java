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
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ChangeSeat extends JFrame{
		ChangeSeat(){
			 setTitle("좌석 수 변경");
			 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 Container c=getContentPane();
			
			 JLabel j=new JLabel("변경하고 싶으신 좌석 수를 입력해주세요");
			 JTextField  jt= new JTextField(8);
			 JButton b1=new JButton("뒤로가기");
			 b1.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						new ManagerMain();
				}
			 });
			 JButton b2=new JButton("변경하기");
			 b2.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {

				        int t=Integer.parseInt(jt.getText());
						
						Connection conn = null; // DB연결된 상태(세션)을 담은 객체
					    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
					    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
						String user = "sys as sysdba"; 
				        String pw = "Gksmf1238";
				        String url = "jdbc:oracle:thin:@localhost:1521:orcl";	 
						String SQL="UPDATE 피시방 SET TOTAL="+jt.getText()+" WHERE PC=1";
				        String did=null;
				        int dpc=0;
				        try {
				        Class.forName("oracle.jdbc.driver.OracleDriver");      
				        conn = DriverManager.getConnection(url, user, pw);	     
				        if(!jt.getText().equals(null)&&t<166) {
			            pstm = conn.prepareStatement(SQL);
			        	int r= pstm.executeUpdate();
			        	JOptionPane.showMessageDialog(null, "좌석수가 "+jt.getText()+"으로 변경되었습니다","Message",JOptionPane.INFORMATION_MESSAGE);
				        }
				        else if(t>165) {
				        	JOptionPane.showMessageDialog(null, "좌석은 1000개 이상 만들 수 없습니다","Message",JOptionPane.INFORMATION_MESSAGE);
				        
				        }
				        else {
				        	JOptionPane.showMessageDialog(null, "좌석 수를 입력해주세요","Message",JOptionPane.INFORMATION_MESSAGE);
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
				  
								setVisible(false);
								new ManagerMain();
				            }catch(Exception me){
				                throw new RuntimeException(me.getMessage());
				            }	        
				        }			
				}
			 });
			 JPanel a= new JPanel();
			 a.setLayout(new FlowLayout(FlowLayout.CENTER,70,40));
			 a.add(j);
			 a.add(jt);
			 a.add(b1);
			 a.add(b2);
			 c.add(a);

			setVisible(true);
			setSize(400,400);
		}
}
