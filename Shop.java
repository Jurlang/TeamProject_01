package TeamProject_01;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Shop extends JPanel{

	private JLabel showNameLa;
	private JPanel itemListPanel;

	public Shop(String store, ImageIcon[] img, String[] name, String[] func) {

		this.setBounds(0, 0, 485, 748);
		this.setLayout(null);

		
		JPanel inShopPanel = new JPanel();
		inShopPanel.setBounds(55, 180, 353, 419);
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
		ItemPanel[] item = new ItemPanel[3];
		itemListPanel.setLayout(new GridLayout(item.length,1 , 0, 0));
		this.add(inShopPanel);
		
		JLabel quitShopLa = new JLabel();
		quitShopLa.setBounds(0, 0, 485, 748);
		quitShopLa.addMouseListener(new QuitShopMouseListener(this));
		
		this.add(quitShopLa);
		
		
		for(int i=0;i<item.length;i++) {
			item[i] = new ItemPanel(itemListPanel, img[i], name[i], func[i]);
		}
		
		this.setVisible(false);
	}
}
//inShopPanel.setBounds(55, 180, 353, 419);
//showNameLa.setBounds(12, 10, 329, 41);
//itemListPanel.setBounds(12, 50, 329, 359);
//quitShopLa.setBounds(0, 0, 485, 748);




@SuppressWarnings("serial")
class ItemPanel extends JPanel {
	ImageIcon img;
	String name, func;
	ItemPanel(JPanel p, ImageIcon img, String name, String func) {
		this.setBounds(0, 0, 329, 65);
		p.add(this);
		this.setLayout(null);

		JLabel itemImg = new JLabel(img);
		itemImg.setBackground(new Color(51, 51, 51));
		itemImg.setBounds(12, 9, 57, 46);
		itemImg.setOpaque(true);
		this.add(itemImg);

		JLabel itemName = new JLabel("   이름 : " +name);
		itemName.setBackground(new Color(153, 0, 0));
		itemName.setBounds(81, 11, 155, 20);
		itemName.setOpaque(true);
		this.add(itemName);

		JLabel itemFunc = new JLabel("   성능 : " + func);
		itemFunc.setBackground(new Color(0, 102, 102));
		itemFunc.setBounds(81, 35, 155, 20);
		itemFunc.setOpaque(true);
		this.add(itemFunc);

		JButton buyBtn = new JButton("\uAD6C\uC785");
		buyBtn.setBounds(240, 9, 77, 48);
		this.add(buyBtn);
	}
}

class QuitShopMouseListener implements MouseListener{
	Shop s;
	QuitShopMouseListener(Shop s){
		this.s = s;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		s.setVisible(false);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
}

class ShopTh extends Thread{
	Shop s;
	JButton btn;
	ShopTh(Shop s, JButton btn){
		this.s = s;
		this.btn = btn;
	}
	
	@Override
	public void run() {
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				s.setVisible(true);
			}
		});
	}
}