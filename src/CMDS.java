/*
 * Author: Michael R. Callan III
 * Version: 1.06
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

public class CMDS {
	// Settings
	private String savePath = "servers/";
	
	// GUI variables
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
					ServerSettings settings = new ServerSettings(savePath);
					settings.setName("Server");
					tabbedPane.add(settings.getName(), new Server(settings));
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
		LoadSettings servers = new LoadSettings(savePath);
		while(servers.hasSettings()) {
			tabbedPane.add(servers.peek().getName(), new Server(servers.pop()));
		}
	}
}
