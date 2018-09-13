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
				// 로그인 시도 감지
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

	// 동시 로그인을 위해 로그인처리를 Thread화 함
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
//					//로그인 성공
//					//add new Customer to CustomerList
//				}else if(login_result==201) {
//					//아이디 없음
//				}else if(login_result==202){
//					//비밀번호 틀림
//				}else if(login_result==203) {
//					//클라이언트측 오류
//				}else if(login_result==204) {
//					//서버측 오류
//				}else {
//					//알 수 없는 오류
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
			// 로그인 요청 문자열에서 아이디를 파싱하여 리턴
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
