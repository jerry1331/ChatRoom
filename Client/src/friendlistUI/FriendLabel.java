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

/*
 * ������б��е�ÿһ���û���װ��һ��JLabel
 * �������JScollPane����
 * 
 */
public class FriendLabel extends JLabel {

	private static final long serialVersionUID = 1L;
	public JPanel jPanel = new JPanel(); // ģ��������
	private JLabel lb_nickName = null; // ��ʾ�ǳƣ�
	private JLabel lb_IDnum = null; // ��ʾID��
	private boolean is_exit = true;
	private int MemberIDNum;
	private JLabel lb_State;
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	private int pic;
	private String nickname;
	public int getPic() {
		return pic;
	}

	public void setPic(int pic) {
		this.pic = pic;
	}

	public int getMemberIDNum() {
		return MemberIDNum;
	}

	public void setMemberIDNum(int memberIDNum) {
		MemberIDNum = memberIDNum;
	}

	public FriendLabel(int picNum, String nickname, int IDNum, byte state) {
		MemberIDNum = IDNum;
		pic = picNum;
		this.nickname = nickname;
		setBackground(Color.darkGray);
		/*
		 * �����û���
		 */
		lb_nickName = new JLabel();
		lb_nickName.setForeground(Color.WHITE);
		lb_nickName.setBounds(new Rectangle(70, 10, 95, 20));
		lb_nickName.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 18));
		lb_nickName.setText(nickname);

		/*
		 * �����û��˺�
		 */
		lb_IDnum = new JLabel();
		lb_IDnum.setForeground(Color.WHITE);
		lb_IDnum.setBounds(new Rectangle(70, 38, 150, 20));
		lb_IDnum.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lb_IDnum.setText("IDNum:(" + IDNum + ")");

		/*
		 * �����Ƿ�����
		 */
		String SState;
		if (state == 0)
			SState = "OnLine";
		else
			SState = "OffLine";
		lb_State = new JLabel();
		lb_State.setText(SState);
		lb_State.setForeground(Color.WHITE);
		lb_State.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 18));
		lb_State.setBounds(new Rectangle(70, 10, 95, 20));
		lb_State.setBounds(180, 10, 95, 20);
		add(lb_State);

		/*
		 * �����û�ͷ��
		 */
		JButton UserIcon = new JButton();
		UserIcon.setBorder(null);
		UserIcon.setBounds(10, 10, 50, 50);
		UserIcon.setIcon(new ImageIcon("img/AvatarImg/" + pic + ".jpg"));

		/*
		 * ���ӱ��� ���������ô��
		 */
		setIcon(new ImageIcon("img/ListImg/memberBGOff.jpg"));

		setSize(new Dimension(272, 70));
		setLayout(null);
		add(UserIcon);
		add(lb_nickName);
		add(lb_IDnum);
		
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
				DialogUI dialog;
				if(DialogDB.dialogDB.containsKey(String.valueOf(IDNum))){
					dialog = DialogDB.dialogDB.get(String.valueOf(IDNum));
					dialog.LetsShake();
				}
				else{
					dialog = new DialogUI(nickname,picNum,IDNum);
					DialogRegDelTool.RegDialog(IDNum, dialog);
				}
			}
		});
		
		
		
	}

	/*
	 * ������������˵���Ϣ
	 */
	public void hav_msg(){
//		System.out.println("Have_A_MSG");
		setIcon(new ImageIcon("img/ListImg/memberBGMsg.jpg"));
	}
	
	public void set_state(byte state){
		String SState;
		if (state == 0)
			SState = "OnLine";
		else
			SState = "OffLine";
		lb_State.setText(SState);
	}
	
}
