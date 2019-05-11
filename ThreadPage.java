package TeamProject_01;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

class Moving extends Thread {
	boolean mov = true;
	JLabel la = null;
	ImageIcon f = null;
	ImageIcon s = null;
	
	Moving(JLabel la,ImageIcon f,ImageIcon s){
		this.la = la;
		this.f = f;
		this.s = s;
	}
	
	public void run() {
		while (true) {
			try {
				sleep(500);
				if (mov) {
					la.setIcon(f);
					mov = false;
				}
				else {
					la.setIcon(s);
					mov = true;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				return;
			}
		}
	}
	
}