import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Manager4 extends JFrame {

	Manager4(){
		JFrame frame = new JFrame();
		ImagePanel Background= new ImagePanel(new ImageIcon("C:/Users/pc/eclipse-workspace/PC Reservation ManageMent Program/image/City.jpg").getImage());
		frame.getContentPane().add(Background,BorderLayout.NORTH);
		frame.setTitle("좌석 관리 화면");
		
		
		
		Background.setLayout(null);
		
		JButton back = new JButton ("뒤로 가기");
		back.setLayout(null);
		back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		back.setBackground(Color.LIGHT_GRAY);
		back.setForeground(Color.BLUE);
		back.setBounds(12, 115, 105, 35);
		Background.add(back);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ManagerMain();
				
			}
			
		});
		JButton b2 = new JButton("좌석 수 변경");
		b2.setLayout(null);
		b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b2.setBackground(Color.LIGHT_GRAY);
		b2.setForeground(Color.BLUE);
		b2.setBounds(149, 115, 105, 35);
		Background.add(b2);
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ChangeSeat();
			}
			
		});
		frame.setSize(300,300);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		}
	}
