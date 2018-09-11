package client;

import java.awt.*;

import javax.swing.*;

import client.chat.*;

public class Panel_rooms extends JPanel{
	private MainFrame mainFrame;
	
	//GUI
	private JPanel room_panel;
	private JPanel chat_panel;
	private JPanel chat_info_panel;
	private TextArea chat_log_panel;
	private JPanel chat_send_panel;
	private TextArea send_textarea;
	private JButton send_btn;
	
	//process
	private ChatClient chatClient;
	
	public Panel_rooms(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		init();
		
		send_textarea.requestFocus();
		
		//채팅 연결
		chatClient = new ChatClient(chat_log_panel,send_textarea,send_btn);
	}
	
	private void init() {
		//컴포넌트 초기화
		room_panel = new JPanel();
		chat_panel = new JPanel();
		chat_info_panel = new JPanel();
		chat_log_panel = new TextArea();
		chat_send_panel = new JPanel();
		send_textarea = new TextArea();
		send_btn = new JButton("보내기");
		
		//컴포넌트 추가
		setLayout(null);
		add(room_panel);
		add(chat_panel);

		//패널 크기 설정
		room_panel.setBounds(0, 0, mainFrame.getWidth()*6/10,mainFrame.getHeight());
		int chat_panel_width = (mainFrame.getWidth()*4)/10;
		chat_panel.setBounds(mainFrame.getWidth()*6/10, 0, chat_panel_width,mainFrame.getHeight());
		chat_info_panel.setPreferredSize(new Dimension(chat_panel_width,100));
		chat_send_panel.setPreferredSize(new Dimension(chat_panel_width,220));

		//chat_info_panel
		
		//chat_send_panel
		chat_send_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		chat_log_panel.setFont(new Font("Serif", Font.PLAIN, 20));
		chat_log_panel.setEditable(false);
		
		send_textarea.setFont(new Font("Serif", Font.PLAIN, 20));
		send_textarea.setPreferredSize(new Dimension(chat_panel_width-150,165));
		chat_send_panel.add(send_textarea);
		
		send_btn.setPreferredSize(new Dimension(135,165));
		chat_send_panel.add(send_btn);
		

		//chat_panel
		chat_panel.setLayout(new BorderLayout(8,8));
		chat_panel.add(chat_info_panel,BorderLayout.NORTH);
		chat_panel.add(chat_send_panel,BorderLayout.SOUTH);
		chat_panel.add(chat_log_panel,BorderLayout.CENTER);
		
		
		//left container
		room_panel.setBackground(Color.black);
				

	}
	
//	private
}
