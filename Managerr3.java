import java.awt.BorderLayout;
import java.awt.Container;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class Newdelete extends JFrame{
String did1=null;
Newdelete(){ 
	
	JFrame frame = new JFrame();

	ImagePanel Background= new ImagePanel(new ImageIcon("C:/Users/82105/eclipse-workspace/dbj/image/abc.jpg").getImage());
	frame.getContentPane().add(Background,BorderLayout.NORTH);
	frame.setTitle("회원삭제");
	
	Background.setLayout(null);
	
	JLabel MainLabel = new JLabel("PC방 회원정보 삭제");
	MainLabel.setLayout(null);
	MainLabel.setBounds(66, 10, 173, 27);
	Background.add(MainLabel);
	
	JLabel NEW_ID = new JLabel("아이디");
	NEW_ID.setLayout(null);
	NEW_ID.setBounds(52, 47, 83, 27);
	Background.add(NEW_ID);
	
	JTextField ID_TXT = new JTextField();
	ID_TXT.setBounds(93, 47, 96, 21);
	Background.add(ID_TXT);
	ID_TXT.setColumns(10);
	
	JButton ID_BTN = new JButton("아이디 확인");
	ID_BTN.setLayout(null);
	ID_BTN.setBounds(78, 84, 133, 42);
	Background.add(ID_BTN);
	ID_BTN.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String id;
			id=ID_TXT.getText();
			Connection conn = null; // DB연결된 상태(세션)을 담은 객체
		    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
		    PreparedStatement spstm=null;
		    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
		    String sql="insert into 회원 values(?,?,?)";
		    String select="select ID from 회원";
			String user = "kimheemok"; 
		    String pw = "1234";
		    String url = "jdbc:oracle:thin:@localhost:1521:xe";	 
		    String did = "";
		    try {
		    Class.forName("oracle.jdbc.driver.OracleDriver");      
		    conn = DriverManager.getConnection(url, user, pw);	       
			spstm=conn.prepareStatement(select);
			rs=spstm.executeQuery();
			while(rs.next()) {
				did= rs.getString("ID");
				if(did.equals(id)) {
		    		did1=id;
					JOptionPane.showMessageDialog(null, "삭제 할 수 있는 아이디 입니다","Message",JOptionPane.INFORMATION_MESSAGE);
					break;
				}
			
			}
		     } catch (SQLException me1) {
					System.out.println("sql오류");      
					me1.printStackTrace();
		    }catch(Exception me) {
		    	System.out.println("오류");
		    }
		    finally{
		        // DB 연결을 종료한다.
		    	if(!did.equals(id)) {
					JOptionPane.showMessageDialog(null, "존재하지않는 아이디입니다.","Message",JOptionPane.ERROR_MESSAGE);
				
				}
		        try{
		            if ( rs != null ){rs.close();}   
		            if ( pstm != null ){pstm.close();}   
		            if ( conn != null ){conn.close(); }
		        }catch(Exception ne){
		            throw new RuntimeException(ne.getMessage());
		        }
		        
		    }
		}
		
	});
	

	JButton Back_btn = new JButton("뒤로가기");
	 Back_btn.setLayout(null);
	 Back_btn.setBounds(12, 210, 112, 43);
	 Background.add(Back_btn);
	 Back_btn.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			new Login();
			
		}
		 
	 });
	 
	 
	JButton Sing_up =new JButton("삭제");
	Sing_up.setLayout(null);
	Sing_up.setBounds(144, 210, 112, 43);
	Background.add(Sing_up);
	Sing_up.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String id,pwd,phone;
			id=ID_TXT.getText();
			
			Connection conn = null; // DB연결된 상태(세션)을 담은 객체
		    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
		    PreparedStatement pstm1 = null;  // SQL 문을 나타내는 객체
		    PreparedStatement spstm=null;
		    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
		    String sql="delete from 회원 where ID = '"+did1+"'";
		    String sql1="delete from 예약좌석 where ID = '"+did1+"'";
            String select="select ID from 회원";
            String user = "kimheemok"; 
            String pw = "1234";
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String did = "";
            try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, pw);
            spstm=conn.prepareStatement(sql);
            pstm=conn.prepareStatement(sql1);
                if(did1.equals(id)) {
                	rs=pstm.executeQuery();
                    rs=spstm.executeQuery();
                    JOptionPane.showMessageDialog(null, "회원삭제가 완료되었습니다.","Message",JOptionPane.    INFORMATION_MESSAGE);
                    frame.setVisible(false);
                    new ManagerMain();

                    
                } 
            else {
                JOptionPane.showMessageDialog(null, "아이디가 다릅니다. 다시 확인해주세요","Message",JOptionPane.ERROR_MESSAGE);

            }
		     } catch (SQLException me1) {
					System.out.println("sql오류");      
					me1.printStackTrace();
		    }catch(Exception me) {
		    	System.out.println("오류");
		    }
		    finally{
		        // DB 연결을 종료한다.
		        try{
		            if ( rs != null ){rs.close();}   
		            if ( pstm != null ){pstm.close();}   
		            if ( conn != null ){conn.close(); }
		        }catch(Exception ne){
		            throw new RuntimeException(ne.getMessage());
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
