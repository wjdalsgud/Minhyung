import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ManagerMain extends JFrame{
ManagerMain(){
	setTitle("관리자 메인 화면");
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 Container c=getContentPane();
	 JPanel q1= new JPanel();
	 JLabel j=new JLabel("관리자 메인 화면");
	 q1.add(j);
	 JButton jb=new JButton("회원 확인");
		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Manage1();
			}
		});
	 JButton jb1=new JButton("회원 삭제");
		jb1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Manage2();
			}
		});
	 JButton jb2=new JButton("회원 수정");
		jb2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Manage3();
			}
		});
	 JButton jb3=new JButton("좌석 관리");
		jb3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
		});
		JButton abcd= new JButton("로그아웃");
		 abcd.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new Manager();	
			}
		 });
		 JButton abcd11= new JButton("사용자 화면으로");
		 abcd11.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new Login();	
			}
		 });
		 JButton abcd1= new JButton("종료");
		 abcd1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					System.exit(0);
			}
		 });
		 c.setLayout(new BorderLayout());
	 q1.add(jb);
	 q1.add(jb1);
	 q1.add(jb2);
	 q1.add(jb3);
	 q1.add(abcd);
	 q1.add(abcd1);
	 q1.add(abcd11);
	 c.add(q1);
	 setSize(400,500);
	 setVisible(true);
}
}
