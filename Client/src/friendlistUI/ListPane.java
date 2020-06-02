package friendlistUI;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import database.UserInfo;
import database.*;

/*
 * ����Ǻ����б�����
 * ���캯����Ҫ��������б���Ϣ��ListInfo list��
 */
public class ListPane extends JPanel{

	private static final long serialVersionUID = 1L;
	private ListInfo listInfo;
	
	//	�������
	private FriendLabel friends[][];
	
	private byte collectionCount;
	private byte[] bodyCount;
	private byte[][] state;
	
	public ListPane(ListInfo listInfo) {
		super();
		this.listInfo = listInfo;
		initialize();
	}
	//	��ʼ��
	private void initialize() {
		collectionCount = listInfo.getCollectionCount();	//��ȡ������Ŀ
		bodyCount = listInfo.getBodyCount();	//��ȡÿ�������Ŀ
		String[] listName = listInfo.getListName();	//��ȡ���������б�
		int[][] bodyID = listInfo.getBodyNum();	//��ȡ����ID�б�
		String[][] nickName = listInfo.getBodyName();	//��ȡ�����ǳ��б�
		int[][] bodyAvatar = listInfo.getBodypic();	//��ȡ����ͷ���б�
		state = listInfo.getBodyState();	//��ȡ����״̬�б�
		FriendGroup[] groupList = new FriendGroup[collectionCount];	//����FriendGroup�б�
		friends = new FriendLabel[collectionCount][];	//����FriendLabel�б�
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
			this.add(groupList[i]);	//��Ⱦ���ѷ���Label
			for (j = 0; j < bodyCount[i]; j++) {
				this.add(friends[i][j]);	//��Ⱦ�������Label
			}
		}
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setSize(272, 450);
		this.setLocation(0, 0);
		
	}
	//	��ʾ����Ϣ
	public void HaveNewMsg(int ID){
		for(int i = 0;i < collectionCount; i++){
			for(int j = 0; j < bodyCount[i]; j++){
				if(friends[i][j].getID() == ID){
					friends[i][j].haveMSG();
				}
			}
		}
	}
	
	//	�����û�ID���б����Ҷ�Ӧ�û�
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
	
	//	ˢ���б�
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
