package TeamProject_01;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class In_game_main extends JFrame {
	boolean chk = true;
	private JPanel contentPane;
	ImageIcon f1 = new ImageIcon("images/ºí·¢Ã¼Å©.png");
	ImageIcon s1 = new ImageIcon("images/»¡°£Ã¼Å©.png");
	ImageIcon no_c = new ImageIcon("images/°ËÀºº°.png");
	ImageIcon yes_c = new ImageIcon("images/»¡°£º°.png");

	public In_game_main() {
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
		shopPanel.setLayout(new GridLayout(1, 3, 15, 20));
		
		JButton itemshopBtn = new JButton("\uC544\uC774\uD15C");
		itemshopBtn.setIcon(new ImageIcon("images/»¡°£º°.png"));
		itemshopBtn.setHorizontalTextPosition(JButton.CENTER);
		shopPanel.add(itemshopBtn);
		
		JButton frishopBtn = new JButton("\uB3D9\uB8CC");
		shopPanel.add(frishopBtn);
		
		JButton statisticsBtn = new JButton("\uD1B5\uACC4");
		shopPanel.add(statisticsBtn);
		
		JButton levelupBtn = new JButton("\uB808\uBCA8\uC5C5 !!!!!! \uB6B1\uAE61\uB6B1\uAE61");
		levelupBtn.setBounds(0, 53, 485, 48);
		menuPanel.add(levelupBtn);
		
		JPanel infopanel = new JPanel();
		infopanel.setBounds(0, 490, 485, 81);
		bgImgPanel.add(infopanel);
		infopanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel FLabel1 = new JLabel("\uB3D9\uB8CC1");
		FLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		FLabel1.setBackground(Color.GRAY);
		infopanel.add(FLabel1);
		
		JLabel FLabel2 = new JLabel("\uB3D9\uB8CC2");
		FLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		FLabel2.setBackground(Color.GRAY);
		infopanel.add(FLabel2);
		
		JLabel FLabel3 = new JLabel("\uB3D9\uB8CC3");
		FLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		FLabel3.setBackground(Color.GRAY);
		infopanel.add(FLabel3);
		
		JPanel mainpanel = new JPanel();
		mainpanel.setBounds(0, 0, 485, 490);
		bgImgPanel.add(mainpanel);
		mainpanel.setLayout(null);
		
		JLabel mainch = new JLabel("");
		mainch.setBounds(160, 251, 143, 166);
		mainch.setIcon(no_c);
		mainpanel.add(mainch);
		
		JLabel fr3 = new JLabel();
		fr3.setBounds(335, 27, 103, 147);
		Moving th3 = new Moving(fr3, f1, s1);
		th3.start();
		mainpanel.add(fr3);
		
		JLabel fr2 = new JLabel();
		fr2.setBounds(189, 27, 103, 147);
		Moving th2 = new Moving(fr2, f1, s1);
		th2.start();
		mainpanel.add(fr2);
		
		JLabel fr1 = new JLabel();
		Moving th1 = new Moving(fr1, f1, s1);
		th1.start();
		fr1.setBounds(52, 27, 103, 147);
		mainpanel.add(fr1);
		
		JLabel moneyLa = new JLabel("0\uC6D0");
		moneyLa.setBounds(0, 0, 485, 43);
		mainpanel.add(moneyLa);
		
		if(chk) {
			fr1.setVisible(true);
			fr2.setVisible(true);
			fr3.setVisible(true);
		}else {
			fr1.setVisible(false);
			fr2.setVisible(false);
			fr3.setVisible(false);
		}
		
		bgImgPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				mainch.setIcon(no_c);
				String m = moneyLa.getText();
				int num = Integer.parseInt(m.substring(0, m.indexOf("¿ø")));
				num = num + 1;
				System.out.println(num);
				m = num+"¿ø";
				moneyLa.setText(m);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				mainch.setIcon(yes_c);
			}
		});
		
		this.setVisible(true);
	}
}
