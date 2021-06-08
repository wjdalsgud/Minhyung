import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Reservation3 extends JFrame{
		Reservation3(){
			 setTitle("예약확인화면");
			 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 Container c=getContentPane();
			 c.setLayout(new FlowLayout(FlowLayout.LEFT,5,40));
			 JLabel jl=new JLabel();
if(LoginNextMain.dseat==0) {
	  jl=new JLabel("현재 예약하신 좌석이 없습니다.");
}else {
	  jl=new JLabel("현재 예약된 좌석은 "+LoginNextMain.dseat+"번입니다.");
    }
			 JButton b1=new JButton("뒤로가기");
			 b1.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						new LoginNextMain();	
				}
			 });
			 JPanel a= new JPanel();
			 a.setLayout(new FlowLayout(FlowLayout.CENTER,140,40));
			 a.add(jl);
			 a.add(b1);
			 c.add(a);

			setVisible(true);
			setSize(800,400);
		}
}
