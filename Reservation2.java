import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Reservation2 extends JFrame{
	JCheckBox[] place=new JCheckBox[14];
	String[] name = {"1번자리","2번자리","3번자리","4번자리","5번자리","6번자리","7번자리","8번자리","9번자리","10번자리","11번자리","12번자리","13번자리","14번자리"};
		Reservation2(){
			 setTitle("예약취소화면");
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
			 JButton b2=new JButton("예약취소하기");
			 b2.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
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
