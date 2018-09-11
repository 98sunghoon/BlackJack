package client;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Panel_main extends JPanel {
	private MainFrame mainFrame;

	private JPanel login_panel;
	private JLabel id_label;
	private TextField id_field;
	private JLabel password_label;
	private JPasswordField password_field;
	public JButton login_btn;
	public JButton signup_btn;

	private Image background;

	public Panel_main(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		init();

	}

	private void init() {
		// 이미지 초기화
		background = new ImageIcon(".\\src\\rsc\\background.png").getImage();

		// 컴포넌트 초기화
		login_panel = new JPanel();
		id_label = new JLabel("ID : ");
		id_field = new TextField(20);
		password_label = new JLabel("PASSWORD : ");
		password_field = new JPasswordField(20);
		login_btn = new JButton("LogIn");
		signup_btn = new JButton("SignUp");

		// 레이아웃 구성
		this.setLayout(null);
		login_panel.setBounds(299, 400, 402, 400);
		this.add(login_panel);

		login_panel.setOpaque(false);
		login_panel.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 10));

		id_label.setFont(new Font("Serif", Font.BOLD, 20));
		id_label.setPreferredSize(new Dimension(147, 35));
		login_panel.add(id_label);

		id_field.setFont(new Font("Serif", Font.BOLD, 20));
		id_field.setPreferredSize(new Dimension(247, 35));
		login_panel.add(id_field);

		password_label.setFont(new Font("Serif", Font.BOLD, 20));
		password_label.setPreferredSize(new Dimension(147, 35));
		login_panel.add(password_label);

		password_field.setFont(new Font("Abadi", Font.BOLD, 12));
		password_field.setPreferredSize(new Dimension(247, 35));
		login_panel.add(password_field);

		login_btn.setPreferredSize(new Dimension(250, 50));
		login_panel.add(login_btn);

		signup_btn.setPreferredSize(new Dimension(130, 50));
		login_panel.add(signup_btn);

		// 리스너 부착
		id_field.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				password_field.requestFocus();
			}
		});
		password_field.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				login_btn.doClick();
			}
		});
		login_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				login();
			}
		});
		signup_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				signup();
			}
		});

	}

	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		setOpaque(false);
		super.paintComponent(g);
	}

	public void login() {
		mainFrame.changePanel("panel_rooms");
	}

	public void signup() {
		//클래스 만들어서 생성//경고창 어떻게?
	}

}