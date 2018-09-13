package client;

import java.awt.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;

public class MainFrame extends JFrame {
	// Screens
	private Panel_login panel_login;
	private Panel_rooms panel_rooms;
	
	public MainFrame() {
		init();
		setResizable(false);
		setVisible(true);
	}

	private void init() {
		//ȭ�� ����
		setTitle("BlackJack for ITM");
		setSize(1000, 800);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//panel �ʱ�ȭ
		panel_login = new Panel_login(this);
		panel_rooms = new Panel_rooms(this);
		
		//�ʱ� panel
		add(panel_login);
		
	}

	protected void changePanel(String panelName) {
		if (panelName == null) {
			return;
		}

		getContentPane().removeAll();

		if (panelName.equals("panel_main")) {
			setSize(1000,800);
			getContentPane().add(panel_login);
		} else if (panelName.equals("panel_rooms")) {
			setSize(1300,800);
			getContentPane().add(panel_rooms);
		} else if(panelName.equals("panel_game")) {
			setSize(1300,800);
			
		}

		revalidate();
		repaint();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame mainFrame = new MainFrame();
	}

}
