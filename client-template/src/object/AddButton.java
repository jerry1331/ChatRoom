package object;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.UIManager;

public class AddButton extends JButton{
	private static final long serialVersionUID = 1L;
	public boolean isExit = true;
	
	public AddButton() {
		this.setPreferredSize(new Dimension(40, 40));
		this.setBackground(Color.DARK_GRAY);
		this.setText("+");
		this.setFont(new Font("Microsoft YaHei", Font.PLAIN, 40));
		this.setFocusPainted(false);// 设置不要焦点（文字的边框）
		this.setBorder(null);
		this.setForeground(Color.WHITE);

		// 设置按下的颜色
		UIManager.put("Button.select", new Color(220, 220, 220));
		this.addMouseListener(new MouseListener() {

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
				setBackground(Color.DARK_GRAY);
				isExit = true;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				setBackground(new Color(192, 192, 192));
				isExit = false;
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

}
