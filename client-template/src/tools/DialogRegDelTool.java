package tools;

import chatUI.DialogUI;
import dataBase.DialogDB;

/*
 * ����������ڴ������ݿ���ע�����ɾ���Ự����
 */
public class DialogRegDelTool {
	/*
	 * �����ݿ���ע��DialogUI
	 */
	public static void RegDialog(int IDNum,DialogUI dialog){
		DialogDB.dialogDB.put(String.valueOf(IDNum), dialog);
	}
	/*
	 * �����ݿ���ɾ��DialogUI
	 */
	public static void DelDialog(int IDNum){
		DialogDB.dialogDB.remove(String.valueOf(IDNum));
	}
}
