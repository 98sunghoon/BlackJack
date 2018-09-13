package server;

import java.io.*;
import java.net.*;
import java.util.*;

public class _ChatServer {

	public static ArrayList<PrintWriter> OutputList;

	public static void main(String[] args) {
		OutputList = new ArrayList<PrintWriter>();

		try {
			ServerSocket s_socket = new ServerSocket(8888);

			while (true) {
				Socket c_socket = s_socket.accept();
				_ClientManagerThread c_thread = new _ClientManagerThread();
				c_thread.setSocket(c_socket);

				OutputList.add(new PrintWriter(c_socket.getOutputStream()));
				c_thread.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
