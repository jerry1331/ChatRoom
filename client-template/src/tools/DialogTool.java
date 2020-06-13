package tools;

import java.io.*;

import chatUI.*;
import dataBase.*;

/*
 * 这个工具主要处理会话相关功能
 * 1.显示消息
 * 2.发送消息
 */
public class DialogTool {
	public static void ShowMessage(int from,String msg){
		
//		  是否已经打开了此对话
		 
		if(DialogDB.dialogDB.containsKey(String.valueOf(from))){
			DialogUI dialog = DialogDB.dialogDB.get(String.valueOf(from));
			dialog.ShowMsg(msg);
			//dialog.LetsShake();
		}
		else{
			UserInfo user = Figures.list.findUserByID(from);
			//
			DialogUI dialog =  new DialogUI(null, from, from);
			DialogRegDelTool.RegDialog(from, dialog);
			dialog.ShowMsg(msg);
		}
	}
	
	public static void SendMessage(int to,String msg){	
		try {
			Figures.cc.sendMsg(to, msg);
			System.out.println("发送消息成功");
		} catch (IOException e) {
			System.out.println("发送消息失败");
			e.printStackTrace();
		}
	}
}
