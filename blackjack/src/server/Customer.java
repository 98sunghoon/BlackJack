package server;

import java.io.*;
import java.net.*;

public class Customer {
	private String id;
	private String pw;
	private String name;
	private int balance;
	Socket socket;
	
	public Customer() {
		try {
			socket = new Socket("",7010);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
