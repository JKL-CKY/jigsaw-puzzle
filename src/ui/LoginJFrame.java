package ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LoginJFrame extends JFrame {
    //创建一个集合存储正确的用户名和密码
    static ArrayList<User> list = new ArrayList<>();
    static {
        list.add(new User("jiangkailong", "123456"));
        list.add(new User("chenkeying", "654321"));
    }
    // 用于存储用户名输入框和密码输入框
    private JTextField username;
    private JTextField password;

    public LoginJFrame() {
        //初始化界面
        initJFrame();
        //在这个界面中添加内容
        initView();
        //让当前界面显示出来
        this.setVisible(true);
    }

    public void initView() {
        //1. 添加用户名文字
        JLabel usernameText = new JLabel(new ImageIcon("D:\\JAVA\\代码\\java.jichu\\JAVA基础\\day17\\image\\login\\用户名.png"));
        usernameText.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernameText);

        //2.添加用户名输入框
        username = new JTextField();
        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);

        //3.添加密码文字
        JLabel passwordText = new JLabel(new ImageIcon("D:\\JAVA\\代码\\java.jichu\\JAVA基础\\day17\\image\\login\\密码.png"));
        passwordText.setBounds(130, 195, 32, 16);
        this.getContentPane().add(passwordText);

        //4.密码输入框
        password = new JTextField();
        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);

        //验证码提示
        JLabel codeText = new JLabel(new ImageIcon("D:\\JAVA\\代码\\java.jichu\\JAVA基础\\day17\\image\\login\\验证码.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);

        //验证码的输入框
        JTextField code = new JTextField();
        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);

        String codeStr = CodeUtil.getCode();
        JLabel rightCode = new JLabel();
        //设置内容
        rightCode.setText(codeStr);
        //位置和宽高
        rightCode.setBounds(300, 256, 50, 30);
        //添加到界面
        this.getContentPane().add(rightCode);

        //5.添加登录按钮
        JButton login = new JButton();
        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("D:\\JAVA\\代码\\java.jichu\\JAVA基础\\day17\\image\\login\\登录按钮.png"));
        //去除按钮的默认边框
        login.setBorderPainted(false);
        //去除按钮的默认背景
        login.setContentAreaFilled(false);
        login.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 点击时校验用户名和密码
                String usernameText = LoginJFrame.this.username.getText();
                String passwordText = LoginJFrame.this.password.getText();
                boolean isCorrect = checkCredentials(usernameText, passwordText);
                if (isCorrect) {
                    // 用户名和密码正确，可进行登录操作
                    LoginJFrame.this.dispose();
                    new GameJFrame();
                } else {
                    // 用户名或密码错误，显示错误提示
                    showJDialog("用户名或密码错误");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // 按下时修改按钮背景色为蓝色
                login.setIcon(new ImageIcon("D:\\JAVA\\代码\\java.jichu\\JAVA基础\\day17\\image\\login\\注册按下.png"));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // 松开时修改按钮背景色为红色
                login.setIcon(new ImageIcon("D:\\JAVA\\代码\\java.jichu\\JAVA基础\\day17\\image\\login\\登录按下.png"));
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        this.getContentPane().add(login);

        //6.添加注册按钮
        JButton register = new JButton();
        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("D:\\JAVA\\代码\\java.jichu\\JAVA基础\\day17\\image\\login\\注册按钮.png"));
        //去除按钮的默认边框
        register.setBorderPainted(false);
        //去除按钮的默认背景
        register.setContentAreaFilled(false);
        this.getContentPane().add(register);

        //7.添加背景图片
        JLabel background = new JLabel(new ImageIcon("D:\\JAVA\\代码\\java.jichu\\JAVA基础\\day17\\image\\login\\background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
    }

    private boolean checkCredentials(String username, String password) {
        for (User user : list) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


    public void initJFrame() {
        this.setSize(488, 430);//设置宽高
        this.setTitle("蒋凯龙拼图游戏 V1.0登录");//设置标题
        this.setDefaultCloseOperation(3);//设置关闭模式
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(null);//取消内部默认布局
    }


    //要展示用户名或密码错误
    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }
}