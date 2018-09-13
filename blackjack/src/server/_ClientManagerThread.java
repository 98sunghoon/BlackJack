package server;

import java.io.*;
import java.net.*;

public class _ClientManagerThread extends Thread {
	private Socket m_socket;
	private String m_ID;

//	public ClientManagerThread(String m_ID) {
//		this.m_ID = m_ID;
//	}

	@Override
	public void run() {
		super.run();
		try {
			BufferedReader tmpbuffer = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));

			String text;

			while (true) {
				text = tmpbuffer.readLine();

				// ÀÔÀå Ã³¸®
				if (text == null) {
					// System.out.println(m_ID + "´ÔÀÌ ÅðÀåÇÏ¼Ì½À´Ï´Ù.");
					for (int i = 0; i < _ChatServer.OutputList.size(); ++i) {
						_ChatServer.OutputList.get(i).println(m_ID + "´ÔÀÌ ÅðÀåÇÏ¼Ì½À´Ï´Ù.");
						_ChatServer.OutputList.get(i).flush();
					}
					break;
				}

				String[] split = text.split("highkrs12345");
				if (split.length == 2 && split[0].equals("ID")) {
					m_ID = split[1];
					System.out.println(m_ID + "´ÔÀÌ ÀÔÀåÇÏ¼Ì½À´Ï´Ù.");
					for (int i = 0; i < _ChatServer.OutputList.size(); ++i) {
						_ChatServer.OutputList.get(i).println(m_ID + "´ÔÀÌ ÀÔÀåÇÏ¼Ì½À´Ï´Ù.");
						_ChatServer.OutputList.get(i).flush();
					}
					continue;
				}

				for (int i = 0; i < _ChatServer.OutputList.size(); ++i) {
					_ChatServer.OutputList.get(i).println(m_ID + "> " + text);
					_ChatServer.OutputList.get(i).flush();
				}
			}
			_ChatServer.OutputList.remove(new PrintWriter(m_socket.getOutputStream()));
			m_socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSocket(Socket socket) {
		m_socket = socket;
	}
}
