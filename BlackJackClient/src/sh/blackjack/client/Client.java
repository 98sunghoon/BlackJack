package sh.blackjack.client;

import java.io.*;
import java.net.*;

import javax.swing.*;

import sh.blackjack.client.ChatClient.*;
import sh.blackjack.client.view.*;

public class Client extends Thread {
	private String id;
	private String pw;
	private String username;
	private int balance;

	public static final String serverIP = "117.17.187.54";

	Socket socket;
	DataInputStream in;
	DataOutputStream out;
	// ObjectInputStream inObj;
	Socket objSocket;
//	ObjectOutputStream objout;


	private WaitingRoomController waitingRoomController;
	private Thread receiver;

	public Client() {
	}

	public void setWaitingRoomController(WaitingRoomController waitingRoomController) {
		this.waitingRoomController = waitingRoomController;
		waitingRoomController.setUserInfo(username, id);
		waitingRoomController.setBalance(balance);

	}

	public void connect(String username, int balance, Socket socket) {
		this.username = username;
		this.balance = balance;
		this.socket = socket;
		try {
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
//			objout = new ObjectOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			System.out.println("error");
		}
	}

	public String getUsername() {
		return username;
	}

	public int LogIn(String id, String pw) {
		this.id = id;
		this.pw = pw;

		TextManager tm = new TextManager();
		Socket socket = null;
		DataOutputStream out = null;
		DataInputStream in = null;

		int login_result = -1;
		try {
			socket = new Socket(serverIP, 7000);
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			// login request send
			out.writeUTF(tm.makeLoginRequest(id, pw));
			// login result receive
			// int count = 0;
			do {
				login_result = in.readInt();
				// count++;
				// if(count == 5000) {
				// throw new Exception();
				// }
			} while (login_result == -1);
		} catch (UnknownHostException e) {
		} catch (IOException e) {
			// 서버 닫힘
			System.out.println("서버에 연결할 수 없습니다.");
		} catch (Exception e) {
		}

		if (login_result == 200) {
			String username = "default";
			int balance = -1;
			try {
				do {
					username = in.readUTF();
					balance = in.readInt();
				} while (balance == -1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.connect(username, balance, socket);
			return 200;
		} else if (login_result == 201) {
		} else {
			System.out.println("failed");
		}

		return 1;
	}

	public void activateChat() {
		receiver = new Thread(new Receiver());
		receiver.start();
	}

	public void sendMsg(String msg) {
		TextManager tm = new TextManager();
		String sendMsg = tm.makeChatMsg(getUsername(), msg);
		try {
			out.writeUTF(sendMsg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Receiver implements Runnable {
		public void run() {
			try {
				while (in != null) {
					if (waitingRoomController != null) {
						waitingRoomController.showMsg(in.readUTF() + "\n");
					}
				}
			} catch (IOException e) {
				System.out.println("서버가 종료되었습니다.");
			}

		}
	}

}
