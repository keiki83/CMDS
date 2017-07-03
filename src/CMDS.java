import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

public class CMDS {
	// variables
	private int count = 0;

	// gui variables
	private JFrame frmCmds;
	private JButton btnRemoveServer;
	private JTabbedPane tabbedPane;

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
		loadServers();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCmds = new JFrame();
		frmCmds.setTitle("CMDS");
		frmCmds.setBounds(100, 100, 832, 595);
		frmCmds.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCmds.getContentPane().setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 796, 509);
		frmCmds.getContentPane().add(tabbedPane);

		JMenuBar menuBar = new JMenuBar();
		frmCmds.setJMenuBar(menuBar);

		JButton btnAddServer = new JButton("Add Server");
		btnAddServer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseClick) {
				if(SwingUtilities.isLeftMouseButton(mouseClick)) {
					tabbedPane.add("Server" + Integer.toString(count) , new Server(count));
					count++;
				}
			}
		});
		menuBar.add(btnAddServer);

		btnRemoveServer = new JButton("Remove Server");
		btnRemoveServer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseClick) {
				if(SwingUtilities.isLeftMouseButton(mouseClick)) {
					tabbedPane.remove(tabbedPane.getSelectedIndex());
				}
			}
		});
		menuBar.add(btnRemoveServer);

	}

	private void loadServers() {
		tabbedPane.add("Server" + Integer.toString(count) , 
				new Server(count, 
						"D:\\SteamLibrary\\steamapps\\common\\Avorion\\", 
						"D:\\SteamLibrary\\steamapps\\common\\Avorion\\bin\\AvorionServer.exe", 
						"--use-steam-networking 1 --galaxy-name dedicated_server_beta --admin tps"));
		count++;
		tabbedPane.add("Server" + Integer.toString(count) , 
				new Server(count, 
						"D:\\SteamLibrary\\steamapps\\common\\7 Days to Die Dedicated Server\\", 
						"D:\\SteamLibrary\\steamapps\\common\\7 Days to Die Dedicated Server\\7DaysToDieServer.exe", 
						"-logfile 7DaysToDieServer_Data\\output_log.txt -quit -batchmode -nographics -configfile=serverconfig.xml -dedicated"));
		count++;tabbedPane.add("Server" + Integer.toString(count) , 
				new Server(count, 
						"c:/", 
						"cmd.exe", 
						""));
		count++;
	}
}
