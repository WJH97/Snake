package com;

import java.awt.*;



//这是一个网格类
public class Cell1 {

    public  static final int CELL_SIZE = 10; //设置每一个格子的大小

    private int x ;
    private int y;

    // 设置网格的坐标
    public Cell1(int x,int y){
        this.x= x;
        this.y=y;
    }


    // 获取网格的纵坐标
    public int getX(){
        return x;
    }

   //获取网格的横坐标
    public int getY(){
        return y;
    }

    public void paintCell(Graphics g){
        g.fillRect(x*CELL_SIZE,y*CELL_SIZE,CELL_SIZE,CELL_SIZE);
    }

}
