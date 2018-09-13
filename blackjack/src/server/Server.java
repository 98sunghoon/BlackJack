package server;

import java.util.*;

public class Server {
	private LoginServer loginServer;
	private Database database;
	private GameServer gameServer;	
	
	public static ArrayList<Customer> Customers;
	
	Server() {
		Customers = new ArrayList<Customer>();
		loginServer = new LoginServer();
		database = new Database();
		gameServer = new GameServer();
		
		loginServer.run();
		gameServer.run();
	}
	
	public static void main(String[] args) {
		Server server = new Server();
	}
}
