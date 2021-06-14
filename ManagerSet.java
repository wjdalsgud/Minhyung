import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ManagerSet extends JFrame{

	ManagerSet(){
		JFrame frame = new JFrame();
		ImagePanel Background= new ImagePanel(new ImageIcon("C:/Users/82105/eclipse-workspace/dbj/image/Clock.jpg").getImage());
		frame.getContentPane().add(Background,BorderLayout.NORTH);
		frame.setTitle("예약확인화면");

		Background.setLayout(null);

		JLabel j1 = new JLabel();

		if(LoginNextMain.dseat==0) {
			  j1=new JLabel("현재 예약하신 좌석이 없습니다.");
		}else {
			  j1=new JLabel("현재 예약된 좌석은 "+LoginNextMain.dseat+"번입니다.");
		    }
		j1.setFont(new Font("HT수평선",Font.BOLD,13));
		j1.setForeground(Color.WHITE);
		j1.setBounds(102, 138, 214, 43);
		Background.add(j1);

		JButton btnNewButton = new JButton("뒤로 가기");
		btnNewButton.setLayout(null);
		btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setBounds(284, 131, 111, 57);
		Background.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

	
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ManagerMain();

			}

		});

		frame.setSize(450, 500);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
