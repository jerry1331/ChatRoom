package friendlistUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import database.Figures;
import database.ListInfo;
import friendlistUI.ListPane;
import object.AddButton;
import object.ExitButton;
import object.MinimizeButton;
import object.ScrollBarUI;

public class FriendListPane extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isAdding = false;
	private boolean isDraging = false;
	private ListPane list;
	private int xx, yy;
	private FriendListPane flu;
	private JScrollPane scrollPane;
	private JPanel panel;
	private ListInfo user;
	private JPanel contentPane;
	
	public boolean isAdding() {
		return isAdding;
	}

	public void setAdding(boolean isAdding) {
		this.isAdding = isAdding;
	}	
	
	public FriendListPane() {
		flu = this;
		Figures.flu = this;
		setBackground(Color.DARK_GRAY);

		// �����б���Ϣ
		try {
			user = Figures.cc.getlist();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		//���ò���
		Figures.IDNum = user.getIDNum();
		Figures.NickName = user.getNickName();
		
		// �����ޱ�����
		setUndecorated(true);

		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				isDraging = true;
				xx = e.getX();
				yy = e.getY();
			}

			public void mouseReleased(MouseEvent e) {
				isDraging = false;
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (isDraging) {
					int left = getLocation().x;
					int top = getLocation().y;
					setLocation(left + e.getX() - xx, top + e.getY() - yy);
				}
			}
		});

		setBounds(100, 100, 300, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);

		// �Զ��尴ť

		ExitButton eb = new ExitButton();
		int windowWeith = this.getWidth();
		eb.setBounds(windowWeith - 4 - 40, 0, 40, 30);
		contentPane.add(eb);
		MinimizeButton mb = new MinimizeButton(this);
		mb.setBounds(windowWeith - 4 - 80, 0, 40, 30);
		contentPane.add(mb);

		JPanel OwnInfo = new JPanel();
		OwnInfo.setBounds(15, 15, 272, 161);
		OwnInfo.setBackground(Color.DARK_GRAY);
		OwnInfo.setPreferredSize(new Dimension(200, 150));
		contentPane.add(OwnInfo);
		OwnInfo.setLayout(null);

		JLabel UserInfo = new JLabel(user.getNickName());
		UserInfo.setForeground(Color.WHITE);
		UserInfo.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 35));
		UserInfo.setBounds(1, 20, 202, 47);
		OwnInfo.add(UserInfo);

		JLabel lblTest = new JLabel("ID: " + user.getIDNum());
		lblTest.setForeground(Color.WHITE);
		lblTest.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 17));
		lblTest.setBounds(1, 134, 137, 27);
		OwnInfo.add(lblTest);

		JLabel lblChatRoom = new JLabel("������");
		lblChatRoom.setForeground(Color.WHITE);
		lblChatRoom.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 20));
		lblChatRoom.setBounds(1, 10, 137, 17);
		OwnInfo.add(lblChatRoom);

		JLabel lblContacts = new JLabel("����");
		lblContacts.setForeground(Color.WHITE);
		lblContacts.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 40));
		lblContacts.setBounds(15, 175, 226, 59);
		contentPane.add(lblContacts);

		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(15, 244, 272, 450);
		panel.setBorder(null);
		contentPane.add(panel);
		panel.setLayout(null);

		list = new ListPane(user);
		scrollPane = new JScrollPane(list);
		Figures.list = list;	//����list
		scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI()); 
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);	// ����ʾˮƽ��������
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(null);
		scrollPane.setBounds(0, 0, 272, 420);
		panel.add(scrollPane);
		
		AddButton button = new AddButton();
		button.setFont(new Font("Microsoft YaHei", Font.PLAIN, 36));
		button.setBounds(236, 186, 40, 40);
		contentPane.add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(isAdding != true){
					isAdding = true;
					AddFriendPane afu = new AddFriendPane(flu, Figures.cc);
				}
				
			}
		});
		
		setVisible(true);
		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�
		Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width; // ��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height; // ��ȡ��Ļ�ĸ�
		int height = this.getHeight();
		setLocation(screenWidth * 3 / 4, (screenHeight - height) / 2);
		
		
		/*
		 * �����ӷ���������ϻ�ȡ��Ϣ
		 */
		Figures.cc.start();
	}
	public void updatelist(ListPane new_list){
//		scrollPane.updateUI();
//		scrollPane.setBorder(null);
//		scrollPane.setBounds(0, 0, 272, 420);
//		scrollPane = new JScrollPane(new_list);
//		Figures.list = list;//����list
//		scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI()); 
//		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);// ����ʾˮƽ��������
//		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//		scrollPane.setBorder(null);
//		scrollPane.setBounds(0, 0, 272, 420);
//		panel.add(scrollPane);
	}
}
