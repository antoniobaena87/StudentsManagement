package gestion_alumnos;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class AddPanel extends JPanel {
	
	MainFrame mainFrame;
	
	JPanel personalDataPanel, addressPanel, buttonsPanel;
	
	JTextField keyField, nameField, surnameField, ageField;
	JTextField streetField, numberField, postCodeField;
	ArrayList<JTextField> fieldsList;
	
	JButton cleanButton, addButton;
	
	public AddPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		this.setLayout(new BoxLayout(this, 1));
		
		fieldsList = new ArrayList<>();
		
		initUpperPanel();
		initBottomPanel();
		initButtonsPanel();
		
		this.add(personalDataPanel);
		this.add(addressPanel);
		this.add(buttonsPanel);
	}
	
	void initUpperPanel(){
		personalDataPanel = new JPanel();
		TitledBorder border = new TitledBorder("Datos personales:");
		personalDataPanel.setBorder(border);
		personalDataPanel.setLayout(new GridLayout(4, 2));
		
		JLabel keyLabel = new JLabel("Clave:");
		JLabel nameLabel = new JLabel("Nombre:");
		JLabel surnameLabel = new JLabel("Apellidos:");
		JLabel ageLabel = new JLabel("Edad:");
		
		int columns = 10;
		keyField = new JTextField(columns);
		nameField = new JTextField(columns);
		surnameField = new JTextField(columns);
		ageField = new JTextField(columns);
		
		fieldsList.add(keyField);
		fieldsList.add(nameField);
		fieldsList.add(surnameField);
		fieldsList.add(ageField);
		
		personalDataPanel.add(keyLabel);
		personalDataPanel.add(keyField);
		personalDataPanel.add(nameLabel);
		personalDataPanel.add(nameField);
		personalDataPanel.add(surnameLabel);
		personalDataPanel.add(surnameField);
		personalDataPanel.add(ageLabel);
		personalDataPanel.add(ageField);
	}
	
	void initBottomPanel() {
		addressPanel = new JPanel();
		TitledBorder border = new TitledBorder("Dirección:");
		addressPanel.setBorder(border);
		addressPanel.setLayout(new GridLayout(3, 2));
		
		JLabel streeLabel = new JLabel("Calle:");
		JLabel numberLabel = new JLabel("Número:");
		JLabel postCodeLabel = new JLabel("Código Postal:");
		
		int columns = 10;
		streetField = new JTextField(columns);
		numberField = new JTextField(columns);
		postCodeField = new JTextField(columns);
		
		fieldsList.add(streetField);
		fieldsList.add(numberField);
		fieldsList.add(postCodeField);
		
		addressPanel.add(streeLabel);
		addressPanel.add(streetField);
		addressPanel.add(numberLabel);
		addressPanel.add(numberField);
		addressPanel.add(postCodeLabel);
		addressPanel.add(postCodeField);
	}
	
	void initButtonsPanel() {
		buttonsPanel = new JPanel();
		
		cleanButton = new JButton("Limpiar");
		cleanButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clean();
			}
		});
		
		addButton = new JButton("Dar de alta");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add();
			}
		});
		
		buttonsPanel.add(cleanButton);
		buttonsPanel.add(addButton);
	}
	
	void clean() {
		for(JTextField field:fieldsList) {
			field.setText("");
		}
	}
	
	void add() {
		Alumno alumno = new Alumno(keyField.getText(), nameField.getText(), surnameField.getText(),
				ageField.getText(), streetField.getText(), numberField.getText(), postCodeField.getText());
		if(mainFrame.addAlumno(alumno) != -1) clean();
	}
}
