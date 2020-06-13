package friendListUI;

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

import dataBase.Figures;
import dataBase.ListInfo;
import friendListUI.ListPane;
import object.AddButton;
import object.ExitButton;
import object.MinimizeButton;
import object.ScrollBarUI;

public class FriendListUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isAdding = false;
	private boolean isDraging = false;
	private ListPane list;
	private int xx, yy;
	private FriendListUI flu;
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
	
	public FriendListUI() {
		flu = this;
		Figures.flu = this;
		setBackground(Color.DARK_GRAY);

		// 接收列表信息
		try {
			user = Figures.cc.getlist();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		//设置参数
		Figures.IDNum = user.getIDNum();
		Figures.NickName = user.getNickName();
		
		// 设置无标题栏
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

		// 自定义按钮

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
		lblTest.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 24));
		lblTest.setBounds(1, 80, 137, 37);
		OwnInfo.add(lblTest);


		JLabel lblContacts = new JLabel("好友");
		lblContacts.setForeground(Color.WHITE);
		lblContacts.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 40));
		lblContacts.setBounds(15, 170, 226, 50);
		contentPane.add(lblContacts);

		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(15, 230, 272, 450);
		panel.setBorder(null);
		contentPane.add(panel);
		panel.setLayout(null);

		list = new ListPane(user);
		scrollPane = new JScrollPane(list);
		Figures.list = list;	//设置list
		scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI()); 
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);	// 不显示水平滚动条；
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
					AddFriendUI afu = new AddFriendUI(flu, Figures.cc);
				}
				
			}
		});
		
		setVisible(true);
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width; // 获取屏幕的宽
		int screenHeight = screenSize.height; // 获取屏幕的高
		int height = this.getHeight();
		setLocation(screenWidth * 3 / 4, (screenHeight - height) / 2);
		
		
		/*
		 * 开启从服务器不间断获取信息
		 */
		Figures.cc.start();
	}
	
}
