package friendlistUI;

import javax.swing.ImageIcon;

import javax.swing.JPanel;

import chatUI.DialogUI;
import database.DialogDB;
import tools.DialogRegDelTool;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;


public class FriendLabel extends JLabel{

	private static final long serialVersionUID = 1L;
	//	容器
	public JPanel jContainer = new JPanel();
	//	ID
	private JLabel jID = null;
	//	昵称
	private JLabel jNickName = null;
	//	状态
	private JLabel jState;
	
	
	//	ID
	private int ID;
	//	昵称
	private String nickname;
	//	头像
	private int avatar;
	//	
	private boolean isExit = true;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNickName() {
		return nickname;
	}
	public void setNickName(String nickname) {
		this.nickname = nickname;
	}
	//	获取头像
	public int getAvatar() {
		return avatar;
	}
	//	设置头像
	public void setAvatar(int avatar) {
		this.avatar = avatar;
	}
	
	public FriendLabel(int ID, String nickname, int avatar, byte state) {
		this.setID(ID);
		this.avatar = avatar;
		this.nickname = nickname;
		setBackground(Color.darkGray);
		
		/*
		 * 设置ID
		 */
		this.jID = new JLabel();
		this.jID.setForeground(Color.WHITE);
		this.jID.setBounds(new Rectangle(70, 38, 150, 20));
		this.jID.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		this.jID.setText("ID:(" + ID + ")");
		
		/*
		 * 设置昵称
		 */
		this.jNickName = new JLabel();
		this.jNickName.setForeground(Color.WHITE);
		this.jNickName.setBounds(new Rectangle(70, 10, 95, 20));
		this.jNickName.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 18));
		this.jNickName.setText(nickname);

		/*
		 * 设置用户头像
		 */
		JButton UserIcon = new JButton();
		UserIcon.setBorder(null);
		UserIcon.setBounds(10, 10, 50, 50);
		UserIcon.setIcon(new ImageIcon("img/AvatarImg/" + avatar + ".jpg"));

		/*
		 * 设置是否在线
		 */
		String SState;
		if (state == 0)
			SState = "在线";
		else
			SState = "离线";
		this.jState = new JLabel();
		this.jState.setText(SState);
		this.jState.setForeground(Color.WHITE);
		this.jState.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 18));
		this.jState.setBounds(new Rectangle(70, 10, 95, 20));
		this.jState.setBounds(180, 10, 95, 20);
		add(this.jState);

		/*
		 * 渲染容器
		 */
		setIcon(new ImageIcon("img/memberBGOff.jpg"));

		setSize(new Dimension(272, 70));
		setLayout(null);
		add(UserIcon);
		add(jID);
		add(jNickName);
		
		/*
		 * 增加鼠标事件
		 */
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(isExit){
					setIcon(new ImageIcon("img/memberBGOff.jpg"));
				}
				else{
					setIcon(new ImageIcon("img/memberBGOn.jpg"));
				}
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setIcon(new ImageIcon("img/memberBGOff.jpg"));
				isExit = true;
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setIcon(new ImageIcon("img/memberBGOn.jpg"));
				isExit = false;
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				DialogUI dialog;
				if(DialogDB.dialogDB.containsKey(String.valueOf(ID))){
					dialog = DialogDB.dialogDB.get(String.valueOf(ID));
					// 抖动
					dialog.LetsShake();
				}
				else{
					dialog = new DialogUI(nickname,avatar,ID);
					DialogRegDelTool.RegDialog(ID, dialog);
				}
				
			}
		});
	}
	
	/*
	 * 当有来自某位好友的信息
	 */
	public void haveMSG(){
//		System.out.println("have a msg");
		setIcon(new ImageIcon("img/memberBGMsg.jpg"));
	}
	
	public void setState(byte state){
		String tmpState;
		if (state == 0)
			tmpState = "在线";
		else
			tmpState = "离线";
		jState.setText(tmpState);
	}
}
