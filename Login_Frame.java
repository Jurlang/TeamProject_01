package TeamProject_01;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Login_Frame extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JPasswordField tfPw;
	private Main_Frame mainFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					ProjectLogin frame = new ProjectLogin();
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login_Frame(Main_Frame mainFrame) {
		this.mainFrame = mainFrame;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		panel1.setBounds(78, 134, 277, 31);
		contentPane.add(panel1);
		panel1.setLayout(null);

		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514");
		lblNewLabel.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 60, 15);
		panel1.add(lblNewLabel);

		tfId = new JTextField();
		tfId.setBounds(86, 0, 191, 31);
		panel1.add(tfId);
		tfId.setColumns(17);

		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setBounds(78, 170, 277, 31);
		contentPane.add(panel2);
		panel2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_1.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setBounds(12, 10, 60, 15);
		panel2.add(lblNewLabel_1);

		tfPw = new JPasswordField();
		tfPw.setBounds(86, 0, 191, 31);
		panel2.add(tfPw);
		tfPw.setColumns(10);

		JPanel panel3 = new JPanel();
		panel3.setBackground(Color.WHITE);
		panel3.setBounds(78, 218, 277, 33);
		contentPane.add(panel3);
		panel3.setLayout(null);

		JButton btnLogin = new JButton("\uB85C\uADF8\uC778", new ImageIcon("images/메인버튼.jpg"));
		btnLogin.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		btnLogin.setVerticalTextPosition(SwingConstants.CENTER);
		btnLogin.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogin.setBounds(38, 0, 88, 33);
		panel3.add(btnLogin);

		JButton btnCancel = new JButton("\uB098\uAC00\uAE30", new ImageIcon("images/메인버튼.jpg"));
		btnCancel.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		btnCancel.setVerticalTextPosition(SwingConstants.CENTER);
		btnCancel.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancel.setBounds(157, 0, 88, 33);
		panel3.add(btnCancel);
		
		JLabel misaeFontLogo = new JLabel(new ImageIcon("images/돈미새글씨_수정.png"));
		misaeFontLogo.setBounds(32, 24, 374, 94);
		contentPane.add(misaeFontLogo);

		setVisible(true);

		MyListener l = new MyListener(this);
		btnLogin.addActionListener(l);
		btnCancel.addActionListener(l);

	}

	class MyListener implements ActionListener {
		Login_Frame frame;

		MyListener(Login_Frame frame) {
			this.frame = frame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String log = e.getActionCommand();

			if (log.trim().equals("로그인")) {
				String id = tfId.getText();
				@SuppressWarnings("deprecation")
				String pw = tfPw.getText();

				DBConn dbConn = DBConn.getInstance();
				int login = dbConn.confirm(id, pw);
				String msg = "";

				if (login == -2) {
					msg = id + "는 없는 회원입니다.";
				} else if (login == -1) {
					msg = "비밀번호가 틀림";
				} else {
					Member_Class m = dbConn.selectOne(id);
					msg = "환영합니다" + id + "(" + m.getName() + ")님";
					frame.dispose();
					Login_info_Class l = dbConn.info_load(login);
					new Game_Main(login, l, m.getName());
				}
				JOptionPane.showMessageDialog(null, msg);

			} else {
				frame.dispose();
				mainFrame.setVisible(true);
			}
		}

	}

}
