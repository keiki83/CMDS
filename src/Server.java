import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Server extends JPanel {
	// variables
	Process p;
	
	// GUI variables
	private JTextPane serverPath;
	private JTextField serverExe;
	private JTextField param0;
	private JTextField param1;
	private JTextField param2;
	private JTextField val0;
	private JTextField val1;
	private JTextField val2;
	private JTextArea serverConsole;
	private JButton btnStopServer;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public Server(int index) {
		setLayout(null);
		
		serverPath = new JTextPane();
		serverPath.setText("D:\\SteamLibrary\\steamapps\\common\\Avorion\\");
		serverPath.setBounds(10, 11, 487, 20);
		add(serverPath);
		
		serverExe = new JTextField();
		serverExe.setText("bin\\AvorionServer.exe");
		serverExe.setColumns(10);
		serverExe.setBounds(10, 42, 487, 20);
		add(serverExe);
		
		param0 = new JTextField();
		param0.setText("--use-steam-networking");
		param0.setColumns(10);
		param0.setBounds(10, 73, 189, 20);
		add(param0);
		
		param1 = new JTextField();
		param1.setText("--galaxy-name");
		param1.setColumns(10);
		param1.setBounds(10, 104, 189, 20);
		add(param1);
		
		param2 = new JTextField();
		param2.setText("--admin");
		param2.setColumns(10);
		param2.setBounds(10, 135, 189, 20);
		add(param2);
		
		val0 = new JTextField();
		val0.setText("1");
		val0.setColumns(10);
		val0.setBounds(209, 73, 189, 20);
		add(val0);
		
		val1 = new JTextField();
		val1.setText("dedicated_server_beta");
		val1.setColumns(10);
		val1.setBounds(209, 104, 189, 20);
		add(val1);
		
		val2 = new JTextField();
		val2.setText("tps");
		val2.setColumns(10);
		val2.setBounds(209, 135, 189, 20);
		add(val2);
		
		JButton btnLaunchServer = new JButton("Launch Server");
		btnLaunchServer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				launchServer();
			}
		});
		btnLaunchServer.setBounds(10, 166, 119, 23);
		add(btnLaunchServer);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 200, 756, 266);
		add(scrollPane);
		
		serverConsole = new JTextArea();
		scrollPane.setViewportView(serverConsole);
		serverConsole.setWrapStyleWord(true);
		serverConsole.setLineWrap(true);
		serverConsole.setEditable(false);
		
		btnStopServer = new JButton("Stop Server");
		btnStopServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopServer();
			}
		});
		btnStopServer.setBounds(139, 166, 119, 23);
		add(btnStopServer);

	}
	
	/*
	 *	Helper Functions 
	 */
	
	// Launch the Server
	private void launchServer() {
		try {	
			File dir = new File(serverPath.getText());
			String[] cmd = {serverPath.getText() + serverExe.getText(), param0.getText(), val0.getText(), param1.getText(), val1.getText(), param2.getText(), val2.getText()};
			ProcessBuilder pb = new ProcessBuilder(cmd);
			pb.directory(dir);
			p = pb.start();
			new Thread(new Runnable() {
				public void run() {
					BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
					String line = null; 
					try {
						// update output text window
						while ((line = input.readLine()) != null) {	
							serverConsole.append(line + "\r\n");
							serverConsole.setCaretPosition(serverConsole.getDocument().getLength());
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Shutdown the Server forcefully
	private void stopServer() {
		if(p != null & p.isAlive()) {
			p.destroy();
		}
	}
	
}
