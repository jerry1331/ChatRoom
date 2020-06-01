package tools;

import chatUI.DialogUI;
import database.DialogDB;

public class DialogRegDelTool {
	
//	  向数据库中注册此Dialog
	 
	public static void RegDialog(int IDNum,DialogUI dialog){
		DialogDB.dialogDB.put(String.valueOf(IDNum), dialog);
	}
	
//	  从数据库中删除此Dialog
	 
	public static void DelDialog(int IDNum){
		DialogDB.dialogDB.remove(String.valueOf(IDNum));
	}
}
