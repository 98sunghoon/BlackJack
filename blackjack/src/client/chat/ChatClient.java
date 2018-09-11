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
	public static String UserID = "[guest"+ (new Date().getTime())%11+"]";
	
	private TextArea chat_log_panel;
	private TextArea send_textarea;
	private JButton send_btn;
	private PrintWriter sendWriter;
	
	String sendString;
	
	public ChatClient(TextArea chat_log_panel,TextArea send_textarea,JButton send_btn) {
		setLogPanel(chat_log_panel);
		setSendArea(send_textarea);
		setSendBtn(send_btn);
		
		try {
			Socket c_socket = new Socket("192.168.181.1",8888);
			
			ReceiveThread rec_thread = new ReceiveThread(chat_log_panel);
			rec_thread.setSocket(c_socket);
			rec_thread.start();
			sendWriter = new PrintWriter(c_socket.getOutputStream());
			setSendBtnListener();
			
		}catch(UnknownHostException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void setSendBtnListener() {
		send_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sendWriter.println("IDhighkrs12345" + ChatClient.UserID);
				sendWriter.flush();
				sendString = send_textarea.getText();
				sendWriter.println(sendString);
				sendWriter.flush();
				
				chat_log_panel.append(ChatClient.UserID+">>"+sendString+"\n");
				send_textarea.setText("");
			}
		});
	}
	
	public void setLogPanel(TextArea chat_log_panel) {
		this.chat_log_panel = chat_log_panel;
	}
	public void setSendArea(TextArea send_textarea) {
		this.send_textarea = send_textarea;
	}
	public void setSendBtn(JButton send_btn) {
		this.send_btn = send_btn;
	}
}
