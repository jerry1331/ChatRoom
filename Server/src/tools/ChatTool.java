package tools;

import java.io.*;

import database.*;
import server.*;

/*
 * 这个工具用来实于以下功能
 * 1.向目的ID号发送信息
 * 2.将未发送成功的信息存在服务器
 */
public class ChatTool {

//	发送信息
	public static boolean sendMsg(int from,int to, String msg) {
		ServerThread st = ThreadDB.threadDB.get(String.valueOf(to));
		
		if(st == null ) {
			System.out.println("目标不在线");
			return false;
		}
		
		try {
			st.sendMsg(from, msg);
//			System.out.println("Finish Sendding");
			return true;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
//	尚未实现
	public static void saveOnServer(int from,int to, String Msg) {

	}
}
