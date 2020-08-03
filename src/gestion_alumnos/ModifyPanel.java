package gestion_alumnos;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ModifyPanel extends JPanel {
	
MainFrame mainFrame;
	
	JPanel alumnosPanel, personalDataPanel, addressPanel;
	
	JTextField nameField, surnameField, ageField;
	JTextField streetField, numberField, postCodeField;
	
	JButton actionButton;
	
	Alumno selectedAlumno;
	
	JComboBox<String> keysBox;
	
	public ModifyPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		this.setLayout(new BoxLayout(this, 1));
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				keysBox.setSelectedIndex(-1);
				cleanData();
				super.componentHidden(e);
			}
		});
		
		initAlumnosPanel();
		initPersonalDataPanel();
		initAddressPanel();
		initButton();
		
		this.add(alumnosPanel);
		this.add(personalDataPanel);
		this.add(addressPanel);
		this.add(actionButton);
	}
	
	protected void initAlumnosPanel() {
		alumnosPanel = new JPanel();
		TitledBorder border = new TitledBorder("Alumnos:");
		alumnosPanel.setBorder(border);
		alumnosPanel.setLayout(new GridLayout(1, 2));
		
		JLabel keyLabel = new JLabel("Clave:");
		
		keysBox = new JComboBox<>();
		for(String alumnoKey:mainFrame.getAlumnos().keySet()) {
			keysBox.addItem(alumnoKey);
		}
		keysBox.setSelectedIndex(-1);
		keysBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(keysBox.getSelectedIndex() != -1) {
					getData(keysBox.getSelectedItem().toString());
				}
			}
		});
		
		alumnosPanel.add(keyLabel);
		alumnosPanel.add(keysBox);
	}
	
	protected void initPersonalDataPanel() {
		personalDataPanel = new JPanel();
		TitledBorder border = new TitledBorder("Datos personales:");
		personalDataPanel.setBorder(border);
		personalDataPanel.setLayout(new GridLayout(3, 2));
		
		JLabel nameLabel = new JLabel("Nombre:");
		JLabel surnameLabel = new JLabel("Apellidos:");
		JLabel ageLabel = new JLabel("Edad:");
		
		nameField = new JTextField();
		surnameField = new JTextField();
		ageField = new JTextField();
		
		personalDataPanel.add(nameLabel);
		personalDataPanel.add(nameField);
		personalDataPanel.add(surnameLabel);
		personalDataPanel.add(surnameField);
		personalDataPanel.add(ageLabel);
		personalDataPanel.add(ageField);
	}
	
	protected void initAddressPanel() {
		addressPanel = new JPanel();
		TitledBorder border = new TitledBorder("Dirección:");
		addressPanel.setBorder(border);
		addressPanel.setLayout(new GridLayout(3, 2));
		
		JLabel streetLabel = new JLabel("Calle:");
		JLabel numberLabel = new JLabel("Número:");
		JLabel postCodeLabel = new JLabel("Código Postal:");
		
		streetField = new JTextField();
		numberField = new JTextField();
		postCodeField = new JTextField();
		
		addressPanel.add(streetLabel);
		addressPanel.add(streetField);
		addressPanel.add(numberLabel);
		addressPanel.add(numberField);
		addressPanel.add(postCodeLabel);
		addressPanel.add(postCodeField);
	}
	
	protected void initButton() {
		actionButton = new JButton("Modificar");
		actionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedAlumno != null) {
					modifyData();
				}
			}
		});
	}
	
	void addAlumno(String key) {
		keysBox.addItem(key);
	}
	
	void removeAlumno(String key) {
		keysBox.removeItem(key);
	}
	
	private void getData(String key) {
		selectedAlumno = mainFrame.getAlumno(key);
		nameField.setText(selectedAlumno.getName());
		surnameField.setText(selectedAlumno.getSurname());
		ageField.setText(selectedAlumno.getAge());
		streetField.setText(selectedAlumno.getStreet());
		numberField.setText(selectedAlumno.getNumber());
		postCodeField.setText(selectedAlumno.getPostCode());
	}
	
	private void modifyData() {
		selectedAlumno.setName(nameField.getText());
		selectedAlumno.setSurname(surnameField.getText());
		selectedAlumno.setAge(ageField.getText());
		selectedAlumno.setStreet(streetField.getText());
		selectedAlumno.setNumber(numberField.getText());
		selectedAlumno.setPostCode(postCodeField.getText());
		mainFrame.needSaving = true;
	}
	
	private void cleanData() {
		selectedAlumno = null;
		nameField.setText("");
		surnameField.setText("");
		ageField.setText("");
		streetField.setText("");
		numberField.setText("");
		postCodeField.setText("");
	}

}
