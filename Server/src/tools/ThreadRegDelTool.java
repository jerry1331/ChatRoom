package tools;

import database.ThreadDB;
import server.ServerThread;

public class ThreadRegDelTool {
	// 注册到线程数据库
	public static void RegThread(ServerThread thread) {
		ThreadDB.threadDB.put(String.valueOf(thread.getUserID()), thread);
	}

	// 从线程数据库中间删除
	public static void DelThread(int UserID) {
//			System.out.println("Del ID");
		ThreadDB.threadDB.remove(String.valueOf(UserID));
	}

}
