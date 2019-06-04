package TeamProject_01;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Register_Frame extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	// private JLabel lblNewLabel_1;
	private JPasswordField tfPw;
	// private JLabel lblNewLabel_2;
	private JTextField tfName;
	private JTextField tfBir;
	private JTextField tfEml;
	private Main_Frame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					ProjectRegister frame = new ProjectRegister();
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 아이디,비번,이름,생년월일,이멜 글자수를 10개로 해놓음
	/**
	 * 칸이 너무 길어보여서 일단 10개로.... Create the frame.
	 */
	public Register_Frame(Main_Frame frame) {
		this.frame = frame;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 489);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pn = new JPanel();
		pn.setBackground(Color.WHITE);
		pn.setBounds(23, 156, 237, 198);
		contentPane.add(pn);
		pn.setLayout(new GridLayout(5, 2, 5, 15)); // (행,열, 행너비, 열높이)

		JLabel lblNewLabel = new JLabel("\uC544  \uC774  \uB514");
		lblNewLabel.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pn.add(lblNewLabel);

		tfId = new JTextField();
		pn.add(tfId);
		tfId.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_1.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		pn.add(lblNewLabel_1);

		tfPw = new JPasswordField();
		pn.add(tfPw);
		tfPw.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("\uC774       \uB984");
		lblNewLabel_2.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		pn.add(lblNewLabel_2);

		tfName = new JTextField();
		pn.add(tfName);
		tfName.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("생년월일");
		lblNewLabel_3.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		pn.add(lblNewLabel_3);

		tfBir = new JTextField();
		pn.add(tfBir);
		tfBir.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("이  메  일");
		lblNewLabel_4.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		pn.add(lblNewLabel_4);

		tfEml = new JTextField();
		pn.add(tfEml);
		tfEml.setColumns(10);

		JPanel ps = new JPanel();
		ps.setBackground(Color.WHITE);
		ps.setBounds(12, 395, 260, 33);
		contentPane.add(ps);
		ps.setLayout(null);

		JButton btnAdd = new JButton("\uD68C\uC6D0\uAC00\uC785", new ImageIcon("images/메인버튼.jpg"));
		btnAdd.setBounds(12, 5, 100, 25);
		btnAdd.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		btnAdd.setVerticalTextPosition(SwingConstants.CENTER);
		btnAdd.setHorizontalTextPosition(SwingConstants.CENTER);
		ps.add(btnAdd);

		JButton btnCancel = new JButton("\uCDE8\uC18C", new ImageIcon("images/메인버튼.jpg"));
		btnCancel.setBounds(114, 5, 70, 25);
		btnCancel.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		btnCancel.setVerticalTextPosition(SwingConstants.CENTER);
		btnCancel.setHorizontalTextPosition(SwingConstants.CENTER);
		ps.add(btnCancel);

		JButton btnClose = new JButton("\uB2EB\uAE30", new ImageIcon("images/메인버튼.jpg"));
		btnClose.setBounds(187, 5, 70, 25);
		btnClose.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		btnClose.setHorizontalTextPosition(SwingConstants.CENTER);
		btnClose.setVerticalTextPosition(SwingConstants.CENTER);
		ps.add(btnClose);
		
		JLabel misaeLogo = new JLabel(new ImageIcon("images/돈미새로고.png"));
		misaeLogo.setBounds(74, 10, 132, 136);
		contentPane.add(misaeLogo);

		setVisible(true);

		MyListener l = new MyListener(frame, this);
		btnAdd.addActionListener(l);
		btnCancel.addActionListener(l);
		btnClose.addActionListener(l);
	}

	class MyListener implements ActionListener {
		Main_Frame mframe;
		Register_Frame tframe;

		MyListener(Main_Frame mframe, Register_Frame tframe) {
			this.mframe = mframe;
			this.tframe = tframe;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String cmd = e.getActionCommand();
			if (cmd.trim().equals("회원가입")) {
				String id = tfId.getText();
				@SuppressWarnings("deprecation")
				String pw = tfPw.getText();
				String name = tfName.getText();
				String bir = tfBir.getText();
				String eml = tfEml.getText();

				Member_Class m = new Member_Class(id, pw, name, bir, eml);
				DBConn dbConn = DBConn.getInstance();
				dbConn.insert(m);
				tframe.dispose();
				frame.setVisible(true);
			} else if (cmd.trim().equals("취소")) {
				init();
			} else if (cmd.trim().equals("닫기")) {
				tframe.dispose();
				frame.setVisible(true);
			}
		}

		private void init() {
			tfId.setText("");
			tfPw.setText("");
			tfName.setText("");
			tfBir.setText("");
			tfEml.setText("");
		}
	}
}
