package com;

import java.util.Arrays;
import java.awt.*;
//这是贪吃蛇类
public class Worm {
    public static final int UP = 1;
    public static final int DOWN = -1;
    public static final int LEFT = 2;
    public static final int RIGHT = -2;
    public static final int DEFAULT_LENGTH = 6; //贪吃蛇的默认长度
    public static final int DEFALUT_DIRECTION = RIGHT; // 初始默认方向为→
    public static final int INIT_SPEED = 100;  //初始速度为100

    private int currentLength;  //
    private int currenDirection;
    private boolean eat;
    private Cell1[] cells;

    //初始化贪吃蛇
    public Worm() {
        cells = new Cell1[DEFAULT_LENGTH];
        currenDirection = DEFALUT_DIRECTION;
        currentLength = DEFAULT_LENGTH;

        for (int i = 0; i < DEFAULT_LENGTH; i++) {
            cells[i] = new Cell1(DEFAULT_LENGTH - i - 1, 0);
        }

    }

    // 获取当前贪吃蛇的长度
    public int getCurrentLength() {
        return currentLength;
    }

    //获取贪吃蛇当前的方向
    public int getCurrenDirection() {
        return currenDirection;
    }

    //判断贪吃蛇数组是否有一个节点的位置重合
    //返回true则重合，false不重合
    public boolean contains(int x, int y) {
        for (int i = 0; i < currentLength; i++) {
            if (x == cells[i].getX() && y == cells[i].getY()) {
                return true;
            }
        }
        return false;

    }

    //改变方向的算法
    public  void changeDirection(int direction){
        if(currenDirection == direction || currenDirection+direction==0)
        {
            return;
        }
        currenDirection = direction;
    }

    //爬行算法：移除末尾的结点，然后所有结点后移一位，再把末尾的结点移到头结点
    public boolean climb(int direction,Cell1 food){
        eat = false;
        currenDirection = direction; //将爬行的方向设为输入的方向
        Cell1 head = newHead(currenDirection);  // 重设头结点

        //如果下个结点正好吃到食物，则进行数组扩容后生成新的贪吃蛇数组，并进行重新生成食物
        if(head.getX() == food.getX() && head.getY() == food.getY()){
            cells = Arrays.copyOf(cells,cells.length+1);
            eat =true;
            currentLength++;
        }

        for (int i = cells.length-1; i >0 ; i--) {
            cells[i] = cells[i-1];

        }
        cells[0] = head;
        return eat;
    }
    // 爬行方向发生改变，生成新的头结点
    private Cell1 newHead(int currentDirection) {
        Cell1 newHead = null;

        switch (currentDirection){
            case UP:
                newHead= new Cell1(cells[0].getX(),cells[0].getY()-1);
                break;
            case DOWN:
                newHead = new Cell1(cells[0].getX(),cells[0].getY()+1);
                break;
            case LEFT:
                newHead = new Cell1(cells[0].getX() - 1, cells[0].getY());
                break;
            case RIGHT:
                newHead = new Cell1(cells[0].getX() + 1, cells[0].getY());
                break;
        }
        return newHead;
    }

    //撞击算法
    public boolean hit(int direction){
        Cell1 nextHead = newHead(direction);

        // 检查是否撞到自身
        if(this.contains(nextHead.getX(),nextHead.getY())){
            return true;
        }

        //检查是否撞到墙壁
        if(nextHead.getX() < 0 || nextHead.getX()>= SnakeStage.COLLUMNS ||
        nextHead.getY() < 0 || nextHead.getY() >= SnakeStage.ROWS){
            return true;
        }
        return false;
    }

    // 绘制贪吃蛇
    public void paint(Graphics g){
        for (int i = 0; i < cells.length; i++) {
            cells[i].paintCell(g);
        }
    }
}
