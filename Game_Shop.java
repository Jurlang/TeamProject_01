package TeamProject_01;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Game_Shop extends JFrame {

	private JPanel contentPane;
	private JLabel showNameLa;
	private JPanel itemListPanel;
	ItemPanel[] item;
	
	Game_Main main;

	public Game_Shop(String store, Game_Main main) {
		this.main = main;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 379, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setSize(400, 419);
		setContentPane(contentPane);

		JPanel inShopPanel = new JPanel();
		inShopPanel.setBounds(55, 180, 400, 419);
		inShopPanel.setLayout(null);

		showNameLa = new JLabel(store);
		showNameLa.setBackground(new Color(0, 153, 153));
		showNameLa.setHorizontalAlignment(SwingConstants.CENTER);
		showNameLa.setBounds(12, 10, 329, 41);
		showNameLa.setOpaque(true);
		inShopPanel.add(showNameLa);

		itemListPanel = new JPanel();
		itemListPanel.setBounds(12, 50, 329, 359);
		inShopPanel.add(itemListPanel);
		item = new ItemPanel[3];
		itemListPanel.setLayout(new GridLayout(item.length, 1, 0, 0));
		getContentPane().add(inShopPanel);

		JButton inShopBtn = new JButton("\uC644\uB8CC");
		inShopBtn.setBounds(131, 419, 91, 40);
		inShopBtn.addActionListener(new BtnActionListener(this));
		inShopPanel.add(inShopBtn);

		for (int i = 0; i < item.length; i++) {
			item[i] = new ItemPanel(itemListPanel, main.img[i], main.name[i], main.func[i]);
		}
		this.setVisible(true);
	}
}

class BtnActionListener implements ActionListener {

	Game_Shop rs;

	BtnActionListener(Game_Shop rs) {
		this.rs = rs;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		rs.dispose();
	}

}

@SuppressWarnings("serial")
class ItemPanel extends JPanel {
	ImageIcon img;
	String name, func;
	JButton buyBtn;
	JLabel itemFunc;

	ItemPanel(JPanel p, ImageIcon img, String name, String func) {
		this.setBounds(0, 0, 329, 65);
		p.add(this);
		this.setLayout(null);

		JLabel itemImg = new JLabel(img);
		itemImg.setBackground(new Color(51, 51, 51));
		itemImg.setBounds(12, 9, 57, 46);
		itemImg.setOpaque(true);
		this.add(itemImg);

		JLabel itemName = new JLabel("   �̸� : " + name);
		itemName.setBackground(new Color(153, 0, 0));
		itemName.setBounds(81, 11, 155, 20);
		itemName.setOpaque(true);
		this.add(itemName);

		itemFunc = new JLabel("   ���� : " + func);
		itemFunc.setBackground(new Color(0, 102, 102));
		itemFunc.setBounds(81, 35, 155, 20);
		itemFunc.setOpaque(true);
		this.add(itemFunc);

		buyBtn = new JButton("\uAD6C\uC785");
		buyBtn.setBounds(240, 9, 77, 48);
		buyBtn.setEnabled(false);
		this.add(buyBtn);
	}
}


//------------- �뺴 �� �⺻ Shop�� ��ӹ���.
@SuppressWarnings("serial")
class FriendShop extends Game_Shop {
	FriendShop fs;
	Game_Main main;
	SharedMoney m;
	
	public FriendShop(String store, Game_Main main, SharedMoney m) {
		super(store, main);
		this.main = main;
		this.m = m;
		this.fs = this;
		for (int i = 0; i < item.length; i++) {
			super.item[i].buyBtn.setText(main.fprice[i]);
		}

		for (int i = 0; i < item.length; i++) {
			super.item[i].buyBtn.addActionListener(new BuyBtnListener(fs, i, main, m));
			// ������ ��� -> ����
		}
		FriendBuyBtn th = new FriendBuyBtn(main, this);
		th.start();
		// ������ ���� ���� �뺴�� �����Ҽ� �ִ��� Ȯ���ϴ� ������
	}

}

// ������ Ŭ����
class BuyBtnListener implements ActionListener {
	int btnNum;
	Game_Main main;
	SharedMoney m;
	FriendShop fs;
	BuyBtnListener(FriendShop fs, int btnNum, Game_Main main, SharedMoney m) {
		this.btnNum = btnNum;
		this.main = main;
		this.m = m;
		this.fs = fs;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		main.chk[btnNum] = true;
		String bm = fs.item[btnNum].buyBtn.getText();
		int bch = Integer.parseInt(bm.substring(0, bm.indexOf("��")));
		// ��ư�� �����ִ� ���� �޾ƿ�.
		m.minus(bch);
		// ��ư�� �ִ� ����ŭ ���� ���������� ����.
		bm = ((int) (bch * 1.5)) + "��";
		// ��ư ���ſ� �ʿ��� ���� �� ���δ�.
		main.fprice[btnNum] = bm;
		// ��ư ���� �ʿ��� �� �迭�� ���� ����
		fs.item[btnNum].buyBtn.setText(bm);
		// ��ư�� ���� ���� �ٲ��ش�.
		
		String am = main.autoMoney.getText();
		int ach = Integer.parseInt(am.substring(0, am.indexOf("��")));
		// ���� ����� ���� ���� ���´�.
		String plus_am = fs.item[btnNum].itemFunc.getText();
		int pam = Integer.parseInt(plus_am.substring(plus_am.indexOf("��") + 2, plus_am.indexOf("��")));
		// �������� ���ɿ� �����ִ� +�ϴ� ��
		main.autoMoney.setText((ach + pam) + "��");
		// ����� ���� ���� ���׷��̵�
		
		if(btnNum == 0)
			main.func[btnNum] = "�ʴ� "+(pam+1)+"��";
		else if(btnNum == 1)
			main.func[btnNum] = "�ʴ� "+(pam+10)+"��";
		else if(btnNum == 2)
			main.func[btnNum] = "�ʴ� "+(pam+100)+"��";
		// ����� ģ���� ���Կ� �ִ� �뺴 ģ���� ���׷��̵� ���� ������
		
		fs.item[btnNum].itemFunc.setText("���� : " + main.func[btnNum]);
		// ������ ���� �����ֱ�.

	}

}

class FriendBuyBtn extends Thread {

	Game_Main main;
	FriendShop fs;

	FriendBuyBtn(Game_Main main, FriendShop fs) {
		this.main = main;
		this.fs = fs;
	}

	@Override
	public void run() {
		while (true) {
			String mymoney = main.moneyLa.getText();
			int m = Integer.parseInt(mymoney.substring(0, mymoney.indexOf("��")));
			String[] bm = new String[3];
			int[] btnmoney = new int[3];
			for (int i = 0; i < 3; i++) {
				bm[i] = fs.item[i].buyBtn.getText();
				btnmoney[i] = Integer.parseInt(bm[i].substring(0, bm[i].indexOf("��")));
			}
			for (int i = 0; i < 3; i++) {
				if (m >= btnmoney[i])
					fs.item[i].buyBtn.setEnabled(true);
				else
					fs.item[i].buyBtn.setEnabled(false);
			}
		}
	}
}

class LevelBuyBtn extends Thread{
	Game_Main main;
	
	LevelBuyBtn(Game_Main main){
		this.main = main;
	}
	
	@Override
	public void run() {
		while (true) {
			String mymoney = main.moneyLa.getText();
			int n = Integer.parseInt(mymoney.substring(0, mymoney.indexOf("��")));
			
			String levelupMoney = main.levelupBtn.getText();
			int o = Integer.parseInt(levelupMoney.substring(0, levelupMoney.indexOf("��")));
			
			if(n >= o)
				main.levelupBtn.setEnabled(true);
			else
				main.levelupBtn.setEnabled(false);
		}
	}
}