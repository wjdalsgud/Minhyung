import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Manage4 extends JFrame {
	
		Manage4(){
				 setTitle("좌석 관리 화면");
				 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 Container c=getContentPane();
				 c.setLayout(new FlowLayout(FlowLayout.LEFT,5,40));
				 JButton b1=new JButton("뒤로가기");
				 b1.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							setVisible(false);
							new ManagerMain();	
					}
				 });
				 JButton b2=new JButton("좌석 수 변경");
				 b2.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							setVisible(false);
							new ChangeSeat();	
					}
				 });
				 JPanel a= new JPanel();
				 a.setLayout(new FlowLayout(FlowLayout.CENTER,140,40));
				 a.add(b1);
				 a.add(b2);
				 c.add(a);

				setVisible(true);
				setSize(400,400);
		
	}

}
