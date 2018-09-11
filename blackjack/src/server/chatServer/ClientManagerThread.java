package server.chatServer;

import java.io.*;
import java.net.*;

public class ClientManagerThread extends Thread{
	private Socket m_socket;
	private String m_ID;
	
	@Override
	public void run() {
		super.run();
		try {
			BufferedReader tmpbuffer = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));
			
			String text;
			
			while(true) {
				text = tmpbuffer.readLine();
				
				if(text == null) {
					System.out.println(m_ID + "´ÔÀÌ ÅðÀåÇÏ¼Ì½À´Ï´Ù.");
					for(int i = 0;i<ChatServer.OutputList.size();++i) {
						ChatServer.OutputList.get(i).println(m_ID + "´ÔÀÌ ÅðÀåÇÏ¼Ì½À´Ï´Ù.");
						ChatServer.OutputList.get(i).flush();
					}
					break;
				}
				
				String[] split = text.split("highkrs12345");
				if(split.length == 2 && split[0].equals("ID")) {
					m_ID = split[1];
					System.out.println(m_ID + "´ÔÀÌ ÀÔÀåÇÏ¼Ì½À´Ï´Ù.");
					for(int i = 0;i<ChatServer.OutputList.size();++i) {
						ChatServer.OutputList.get(i).println(m_ID + "´ÔÀÌ ÀÔÀåÇÏ¼Ì½À´Ï´Ù.");
						ChatServer.OutputList.get(i).flush();
					}
					continue;
				}
				
				for(int i = 0;i<ChatServer.OutputList.size();++i) {
					ChatServer.OutputList.get(i).println(m_ID + "> " + text);
					ChatServer.OutputList.get(i).flush();
				}
			}
			ChatServer.OutputList.remove(new PrintWriter(m_socket.getOutputStream()));
			m_socket.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setSocket(Socket socket) {
		m_socket = socket;
	}
}
