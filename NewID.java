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
import javax.swing.JTextField;

public class NewID extends JFrame{

String did1=null;
NewID(){
	setTitle("회원가입");
	Container c= getContentPane();
	JPanel jp= new JPanel();
	JPanel jp1= new JPanel();
	JPanel jp2= new JPanel();
	JPanel jp3= new JPanel();
	JPanel jp12= new JPanel();
	JPanel jp132= new JPanel();
	JLabel a= new JLabel("좌석 예약 회원가입");
	JTextField k123= new JTextField("");
	jp.setLayout(new FlowLayout());
	jp1.setLayout(new FlowLayout());
	jp2.setLayout(new FlowLayout());
	jp3.setLayout(new FlowLayout());
	JLabel a1= new JLabel("  아 이 디");
	JTextField jf= new JTextField(10);
	JButton jb= new JButton("아이디 중복확인");
	jb.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {

		    String id;
			id=jf.getText();
			Connection conn = null; // DB연결된 상태(세션)을 담은 객체
		    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
		    PreparedStatement spstm=null;
		    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
		    String sql="insert into 회원 values(?,?,?)";
		    String select="select ID from 회원";
			String user = "sys as sysdba"; 
		    String pw = "Gksmf1238";
		    String url = "jdbc:oracle:thin:@localhost:1521:orcl";	 
		    String did = null;
		    try {
		    Class.forName("oracle.jdbc.driver.OracleDriver");      
		    conn = DriverManager.getConnection(url, user, pw);	       
			spstm=conn.prepareStatement(select);
			rs=spstm.executeQuery();
			while(rs.next()) {
				did= rs.getString("ID");
				if(did.equals(id)) {
					JOptionPane.showMessageDialog(null, "ID가 중복되서 회원가입을 할 수 없습니다","Message",JOptionPane.ERROR_MESSAGE);
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
		    		did1=id;
					JOptionPane.showMessageDialog(null, "회원가입 할 수 있는 아이디 입니다","Message",JOptionPane.INFORMATION_MESSAGE);
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
			
	jp.add(a);
	jp1.add(a1);
	jp1.add(jf);
	jp12.add(jb);
	JLabel a2= new JLabel(" 비밀번호");
	JTextField jf1= new JTextField(10);
	jp2.add(a2);
	jp2.add(jf1);

	JLabel a5= new JLabel(" 전화번호");
	JTextField jf5= new JTextField(10);
	jp132.add(a5);
	jp132.add(jf5);
	
	JButton jb2= new JButton("뒤로가기");
	 jb2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);	
		}
	 });
    JButton jb3=new JButton("회원가입");
    jb3.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {

		    String id,pwd,phone;
			id=jf.getText();
			pwd=jf1.getText();
			phone=jf5.getText();
			Connection conn = null; // DB연결된 상태(세션)을 담은 객체
		    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
		    PreparedStatement spstm=null;
		    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
		    String sql="insert into 회원 values(?,?,?)";
		    String select="select ID from 회원";
			String user = "sys as sysdba"; 
		    String pw = "Gksmf1238";
		    String url = "jdbc:oracle:thin:@localhost:1521:orcl";	 
		    String did = null;
		    try {
		    Class.forName("oracle.jdbc.driver.OracleDriver");      
		    conn = DriverManager.getConnection(url, user, pw);	       
			spstm=conn.prepareStatement(select);
			rs=spstm.executeQuery();
			while(rs.next()) {
				did= rs.getString("ID");
				if(did.equals(id)) {
					JOptionPane.showMessageDialog(null, "ID가 중복되서 회원가입을 할 수 없습니다","Message",JOptionPane.ERROR_MESSAGE);
					break;
				}
			
			}
			if(!did.equals(id)&&id.equals(did1)) {
		        pstm = conn.prepareStatement(sql);
		    	pstm.setString(1, id); 
		    	pstm.setString(2, pwd); 
		    	pstm.setString(3, phone);
		    	pstm.executeUpdate();
		    	JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다","Message",JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);	
			}else {
				JOptionPane.showMessageDialog(null, "ID중복확인을 완료한 아이디로 입력해주세요","Message",JOptionPane.ERROR_MESSAGE);
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


    jp3.add(jb2);
    jp3.add(jb3);
    c.setLayout(new FlowLayout());
    c.add(jp);
    c.add(jp1);
    c.add(jp12);
    c.add(jp2);
    c.add(jp132);
    c.add(jp3);
    setVisible(true);
    setSize(300,300);
}
	
}
