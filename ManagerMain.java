package dbj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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


public class ManagerMain extends JFrame{
	ManagerMain(){ 
		JFrame frame = new JFrame();
		ImagePanel Background= new ImagePanel(new ImageIcon("C:/Users/82105/eclipse-workspace/dbj/image/Clock.jpg").getImage());
		frame.getContentPane().add(Background,BorderLayout.NORTH);
		frame.setTitle("관리자 메인 화면");

		JLabel MainLabel = new JLabel("관리자 메인 화면");
		MainLabel.setLayout(null);
		MainLabel.setBounds(83, 10, 171, 52);
		MainLabel.setFont(new Font("HY수평선",Font.BOLD,15));
		MainLabel.setForeground(Color.WHITE);
		Background.add(MainLabel);

		JButton Confirm_btn = new JButton("회원 확인");
		 Confirm_btn.setLayout(null);
		 Confirm_btn.setBounds(12, 72, 107, 42);
		 Background.add(Confirm_btn);
		 Confirm_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new Manage1();
			}
		});
		 JButton Delete_member_btn = new JButton("회원 삭제");
		 Delete_member_btn.setLayout(null);
			Delete_member_btn.setBounds(12, 138, 107, 42);
			Background.add(Delete_member_btn);
			Delete_member_btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
					new Manage2();

			}
		});
			JButton Revise_member_btn = new JButton("회원 수정");
			Revise_member_btn.setLayout(null);
			Revise_member_btn.setBounds(12, 209, 107, 42);
			Background.add(Revise_member_btn);
			Revise_member_btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
					new Manage3();
				
			}
		});
			JButton Manage_seat_btn = new JButton("좌석 관리");
			Manage_seat_btn.setLayout(null);
			Manage_seat_btn.setBounds(12, 283, 107, 42);
			Background.add( Manage_seat_btn);
			Manage_seat_btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
					new Manage4();
				
			}
		});
			 JButton Logout_btn = new JButton("로그아웃");
			 Logout_btn .setLayout(null);
			 Logout_btn.setBounds(193, 72, 107, 42);
			 Background.add(Logout_btn);
			 Logout_btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
					new Manager();

			}
		 });
			 JButton Exit_btn = new JButton("종료");
			 Exit_btn.setLayout(null);
			 Exit_btn.setBounds(193, 138, 107, 42);
			 Background.add(Exit_btn);
			 Exit_btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
					System.exit(0);
			}
		 });
				JButton Signup_btn= new JButton("회원가입");
				Signup_btn.setLayout(null);
				Signup_btn.setBounds(193,290,107,42);
				 Background.add(Signup_btn);
				Signup_btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
					     setVisible(false);
						new ManagerID();
						
					}
					
				});
			 JButton go_user = new JButton("사용자 화면으로");
			 go_user.setLayout(null);
			 go_user.setBounds(193, 209, 107, 42);
			 Background.add(go_user);
			 go_user.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
					new Login();

			}
		 });

				frame.setSize(400,500);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			}
