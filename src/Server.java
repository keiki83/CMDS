/*
 * Author: Michael R. Callan III
 * Version: 1.04
 */

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JTextPane;

import com.ibm.icu.util.StringTokenizer;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;

public class Server extends JPanel {
	// variables
	private Process p;
	private BufferedWriter consoleInput;
	private BufferedReader consoleOutput;
	
	// GUI variables
	private JTextPane serverPath;
	private JTextField serverExe;
	private JTextArea serverConsole;
	private JButton btnLaunchServer;
	private JButton btnStopServer;
	private JScrollPane scrollPane;
	private JTextField serverArg;
	private JTextField commandLine;
	private JLabel lblWorkingDirectory;
	private	JLabel lblExecutable;
	private JLabel lblArguements;
	private JCheckBox chckbxRelativePath;

	/*
	 * Create the panel.
	 */
	public Server(ServerSettings settings) {
		setLayout(null);

		/*
		 *	Labels
		 */
		
		lblWorkingDirectory = new JLabel("Working Directory");
		lblWorkingDirectory.setBounds(10, 11, 86, 14);
		add(lblWorkingDirectory);
		
		lblExecutable = DefaultComponentFactory.getInstance().createLabel("Executable");
		lblExecutable.setBounds(10, 45, 92, 14);
		add(lblExecutable);
		
		lblArguements = DefaultComponentFactory.getInstance().createLabel("Arguements");
		lblArguements.setBounds(10, 76, 92, 14);
		add(lblArguements);
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setBounds(10, 101, 86, 14);
		add(lblOptions);
		
		/*
		 * 	JTextPanes
		 */
		
		serverPath = new JTextPane();
		serverPath.setText(settings.getPath());
		serverPath.setBounds(139, 11, 487, 20);
		add(serverPath);

		serverExe = new JTextField();
		serverExe.setText(settings.getExe());
		serverExe.setColumns(10);
		serverExe.setBounds(139, 42, 487, 20);
		add(serverExe);
		
		serverArg = new JTextField();
		serverArg.setText(settings.getArg());
		serverArg.setBounds(139, 73, 487, 20);
		add(serverArg);
		serverArg.setColumns(10);

		/*
		 * 	JButtons
		 */
		
		btnLaunchServer = new JButton("Launch Server");
		btnLaunchServer.setBounds(636, 11, 119, 23);
		add(btnLaunchServer);

		btnStopServer = new JButton("Stop Server");
		btnStopServer.setBounds(636, 41, 119, 23);
		add(btnStopServer);
		
		/*
		 * 	JScrollPanes
		 */
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 152, 756, 283);
		add(scrollPane);

		/*
		 * 	JTextAreas
		 */
		
		serverConsole = new JTextArea();
		scrollPane.setViewportView(serverConsole);
		serverConsole.setWrapStyleWord(true);
		serverConsole.setLineWrap(true);
		serverConsole.setEditable(false);

		commandLine = new JTextField();
		commandLine.setBounds(10, 446, 756, 20);
		add(commandLine);
		commandLine.setColumns(10);
		
		/*
		 * 	JCheckBoxes
		 */
		
		chckbxRelativePath = new JCheckBox("Relative Path Executable");
		chckbxRelativePath.setBounds(10, 122, 200, 23);
		add(chckbxRelativePath);
		if(settings.isRelativePath()) {
			chckbxRelativePath.setSelected(true);
		}

		/*
		 *	Listeners
		 */
		
		btnLaunchServer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				launchServer();
			}
		});
		
		btnStopServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopServer();
			}
		});
		
		commandLine.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent key) {
				if(key.getKeyCode()==KeyEvent.VK_ENTER){						
					try {
						consoleInput.write(commandLine.getText() + "\n");
						consoleInput.flush();
						commandLine.setText("");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
	}

	/*
	 *	Helper Functions 
	 */

	// Launch the Server
	private void launchServer() {
		try {	
			File dir = new File(serverPath.getText());
			StringTokenizer st = new StringTokenizer(serverArg.getText());
			LinkedList<String> cmd = new LinkedList<String>();
			if(chckbxRelativePath.isSelected()) {
				cmd.add(serverPath.getText() + serverExe.getText());
			}
			else {
				cmd.add(serverExe.getText());
			}
			
			while (st.hasMoreTokens()) {
				cmd.add(st.nextToken());
			}
			ProcessBuilder pb = new ProcessBuilder(cmd);
			pb.directory(dir);
			p = pb.start();			consoleInput = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
			consoleOutput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		
			new Thread(new Runnable() {
				public void run() {

					String line = null; 
					try {
						// update output text window
						while ((line = consoleOutput.readLine()) != null) {	
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
