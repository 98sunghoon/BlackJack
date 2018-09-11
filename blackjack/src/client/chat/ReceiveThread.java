package client.chat;

import java.awt.*;
import java.io.*;
import java.net.*;

public class ReceiveThread extends Thread{
	private Socket m_Socket;
	
	private TextArea chat_log_panel;
	
	public ReceiveThread(TextArea chat_log_panel) {
		// TODO Auto-generated constructor stub
		this.chat_log_panel = chat_log_panel;
	}
	
	@Override
	public void run() {
		super.run();
		
		try {
			BufferedReader tmpbuf = new BufferedReader(new InputStreamReader(m_Socket.getInputStream()));
			
			String receiveString;
			String[] split;
			
			while(true) {
				receiveString = tmpbuf.readLine();
				
				split = receiveString.split(">");
				if(split.length >= 2 && split[0].equals(ChatClient.UserID)) {
					continue;
				}
				
				chat_log_panel.append(receiveString+"\n");
			}
		}catch(IOException e) {
		}
	}
	
	public void setSocket(Socket socket) {
		m_Socket = socket;
	}
}
