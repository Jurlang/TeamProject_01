package TeamProject_01;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

@SuppressWarnings("serial")
public class Statistics_Frame extends JFrame {

	private JPanel contentPane;
	JLabel unl;
	JLabel aml;
	JLabel ucml;
	JLabel ull;
	JLabel utml;
	JLabel usml;
	JLabel gll;
	JLabel sll;
	JLabel jll;
	JTextArea uil;

	/**
	 * Create the frame.
	 */
	public Statistics_Frame(Game_Main main, String name, int allmoney, int curmoney, int lv, int tm, int am, int[] flv,
			int item) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel tongPa = new JPanel();
		tongPa.setBackground(Color.WHITE);
		tongPa.setBounds(0, 0, 436, 763);
		contentPane.add(tongPa);


		JLabel titleLa = new JLabel("\uD1B5         \uACC4");
		titleLa.setBackground(Color.WHITE);
		titleLa.setHorizontalAlignment(SwingConstants.CENTER);
		titleLa.setFont(new Font("±Ã¼­", Font.PLAIN, 49));
		titleLa.setBounds(0, 0, 436, 70);
		tongPa.add(titleLa);

		JPanel dataPa = new JPanel();
		dataPa.setBackground(Color.WHITE);
		dataPa.setBounds(0, 70, 436, 628);
		tongPa.add(dataPa);
		dataPa.setLayout(new GridLayout(10, 2, 15, 15));

		JLabel untl = new JLabel("\uB2C8 \uC774\uB984");
		untl.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
		untl.setHorizontalAlignment(SwingConstants.CENTER);
		dataPa.add(untl);

		unl = new JLabel("");
		unl.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
		unl.setText(name);
		dataPa.add(unl);

		JLabel amtl = new JLabel("\uB2C8 \uCD1D \uB3C8");
		amtl.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
		amtl.setHorizontalAlignment(SwingConstants.CENTER);
		dataPa.add(amtl);

		aml = new JLabel("");
		aml.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
		aml.setText(allmoney + "¿ø");
		dataPa.add(aml);

		JLabel ucmtl = new JLabel("\uB2C8 \uD604\uC7AC \uB3C8");
		ucmtl.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
		ucmtl.setHorizontalAlignment(SwingConstants.CENTER);
		dataPa.add(ucmtl);

		ucml = new JLabel("");
		ucml.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
		ucml.setText(curmoney + "¿ø");
		dataPa.add(ucml);

		JLabel ultl = new JLabel("\uB2C8 \uB808\uBCA8");
		ultl.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
		ultl.setHorizontalAlignment(SwingConstants.CENTER);
		dataPa.add(ultl);

		ull = new JLabel("");
		ull.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
		ull.setText("Lv ." + lv);
		dataPa.add(ull);

		JLabel utmtl = new JLabel("\uB2C8 \uD0ED\uB2F9 \uB3C8");
		utmtl.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
		utmtl.setHorizontalAlignment(SwingConstants.CENTER);
		dataPa.add(utmtl);

		utml = new JLabel("");
		utml.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
		utml.setText(tm + "¿ø");
		dataPa.add(utml);

		JLabel usmtl = new JLabel("\uB2C8 \uCD08\uB2F9 \uB3C8");
		usmtl.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
		usmtl.setHorizontalAlignment(SwingConstants.CENTER);
		dataPa.add(usmtl);

		usml = new JLabel("");
		usml.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
		usml.setText(am + "¿ø");
		dataPa.add(usml);

		JLabel gltl = new JLabel("\uAC1C\uC0C8 \uB808\uBCA8");
		gltl.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
		gltl.setHorizontalAlignment(SwingConstants.CENTER);
		dataPa.add(gltl);

		gll = new JLabel("");
		gll.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
		gll.setText("Lv ." + flv[0]);
		dataPa.add(gll);

		JLabel sltl = new JLabel("10\uC0C8 \uB808\uBCA8");
		sltl.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
		sltl.setHorizontalAlignment(SwingConstants.CENTER);
		dataPa.add(sltl);

		sll = new JLabel("");
		sll.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
		sll.setText("Lv ." + flv[1]);
		dataPa.add(sll);

		JLabel jltl = new JLabel("\uC9ED\uC0C8 \uB808\uBCA8");
		jltl.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
		jltl.setHorizontalAlignment(SwingConstants.CENTER);
		dataPa.add(jltl);

		jll = new JLabel("");
		jll.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
		jll.setText("Lv ." + flv[2]);
		dataPa.add(jll);

		JLabel uitl = new JLabel("\uB2C8\uAC00 \uAC00\uC9C4 \uC544\uC774\uD15C");
		uitl.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
		uitl.setHorizontalAlignment(SwingConstants.CENTER);
		dataPa.add(uitl);

		uil = new JTextArea("");
		//uil.setEnabled(false);
		uil.setEditable(false);
		uil.setOpaque(false);
		uil.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 12));
		uil.setForeground(Color.black);
		String itemlist = "";
		for (int i = 0; i < item; i++) {
			if (i == item - 1)
				itemlist += main.iname[i];
			else if(i == 1)
				itemlist += main.iname[i] + ",\n";
			else
				itemlist += main.iname[i] + ", ";
		}
		uil.setText(itemlist);
		dataPa.add(uil);

		JButton commitBtn = new JButton("\uC644\uB8CC", new ImageIcon("images/¸ÞÀÎ¹öÆ°.jpg"));
		commitBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		commitBtn.setVerticalTextPosition(SwingConstants.CENTER);
		commitBtn.setBounds(137, 708, 143, 51);
		commitBtn.addActionListener(new CommitBtnAL(this));
		tongPa.add(commitBtn);

		setVisible(true);
	}
}

class CommitBtnAL implements ActionListener {
	Statistics_Frame sf;

	CommitBtnAL(Statistics_Frame sf) {
		this.sf = sf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		sf.dispose();
	}

}