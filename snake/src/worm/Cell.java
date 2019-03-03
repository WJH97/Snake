package worm;

import java.awt.*;

/**
 * ������
 * @author Leslie Leung
 */
public class Cell {
	
	public static final int CELL_SIZE = 10;	//����ÿ�����ӵĴ�С����СΪ10����
	
	/* ����������� */
	private int x;
	private int y;
	
	/**
	 * ���췽�����������������
	 * @param x ������
	 * @param y ������
	 */
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * ��ȡ����ĺ�����
	 * @return ����ĺ�����
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * ��ȡ�����������
	 * @return �����������
	 */
	public int getY() {
		return y;
	}

	public void paintCell(Graphics g) {
		g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
	}
}
