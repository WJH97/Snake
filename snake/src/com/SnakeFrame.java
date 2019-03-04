package com;


import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class SnakeFrame  extends JFrame {
    private SnakeStage snakeStage;
    private JLabel jLabel;
    public SnakeFrame(){
        setSize(500,500);
        setLocationRelativeTo(null); // 设置窗体在屏幕中间
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("snake");// 设置标题
        setResizable(false);// 不允许缩放
        setLayout(new FlowLayout());// 设置布局管理器

        snakeStage = new SnakeStage();


        jLabel = new JLabel("请按空格开始和暂停，方向键控制方向");
        add(jLabel);
        add(snakeStage);
       // add(ws);

        addKeyListener(snakeStage.getInnerInstanceOfKeyControl());
        snakeStage.addKeyListener(snakeStage.getInnerInstanceOfKeyControl());
        setVisible(true);
    }

    public static void main(String[] args) {
        new SnakeFrame();
    }
}
