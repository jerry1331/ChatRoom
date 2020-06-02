package friendlistUI;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import database.UserInfo;
import database.*;

/*
 * 这个是好友列表的面板
 * 构造函数需要传入好友列表信息（ListInfo list）
 */
public class ListPane extends JPanel{

	private static final long serialVersionUID = 1L;
	private ListInfo listInfo;
	
	//	分组好友
	private FriendLabel friends[][];
	
	private byte collectionCount;
	private byte[] bodyCount;
	private byte[][] state;
	
	public ListPane(ListInfo listInfo) {
		super();
		this.listInfo = listInfo;
		initialize();
	}
	//	初始化
	private void initialize() {
		collectionCount = listInfo.getCollectionCount();	//获取分组数目
		bodyCount = listInfo.getBodyCount();	//获取每组好友数目
		String[] listName = listInfo.getListName();	//获取分组名称列表
		int[][] bodyID = listInfo.getBodyNum();	//获取好友ID列表
		String[][] nickName = listInfo.getBodyName();	//获取好友昵称列表
		int[][] bodyAvatar = listInfo.getBodypic();	//获取好友头像列表
		state = listInfo.getBodyState();	//获取好友状态列表
		FriendGroup[] groupList = new FriendGroup[collectionCount];	//生成FriendGroup列表
		friends = new FriendLabel[collectionCount][];	//生成FriendLabel列表
		int i, j;
		for (i = 0; i < collectionCount; i++) {
			friends[i] = new FriendLabel[bodyCount[i]];
			for (j = 0; j < bodyCount[i]; j++) {
				int ID = bodyID[i][j];
				String name = nickName[i][j];
				int avatar = bodyAvatar[i][j];
				byte State = state[i][j];
				friends[i][j] = new FriendLabel(ID, name, avatar, State);
			}
			groupList[i] = new FriendGroup(listName[i], friends[i]);
			this.add(groupList[i]);	//渲染好友分组Label
			for (j = 0; j < bodyCount[i]; j++) {
				this.add(friends[i][j]);	//渲染分组好友Label
			}
		}
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setSize(272, 450);
		this.setLocation(0, 0);
		
	}
	//	显示新信息
	public void HaveNewMsg(int ID){
		for(int i = 0;i < collectionCount; i++){
			for(int j = 0; j < bodyCount[i]; j++){
				if(friends[i][j].getID() == ID){
					friends[i][j].haveMSG();
				}
			}
		}
	}
	
	//	根据用户ID在列表里找对应用户
	public UserInfo findUserByID(int IDNum) {
		UserInfo result = new UserInfo();
		for(int i = 0; i < collectionCount; i++){
			for(int j = 0; j < bodyCount[i]; j++){
				if(friends[i][j].getID() == IDNum){
					result.setIDNum(IDNum);
					result.setNickName(friends[i][j].getNickName());
					result.setAvatar(friends[i][j].getAvatar());
					break;
				}
			}
		}
		return result;
	}
	
	//	刷新列表
	public void Refresh_List(ListInfo new_list){
		byte newCollectionCount = new_list.getCollectionCount();
		byte[] newBodyCount = new_list.getBodyCount();
		byte[][] state = new_list.getBodyState();
		boolean hasNewMember = false;
		boolean hasNewList = false;
		if(newCollectionCount == collectionCount){
			for(int i = 0; i < collectionCount; i++){
				if(newBodyCount[i] != bodyCount[i]){
					hasNewMember = true;
					break;
				}
				for(int j = 0; j < bodyCount[i]; j++){
					friends[i][j].setState(state[i][j]);
				}
			}
		}
		else{
			hasNewList = true;
		}
		if(hasNewMember || hasNewList){
			this.removeAll();
			this.listInfo = new_list;
			initialize();
		}
	}
}
