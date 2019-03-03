package worm;

import javax.swing.*;
import java.util.*;
import java.util.Timer;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * ��̨��
 * @author Leslie Leung
 * @see Worm
 * @see Food
 */
public class WormStage extends JPanel {
	
	public static final int ROWS = 35;	//������������������
	public static final int COLUMNS = 35;	//������������������
	
	private Cell food;	//ʳ�������
	private Worm worm;	//̰���������
	private boolean on;	//�ж�̰������Ϸ�ǿ�ʼ������ͣ
	private Timer timer;		//��ʱ��	
	private Random random = new Random();
	private KeyControl keyListener;		//����ʱ�������
	
	/**
	 * ���췽������ʼ��̰������̨
	 */
	public WormStage() {
		setPreferredSize(new Dimension(ROWS * Cell.CELL_SIZE, COLUMNS * Cell.CELL_SIZE));
		
		worm = new Worm();	//�½�̰����
		food = randomFood();	//�½�ʳ��
		on = false;
		keyListener = new KeyControl();
	}
	
	/**
	 * ����KeyControl��ʵ��
	 * @return KeyControl��ʵ��
	 */
	public KeyControl getInnerInstanceOfKeyControl() {
		return keyListener;
	}
	
	/**
	 * ÿ��һ��ʳ�ͨ����Сtimertask��ʱ�������ӿ��ٶ�
	 * @return �ı��ļ��ֵ
	 */
	public double interval() {
		return Worm.INIT_SPEED * Math.pow((double)39 / 38, Worm.DEFAULT_LENGTH - worm.getCurrentLength());
	}

	/**
	 * �����ڲ���̳�TimerTask��ʵ��̰���ߵ��Զ�����
	 * @author Leslie Leung
	 *
	 */
	private class Move extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(worm.hit(worm.getCurrentDirection())) {//�ж��Ƿ����ײ��
				
				JOptionPane.showMessageDialog(null, "������");	//������ʾ
				timer.cancel();	//ȡ����ʱ��
				on = false;
				
				worm = new Worm();
				food = randomFood();
				
			} else if(worm.creep(worm.getCurrentDirection(), food)) {//���������㷨���ж�ʳ���Ǳ��ԣ����ԵĻ���������ʳ��
				
				food = randomFood();	//����ʳ��
				
				timer.cancel();
				timer = new Timer();
				
				timer.scheduleAtFixedRate(new Move(), 0, (long)interval());	//interval()��������ÿ�γԵ�ʳ���Ժ����
				
			}
			
			repaint();
		}
		
	}
	
	/**
	 * �����ڲ���̳�KeyAdapter��ʵ�ֶ�̰���ߵ��¼�����
	 * @author Leslie Leung
	 *
	 */
	private class KeyControl extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			switch(e.getKeyCode()) {
				case KeyEvent.VK_UP: //��������
					worm.changeDirection(Worm.UP);
					break;
					
				case KeyEvent.VK_DOWN:	//��������
					worm.changeDirection(Worm.DOWN);
					break;
					
				case KeyEvent.VK_LEFT:	//��������
					worm.changeDirection(Worm.LEFT);
					break;
					
				case KeyEvent.VK_RIGHT:	//��������
					worm.changeDirection(Worm.RIGHT);
					break;
					
				case KeyEvent.VK_SPACE:	//������Ϸ��ͣ�Ϳ�ʼ
					if(on) {
						/* �����Ϸ��ʼ�����ո����ʾ��ͣ */
						timer.cancel();
						
						on = false;
						break;
					} else {
						/* ���´�����ʱ�����󲢵��ö�ʱ�� */
						timer = new Timer();
						timer.scheduleAtFixedRate(new Move(), 0, (long)interval());
						
						on = true;
						break;
					}
			}
		}
	}
	
	/**
	 * �������ʳ��
	 * @return ʳ�����
	 */
	public Cell randomFood() {
		int x, y;
		
		do {
			x = random.nextInt(COLUMNS);
			y = random.nextInt(ROWS);
		} while(worm.contains(x, y));
		
		return new Cell(x, y);
	}
	
	@Override
	public void paint(Graphics g) {	
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getBounds().width, getBounds().height);
		
		g.setColor(Color.BLUE);
		worm.paint(g);	//����̰����
		
		g.setColor(Color.YELLOW);
		food.paintCell(g);	//����ʳ��
	}

}

