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
	//	����
	public JPanel jContainer = new JPanel();
	//	ID
	private JLabel jID = null;
	//	�ǳ�
	private JLabel jNickName = null;
	//	״̬
	private JLabel jState;
	
	
	//	ID
	private int ID;
	//	�ǳ�
	private String nickname;
	//	ͷ��
	private int avatar;
	//	
	private boolean is_exit = true;
	
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
	//	��ȡͷ��
	public int getAvatar() {
		return avatar;
	}
	//	����ͷ��
	public void setAvatar(int avatar) {
		this.avatar = avatar;
	}
	
	public FriendLabel(int ID, String nickname, int avatar, byte state) {
		this.setID(ID);
		this.avatar = avatar;
		this.nickname = nickname;
		setBackground(Color.darkGray);
		
		/*
		 * ����ID
		 */
		this.jID = new JLabel();
		this.jID.setForeground(Color.WHITE);
		this.jID.setBounds(new Rectangle(70, 38, 150, 20));
		this.jID.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		this.jID.setText("ID:(" + ID + ")");
		
		/*
		 * �����ǳ�
		 */
		this.jNickName = new JLabel();
		this.jNickName.setForeground(Color.WHITE);
		this.jNickName.setBounds(new Rectangle(70, 10, 95, 20));
		this.jNickName.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 18));
		this.jNickName.setText(nickname);

		/*
		 * �����û�ͷ��
		 */
		JButton UserIcon = new JButton();
		UserIcon.setBorder(null);
		UserIcon.setBounds(10, 10, 50, 50);
		UserIcon.setIcon(new ImageIcon("img/AvatarImg/" + avatar + ".jpg"));

		/*
		 * �����Ƿ�����
		 */
		String SState;
		if (state == 0)
			SState = "����";
		else
			SState = "����";
		this.jState = new JLabel();
		this.jState.setText(SState);
		this.jState.setForeground(Color.WHITE);
		this.jState.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 18));
		this.jState.setBounds(new Rectangle(70, 10, 95, 20));
		this.jState.setBounds(180, 10, 95, 20);
		add(this.jState);

		/*
		 * ��Ⱦ����
		 */
		setIcon(new ImageIcon("img/ListImg/memberBGOff.jpg"));

		setSize(new Dimension(272, 70));
		setLayout(null);
		add(UserIcon);
		add(jID);
		add(jNickName);
		
		/*
		 * ��������¼�
		 */
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(is_exit){
					setIcon(new ImageIcon("img/ListImg/memberBGOff.jpg"));
				}
				else{
					setIcon(new ImageIcon("img/ListImg/memberBGOn.jpg"));
				}
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setIcon(new ImageIcon("img/ListImg/memberBGOff.jpg"));
				is_exit = true;
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setIcon(new ImageIcon("img/ListImg/memberBGOn.jpg"));
				is_exit = false;
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				/*
				DialogUI dialog;
				if(DialogDB.dialogDB.containsKey(String.valueOf(IDNum))){
					dialog = DialogDB.dialogDB.get(String.valueOf(IDNum));
					dialog.LetsShake();
				}
				else{
					dialog = new DialogUI(nickname,avatar,ID);
					DialogRegDelTool.RegDialog(ID, dialog);
				}
				*/
			}
		});
		
		
		
		
	}
	
	
}
