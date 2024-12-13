package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

//ctrl+B查看原码
public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    //GameJFrame表示游戏的主界面
    //创建二维数组  目的：管理数据
    int[][] data = new int[4][4];
    //记录空白方块在二维数组中的位置
    int x = 3;
    int y = 3;
    // 在这里定义图片路径数组
    String[] girlPaths = new String[12];
    {
        for (int i = 0; i < girlPaths.length; i++) {
            girlPaths[i] = "D:/JAVA/代码/java.jichu/JAVA基础/day16" + "/image/girl/girl" + (i + 1) + "/";
        }
    }
    String[] animalPaths = new String[7];{
        for (int i = 0; i < animalPaths.length; i++) {
            animalPaths[i] = "D:\\JAVA\\代码\\java.jichu\\JAVA基础\\day16\\image\\animal\\animal" + (i + 1) + "\\";
        }
    }
    String[] sportPaths = new String[9];{
        for (int i = 0; i < sportPaths.length; i++) {
            sportPaths[i] = "D:\\JAVA\\代码\\java.jichu\\JAVA基础\\day16\\image\\sport\\sport" + (i + 1) + "\\";
        }
    }


    //创建选项下面的条目对象

    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem A = new JMenuItem("按下a查看原图");
    JMenuItem W = new JMenuItem("按下w一键通过");
    JMenuItem girl = new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sport = new JMenuItem("运动");
    JMenuItem accountItem = new JMenuItem("作者微信二维码");
    //定义一个变量，记录当前展示图片
    String path ="D:\\JAVA\\代码\\java.jichu\\JAVA基础\\day16\\image\\girl\\girl8\\";
    //定义一个变量，记录当前展示图片的路径
    int [][] win ={
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };
    //定义变量用来统计部署
    int step = 0;

    //

    public GameJFrame() {
        //ctrl+alt+m 封装
        //初始化界面
        initJFrame();
        //初始化菜单
        initJMenuBar();

        //初始化数据(打乱)
        InitData();
        //初始化图片(根据大乱之后的结果去加载图片)
        initImage();


        //让界面显示出来
        this.setVisible(true);
    }
    private  void InitData() {

        //1定义一个一维数组
        int [] tempArray = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        //2.打乱数组中的数据的顺序
        //遍历数据，得到每一个元素，拿着每一元素跟随机索引上上的数据进行交换
        Random random = new Random();
        for (int i = 0; i < tempArray.length; i++) {
            //获取到随机索引
            int index = random.nextInt(tempArray.length);
            int temp = tempArray[i];
            tempArray[i] = tempArray[index];
            tempArray[index] = temp;
        }
        System.out.println();

        //5.添加到二维数组里
        for (int i = 0; i < tempArray.length; i++) {
            if(tempArray[i] == 0){
                x=i/4;
                y=i%4;
            }
            data[i/4][i%4] = tempArray[i];
        }
    }
    private void initImage() {
        //清空原本已经出现的所有图片
        this.getContentPane().removeAll();

        if (victory()){
            //返回胜利的图片
            JLabel win = new JLabel(new ImageIcon("D:\\JAVA\\代码\\java.jichu\\JAVA基础\\day17\\image\\win.png"));
            win.setBounds(203,283,197,73);
            this.getContentPane().add(win);
        }
        JLabel stepCount = new JLabel("步数:"+step);
        stepCount.setBounds(50,50,100,20);
        this.getContentPane().add(stepCount);

        //路径分为两种
        //绝对路径:一定是从盘符开始的，C:\
        //相对路径:相对于当前项目而言

        //细节：
        //先加载的图片在上方，后加载的图片写在下方。（会覆盖）

        //添加图片的时候需要按照二维数组中管理的数据添加图片
        //外循环
        for (int i = 0; i < 4; i++) {
            //内循环
            for (int j = 0; j < 4; j++) {
                //获取当前要加载图片的序号
                int num = data[i][j];
                //创建ImageIcon对象
                ImageIcon icon = new ImageIcon(path+num+".jpg");
                //创建Jlabel的对象
                JLabel jLabel = new JLabel(icon);
                //指定位置  （一定要在添加前指定 ）
                jLabel.setBounds(105*j+83,105*i+134,105,105);
                //给图片添加边框
                jLabel.setBorder(new BevelBorder(1));//0图片凹下去 1图片凸出来
                //把管理容器添加到界面中
                //this.add(jLabel1);
                this.getContentPane().add(jLabel);

            }
        }
                //添加背景图片
                ImageIcon bg = new ImageIcon("D:\\JAVA\\代码\\java.jichu\\JAVA基础\\day17\\image\\background.png ");
                JLabel background = new JLabel(bg);
                background.setBounds(40, 40, 508,560);
                //把背景图片添加到界面中
                this.getContentPane().add(background);
                //刷新一下界面
                this.getContentPane().repaint();
    }
    private void initJMenuBar() {
        //初始化菜单
        //创建整个菜单对象   JMenuBar
        JMenuBar jMenuBar = new JMenuBar();
        //创建菜单上面的两个选项的对象 （功能 关于我们）

        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("查看作者");
        JMenu changeImage = new JMenu("更换图片");

        //将每一个选项下面的条目添加到选项当中
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        functionJMenu.add(A);
        functionJMenu.add(W);
        changeImage.add(girl);
        changeImage.add(animal);
        changeImage.add(sport);
        aboutJMenu.add(accountItem);

        //给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        girl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);


        //将菜单里面的两个选项添加到菜单当中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);
        jMenuBar.add(changeImage);
        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }
    private void initJFrame() {
        //设置界面的宽高
        this.setSize(603,680);
        //设置界面的标题
        this.setTitle("蒋凯龙拼图小游戏 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式  （所有的界面都设置才会有效）
        this.setDefaultCloseOperation(3);//   0/ 1/ 2/ 3
        //取消默认的居中放置，只有取消了才会按照xy轴的形式添加组件
        this.setLayout(null);
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    //按下不松会调用这个方法
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 65){
            //把页面中所有的图片全部删除
            this.getContentPane().removeAll();
            //加载第一张完整的图片
            JLabel all = new JLabel(new ImageIcon(path+"all.jpg"));
            all.setBounds(83,134,420,420);
            //加载背景图片
            this.getContentPane().add(all);
            //添加背景图片
            ImageIcon bg = new ImageIcon("D:\\JAVA\\代码\\java.jichu\\JAVA基础\\day17\\image\\background.png ");
            JLabel background = new JLabel(bg);
            background.setBounds(40, 40, 508,560);
            //把背景图片添加到界面中
            this.getContentPane().add(background);
            //刷新界面
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //判断游戏是否胜利，如果胜利就结束不再执行下面的移动代码了
        if(victory()){
            return;
        }


        //对上下左右进行判断
        //左：37 上：38 右：39 下：40
        int code = e.getKeyCode();
        if(code == 37){
            //边界处理
            if(y==0){
                return;
            }
            data[x][y]=data[x][y-1];
            data[x][y-1]=0;
            y--;

            step++;
            //调用方法按照最新的数字调用图片
            initImage();

        }else if(code == 38){
            //边界处理
            if(x==0){
                return;
            }
        //x,y表示空白方框
            data[x][y]=data[x-1][y];
            data[x-1][y]=0;
            x--;
            step++;
            //调用方法按照最新的数字调用图片
            initImage();
        }else if(code == 39){
            //边界处理
            if(y==3){
                return;
            }
            data[x][y]=data[x][y+1];
            data[x][y+1]=0;
            y++;
            step++;
            //调用方法按照最新的数字调用图片
            initImage();
        }else if(code == 40){
            if(x==3){
                return;
            }
            //x,y表示空白方框
            data[x][y]=data[x+1][y];
            data[x+1][y]=0;
            x++;
            step++;
            //调用方法按照最新的数字调用图片
            initImage();
        }else if(code == 65){
            //松开A键重新加载图片
            initImage();
        } else if(code == 87){
            //按下w一件通过
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            initImage();
        }
    }
    //判断data数组中的数据是否跟win数组中相同
    //如果全部相同，返回true，否则false
    public boolean victory(){
        for (int i = 0; i < data.length; i++) {
            //i依次表示二维数组 data里面的索引
            //data[i]依次表示每一个一维数组
            for (int j = 0; j < data[0].length; j++) {
                if(data[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return  true;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击的条目对象
          Object obj = e.getSource();
        //判断
        if(obj == replayItem){
            System.out.println("重新游戏");
            //计步器清零
            step=0;
            //再次打乱二维数组中的数据
            InitData();
            //重新加载图片
            initImage();
        }else if(obj == reLoginItem){
            System.out.println("重新登录");
            //关闭当前的游戏界面
            this.setVisible(false);
            //打开登录界面
            new LoginJFrame();
        }else if(obj == closeItem){
            System.out.println("关闭游戏");
            System.exit(0);
        }else if(obj == accountItem){
            System.out.println("查看作者");
            //创建一个弹框对象
            JDialog jd = new JDialog();
            //创建一个管理图片的JLable
            JLabel jLabel = new JLabel(new ImageIcon("D:\\JAVA\\代码\\java.jichu\\JAVA基础\\day17\\image\\9d31db1c482d7bb47eb4adc7ccd6795.png"));
            //设置位置宽高
            jLabel.setBounds(0,0,258,258);
            //把图片添加到弹框中
            jd.getContentPane().add(jLabel);
            //给弹框设置大小
            jd.setSize(344,344);
            jd.setAlwaysOnTop(true);
            jd.setLocationRelativeTo(null);
            //弹框不关闭则无法操作下面的界面
            jd.setModal(true);
            jd.setVisible(true);
        } else if (obj== girl) {
            System.out.println("美女");
            // 创建随机数生成器
            Random random = new Random();
            // 随机选择一个索引
            int index = random.nextInt(girlPaths.length);
            // 设置路径为随机选择的美女图片路径
            path = girlPaths[index];
            // 根据新路径重新初始化图片
            //计步器清零
            step=0;
            //再次打乱二维数组中的数据
            InitData();
            initImage();
        } else if (obj==sport) {
            Random random = new Random();
            int index = random.nextInt(sportPaths.length);
            path = sportPaths[index];
            step=0;
            InitData();
            initImage();
        } else if (obj==animal) {
            Random random = new Random();
            int index = random.nextInt(animalPaths.length);
            path = animalPaths[index];
            step=0;
            InitData();
            initImage();
        }
    }
}
