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

        // �����ޱ�����
        setUndecorated(true);

        // ������� ȷ�������ܹ���ק
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
        //����JFrame���ñ�����ۣ�ʹ�������Զ������õ���ۣ�
        JFrame.setDefaultLookAndFeelDecorated(true);
        setBounds(100, 100, 300, 490);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        /**
         * �Դ�����л�������
         */
        //���ô����ڼ�������ڵ����Ĳ�λ��ʾ
        setLocationRelativeTo(getOwner());
        // ȥ�����ڵ�װ��
        setUndecorated(true);
        //����ָ���Ĵ���װ�η��
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        //���ô���Բ�ǣ�������������ֱ�ΪԲ�ǵĿ�ȡ��߶���ֵ��һ����������ֵ����һ����
        //AWTUtilities.setWindowShape(frame,
        //       new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 20.0D, 20.0D));
        //���ñ�����ɫ����סһ��Ҫ�޸�frame.getContentPane()����ɫ����Ϊ���ǿ����Ķ����������ɫ��������frame����ɫ
        getContentPane().setBackground(Color.white);
        /**
         * ���붥���Ƿ���������ͼƬ
         */
        //�������зֲ��JLayeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, -5, 300, 200);
        getContentPane().add(layeredPane);
        // ����ͼƬ����
        ImageIcon img = new ImageIcon("img/topbackgroud.jpg");
        //����ͼƬ�ڴ�������ʾ�Ŀ�ȡ��߶�
        img.setImage(img.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT));

        JPanel panel = new JPanel();
        panel.setBounds(0, -5, 300, 200);
        layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
        JLabel lblNewLabel = new JLabel("");
        panel.add(lblNewLabel);
        lblNewLabel.setIcon(img);
        /**
         * ���봰��رյı���ͼƬ������
         */


        // ����ͼƬ����
        ImageIcon closeImg = new ImageIcon("img/exit.jpg");
        //����ͼƬ�ڴ�������ʾ�Ŀ�ȡ��߶�
        closeImg.setImage(closeImg.getImage().getScaledInstance(31, 31,Image.SCALE_DEFAULT));

        JPanel closePanel = new JPanel();
        closePanel.setBounds(269, -5, 31, 31);
        layeredPane.add(closePanel,JLayeredPane.MODAL_LAYER);

        JLabel closeLabel = new JLabel(closeImg);
        closePanel.add(closeLabel);
        closeLabel.setIcon(closeImg);
        closeLabel.addMouseListener(new MouseAdapter() {
            //������ر�ͼƬ��ʵ�ֹرմ���Ĺ���
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
//                System.exit(0);
            }
            //�����룬���رյı���ͼƬ
            @Override
            public void mouseEntered(MouseEvent e) {
                // ����ͼƬ����
                ImageIcon closeImg1 = new ImageIcon("img/exit_1.jpg");
                //����ͼƬ�ڴ�������ʾ�Ŀ�ȡ��߶�
                closeImg1.setImage(closeImg1.getImage().getScaledInstance(31, 31,Image.SCALE_DEFAULT));
                closeLabel.setIcon(closeImg1);
            }
            //����뿪�����رյı���ͼƬ
            @Override
            public void mouseExited(MouseEvent e) {
                // ����ͼƬ����
                ImageIcon closeImg = new ImageIcon("img/exit.jpg");
                //����ͼƬ�ڴ�������ʾ�Ŀ�ȡ��߶�
                closeImg.setImage(closeImg.getImage().getScaledInstance(31, 31,Image.SCALE_DEFAULT));
                closeLabel.setIcon(closeImg);
            }
        });
        /**
         * ���봰����С���ı���ͼƬ������
         */
        // ����ͼƬ����
        ImageIcon minImg = new ImageIcon("img/min.jpg");
        //����ͼƬ�ڴ�������ʾ�Ŀ�ȡ��߶�
        minImg.setImage(minImg.getImage().getScaledInstance(31, 31,Image.SCALE_DEFAULT));

        JPanel minPanel = new JPanel();
        minPanel.setBounds(237, -5, 31, 31);
        layeredPane.add(minPanel,JLayeredPane.MODAL_LAYER);

        JLabel minLabel = new JLabel("");
        minLabel.addMouseListener(new MouseAdapter() {
            //�������С��ͼƬ��ʵ����С������Ĺ���
            @Override
            public void mouseClicked(MouseEvent e) {
                setExtendedState(JFrame.ICONIFIED);//��С������
            }
            //�����룬����С���ı���ͼƬ
            @Override
            public void mouseEntered(MouseEvent e) {
                // ����ͼƬ����
                ImageIcon minImg1 = new ImageIcon("img/min_1.jpg");
                //����ͼƬ�ڴ�������ʾ�Ŀ�ȡ��߶�
                minImg1.setImage(minImg1.getImage().getScaledInstance(31, 31,Image.SCALE_DEFAULT));
                minLabel.setIcon(minImg1);
            }
            //����뿪������С���ı���ͼƬ
            @Override
            public void mouseExited(MouseEvent e) {
                // ����ͼƬ����
                ImageIcon minImg = new ImageIcon("img/min.jpg");
                //����ͼƬ�ڴ�������ʾ�Ŀ�ȡ��߶�
                minImg.setImage(minImg.getImage().getScaledInstance(31, 31,Image.SCALE_DEFAULT));
                minLabel.setIcon(minImg);
            }
        });
        minPanel.add(minLabel);
        minLabel.setIcon(minImg);
        /**
         * �����û��������ǰ���ͼƬ
         */
        // ����ͼƬ����
        ImageIcon userNameImg = new ImageIcon("img/login.png");
        //����ͼƬ�ڴ�������ʾ�Ŀ�ȡ��߶�
        userNameImg.setImage(userNameImg.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT));

        JLabel userNameLabel = new JLabel("");
        userNameLabel.setBounds(0, 220, 40, 40);
        userNameLabel.setIcon(userNameImg);
        //Ĭ�ϻ�ȡ���
        userNameLabel.setFocusable(true);
        getContentPane().add(userNameLabel);
        /**
         * ���Բ�ǵ��û��������
         */
        NikeName = new JTextField();
        NikeName.setBounds(50, 220, 235, 50);
        NikeName.setBorder(bottomBorder);
        NikeName.setText("  �ǳ�");
        NikeName.setFont(new Font("΢���ź�", 0, 14));
        NikeName.setForeground(Color.GRAY);//Ĭ������������е�������ɫΪ��ɫ
        NikeName.addFocusListener(new FocusAdapter() {
            //��ȡ����¼�
            @Override
            public void focusGained(FocusEvent e) {
                //��ȡ����ʱ��������������ǡ��û���������ôȥ�����������ʾ�����ݣ�
                if("�û���".equals((NikeName.getText().trim()))){
                    NikeName.setText("");
                    NikeName.setForeground(Color.black);//������ɫΪ��ɫ
                }
            }
            //ʧȥ����¼�
            @Override
            public void focusLost(FocusEvent e) {
                //ʧȥ����ʱ������������ȥ���ո����ַ���Ϊ�մ�����ʾ�û���
                if("".equals((NikeName.getText().trim()))){
                    NikeName.setText("  �û���");
                    NikeName.setFont(new Font("΢���ź�", 0, 14));
                    NikeName.setForeground(Color.GRAY);//Ĭ������������е�������ɫΪ��ɫ
                }
            }
        });
        getContentPane().add(NikeName);
        NikeName.setColumns(10);
        /**
         * �������������ǰ���ͼƬ
         */
        // ����ͼƬ����
        ImageIcon passwordImg = new ImageIcon("img/loginpassword.png");
        //����ͼƬ�ڴ�������ʾ�Ŀ�ȡ��߶�
        passwordImg.setImage(passwordImg.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT));

        JLabel passwordLabel = new JLabel("");
        passwordLabel.setBounds(0, 280, 40, 40);
        passwordLabel.setIcon(passwordImg);
        getContentPane().add(passwordLabel);
        /**
         * ���Բ�ǵ����������
         */
        password = new JPasswordField();
        password.setBounds(50, 280, 235, 50);
        password.setBorder(bottomBorder);
        password.setText("  ����");
        password.setFont(new Font("΢���ź�", 0, 14));
        password.setForeground(Color.GRAY);//Ĭ������������е�������ɫΪ��ɫ
        password.setEchoChar((char)0);//��ʾ���������������
        password.addFocusListener(new FocusAdapter() {
            //��ȡ����¼�
            @Override
            public void focusGained(FocusEvent e) {
                //��ȡ����ʱ��������������ǡ��û���������ôȥ�����������ʾ�����ݣ�
                if("����".equals((password.getText().trim()))){
                    password.setText("");
                    password.setEchoChar('*');//��ʾ���������������
                    password.setForeground(Color.black);//������ɫΪ��ɫ
                }
            }
            //ʧȥ����¼�
            @Override
            public void focusLost(FocusEvent e) {
                //ʧȥ����ʱ������������ȥ���ո����ַ���Ϊ�մ�����ʾ�û���
                if("".equals((password.getText().trim()))){
                    password.setText("  ����");
                    password.setFont(new Font("΢���ź�", 0, 14));
                    password.setForeground(Color.GRAY);//Ĭ������������е�������ɫΪ��ɫ
                    password.setEchoChar((char)0);//��ʾ���������������
                }
            }
        });
        /**
         * ���Բ�ǵ��ύ��ť
         */
        MyButton registerButton = new MyButton("ע��", 0);
        registerButton.setBounds(15, 350, 270, 50);
        getContentPane().add(registerButton);
        getContentPane().add(password);
        //	���ü����������ڼ��������¼�

        la.setUsername(NikeName);
        la.setPassword(password);
        registerButton.addActionListener(la);
        registerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //System.out.println("One click");
                if (!cc.Reg(NikeName.getText(), password.getText())) {
                    JOptionPane.showMessageDialog(null, "ע��ʧ�ܣ����볤�Ȳ���С��6λ��", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    la.setIs_Registering(false);
                    dispose();
                }
            }
        });
        // ֻ���������ֺ���ĸ
        password.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if ((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)
                        || (keyChar >= KeyEvent.VK_A && keyChar <= KeyEvent.VK_Z)
                        || (keyChar >= 'a' && keyChar <= 'z')) {

                } else {
                    e.consume(); // �ؼ������ε��Ƿ�����
                }
            }
        });
        setVisible(true);
    }
}