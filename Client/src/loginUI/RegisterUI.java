package loginUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import client.ChatClient;
import object.CloseButton;
import object.ExitButton;
import object.MinimizeButton;
import object.RecButton;
import tools.MyButton;

public class RegisterUI extends JFrame {

    private int xx, yy;
    private boolean isDraging = false;

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPasswordField password;
    private JTextField NikeName;
    private JFrame regframe = this;

    /**
     * Create the frame.
     */
    public RegisterUI(LoginAction la, ChatClient cc) {

        // 设置无标题栏
        setUndecorated(true);

        // 监听鼠标 确保窗体能够拖拽
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

        MatteBorder bottomBorder = new MatteBorder(0, 0, 1, 0, new Color(192, 192, 192));
        //设置JFrame禁用本地外观，使用下面自定义设置的外观；
        JFrame.setDefaultLookAndFeelDecorated(true);
        setBounds(100, 100, 300, 490);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        /**
         * 对窗体进行基本设置
         */
        //设置窗体在计算机窗口的中心部位显示
        setLocationRelativeTo(getOwner());
        // 去掉窗口的装饰
        setUndecorated(true);
        //采用指定的窗口装饰风格
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        //设置窗体圆角，最后两个参数分别为圆角的宽度、高度数值，一般这两个数值都是一样的
        //AWTUtilities.setWindowShape(frame,
        //       new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 20.0D, 20.0D));
        //设置背景颜色，记住一定要修改frame.getContentPane()的颜色，因为我们看到的都是这个的颜色而并不是frame的颜色
        getContentPane().setBackground(Color.white);
        /**
         * 插入顶部非凡汽车背景图片
         */
        //创建具有分层的JLayeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, -5, 300, 200);
        getContentPane().add(layeredPane);
        // 创建图片对象
        ImageIcon img = new ImageIcon("img/topbackgroud.jpg");
        //设置图片在窗体中显示的宽度、高度
        img.setImage(img.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT));

        JPanel panel = new JPanel();
        panel.setBounds(0, -5, 300, 200);
        layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
        JLabel lblNewLabel = new JLabel("");
        panel.add(lblNewLabel);
        lblNewLabel.setIcon(img);
        /**
         * 插入窗体关闭的背景图片及功能
         */


        // 创建图片对象
        ImageIcon closeImg = new ImageIcon("img/exit.jpg");
        //设置图片在窗体中显示的宽度、高度
        closeImg.setImage(closeImg.getImage().getScaledInstance(31, 31,Image.SCALE_DEFAULT));

        JPanel closePanel = new JPanel();
        closePanel.setBounds(269, -5, 31, 31);
        layeredPane.add(closePanel,JLayeredPane.MODAL_LAYER);

        JLabel closeLabel = new JLabel(closeImg);
        closePanel.add(closeLabel);
        closeLabel.setIcon(closeImg);
        closeLabel.addMouseListener(new MouseAdapter() {
            //鼠标点击关闭图片，实现关闭窗体的功能
            @Override
            public void mouseClicked(MouseEvent e) {
                //dispose();
                System.exit(0);//使用dispose();也可以关闭只是不是真正的关闭
            }
            //鼠标进入，换关闭的背景图片
            @Override
            public void mouseEntered(MouseEvent e) {
                // 创建图片对象
                ImageIcon closeImg1 = new ImageIcon("img/exit_1.jpg");
                //设置图片在窗体中显示的宽度、高度
                closeImg1.setImage(closeImg1.getImage().getScaledInstance(31, 31,Image.SCALE_DEFAULT));
                closeLabel.setIcon(closeImg1);
            }
            //鼠标离开，换关闭的背景图片
            @Override
            public void mouseExited(MouseEvent e) {
                // 创建图片对象
                ImageIcon closeImg = new ImageIcon("img/exit.jpg");
                //设置图片在窗体中显示的宽度、高度
                closeImg.setImage(closeImg.getImage().getScaledInstance(31, 31,Image.SCALE_DEFAULT));
                closeLabel.setIcon(closeImg);
            }
        });
        /**
         * 插入窗体最小化的背景图片及功能
         */
        // 创建图片对象
        ImageIcon minImg = new ImageIcon("img/min.jpg");
        //设置图片在窗体中显示的宽度、高度
        minImg.setImage(minImg.getImage().getScaledInstance(31, 31,Image.SCALE_DEFAULT));

        JPanel minPanel = new JPanel();
        minPanel.setBounds(237, -5, 31, 31);
        layeredPane.add(minPanel,JLayeredPane.MODAL_LAYER);

        JLabel minLabel = new JLabel("");
        minLabel.addMouseListener(new MouseAdapter() {
            //鼠标点击最小化图片，实现最小化窗体的功能
            @Override
            public void mouseClicked(MouseEvent e) {
                setExtendedState(JFrame.ICONIFIED);//最小化窗体
            }
            //鼠标进入，换最小化的背景图片
            @Override
            public void mouseEntered(MouseEvent e) {
                // 创建图片对象
                ImageIcon minImg1 = new ImageIcon("img/min_1.jpg");
                //设置图片在窗体中显示的宽度、高度
                minImg1.setImage(minImg1.getImage().getScaledInstance(31, 31,Image.SCALE_DEFAULT));
                minLabel.setIcon(minImg1);
            }
            //鼠标离开，换最小化的背景图片
            @Override
            public void mouseExited(MouseEvent e) {
                // 创建图片对象
                ImageIcon minImg = new ImageIcon("img/min.jpg");
                //设置图片在窗体中显示的宽度、高度
                minImg.setImage(minImg.getImage().getScaledInstance(31, 31,Image.SCALE_DEFAULT));
                minLabel.setIcon(minImg);
            }
        });
        minPanel.add(minLabel);
        minLabel.setIcon(minImg);
        /**
         * 插入用户名输入框前面的图片
         */
        // 创建图片对象
        ImageIcon userNameImg = new ImageIcon("img/login.png");
        //设置图片在窗体中显示的宽度、高度
        userNameImg.setImage(userNameImg.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT));

        JLabel userNameLabel = new JLabel("");
        userNameLabel.setBounds(0, 220, 40, 40);
        userNameLabel.setIcon(userNameImg);
        //默认获取光标
        userNameLabel.setFocusable(true);
        getContentPane().add(userNameLabel);
        /**
         * 添加圆角的用户名输入框
         */
        NikeName = new JTextField();
        NikeName.setBounds(50, 220, 235, 50);
        NikeName.setBorder(bottomBorder);
        NikeName.setText("  昵称");
        NikeName.setFont(new Font("微软雅黑", 0, 14));
        NikeName.setForeground(Color.GRAY);//默认设置输入框中的文字颜色为灰色
        NikeName.addFocusListener(new FocusAdapter() {
            //获取光标事件
            @Override
            public void focusGained(FocusEvent e) {
                //获取焦点时，输入框中内容是“用户名”，那么去掉输入框中显示的内容；
                if("用户名".equals((NikeName.getText().trim()))){
                    NikeName.setText("");
                    NikeName.setForeground(Color.black);//设置颜色为黑色
                }
            }
            //失去光标事件
            @Override
            public void focusLost(FocusEvent e) {
                //失去焦点时，如果输入框中去掉空格后的字符串为空串则显示用户名
                if("".equals((NikeName.getText().trim()))){
                    NikeName.setText("  用户名");
                    NikeName.setFont(new Font("微软雅黑", 0, 14));
                    NikeName.setForeground(Color.GRAY);//默认设置输入框中的文字颜色为灰色
                }
            }
        });
        getContentPane().add(NikeName);
        NikeName.setColumns(10);
        /**
         * 插入密码输入框前面的图片
         */
        // 创建图片对象
        ImageIcon passwordImg = new ImageIcon("img/loginpassword.png");
        //设置图片在窗体中显示的宽度、高度
        passwordImg.setImage(passwordImg.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT));

        JLabel passwordLabel = new JLabel("");
        passwordLabel.setBounds(0, 280, 40, 40);
        passwordLabel.setIcon(passwordImg);
        getContentPane().add(passwordLabel);
        /**
         * 添加圆角的密码输入框
         */
        password = new JPasswordField();
        password.setBounds(50, 280, 235, 50);
        password.setBorder(bottomBorder);
        password.setText("  密码");
        password.setFont(new Font("微软雅黑", 0, 14));
        password.setForeground(Color.GRAY);//默认设置输入框中的文字颜色为灰色
        password.setEchoChar((char)0);//显示密码输入框中内容
        password.addFocusListener(new FocusAdapter() {
            //获取光标事件
            @Override
            public void focusGained(FocusEvent e) {
                //获取焦点时，输入框中内容是“用户名”，那么去掉输入框中显示的内容；
                if("密码".equals((password.getText().trim()))){
                    password.setText("");
                    password.setEchoChar('*');//显示密码输入框中内容
                    password.setForeground(Color.black);//设置颜色为黑色
                }
            }
            //失去光标事件
            @Override
            public void focusLost(FocusEvent e) {
                //失去焦点时，如果输入框中去掉空格后的字符串为空串则显示用户名
                if("".equals((password.getText().trim()))){
                    password.setText("  密码");
                    password.setFont(new Font("微软雅黑", 0, 14));
                    password.setForeground(Color.GRAY);//默认设置输入框中的文字颜色为灰色
                    password.setEchoChar((char)0);//显示密码输入框中内容
                }
            }
        });
        /**
         * 添加圆角的提交按钮
         */
        MyButton registerButton = new MyButton("注册", 0);
        registerButton.setBounds(15, 350, 270, 50);
        getContentPane().add(registerButton);
        getContentPane().add(password);
        //	设置监听器，用于监听按键事件

        la.setUsername(NikeName);
        la.setPassword(password);
        registerButton.addActionListener(la);
        registerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //System.out.println("One click");
                if (!cc.Reg(NikeName.getText(), password.getText())) {
                    JOptionPane.showMessageDialog(null, "注册失败", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    regframe.dispose();
                    la.setIs_Registering(false);// 可以打开注册窗口
                }
            }
        });
        // 只能输入数字和字母
        password.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if ((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)
                        || (keyChar >= KeyEvent.VK_A && keyChar <= KeyEvent.VK_Z)
                        || (keyChar >= 'a' && keyChar <= 'z')) {

                } else {
                    e.consume(); // 关键，屏蔽掉非法输入
                }
            }
        });
        setVisible(true);
    }
}