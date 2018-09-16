package client.chat;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.net.UnknownHostException;
import java.rmi.*;
import java.util.*;

import javax.swing.*;

public class ChatClient {
	public static String UserID = "[guest" + (new Date().getTime()) % 11 + "]";

	private TextArea chat_log_panel;
	private TextField send_field;
	private JButton send_btn;
	private PrintWriter sendWriter;

	String sendString;

	public ChatClient(TextArea chat_log_panel, TextField send_field, JButton send_btn) {
		setLogPanel(chat_log_panel);
		setSendArea(send_field);
		setSendBtn(send_btn);

		try {
			Socket c_socket = new Socket("192.168.181.1", 8888);

			ReceiveThread rec_thread = new ReceiveThread(chat_log_panel);
			rec_thread.setSocket(c_socket);
			rec_thread.start();
			sendWriter = new PrintWriter(c_socket.getOutputStream());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void sendMessage(String s) {
		sendWriter.println("IDhighkrs12345" + ChatClient.UserID + ">");
		sendWriter.flush();
		sendString = s;
		sendWriter.println(sendString);
		sendWriter.flush();

//		chat_log_panel.append(ChatClient.UserID + ">>" + sendString + "\n");

	}
	


	public void setLogPanel(TextArea chat_log_panel) {
		this.chat_log_panel = chat_log_panel;
	}

	public void setSendArea(TextField send_field) {
		this.send_field = send_field;
	}

	public void setSendBtn(JButton send_btn) {
		this.send_btn = send_btn;
	}
}
