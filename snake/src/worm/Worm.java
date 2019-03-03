package worm;

import java.awt.*;
import java.util.Arrays;

/**
 * ̰������
 * @author Leslie Leung
 * @see Cell
 */
public class Worm {
	
	public static final int UP = 1;	//������
	public static final int DOWN = -1;		//������
	public static final int LEFT = 2;	//������
	public static final int RIGHT = -2;	//������
	public static final int DEFAULT_LENGTH = 6;	//����̰���ߵ�Ĭ�ϳ���Ϊ6
	public static final int DEFAULT_DIRECTION = RIGHT;	//Ĭ�����з���
	public static final int INIT_SPEED = 100;	//̰���߳�ʼ�ٶ�
	
	private int currentLength;		//̰���ߵ�ǰ����
	private int currentDirection;	//̰���ߵ�ǰ����
	private boolean eat;	//�ж�̰�����Ƿ�Ե�ʳ��
	private Cell[] cells;
	
	/**
	 * ���췽������ʼ��̰����
	 */
	public Worm() {
		cells = new Cell[DEFAULT_LENGTH];
		currentDirection = DEFAULT_DIRECTION;	//������Ϸһ��ʼʱ��Ĭ�Ϸ���ΪDOWN
		currentLength = DEFAULT_LENGTH;		//��ʼ����ǰ̰���߳���ΪĬ�ϳ���
		
		for(int i = 0;i < DEFAULT_LENGTH; i ++) {
			cells[i] = new Cell(DEFAULT_LENGTH - i - 1, 0);
		}

	}
	
	/**
	 * ��ȡ̰���ߵ�ǰ����
	 * @return ̰���ߵĵ�ǰ����
	 */
	public int getCurrentLength() {
		return currentLength;
	}
	
	/**
	 * ��ȡ̰���ߵĵ�ǰ����
	 * @return ̰���ߵĵ�ǰ����
	 */
	public int getCurrentDirection() {
		return currentDirection;
	}
	
	/**
	 * ���̰���������Ƿ���һ������λ���ص�
	 * @param x ����Ľ��ĺ�����
	 * @param y ����Ľ���������
	 * @return �ص���true�����ص���false
	 */
	public boolean contains(int x, int y) {
		
		for(int i = 0; i < currentLength; i ++) {
			if(x == cells[i].getX() && y == cells[i].getY()) {
				return true;
			}
		}
			
		return false;
	}
	
	/**
	 * �ı䷽��
	 * @param ̰�����µ����з���
	 */
	public void changeDirection(int direction) {
		/* ���������·����뵱ǰ̰�������з�����ͬ���෴�����أ�����ȡ�κβ���   */
		if(currentDirection == direction || currentDirection + direction == 0) {
			return;
		}
		
		currentDirection = direction;
	}
	
	/**
	 * �����㷨���Ƴ�ĩβ��㣬�������н�������ƣ��ٰ�ĩβ�����ӵ�ͷ����λ����
	 * @param direction ���з���
	 * @return ̰�����Ƿ�Ե�ʳ�true��ʾ�Ե���false��ʾ�Բ���
	 */
	public boolean creep(int direction, Cell food) {
		eat = false;
		currentDirection = direction;	//�����з�������Ϊ����ķ���
		Cell head = newHead(currentDirection);	//����ͷ���
		
		/* ���̰�������е���һλ���ϴ���ʳ������������ݣ�������̰���ߣ�����������ʳ�� */
		if( head.getX() == food.getX() && head.getY() == food.getY() ) {
			cells = Arrays.copyOf(cells, cells.length + 1);
			eat = true;
			currentLength ++;	//�Ե�ʳ���������
		}
		
		for(int i = cells.length - 1; i > 0; i --) {
			cells[i] = cells[i - 1];
		}
		
		cells[0] = head;
		
		return eat;
	}
	
	/**
	 * ��������ͷ����㷨���������з�����������ͷ���
	 * @param currentDirection ��ǰ���з���
	 * @return �½���ͷ���
	 */
	public Cell newHead(int currentDirection) {
		Cell newHead = null;
		
		switch(currentDirection) {
			case UP: 
				newHead = new Cell(cells[0].getX(), cells[0].getY() - 1);
				break;
			case DOWN:
				newHead = new Cell(cells[0].getX(), cells[0].getY() + 1);
				break;
			case LEFT: 
				newHead = new Cell(cells[0].getX() - 1, cells[0].getY());
				break;
			case RIGHT:
				newHead = new Cell(cells[0].getX() + 1, cells[0].getY());
				break;
		}
		
		return newHead;
	}
	
	/**
	 * ���̰�����Ƿ�ײ���㷨
	 * @param direction ��ǰ�˶�����
	 * @return �Ƿ����ײ��
	 */
	public boolean hit(int direction) {
		Cell nextHead = newHead(direction);
		
		/* ����Ƿ���ײ������ */
		if( this.contains(nextHead.getX(), nextHead.getY()) ) {
			return true;
		}
		
		/* ���̰�����Ƿ����� */
		if(nextHead.getX() < 0 || nextHead.getX() >= WormStage.COLUMNS
				||nextHead.getY() < 0 || nextHead.getY() >= WormStage.ROWS) {
			return true;
		}
		
		return false;
	}
	
	/* ����̰���� */
	public void paint(Graphics g) {
		for(int i = 0; i < cells.length; i ++) {
			cells[i].paintCell(g);
		}
	}
}
