package friendlistUI;

import javax.swing.JPanel;

import database.*;

/*
 * 这个是好友列表的面板
 * 构造函数需要传入好友列表信息（ListInfo list）
 */
public class ListPane extends JPanel{

	private static final long serialVersionUID = 1L;
	private ListInfo listInfo;
	
	
	public ListPane(ListInfo list) {
		
		
	}
	//	初始化
	private void initialize() {
		
		
	}
	//	显示新信息
	public void HaveNewMsg(int IDNum){
		
		
	}
	
	//	根据用户ID在列表里找对应用户
	public UserInfo findUserByID(int IDNum) {

		return null;
	}
	
	//	刷新列表
	public void Refresh_List(ListInfo new_list){
		
	}
}
