import java.awt.EventQueue;

import javax.swing.JFrame;

public class CMDS {

	private JFrame frmCmds;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CMDS window = new CMDS();
					window.frmCmds.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CMDS() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCmds = new JFrame();
		frmCmds.setTitle("CMDS");
		frmCmds.setBounds(100, 100, 450, 300);
		frmCmds.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
