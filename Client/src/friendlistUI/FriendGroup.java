package friendlistUI;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
 * 	生成单个好友分组
 */

public class FriendGroup extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel jName;
	private boolean isClick = false;
	
	public FriendGroup(String name, JLabel[] users) {
		setBackground(Color.DARK_GRAY);
		jName = new JLabel();
		jName.setForeground(Color.WHITE);
		jName.setBounds(new Rectangle(20, 10, 95, 20));
		jName.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 18));
		jName.setText(name);
		this.add(jName);

		setIcon(new ImageIcon("img/ListImg/ListOff.jpg"));
		setSize(new Dimension(272, 50));
		for (int i = 0; i < users.length; i++) {
			users[i].setVisible(false);
		}
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if(isClick){
					setIcon(new ImageIcon("img/ListImg/ListOn.jpg"));
				}
				else{
					setIcon(new ImageIcon("img/ListImg/ListOff.jpg"));
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if(isClick){

					setIcon(new ImageIcon("img/ListImg/ListOnEnter.jpg"));
				}
				else{
					setIcon(new ImageIcon("img/ListImg/ListOffEnter.jpg"));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				isClick = !isClick;
				if(isClick){
					for (int i = 0; i < users.length; i++) {
						users[i].setVisible(true);
					}
					setIcon(new ImageIcon("img/ListImg/ListOnEnter.jpg"));
				} else {
					for (int i = 0; i < users.length; i++) {
						users[i].setVisible(false);
					}
					setIcon(new ImageIcon("img/ListImg/ListOffEnter.jpg"));
				}
			}
		});
	}
}
