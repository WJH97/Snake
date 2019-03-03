package worm;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * ̰���߿����
 * @author Leslie Leung
 */
public class WormFrame extends JFrame {
	private WormStage ws;
	private JLabel label;
	
	/**
	 * ���췽��
	 */
	public WormFrame() {
		setSize(500, 500);	//���ô����С
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);	//���ô�������Ļ����
		setTitle("Worm");		//���ñ���ΪWorm
		setResizable(false);		//������������
		setLayout(new FlowLayout());	//���ò��ֹ�����

		ws = new WormStage();		//�½���̨�����
		
		label = new JLabel("�밴�ո��������Ϸ�Ŀ�ʼ����ͣ�������������̰�����˶�����");
		
		add(label);
		add(ws);
				
		/* ����̰�����˶��¼� */
		addKeyListener(ws.getInnerInstanceOfKeyControl());
		ws.addKeyListener(ws.getInnerInstanceOfKeyControl());
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new WormFrame();
	}
}
