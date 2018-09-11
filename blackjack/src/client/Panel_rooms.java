package client;

import java.awt.*;

import javax.swing.*;

public class Panel_rooms extends JPanel{
	private MainFrame mainFrame;
	
	private JPanel room_panel;
	private JPanel chat_panel;
	private JPanel chat_info_panel;
	private JPanel chat_log_panel;
	private JPanel chat_send_panel;
	
	public Panel_rooms(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		init();
	}
	
	private void init() {
		//컴포넌트 초기화
		room_panel = new JPanel();
		chat_panel = new JPanel();
		chat_info_panel = new JPanel();
		chat_log_panel = new JPanel();
		chat_send_panel = new JPanel();
		
		//컴포넌트 추가
		setLayout(null);
		add(room_panel);
		add(chat_panel);
		add(chat_info_panel);
		add(chat_log_panel);
		add(chat_send_panel);

		//패널 분할
		room_panel.setBounds(0, 0, mainFrame.getWidth()*6/10,mainFrame.getHeight());
		chat_panel.setBounds(mainFrame.getWidth()*6/10, 0, mainFrame.getWidth()*4/10,mainFrame.getHeight());
		chat_info_panel.setPreferredSize(new Dimension(mainFrame.getWidth()*4/10,100));
		chat_log_panel.setPreferredSize(new Dimension(mainFrame.getWidth()*4/10,500));
		chat_send_panel.setPreferredSize(new Dimension(mainFrame.getWidth()*4/10,200));
		
		//left container
		room_panel.setBackground(Color.black);
		
		//right container
		chat_panel.add(chat_info_panel);
		chat_panel.add(chat_log_panel);
		chat_panel.add(chat_send_panel);
		
		
		
	}
	
}
