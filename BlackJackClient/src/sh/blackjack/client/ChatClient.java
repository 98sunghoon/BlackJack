package sh.blackjack.client;

import java.awt.*;
import java.io.*;

import javax.swing.*;

public class ChatClient extends Thread {
	Client client;
	JTextArea chat_log;
	private String spliter = "y12z0f28s0x0asdf9";

	public ChatClient(Client client, JTextArea chat_log) {
		this.client = client;
		this.chat_log = chat_log;
		Thread receiver = new Thread(new Receiver());
		receiver.start();
	}

	public void sendMsg(String msg) {
		String sendMsg = "chat" + spliter + client.getUsername() + spliter + msg;
		try {
			client.out.writeUTF(sendMsg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run() {

	}

	class Receiver implements Runnable {
		public void run() {
			try {
				while (client.in != null) {
					chat_log.append(client.in.readUTF() + "\n");
				}
			} catch (IOException e) {

			}

		}
	}
}
