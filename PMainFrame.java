package TeamProject_01;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class PMainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PMainFrame frame = new PMainFrame();
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
	public PMainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);	// x좌표, y좌표, 너비, 높이
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel lp=new JPanel();
		contentPane.add(lp);
		//lp.setLayout(null);
		
		JButton btnRegi = new JButton("\uD68C\uC6D0\uAC00\uC785");
		//btnRegi.setBounds(165, 188, 81, 23);
		lp.add(btnRegi);
		
		JButton btnLogin = new JButton("\uB85C\uADF8\uC778");
		//btnLogin.setBounds(258, 188, 69, 23);
		lp.add(btnLogin);
		
		JButton btnQuit = new JButton("Quit");
		//btnQuit.setBounds(339, 188, 55, 23);
		lp.add(btnQuit);
		
		
		setVisible(true);
		
		MainListener l=new MainListener(this);
		btnRegi.addActionListener(l);
		btnLogin.addActionListener(l);
		btnQuit.addActionListener(l);
		
	}
	
	class MainListener implements ActionListener{

		PMainFrame frame=null;
		MainListener(PMainFrame frame){
			this.frame=frame;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String cmd=arg0.getActionCommand();
			
			switch(cmd) {
			case "회원가입":
				frame.setVisible(false);
				new ProjectRegister(this.frame);
				break;
			
			case "로그인":
				frame.setVisible(false);
				new ProjectLogin(this.frame);
				break;
				
			case "Quit":
				frame.dispose();
			}
		}
		
	}

}
