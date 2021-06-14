import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Frame;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ChangeSeat extends JFrame{
	ChangeSeat(){
		
		JFrame frame = new JFrame();
		
		ImagePanel Background= new ImagePanel(new ImageIcon("C:/Users/pc/eclipse-workspace/PC Reservation ManageMent Program/image/paint.jpg").getImage()); //paint
		
		frame.getContentPane().add(Background,BorderLayout.NORTH);
		frame.setTitle("좌석 수 변경");
		
		Background.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("변경하고 싶으신 좌석 수를 입력해주세요");
		lblNewLabel.setLayout(null);
		lblNewLabel.setBounds(22, 36, 402, 55);
		Background.add(lblNewLabel);
		
		JTextField a = new JTextField();
		a.setLayout(null);
		a.setBounds(93, 120, 96, 21);
		Background.add(a);
		a.setColumns(10);
		
		
		JButton back = new JButton("뒤로 가기");
		back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		back.setBackground(Color.LIGHT_GRAY);
		back.setForeground(Color.BLUE);
		back.setLayout(null);
		back.setBounds(152, 184, 128, 34);
		Background.add(back);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ManagerMain();
				
			}
			
		});
		
		JButton b2 = new JButton("변경하기");
		b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b2.setBackground(Color.LIGHT_GRAY);
		b2.setForeground(Color.BLUE);
		b2.setLayout(null);
		b2.setBounds(12, 184, 128, 34);
		Background.add(b2);
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				 int t=Integer.parseInt(a.getText());

					Connection conn = null; // DB연결된 상태(세션)을 담은 객체
				    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
				    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
					String user = "minhyung"; 
			        String pw = "1234";
			        String url = "jdbc:oracle:thin:@localhost:1521:xe";	 
					String SQL="UPDATE 피시방 SET TOTAL="+a.getText()+" WHERE PC=1";
			        String did=null;
			        int dpc=0;
			        try {
			        Class.forName("oracle.jdbc.driver.OracleDriver");      
			        conn = DriverManager.getConnection(url, user, pw);	     
			        if(!a.getText().equals(null)&&t<166) {
		            pstm = conn.prepareStatement(SQL);
		        	int r= pstm.executeUpdate();
		        	JOptionPane.showMessageDialog(null, "좌석수가 "+a.getText()+"으로 변경되었습니다","Message",JOptionPane.INFORMATION_MESSAGE);
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
		frame.setSize(300,300);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		}
	}
