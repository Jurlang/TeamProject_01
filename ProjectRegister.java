package TeamProject_01;
import java.awt.EventQueue;

//import ProjectRegister.MyListener;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class ProjectRegister extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	//private JLabel lblNewLabel_1;
	private JTextField tfPw;
	//private JLabel lblNewLabel_2;
	private JTextField tfName;
	private JTextField tfBir;
	private JTextField tfEml;
	private PMainFrame frame;

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
											//���̵�,���,�̸�,�������,�̸� ���ڼ��� 10���� �س���
	/**										  ĭ�� �ʹ� ������ �ϴ� 10����....
	 * Create the frame.
	 */
	public ProjectRegister(PMainFrame frame) {
		this.frame = frame;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout());
		setContentPane(contentPane);
				
		JPanel pn = new JPanel();
		contentPane.add(pn);
		pn.setLayout(new GridLayout(5, 2, 5, 5)); // (��,��, ��ʺ�, ������)
		
		JLabel lblNewLabel = new JLabel("\uC544  \uC774  \uB514");
		pn.add(lblNewLabel);
		
		tfId = new JTextField();
		pn.add(tfId);
		tfId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		pn.add(lblNewLabel_1);
		
		
		tfPw = new JTextField();
		pn.add(tfPw);
		tfPw.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\uC774       \uB984");
		pn.add(lblNewLabel_2);
		
		
		tfName = new JTextField();
		pn.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\uB098       \uC774");
		pn.add(lblNewLabel_3);
		
		
		tfBir = new JTextField();
		pn.add(tfBir);
		tfBir.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("\uC804\uD654\uBC88\uD638");
		pn.add(lblNewLabel_4);
		
		
		tfEml = new JTextField();
		pn.add(tfEml);
		tfEml.setColumns(10);
		
		JPanel ps = new JPanel();
		contentPane.add(ps);
		
		JButton btnAdd = new JButton("\uD68C\uC6D0\uAC00\uC785");
		ps.add(btnAdd);
		
		JButton btnCancel = new JButton("\uCDE8\uC18C");
		ps.add(btnCancel);
		
		JButton btnClose = new JButton("\uB2EB\uAE30");
		ps.add(btnClose);
		
		setVisible(true);
		
		MyListener l=new MyListener(frame, this);
		btnAdd.addActionListener(l);
		btnCancel.addActionListener(l);
		btnClose.addActionListener(l);
	}
	
	class MyListener implements ActionListener{
		PMainFrame mframe;
		ProjectRegister tframe;
		MyListener(PMainFrame mframe, ProjectRegister tframe){
			this.mframe=mframe;
			this.tframe = tframe;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String cmd = e.getActionCommand();
			if(cmd.trim().equals("ȸ������")) {
				String id=tfId.getText();
				String pw=tfPw.getText();
				String name=tfName.getText();
				int bir=Integer.parseInt(tfBir.getText());
				String eml=tfEml.getText();
				
				PMember m=new PMember(id,pw,name,bir,eml);
				DBConn dbConn=DBConn.getInstance();
				dbConn.insert(m);
				init();	//init=�ʱ�ȭ
			}else if(cmd.trim().equals("���")) {
				init();
			}else if(cmd.trim().equals("�ݱ�")) {
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
