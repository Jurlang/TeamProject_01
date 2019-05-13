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
public class Remake_Shop extends JFrame {

	private JPanel contentPane;
	private JLabel showNameLa;
	private JPanel itemListPanel;

	
	static ImageIcon[] img = {new ImageIcon("images/검은별.png"), new ImageIcon("images/빨간별.png"), new ImageIcon("images/블랙체크.png")};
	static String[] name = {"개새", "10새", "짭새"};
	static String[] func = {"x 2", "x 3", "x 4"};

	public Remake_Shop(String store, ImageIcon[] img, String[] name, String[] func) {
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
		ItemPanel[] item = new ItemPanel[3];
		itemListPanel.setLayout(new GridLayout(item.length,1 , 0, 0));
		getContentPane().add(inShopPanel);
		
		JButton inShopBtn = new JButton("\uC644\uB8CC");
		inShopBtn.setBounds(131, 419, 91, 40);
		inShopBtn.addActionListener(new BtnActionListener(this));
		inShopPanel.add(inShopBtn);
		
		for(int i=0;i<item.length;i++) {
			item[i] = new ItemPanel(itemListPanel, img[i], name[i], func[i]);
		}
		this.setVisible(true);
	}
}

class BtnActionListener implements ActionListener{
	
	Remake_Shop rs;
	
	BtnActionListener(Remake_Shop rs){
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


class ShopTh extends Thread{
	Remake_Shop s;
	
	JButton btn;
	ShopTh(Remake_Shop s, JButton btn){
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
