package ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {
    //RegisterJFrame登录界面

    public RegisterJFrame() {
        //设置界面
        this.setSize(488,500);
        //设置界面的标题
        this.setTitle("注册 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式  （所有的界面都设置才会有效）
        this.setDefaultCloseOperation(3);//   0/ 1/ 2/ 3
        this.setVisible(true);
    }
}
