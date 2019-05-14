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
	Moving(JLabel la, ImageIcon f, ImageIcon s, Game_Main main, int num) {
		this.la = la;
		this.f = f;
		this.s = s;
		this.main = main;
		this.num = num;
	}

	public void run() {
		while (true) {
			if (main.chk[num]) {
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
			}
			else {
				la.setVisible(false);
			}

		}
	}

}
//---------------------------------------------------------------------------------------------

class SharedMoney {
	JLabel money;
	private int sum;

	SharedMoney(JLabel money, Game_Main main) {
		this.money = money;
		sum = main.curmoney;
		
	}

	synchronized public void add(int money) {
		sum += money;
		this.money.setText(sum + "원");
	}

	synchronized public void minus(int money) {
		sum -= money;
		this.money.setText(sum + "원");
	}

}

class AutoMoney extends Thread {
	JLabel autoMoneyLa;
	String am;
	SharedMoney cur_m;
	int amoney;

	AutoMoney(JLabel autoMoneyLa, SharedMoney cur_m) {
		this.autoMoneyLa = autoMoneyLa;
		this.cur_m = cur_m;
	}

	@Override
	public void run() {
		while (true) {
			am = autoMoneyLa.getText();
			amoney = Integer.parseInt(am.substring(0, am.indexOf("원")));
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

	TabMoney(JPanel tabPanel, JLabel tabMoneyLa, SharedMoney cur_m, JLabel mainch, ImageIcon f, ImageIcon s) {
		this.tabPanel = tabPanel;
		this.tabMoneyLa = tabMoneyLa;
		this.tm = tabMoneyLa.getText();
		this.tmoney = Integer.parseInt(tm.substring(0, tm.indexOf("원")));
		this.cur_m = cur_m;
		this.mainch = mainch;
		this.f = f;
		this.s = s;
	}

	@Override
	public void run() {
		tabPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				tm = tabMoneyLa.getText();
				tmoney = Integer.parseInt(tm.substring(0, tm.indexOf("원")));
				mainch.setIcon(f);
				cur_m.add(tmoney);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				mainch.setIcon(s);
			}
		});
	}
}