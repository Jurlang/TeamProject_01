package TeamProject_01;

import java.awt.Color;
import java.awt.Font;
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
public class Game_Main extends JFrame {
	boolean[] chk = { false, false, false };
	private JPanel contentPane;
	ImageIcon f1 = new ImageIcon("images/��üũ.png");
	ImageIcon s1 = new ImageIcon("images/����üũ.png");
	ImageIcon no_c = new ImageIcon("images/������.png");
	ImageIcon yes_c = new ImageIcon("images/������.png");
	private SharedMoney m = null;
	ImageIcon[] img = { new ImageIcon("images/������.png"), new ImageIcon("images/������.png"),
			new ImageIcon("images/��üũ.png") };
	String[] fname = { "����", "10��", "¬��" };
	String[] iname = {"�ù�", "��", "��"};
	String[] ffunc = { "�ʴ� 1��", "�ʴ� 10��", "�ʴ� 100��" };
	String[] ifunc = {"x2", "x3", "x4"};
	String[] fprice = { "1000��", "2000��", "3000��" };
	String[] iprice = { "10000��", "20000��", "30000��" };
	JLabel autoMoney;
	JLabel tabMoney;
	JLabel moneyLa;
	JLabel lvLa;
	JButton levelupBtn;

	public Game_Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 787);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel bgImgPanel = new JPanel();
		bgImgPanel.setBounds(0, 0, 485, 748);
		contentPane.add(bgImgPanel);
		bgImgPanel.setLayout(null);

		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 574, 485, 170);
		bgImgPanel.add(menuPanel);
		menuPanel.setLayout(null);

		JPanel shopPanel = new JPanel();
		shopPanel.setBounds(0, 106, 485, 64);
		menuPanel.add(shopPanel);
		shopPanel.setLayout(null);

		JButton itemshopBtn = new JButton();
		itemshopBtn.setBounds(1, 0, 151, 64);
		itemshopBtn.setIcon(new ImageIcon("images/��Ʈ��.png"));
		itemshopBtn.setHorizontalTextPosition(JButton.CENTER);
		itemshopBtn.addActionListener(new ItemShopBtnAL(this, "�����۽����", m));
		shopPanel.add(itemshopBtn);

		JButton frishopBtn = new JButton("\uB3D9\uB8CC");
		frishopBtn.setBounds(167, 0, 151, 64);
		frishopBtn.setIcon(new ImageIcon("images/�뺴.jpg"));

		shopPanel.add(frishopBtn);

		JButton statisticsBtn = new JButton("\uD1B5\uACC4");
		statisticsBtn.setBounds(333, 0, 151, 64);
		statisticsBtn.setIcon(new ImageIcon("images/���.jpg"));
		shopPanel.add(statisticsBtn);

		levelupBtn = new JButton("\uB808\uBCA8\uC5C5 !!!!!! \uB6B1\uAE61\uB6B1\uAE61");
		levelupBtn.setBounds(0, 53, 485, 48);
		
		//levelupBtn.setIcon(new ImageIcon("images/������.jpg"));
		levelupBtn.setText("150��");
		levelupBtn.setEnabled(false);
		menuPanel.add(levelupBtn);

		JLabel mainchImg = new JLabel("\uBA54\uC778\uCF00\uB9AD\uD130\uC774\uBBF8\uC9C0");
		mainchImg.setBounds(20, 2, 70, 50);
		menuPanel.add(mainchImg);

		JLabel autoLa = new JLabel("\uCD08\uB2F9");
		autoLa.setHorizontalAlignment(SwingConstants.CENTER);
		autoLa.setBounds(386, 10, 87, 15);
		menuPanel.add(autoLa);

		JLabel tabLa = new JLabel("\uD0ED\uB2F9");
		tabLa.setHorizontalAlignment(SwingConstants.CENTER);
		tabLa.setBounds(287, 10, 87, 15);
		menuPanel.add(tabLa);

		tabMoney = new JLabel("1\uC6D0");
		tabMoney.setHorizontalAlignment(SwingConstants.CENTER);
		tabMoney.setBounds(287, 27, 87, 22);
		menuPanel.add(tabMoney);

		autoMoney = new JLabel("0\uC6D0");
		autoMoney.setHorizontalAlignment(SwingConstants.CENTER);
		autoMoney.setBounds(386, 27, 87, 22);
		menuPanel.add(autoMoney);

//------------------------------------------------------------------------------------------
		JPanel infopanel = new JPanel();
		infopanel.setBounds(0, 490, 485, 81);
		bgImgPanel.add(infopanel);
		infopanel.setLayout(null);

		JLabel FriendImg1 = new JLabel("\uB3D9\uB8CC1");
		FriendImg1.setBounds(1, 0, 161, 81);
		FriendImg1.setHorizontalAlignment(SwingConstants.CENTER);
		FriendImg1.setBackground(Color.GRAY);
		infopanel.add(FriendImg1);

		JLabel FriendImg2 = new JLabel("\uB3D9\uB8CC2");
		FriendImg2.setBounds(162, 0, 161, 81);
		FriendImg2.setHorizontalAlignment(SwingConstants.CENTER);
		FriendImg2.setBackground(Color.GRAY);
		infopanel.add(FriendImg2);

		JLabel FriendImg3 = new JLabel("\uB3D9\uB8CC3");
		FriendImg3.setBounds(323, 0, 161, 81);
		FriendImg3.setHorizontalAlignment(SwingConstants.CENTER);
		FriendImg3.setBackground(Color.GRAY);
		infopanel.add(FriendImg3);
//------------------------------------------------------------------------------------------
		JPanel mainpanel = new JPanel();
		mainpanel.setBounds(0, 0, 485, 490);
		bgImgPanel.add(mainpanel);
		mainpanel.setLayout(null);

		JLabel mainch = new JLabel("");
		mainch.setBounds(160, 251, 143, 166);
		mainch.setIcon(no_c);
		mainpanel.add(mainch);

//------------------------------------------------------------------------------------------		
		JLabel fr3 = new JLabel();
		fr3.setBounds(335, 56, 103, 123);
		Moving th3 = new Moving(fr3, f1, s1, this, 2);
		th3.start();
		mainpanel.add(fr3);

		JLabel fr2 = new JLabel();
		fr2.setBounds(189, 51, 103, 123);
		Moving th2 = new Moving(fr2, f1, s1, this, 1);
		th2.start();
		mainpanel.add(fr2);

		JLabel fr1 = new JLabel();
		Moving th1 = new Moving(fr1, f1, s1, this, 0);
		th1.start();
		fr1.setBounds(52, 51, 103, 123);
		mainpanel.add(fr1);

		JPanel sangdanBar = new JPanel();
		sangdanBar.setBounds(0, 0, 485, 43);
		mainpanel.add(sangdanBar);
		sangdanBar.setLayout(null);

		JButton btnNewButton = new JButton("\uC800\uC7A5");
		btnNewButton.setBounds(0, 0, 91, 43);
		sangdanBar.add(btnNewButton);
		// ------------------------------------------------------------------------------------------
		moneyLa = new JLabel("0\uC6D0");
		moneyLa.setBounds(92, 0, 392, 43);
		sangdanBar.add(moneyLa);
		moneyLa.setFont(new Font("����", Font.BOLD, 19));
		moneyLa.setHorizontalAlignment(SwingConstants.RIGHT);
		// ------------------------------------------------------------------------------------------
		m = new SharedMoney(moneyLa);
		AutoMoney amth = new AutoMoney(autoMoney, m);
		TabMoney tmth = new TabMoney(bgImgPanel, tabMoney, m, mainch, f1, s1);

		lvLa = new JLabel("Lv .1");
		lvLa.setHorizontalAlignment(SwingConstants.CENTER);
		lvLa.setBounds(156, 21, 87, 22);
		menuPanel.add(lvLa);

		AL l = new AL(this, "�뺴 �����", m);
		frishopBtn.addActionListener(l);

		LevelBuyBtn lbth = new LevelBuyBtn(this);
		lbth.start();
		levelupBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String levelupMoney = levelupBtn.getText();
				int o = Integer.parseInt(levelupMoney.substring(0, levelupMoney.indexOf("��")));
				m.minus(o);
				o = (int)(o+150);
				levelupBtn.setText(o + "��");
				
				String s = lvLa.getText();
				int n = Integer.parseInt(s.substring(s.indexOf(".") + 1));
				n++;
				lvLa.setText("Lv ." + n);
				String w = tabMoney.getText();
				int x = Integer.parseInt(w.substring(0, w.indexOf("��")));
				x++;
				tabMoney.setText(x + "��");
			}
		});

		amth.start();
		tmth.start();
//------------------------------------------------------------------------------------------

		this.setVisible(true);
	}
}

class AL implements ActionListener {
	Game_Main main;
	String store;
	SharedMoney m;

	AL(Game_Main main, String store, SharedMoney m) {
		this.main = main;
		this.store = store;
		this.m = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new FriendShop("�뺴 �����", main, m);
	}
}

class ItemShopBtnAL implements ActionListener {
	Game_Main main;
	String store;
	SharedMoney m;
	
	ItemShopBtnAL(Game_Main main, String store, SharedMoney m) {
		this.main = main;
		this.store = store;
		this.m = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new ItemShop("������ �����", main, m);
	}

}
