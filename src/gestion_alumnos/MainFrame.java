package gestion_alumnos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

public class MainFrame {
	
	Utils controller;
	HashMap<String, Alumno> alumnos;
	boolean needSaving;
	final String DATA_PATH = "alumnos.data";
	
	JFrame frame;
	JMenuBar menuBar;
	JMenu menuFichero;
	JMenuItem saveItem, exitItem;
	
	JPanel panels;
	AddPanel addPanel;
	RemovePanel removePanel;
	ModifyPanel modifyPanel;
	JTabbedPane tabbedPane;
	
	public MainFrame() {
		
		alumnos = Utils.loadAlumnos(DATA_PATH);
		
		frame = new JFrame("Gestión de alumnos");
		frame.setLayout(new GridBagLayout());
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		
		initMenu();
		initPanels();
		initTabbedPane();
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		frame.add(menuBar, gbc);
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		frame.add(tabbedPane, gbc);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	protected void initMenu() {
		menuBar = new JMenuBar();
		menuFichero = new JMenu("Fichero");
		
		saveItem = new JMenuItem("Salvar");
		saveItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
		exitItem = new JMenuItem("Salir");
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		
		menuFichero.add(saveItem);
		menuFichero.add(exitItem);
		menuBar.add(menuFichero);
	}
	
	protected void initPanels() {
		addPanel = new AddPanel(this);
		removePanel = new RemovePanel(this);
		modifyPanel = new ModifyPanel(this);
	}
	
	protected void initTabbedPane() {
		tabbedPane = new JTabbedPane();
		tabbedPane.add("Altas", addPanel);
		tabbedPane.add("Bajas", removePanel);
		tabbedPane.add("Modificaciones", modifyPanel);
	}
	
	/**
	 * 
	 * @param key
	 * @return -1 if failed
	 */
	int addAlumno(Alumno alumno) {
		if(alumnos.containsKey(alumno.getKey())) {
			JOptionPane.showMessageDialog(frame.getContentPane(), "Ya existe un alumno con esa clave.");
			return -1;
		}else{
			alumnos.put(alumno.getKey(), alumno);
			removePanel.addAlumno(alumno.getKey());
			modifyPanel.addAlumno(alumno.getKey());
			needSaving = true;
		}
		
		return 1;
	}
	
	void removeAlumno(String key) {
		if(key != null) {
			alumnos.remove(key);
			modifyPanel.removeAlumno(key);
			needSaving = true;
		}
	}
	
	void save() {
		if(Utils.save(alumnos, DATA_PATH) != -1) {
			needSaving = false;
		}
	}
	
	HashMap<String, Alumno> getAlumnos(){
		return alumnos;
	}
	
	Alumno getAlumno(String key) {
		return alumnos.get(key);
	}
	
	void exit() {
		int result = 0;
		if(needSaving) {
			result = JOptionPane.showConfirmDialog(frame.getContentPane(), "No has guardado. ¿Estás seguro de que quieres salir?");
		}
		if(result == 0) {
			System.out.println(result);
			frame.dispose();
		}
	}
}
