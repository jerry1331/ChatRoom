package server;

import java.io.*;
import java.net.*;

public class Server {
	ServerSocket server;

	public void setupServer(int port) {
		try {
			server = new ServerSocket(port);
			System.out.println("服务器搭建成功");
			while (true) {
				Socket client = server.accept();
				System.out.println("Incoming" + client.getRemoteSocketAddress());
				ServerThread st = new ServerThread(client);
				st.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeServer() {
		try {
			server.close();
			System.out.println("服务器关闭成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	  用来开启服务器
	 
	public static void main(String[] args) {
		System.out.println("服务器开启");
		Server cs = new Server();
		cs.setupServer(9090);
	}

}
