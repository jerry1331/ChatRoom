package tools;

import chatUI.DialogUI;
import dataBase.DialogDB;

/*
 * 这个工具用于从向数据库中注册或者删除会话窗口
 */
public class DialogRegDelTool {
	/*
	 * 向数据库中注册DialogUI
	 */
	public static void RegDialog(int IDNum,DialogUI dialog){
		DialogDB.dialogDB.put(String.valueOf(IDNum), dialog);
	}
	/*
	 * 从数据库中删除DialogUI
	 */
	public static void DelDialog(int IDNum){
		DialogDB.dialogDB.remove(String.valueOf(IDNum));
	}
}
