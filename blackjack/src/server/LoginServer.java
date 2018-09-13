package server;

import java.io.*;
import java.net.*;

import server._ChatServer2.*;

public class LoginServer implements Runnable {
	Database databaseServer;
	ServerSocket serverSocket;
	Socket socket;

	public LoginServer() {
		init();
	}

	public void init() {
		databaseServer = new Database();

	}

	public void run() {
		try {
			serverSocket = new ServerSocket(7000);
			
			while (true) {
				// �α��� �õ� ����
				socket = serverSocket.accept();
				System.out.println("login try");
				LoginReceiver loginReceiver = new LoginReceiver(socket);
				loginReceiver.start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	// ���� �α����� ���� �α���ó���� Threadȭ ��
	class LoginReceiver extends Thread {
		Socket socket;
		DataInputStream in;
		DataOutputStream out;
		String login_request = "";
		int login_result;

		LoginReceiver(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				System.out.println("reading request...");
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				System.out.println("hi");
				login_request = in.readUTF();
				System.out.println("hello");
				System.out.println(login_request);
				login_result = databaseServer.login(idParse(login_request),pwParse(login_request));
				System.out.println(login_result);
//				if(login_result==200) {
//					out.writeInt(200);
//					//�α��� ����
//					//add new Customer to CustomerList
//				}else if(login_result==201) {
//					//���̵� ����
//				}else if(login_result==202){
//					//��й�ȣ Ʋ��
//				}else if(login_result==203) {
//					//Ŭ���̾�Ʈ�� ����
//				}else if(login_result==204) {
//					//������ ����
//				}else {
//					//�� �� ���� ����
//					out.writeInt(205);
//				}
				out.writeInt(200);
				
				socket.close();
				in.close();
				out.close();
			}catch(IOException e) {
				System.out.println(e.toString());
			}
		}

		private String idParse(String login_request) {
			// �α��� ��û ���ڿ����� ���̵� �Ľ��Ͽ� ����
			String[] splits = login_request.split(".");
			if(splits[0].equals("LoginRequest")) {
				return splits[1];
			}else {
				return null;
			}
		}

		private String pwParse(String login_request) {
//			String[] split = login_request.split(".");
//			if(split[0].equals("LoginRequest")) {
//				return split[2];
//			}else {
//				return null;
//			}
			return null;
		}
	}
}
