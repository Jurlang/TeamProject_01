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
	ImageIcon f1 = new ImageIcon("images/블랙체크.png");
	ImageIcon s1 = new ImageIcon("images/빨간체크.png");
	ImageIcon no_c = new ImageIcon("images/검은별.png");
	ImageIcon yes_c = new ImageIcon("images/빨간별.png");
	private SharedMoney m = null;
	
	ImageIcon[] fimg = { new ImageIcon("images/검은별.png"),
						 new ImageIcon("images/빨간별.png"),
						 new ImageIcon("images/블랙체크.png") };
	ImageIcon[] iimg = { new ImageIcon("images/검은별.png"),
						 new ImageIcon("images/빨간별.png"),
						 new ImageIcon("images/블랙체크.png") };
	
	String[] fname = { "개새", "10새", "짭새" };
	String[] ffunc = { "초당 1원", "초당 10원", "초당 100원" };
	String[] fprice = { "1000원", "2000원", "3000원" };
	int[] flevel = {1,1,1};
	
	String[] iname = {"시바", "ㅈ", "꿀"};
	String[] ifunc = {"x2", "x3", "x4"};
	String[] iprice = { "10000원", "20000원", "30000원" };
	
	int uppertabmoney = 1;
	
	JLabel autoMoney;
	JLabel tabMoney;
	JLabel moneyLa;
	JLabel lvLa;
	JButton levelupBtn;
	
	int userNum;

	public Game_Main(int un) {
		this.userNum = un;
		System.out.println(un);
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
		itemshopBtn.setIcon(new ImageIcon("images/세트스.png"));
		itemshopBtn.setHorizontalTextPosition(JButton.CENTER);
		shopPanel.add(itemshopBtn);

		JButton frishopBtn = new JButton("\uB3D9\uB8CC");
		frishopBtn.setBounds(167, 0, 151, 64);
		frishopBtn.setIcon(new ImageIcon("images/용병.jpg"));

		shopPanel.add(frishopBtn);

		JButton statisticsBtn = new JButton("\uD1B5\uACC4");
		statisticsBtn.setBounds(333, 0, 151, 64);
		statisticsBtn.setIcon(new ImageIcon("images/통계.jpg"));
		shopPanel.add(statisticsBtn);

		levelupBtn = new JButton("\uB808\uBCA8\uC5C5 !!!!!! \uB6B1\uAE61\uB6B1\uAE61");
		levelupBtn.setBounds(0, 53, 485, 48);
		
		//levelupBtn.setIcon(new ImageIcon("images/레벨업.jpg"));
		levelupBtn.setText("150원");
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
		moneyLa.setFont(new Font("굴림", Font.BOLD, 19));
		moneyLa.setHorizontalAlignment(SwingConstants.RIGHT);
		// ------------------------------------------------------------------------------------------
		m = new SharedMoney(moneyLa);
		AutoMoney amth = new AutoMoney(autoMoney, m);
		TabMoney tmth = new TabMoney(bgImgPanel, tabMoney, m, mainch, f1, s1);

		lvLa = new JLabel("Lv .1");
		lvLa.setHorizontalAlignment(SwingConstants.CENTER);
		lvLa.setBounds(156, 21, 87, 22);
		menuPanel.add(lvLa);

		AL l = new AL(this, "용병 스토어", m);
		frishopBtn.addActionListener(l);
		itemshopBtn.addActionListener(new ItemShopBtnAL(this, "아이템스토어", m));

		LevelUpBtnAL ll = new LevelUpBtnAL(this, m);
		levelupBtn.addActionListener(ll);
		LevelBuyBtn lbth = new LevelBuyBtn(this);
		lbth.start();

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
		new FriendShop("용병 스토어", main, m);
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
		new ItemShop("아이템 스토어", main, m);
	}

}

class LevelUpBtnAL implements ActionListener{
	Game_Main main;
	SharedMoney m;
	LevelUpBtnAL(Game_Main main, SharedMoney m){
		this.main = main;
		this.m = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String levelupMoney = main.levelupBtn.getText();
		int o = Integer.parseInt(levelupMoney.substring(0, levelupMoney.indexOf("원")));
		m.minus(o);
		o = (int)(o+150);
		main.levelupBtn.setText(o + "원");
		
		String s = main.lvLa.getText();
		int n = Integer.parseInt(s.substring(s.indexOf(".") + 1));
		n++;
		main.lvLa.setText("Lv ." + n);
		String w = main.tabMoney.getText();
		int x = Integer.parseInt(w.substring(0, w.indexOf("원")));
		x = x+main.uppertabmoney;
		main.tabMoney.setText(x + "원");
	}
}
