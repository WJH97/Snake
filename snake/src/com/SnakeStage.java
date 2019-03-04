package com;

import javafx.scene.control.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.util.Random;
import java.util.Timer;
import java.awt.event.KeyEvent;
import java.util.TimerTask;


public class SnakeStage extends JPanel {


    public static final int ROWS = 35;
    public static final int COLLUMNS = 35;


    private Cell1 food;  // 食物类对象
    private Worm worm;  //贪吃蛇对象
    private boolean on; //控制开关
    private Timer timer; //设置试讲监听器
    private Random random = new Random();
    private KeyControl keyListener;  //键盘控制器


    public SnakeStage() {
        setPreferredSize(new Dimension(ROWS * 12, COLLUMNS * 12));

        worm = new Worm();  //实例化Worm对象
        food = randomFood(); //
        on = false;
        keyListener = new KeyControl();
    }
    // 返回KeyControl 类的实例
    public KeyControl getInnerInstanceOfKeyControl() {
        return keyListener;
    }

    // 没吃一次食物通过减少timertask的时间间隔来加快速度
    public double interval(){
        return Worm.INIT_SPEED * Math.pow((double)39/38,Worm.DEFAULT_LENGTH-worm.getCurrentLength());
    }
    // 随机生成食物
    public Cell1 randomFood() {
        int x ;
        int y;
        do {
            x = random.nextInt(COLLUMNS);
            y = random.nextInt(ROWS);
        }while(worm.contains(x,y));
        return  new Cell1(x,y);
    }

    // 简历内部类继承Timertask，实现贪吃蛇的自动运行
    private class Move extends TimerTask{
        @Override
        public void run() {
            if(worm.hit(worm.getCurrenDirection())){
                // 判断是否产生撞击
                JOptionPane.showMessageDialog(null,"你输了");
                timer.cancel();//取消定时器
                on = false;

                worm = new Worm();
                food = randomFood();
            }else if(worm.climb(worm.getCurrenDirection(),food)){

                food = randomFood();  // 生成食物
                timer.cancel();
                timer =new Timer();
                timer.scheduleAtFixedRate(new Move(),0,(long)interval());//interval函数用于加速

            }

            repaint();
        }
    }


    private class KeyControl extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    worm.changeDirection(Worm.UP);
                    break;
                case  KeyEvent.VK_LEFT:
                    worm.changeDirection(Worm.LEFT);
                    break;
                case KeyEvent.VK_DOWN:
                    worm.changeDirection(Worm.DOWN);
                    break;
                case KeyEvent.VK_RIGHT:
                    worm.changeDirection(Worm.RIGHT);
                    break;
                case  KeyEvent.VK_SPACE:
                    if(on){
                        timer.cancel();

                        on = false;
                        break;
                    }else {
                        timer = new Timer();
                        timer.scheduleAtFixedRate(new Move(),0,(long)interval());
                        break;
                    }
            }
        }
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,getBounds().width,getBounds().height);

        g.setColor(Color.BLUE);
        worm.paint(g);

        g.setColor(Color.YELLOW);
        food.paintCell(g);
    }
}