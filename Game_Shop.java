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
	protected JPanel itemListPanel;
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
		item = new ItemPanel[4];
		itemListPanel.setLayout(new GridLayout(item.length, 1, 0, 0));
		getContentPane().add(inShopPanel);

		JButton inShopBtn = new JButton("\uC644\uB8CC");
		inShopBtn.setBounds(131, 419, 91, 40);
		inShopBtn.addActionListener(new BtnActionListener(this));
		inShopPanel.add(inShopBtn);

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

		JLabel itemName = new JLabel("   이름 : " + name);
		itemName.setBackground(new Color(153, 0, 0));
		itemName.setBounds(81, 11, 155, 20);
		itemName.setOpaque(true);
		this.add(itemName);

		itemFunc = new JLabel("   성능 : " + func);
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

//------------- 용병 샾 기본 Shop를 상속받음.
@SuppressWarnings("serial")
class FriendShop extends Game_Shop {
	FriendShop fs;
	Game_Main main;
	SharedMoney m;
	JPanel itemListPanel;

	public FriendShop(String store, Game_Main main, SharedMoney m) {
		super(store, main);
		this.main = main;
		this.m = m;
		this.fs = this;

		for (int i = 0; i < 3; i++) {
			item[i] = new ItemPanel(super.itemListPanel, main.fimg[i], main.fname[i], main.ffunc[i]);
		}
		for (int i = 0; i < 3; i++) {
			super.item[i].buyBtn.setText(main.fprice[i]+"원");
		}

		for (int i = 0; i < 3; i++) {
			super.item[i].buyBtn.addActionListener(new FBuyBtnListener(fs, i, main, m));
			// 리스너 등록 -> 구입
		}
		FriendBuyBtn th = new FriendBuyBtn(main, this);
		th.start();
		// 쓰레드 나의 돈이 용병을 구입할수 있는지 확인하는 스레드
	}

}

// 리스너 클래스
class FBuyBtnListener implements ActionListener {
	int btnNum;
	Game_Main main;
	SharedMoney m;
	FriendShop fs;

	FBuyBtnListener(FriendShop fs, int btnNum, Game_Main main, SharedMoney m) {
		this.btnNum = btnNum;
		this.main = main;
		this.m = m;
		this.fs = fs;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		main.chk[btnNum] = true;
		main.flevel[btnNum] += 1;
		
		String bm = fs.item[btnNum].buyBtn.getText();
		int bch = Integer.parseInt(bm.substring(0, bm.indexOf("원")));
		// 버튼에 적혀있는 돈을 받아옴.
		m.minus(bch);
		// 버튼에 있는 돈만큼 내가 가진돈에서 뺀다.
		bch = (int)(bch * 1.5);
		// 버튼 구매에 필요한 돈을 더 높인다.
		main.fprice[btnNum] = bch;
		// 버튼 구매 필요한 돈 배열에 값을 저장
		fs.item[btnNum].buyBtn.setText(bch+"원");
		// 버튼에 값을 새로 바꿔준다.

		String am = main.autoMoney.getText();
		int ach = Integer.parseInt(am.substring(0, am.indexOf("원")));
		// 내가 오토당 버는 돈을 얻어온다.
		String plus_am = fs.item[btnNum].itemFunc.getText();
		int pam = Integer.parseInt(plus_am.substring(plus_am.indexOf("당") + 2, plus_am.indexOf("원")));
		// 아이템의 성능에 적혀있는 +하는 돈
		main.autoMoney.setText((ach + pam) + "원");
		// 오토당 돈의 성능 업그레이드

		if (btnNum == 0) 
			main.ffunc[btnNum] = "초당 " + (pam + 1) + "원";
		else if (btnNum == 1)
			main.ffunc[btnNum] = "초당 " + (pam + 10) + "원";
		else if (btnNum == 2)
			main.ffunc[btnNum] = "초당 " + (pam + 100) + "원";
		// 사기전 친구들 가게에 있는 용병 친구들 업그레이드 성능 높여줌

		fs.item[btnNum].itemFunc.setText("성능 : " + main.ffunc[btnNum]);
		// 높여준 성능 적어주기.

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
			int m = Integer.parseInt(mymoney.substring(0, mymoney.indexOf("원")));
			String[] bm = new String[3];
			int[] btnmoney = new int[3];
			for (int i = 0; i < 3; i++) {
				bm[i] = fs.item[i].buyBtn.getText();
				btnmoney[i] = Integer.parseInt(bm[i].substring(0, bm[i].indexOf("원")));
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

class LevelBuyBtn extends Thread {
	Game_Main main;

	LevelBuyBtn(Game_Main main) {
		this.main = main;
	}

	@Override
	public void run() {
		while (true) {
			String mymoney = main.moneyLa.getText();
			int n = Integer.parseInt(mymoney.substring(0, mymoney.indexOf("원")));

			String levelupMoney = main.levelupBtn.getText();
			int o = Integer.parseInt(levelupMoney.substring(0, levelupMoney.indexOf("원")));

			if (n >= o)
				main.levelupBtn.setEnabled(true);
			else
				main.levelupBtn.setEnabled(false);
		}
	}
}

@SuppressWarnings("serial")
class ItemShop extends Game_Shop {
	ItemShop is;
	Game_Main main;
	SharedMoney m;

	public ItemShop(String store, Game_Main main, SharedMoney m) {
		super(store, main);
		this.main = main;
		this.m = m;
		this.is = this;

		for (int i = 0; i < item.length; i++) {
			item[i] = new ItemPanel(super.itemListPanel, main.iimg[i], main.iname[i], main.ifunc[i]);
		}

		for (int i = 0; i < item.length; i++) {
			if(i <= main.myitem-1) {
				super.item[i].buyBtn.setText("구입완료");
			}
			else {
				super.item[i].buyBtn.setText(main.iprice[i]);
			}
		}

		for (int i = 0; i < item.length; i++) {
			super.item[i].buyBtn.addActionListener(new IBuyBtnListener(is, i, main, m));
		}
		ItemBuyBtn itemth = new ItemBuyBtn(main, this);
		itemth.start();
	}

}

class IBuyBtnListener implements ActionListener {
	int btnNum;
	Game_Main main;
	SharedMoney m;
	ItemShop is;

	IBuyBtnListener(ItemShop is, int btnNum, Game_Main main, SharedMoney m) {
		this.btnNum = btnNum;
		this.main = main;
		this.m = m;
		this.is = is;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String bm = is.item[btnNum].buyBtn.getText();

		int bch = Integer.parseInt(bm.substring(0, bm.indexOf("원")));
		System.out.println(bch);
		m.minus(bch);
		bm = "구입완료";
		main.iprice[btnNum] = bm;
		is.item[btnNum].buyBtn.setText(bm);

		String am = main.tabMoney.getText();
		int ach = Integer.parseInt(am.substring(0, am.indexOf("원")));
		String plus_am = is.item[btnNum].itemFunc.getText();
		int pam = Integer.parseInt(plus_am.substring(plus_am.indexOf("x") + 1));
		main.uppertabmoney *= pam;
		main.tabMoney.setText((ach * pam) + "원");
	}

}

class ItemBuyBtn extends Thread {

	Game_Main main;
	ItemShop is;

	ItemBuyBtn(Game_Main main, ItemShop is) {
		this.main = main;
		this.is = is;
	}

	@Override
	public void run() {
		while (true) {
			String mymoney = main.moneyLa.getText();
			int m = Integer.parseInt(mymoney.substring(0, mymoney.indexOf("원")));
			String[] bm = new String[4];
			int[] btnmoney = new int[4];
			for (int i = 0; i < is.item.length; i++) {
				bm[i] = is.item[i].buyBtn.getText();
				if (!(bm[i].equals("구입완료")))
					btnmoney[i] = Integer.parseInt(bm[i].substring(0, bm[i].indexOf("원")));
			}
			for (int i = 0; i < is.item.length; i++) {
				if (!(bm[i].equals("구입완료"))) {
					if (m >= btnmoney[i])
						is.item[i].buyBtn.setEnabled(true);
					else
						is.item[i].buyBtn.setEnabled(false);
				}
				else {
					is.item[i].buyBtn.setEnabled(false);
				}
			}
		}
	}
}