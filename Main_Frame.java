package TeamProject_01;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
//¾È¹Ù²åÁö·Õ >_<
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
		setBounds(100, 100, 450, 300);	// xÁÂÇ¥, yÁÂÇ¥, ³Êºñ, ³ôÀÌ
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

		Main_Frame frame=null;
		MainListener(Main_Frame frame){
			this.frame=frame;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String cmd=arg0.getActionCommand();
			
			switch(cmd) {
			case "È¸¿ø°¡ÀÔ":
				frame.setVisible(false);
				new Register_Frame(this.frame);
				break;
			
			case "·Î±×ÀÎ":
				frame.setVisible(false);
				new Login_Frame(this.frame);
				break;
				
			case "Quit":
				frame.dispose();
			}
		}
		
	}

}
