package tools;

import chatUI.DialogUI;
import database.DialogDB;

public class DialogRegDelTool {
	
//	  �����ݿ���ע���Dialog
	 
	public static void RegDialog(int IDNum,DialogUI dialog){
		DialogDB.dialogDB.put(String.valueOf(IDNum), dialog);
	}
	
//	  �����ݿ���ɾ����Dialog
	 
	public static void DelDialog(int IDNum){
		DialogDB.dialogDB.remove(String.valueOf(IDNum));
	}
}
