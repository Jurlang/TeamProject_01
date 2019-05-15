package TeamProject_01;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

	ImageIcon[] fimg = { new ImageIcon("images/검은별.png"), new ImageIcon("images/빨간별.png"),
			new ImageIcon("images/블랙체크.png") };
	ImageIcon[] iimg = { new ImageIcon("images/검은별.png"), new ImageIcon("images/빨간별.png"),
			new ImageIcon("images/블랙체크.png"), new ImageIcon("images/빨간별.png") };

	String[] fname = new String[3];
	String[] ffunc = new String[3];
	int[] fprice = { 1000, 2000, 3000 };
	int[] flevel = new int[3];

	String[] iname = new String[4];
	String[] ifunc = new String[4];
	String[] iprice = { "10000원", "20000원", "30000원", "40000원" };

	int automoney;
	int curmoney;
	int allmoney;
	int tabmoney;
	int uppertabmoney = 1;

	int level;
	int myitem;

	JLabel autoMoney;
	JLabel tabMoney;
	JLabel moneyLa;
	JLabel lvLa;
	JButton levelupBtn;

	String user_name;
	int userNum;

	public Game_Main(int un, Login_info_Class userinfo, String user_name) {

		this.user_name = user_name;
		this.userNum = un;

		fname = userinfo.friendname;
		flevel = userinfo.friendlevel;
		for (int i = 0; i < 3; i++) {
			if (flevel[i] != 0)
				chk[i] = true;
		}
		ffunc[0] = "초당 " + (1 * (flevel[0] + 1)) + "원";
		ffunc[1] = "초당 " + (10 * (flevel[1] + 1)) + "원";
		ffunc[2] = "초당 " + (100 * (flevel[2] + 1)) + "원";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < flevel[i]; j++) {
				fprice[i] *= 1.5;
			}
		}

		iname = userinfo.itemname;
		ifunc[0] = "x" + userinfo.itemfunc[0];
		ifunc[1] = "x" + userinfo.itemfunc[1];
		ifunc[2] = "x" + userinfo.itemfunc[2];
		ifunc[3] = "x" + userinfo.itemfunc[3];

		curmoney = userinfo.curmoney;
		allmoney = userinfo.allmoney;

		level = userinfo.mylevel;
		myitem = userinfo.myitem;
		if (myitem == 1)
			uppertabmoney = 2;
		else if (myitem == 2)
			uppertabmoney = 6;
		else if (myitem == 3)
			uppertabmoney = 24;
		else if (myitem == 4)
			uppertabmoney = 120;

		tabmoney = level * uppertabmoney;
		automoney = (1 * flevel[0]) + (10 * flevel[1]) + (100 * flevel[2]);

		this.addWindowListener(new CloseBtnWL(this));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

		levelupBtn = new JButton();
		levelupBtn.setBounds(0, 53, 485, 48);

		// levelupBtn.setIcon(new ImageIcon("images/레벨업.jpg"));
		levelupBtn.setText((level * 150) + "원");
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

		tabMoney = new JLabel(tabmoney + "\uC6D0");
		tabMoney.setHorizontalAlignment(SwingConstants.CENTER);
		tabMoney.setBounds(287, 27, 87, 22);
		menuPanel.add(tabMoney);

		autoMoney = new JLabel(automoney + "\uC6D0");
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
		FriendTrueOrFalse ftf0 = new FriendTrueOrFalse(this, 0);
		FriendTrueOrFalse ftf1 = new FriendTrueOrFalse(this, 1);
		FriendTrueOrFalse ftf2 = new FriendTrueOrFalse(this, 2);

		JLabel fr3 = new JLabel();
		fr3.setBounds(335, 56, 103, 123);
		Moving th3 = new Moving(fr3, f1, s1, this, ftf2, 2);
		th3.start();
		mainpanel.add(fr3);

		JLabel fr2 = new JLabel();
		fr2.setBounds(189, 51, 103, 123);
		Moving th2 = new Moving(fr2, f1, s1, this, ftf1, 1);
		th2.start();
		mainpanel.add(fr2);

		JLabel fr1 = new JLabel();
		Moving th1 = new Moving(fr1, f1, s1, this, ftf0, 0);
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
		m = new SharedMoney(moneyLa, this);
		AutoMoney amth = new AutoMoney(autoMoney, m);
		TabMoney tmth = new TabMoney(bgImgPanel, tabMoney, m, mainch, f1, s1);

		lvLa = new JLabel("Lv ." + level);
		lvLa.setHorizontalAlignment(SwingConstants.CENTER);
		lvLa.setBounds(156, 21, 87, 22);
		menuPanel.add(lvLa);

		AL l = new AL(this, "용병 스토어", m);
		frishopBtn.addActionListener(l);
		itemshopBtn.addActionListener(new ItemShopBtnAL(this, "아이템스토어", m));

		LevelUpBtnAL ll = new LevelUpBtnAL(this, m);
		levelupBtn.addActionListener(ll);
		LevelBuyBtn lbth = new LevelBuyBtn(this);
		statisticsBtnAL lll = new statisticsBtnAL(this, user_name);
		statisticsBtn.addActionListener(lll);

		lbth.start();
		amth.start();
		tmth.start();

		btnNewButton.addActionListener(new DBUpdate(this, amth, tmth));
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

class LevelUpBtnAL implements ActionListener {
	Game_Main main;
	SharedMoney m;

	LevelUpBtnAL(Game_Main main, SharedMoney m) {
		this.main = main;
		this.m = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String levelupMoney = main.levelupBtn.getText();
		int o = Integer.parseInt(levelupMoney.substring(0, levelupMoney.indexOf("원")));
		m.minus(o);
		o = (int) (o + 150);
		main.levelupBtn.setText(o + "원");

		main.level++;
		String s = main.lvLa.getText();
		int n = Integer.parseInt(s.substring(s.indexOf(".") + 1));
		n++;
		main.tabmoney = n;
		main.lvLa.setText("Lv ." + n);
		String w = main.tabMoney.getText();
		int x = Integer.parseInt(w.substring(0, w.indexOf("원")));
		x = x + main.uppertabmoney;
		main.tabMoney.setText(x + "원");
	}
}

class DBUpdate implements ActionListener {

	Game_Main main;
	AutoMoney amth;
	TabMoney tmth;

	DBUpdate(Game_Main main, AutoMoney amth, TabMoney tmth) {
		this.main = main;
		this.tmth = tmth;
		this.amth = amth;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Update_info_Class a = new Update_info_Class(main.userNum, 0, 0, 0, 0, main.level, main.myitem, main.flevel);
		amth.stop = false;
		a.curmoney = amth.getCurMoney();
		a.allmoney = main.allmoney;
		a.automoney = amth.getAutoMoney();
		a.tabmoney = tmth.tmoney;
		DBConn dbConn = DBConn.getInstance();
		dbConn.info_save(a);
		amth.stop = true;

	}

}

class statisticsBtnAL implements ActionListener {
	Game_Main main;
	String name;

	statisticsBtnAL(Game_Main main, String name) {
		this.main = main;
		this.name = name;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new Statistics_Frame(main, name, main.allmoney, main.curmoney, main.level, main.tabmoney, main.automoney,
				main.flevel, main.myitem);
	}

}

class CloseBtnWL implements WindowListener {
	Game_Main main;

	CloseBtnWL(Game_Main main) {
		this.main = main;
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (JOptionPane.showConfirmDialog(main, "진짜 닫으시겠습니까? 닫으면 저장이 안되고 모든 창이 다 꺼집니다.", "진짜 꺼요  ????",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			System.exit(0);
		} else {

		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

}
