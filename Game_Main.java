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
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class Game_Main extends JFrame {
	boolean[] chk = { false, false, false };
	private JPanel contentPane;
	ImageIcon f[] = {new ImageIcon("images/f1-1.png"),new ImageIcon("images/f2-1.png"),new ImageIcon("images/f3-1.png")};
	ImageIcon s[] = {new ImageIcon("images/f1-2.png"),new ImageIcon("images/f2-2.png"),new ImageIcon("images/f3-2.png")};
	ImageIcon[] no_c = {new ImageIcon("images/1-1.png"), new ImageIcon("images/2-1.png"), new ImageIcon("images/3-1.png"), new ImageIcon("images/4-1.png")};
	ImageIcon[] yes_c = {new ImageIcon("images/1-2.png"), new ImageIcon("images/2-2.png"), new ImageIcon("images/3-2.png"), new ImageIcon("images/4-2.png")};
	private SharedMoney m = null;

	ImageIcon[] fimg = { new ImageIcon("images/상점개새민증.png"), new ImageIcon("images/상점십새민증.png"),
			new ImageIcon("images/상점짭새민증.png") };
	ImageIcon[] iimg = { new ImageIcon("images/상점스냅.png"), new ImageIcon("images/상점썬글.png"),
			new ImageIcon("images/상점다이아.png"), new ImageIcon("images/상점즈금통.png") };

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
	JLabel itemimg;
	JButton levelupBtn;
	JLabel mainch;
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
		EtchedBorder eborder=new EtchedBorder(EtchedBorder.RAISED);//평면에 끌로 판듯이 외곽선 효과를 내는 것이고 양각의 효과를 준다.
		JPanel bgImgPanel = new JPanel();
		bgImgPanel.setBackground(Color.WHITE);
		bgImgPanel.setBounds(0, 0, 485, 748);
		contentPane.add(bgImgPanel);
		bgImgPanel.setLayout(null);

		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(Color.WHITE);
		menuPanel.setBounds(0, 574, 485, 170);
		bgImgPanel.add(menuPanel);
		menuPanel.setLayout(null);

		JPanel shopPanel = new JPanel();
		shopPanel.setBounds(0, 106, 485, 64);
		menuPanel.add(shopPanel);
		shopPanel.setLayout(null);

		JButton itemshopBtn = new JButton("아이템");
		itemshopBtn.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		itemshopBtn.setBounds(1, 0, 151, 64);
		itemshopBtn.setIcon(new ImageIcon("images/상점과통계버튼.jpg"));
		itemshopBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		itemshopBtn.setVerticalTextPosition(SwingConstants.CENTER);
		shopPanel.add(itemshopBtn);

		JButton frishopBtn = new JButton("\uB3D9\uB8CC");
		frishopBtn.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		frishopBtn.setBounds(167, 0, 151, 64);
		frishopBtn.setIcon(new ImageIcon("images/상점과통계버튼.jpg"));
		frishopBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		frishopBtn.setVerticalTextPosition(SwingConstants.CENTER);
		shopPanel.add(frishopBtn);

		JButton statisticsBtn = new JButton("\uD1B5\uACC4");
		statisticsBtn.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		statisticsBtn.setBounds(333, 0, 151, 64);
		statisticsBtn.setIcon(new ImageIcon("images/상점과통계버튼.jpg"));
		statisticsBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		statisticsBtn.setVerticalTextPosition(SwingConstants.CENTER);
		shopPanel.add(statisticsBtn);

		levelupBtn = new JButton(new ImageIcon("images/레벨업버튼.jpg"));
		levelupBtn.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		levelupBtn.setVerticalTextPosition(SwingConstants.CENTER);
		levelupBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		levelupBtn.setBounds(0, 62, 485, 39);

		// levelupBtn.setIcon(new ImageIcon("images/레벨업.jpg"));
		levelupBtn.setText((level * 150) + "원");
		levelupBtn.setEnabled(false);
		menuPanel.add(levelupBtn);

		JLabel mainchImg = new JLabel("\uBA54\uC778\uCF00\uB9AD\uD130\uC774\uBBF8\uC9C0");
		mainchImg.setBorder(eborder);
		mainchImg.setIcon(new ImageIcon("C:\\Users\\JMH\\Desktop\\JAVA\\TeamProject\\images\\\uBBF8\uC0C8\uBBFC\uC99D\uC0AC\uC9C4.png"));
		mainchImg.setBounds(20, 2, 70, 50);
		menuPanel.add(mainchImg);

		JLabel autoLa = new JLabel("\uCD08\uB2F9");
		autoLa.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		autoLa.setHorizontalAlignment(SwingConstants.CENTER);
		autoLa.setBounds(386, 10, 87, 15);
		menuPanel.add(autoLa);

		JLabel tabLa = new JLabel("\uD0ED\uB2F9");
		tabLa.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		tabLa.setHorizontalAlignment(SwingConstants.CENTER);
		tabLa.setBounds(287, 10, 87, 15);
		menuPanel.add(tabLa);

		tabMoney = new JLabel(tabmoney + "\uC6D0");
		tabMoney.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		tabMoney.setHorizontalAlignment(SwingConstants.CENTER);
		tabMoney.setBounds(287, 27, 87, 22);
		menuPanel.add(tabMoney);

		autoMoney = new JLabel(automoney + "\uC6D0");
		autoMoney.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		autoMoney.setHorizontalAlignment(SwingConstants.CENTER);
		autoMoney.setBounds(386, 27, 87, 22);
		menuPanel.add(autoMoney);

//------------------------------------------------------------------------------------------
		JPanel infopanel = new JPanel();
		infopanel.setBorder(new LineBorder(Color.black));
		infopanel.setBackground(Color.WHITE);
		infopanel.setBounds(0, 490, 485, 81);
		bgImgPanel.add(infopanel);
		infopanel.setLayout(null);

		JLabel FriendImg1 = new JLabel(new ImageIcon("images/메인개새민증.png"));
		FriendImg1.setBorder(new LineBorder(Color.black));
		FriendImg1.setBounds(1, 1, 161, 79);
		FriendImg1.setHorizontalAlignment(SwingConstants.CENTER);
		FriendImg1.setBackground(Color.GRAY);
		infopanel.add(FriendImg1);

		JLabel FriendImg2 = new JLabel(new ImageIcon("images/메인십새민증.png"));
		FriendImg2.setBorder(new LineBorder(Color.black));
		FriendImg2.setBounds(162, 1, 161, 79);
		FriendImg2.setHorizontalAlignment(SwingConstants.CENTER);
		FriendImg2.setBackground(Color.GRAY);
		infopanel.add(FriendImg2);

		JLabel FriendImg3 = new JLabel(new ImageIcon("images/메인짭새민증.png"));
		FriendImg3.setBorder(new LineBorder(Color.black));
		FriendImg3.setBounds(323, 1, 161, 79);
		FriendImg3.setHorizontalAlignment(SwingConstants.CENTER);
		FriendImg3.setBackground(Color.GRAY);
		infopanel.add(FriendImg3);
//------------------------------------------------------------------------------------------
		JPanel mainpanel = new JPanel();
		mainpanel.setBackground(Color.WHITE);
		mainpanel.setBounds(0, 0, 485, 484);
		bgImgPanel.add(mainpanel);
		mainpanel.setLayout(null);

		mainch = new JLabel("");
		mainch.setBounds(136, 251, 214, 229);
		if (myitem == 0)
			mainch.setIcon(no_c[0]);
		else if (myitem == 1)
			mainch.setIcon(no_c[1]);
		else if (myitem == 2)
			mainch.setIcon(no_c[2]);
		else if (myitem == 3)
			mainch.setIcon(no_c[3]);
		mainpanel.add(mainch);

//------------------------------------------------------------------------------------------
		FriendTrueOrFalse ftf0 = new FriendTrueOrFalse(this, 0);
		FriendTrueOrFalse ftf1 = new FriendTrueOrFalse(this, 1);
		FriendTrueOrFalse ftf2 = new FriendTrueOrFalse(this, 2);

		JLabel fr3 = new JLabel();
		fr3.setBounds(335, 56, 103, 123);
		Moving th3 = new Moving(fr3, f[2], s[2], this, ftf2, 2);
		th3.start();
		mainpanel.add(fr3);

		JLabel fr2 = new JLabel();
		fr2.setBounds(189, 51, 103, 123);
		Moving th2 = new Moving(fr2, f[1], s[1], this, ftf1, 1);
		th2.start();
		mainpanel.add(fr2);

		JLabel fr1 = new JLabel();
		Moving th1 = new Moving(fr1, f[0], s[0], this, ftf0, 0);
		th1.start();
		fr1.setBounds(52, 51, 103, 123);
		mainpanel.add(fr1);

		JPanel sangdanBar = new JPanel();
		sangdanBar.setBackground(Color.WHITE);
		sangdanBar.setBounds(0, 0, 485, 43);
		mainpanel.add(sangdanBar);
		sangdanBar.setLayout(null);

		JButton btnNewButton = new JButton("\uC800\uC7A5", new ImageIcon("images/메인버튼.jpg"));
		btnNewButton.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		btnNewButton.setVerticalTextPosition(SwingConstants.CENTER);
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
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
		TabMoney tmth = new TabMoney(bgImgPanel, tabMoney, m, mainch, no_c[0], yes_c[0], this);
		
		itemimg = new JLabel("");
		itemimg.setBounds(362, 339, 111, 135);
		mainpanel.add(itemimg);

		lvLa = new JLabel("Lv ." + level);
		lvLa.setFont(new Font("HY견고딕", Font.PLAIN, 12));
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
		MainchChangesyn syn = new MainchChangesyn(this);


		lbth.start();
		amth.start();
		tmth.start();
		MainChChange mccth = new MainChChange(this, tmth, syn);
		mccth.start();
		
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
