package TeamProject_01;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Moving extends Thread {
	boolean mov = true;
	JLabel la = null;
	ImageIcon f = null;
	ImageIcon s = null;
	Game_Main main;
	int num;
	FriendTrueOrFalse x;

	Moving(JLabel la, ImageIcon f, ImageIcon s, Game_Main main, FriendTrueOrFalse x, int num) {
		this.la = la;
		this.f = f;
		this.s = s;
		this.main = main;
		this.x = x;
		this.num = num;
	}

	public void run() {
		while (true) {
			if (x.chk_true(num)) {
				la.setVisible(true);
				try {
					sleep(500);
					if (mov) {
						la.setIcon(f);
						mov = false;
					} else {
						la.setIcon(s);
						mov = true;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					return;
				}
			} else {
				la.setVisible(false);
			}
		}
	}

}
//---------------------------------------------------------------------------------------------

class SharedMoney {
	JLabel money;
	private int sum;
	Game_Main main;

	SharedMoney(JLabel money, Game_Main main) {
		this.money = money;
		sum = main.curmoney;
		this.main = main;
	}

	synchronized public void add(int money) {
		main.allmoney += money;
		sum += money;
		main.curmoney = sum;
		this.money.setText(sum + "��");
	}

	synchronized public void minus(int money) {
		sum -= money;
		main.curmoney = sum;
		this.money.setText(sum + "��");
	}

	public int getSum() {
		return sum;
	}

}

class AutoMoney extends Thread {
	JLabel autoMoneyLa;
	String am;
	SharedMoney cur_m;
	int amoney;
	boolean stop = true;

	AutoMoney(JLabel autoMoneyLa, SharedMoney cur_m) {
		this.autoMoneyLa = autoMoneyLa;
		this.cur_m = cur_m;
	}

	public int getCurMoney() {

		return cur_m.getSum();
	}

	public int getAutoMoney() {

		return amoney;
	}

	@Override
	public void run() {
		while (true) {
			am = autoMoneyLa.getText();
			amoney = Integer.parseInt(am.substring(0, am.indexOf("��")));
			cur_m.add(amoney);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

class TabMoney extends Thread {
	JPanel tabPanel;
	int tmoney;
	SharedMoney cur_m;
	JLabel mainch;
	ImageIcon f, s;
	String tm;
	JLabel tabMoneyLa;
	Game_Main main;

	TabMoney(JPanel tabPanel, JLabel tabMoneyLa, SharedMoney cur_m, JLabel mainch, ImageIcon f, ImageIcon s,
			Game_Main main) {
		this.tabPanel = tabPanel;
		this.tabMoneyLa = tabMoneyLa;
		this.tm = tabMoneyLa.getText();
		this.tmoney = Integer.parseInt(tm.substring(0, tm.indexOf("��")));
		this.cur_m = cur_m;
		this.mainch = mainch;
		this.f = f;
		this.s = s;
		this.main = main;
	}

	@Override
	public void run() {
		tabPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (main.myitem == 1) {
					f = main.no_c[1];
				} else if (main.myitem == 2) {
					f = main.no_c[2];
				} else if (main.myitem == 3) {
					f = main.no_c[3];
				}
				tm = tabMoneyLa.getText();
				tmoney = Integer.parseInt(tm.substring(0, tm.indexOf("��")));
				mainch.setIcon(f);
				cur_m.add(tmoney);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (main.myitem == 1) {
					s = main.yes_c[1];
				} else if (main.myitem == 2) {
					s = main.yes_c[2];
				} else if (main.myitem == 3) {
					s = main.yes_c[3];
				}
				mainch.setIcon(s);
			}
		});
	}
}

class MainChChange extends Thread {
	Game_Main main;
	TabMoney th;
	MainchChangesyn y;
	int x;

	MainChChange(Game_Main main, TabMoney th, MainchChangesyn y) {
		this.th = th;
		this.main = main;
		this.y = y;
	}

	@Override
	public void run() {
		while (true) {
			if (x != y.itemnum(main.myitem)) {
				if (y.itemnum(main.myitem) == 0)
					main.mainch.setIcon(main.no_c[0]);
				else if (y.itemnum(main.myitem) == 1)
					main.mainch.setIcon(main.no_c[1]);
				else if (y.itemnum(main.myitem) == 2)
					main.mainch.setIcon(main.no_c[2]);
				else if (y.itemnum(main.myitem) == 3)
					main.mainch.setIcon(main.no_c[3]);
				else if (y.itemnum(main.myitem)==4)
					main.itemimg.setIcon(new ImageIcon("images/�����.png"));
				x = y.itemnum(main.myitem);
			}
		}
	}
}