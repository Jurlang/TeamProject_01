package TeamProject_01;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;

//안바꿨지롱 >_<
@SuppressWarnings("serial")
public class Main_Frame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Frame frame = new Main_Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main_Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600); // x좌표, y좌표, 너비, 높이
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel lp = new JPanel();
		lp.setBounds(0, 0, 434, 561);
		lp.setBackground(Color.WHITE);
		contentPane.add(lp);
		lp.setLayout(null);

		JButton btnRegi = new JButton("\uD68C\uC6D0\uAC00\uC785", new ImageIcon("images/메인버튼.jpg"));
		btnRegi.setFont(new Font("HY견고딕", Font.PLAIN, 20));
		btnRegi.setVerticalTextPosition(SwingConstants.CENTER);
		btnRegi.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRegi.setBounds(126, 361, 171, 61);
		lp.add(btnRegi);

		JButton btnLogin = new JButton("\uB85C\uADF8\uC778", new ImageIcon("images/메인버튼.jpg"));
		btnLogin.setFont(new Font("HY견고딕", Font.PLAIN, 20));
		btnLogin.setVerticalTextPosition(SwingConstants.CENTER);
		btnLogin.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogin.setBounds(126, 272, 171, 61);
		lp.add(btnLogin);

		JButton btnQuit = new JButton("나가기", new ImageIcon("images/메인버튼.jpg"));
		btnQuit.setFont(new Font("HY견고딕", Font.PLAIN, 20));
		btnQuit.setVerticalTextPosition(SwingConstants.CENTER);
		btnQuit.setHorizontalTextPosition(SwingConstants.CENTER);
		btnQuit.setBounds(126, 450, 171, 61);
		lp.add(btnQuit);
		
		JLabel misaeLogo = new JLabel(new ImageIcon("images/돈미새로고_수정.png"));
		misaeLogo.setBounds(96, 24, 224, 221);
		lp.add(misaeLogo);

		setVisible(true);

		MainListener l = new MainListener(this);
		btnRegi.addActionListener(l);
		btnLogin.addActionListener(l);
		btnQuit.addActionListener(l);

	}

	class MainListener implements ActionListener {

		Main_Frame frame = null;

		MainListener(Main_Frame frame) {
			this.frame = frame;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String cmd = arg0.getActionCommand();

			switch (cmd) {
			case "회원가입":
				frame.setVisible(false);
				new Register_Frame(this.frame);
				break;

			case "로그인":
				frame.setVisible(false);
				new Login_Frame(this.frame);
				break;

			case "나가기":
				frame.dispose();
			}
		}

	}
}
