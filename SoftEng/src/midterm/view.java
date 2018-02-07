package midterm;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class view extends JFrame implements ActionListener {
	public JLabel name, password, confirm, gender, city, mail, a;
	public JTextField nameField, mailField;
	public JPasswordField passwordField, confirmField;
	public JComboBox cityField;
	public JRadioButton male, female;
	public JButton submit;
	
	public view() {
		//labels
		name = new JLabel("Name:");
		add(name).setBounds(20, 20, 50, 20);
		
		password = new JLabel("Password:");
		add(password).setBounds(20, 70, 100, 20);
		
		confirm = new JLabel("Confirm password:");
		add(confirm).setBounds(20, 120, 120, 20);
		
		city = new JLabel("City:");
		add(city).setBounds(20, 170, 50, 20);
		
		gender = new JLabel("Gender:");
		add(gender).setBounds(20, 220, 50, 20);
		
		mail = new JLabel("E-mail:");
		add(mail).setBounds(20, 270, 50, 20);
		
		
		//fields
		nameField = new JTextField();
		add(nameField).setBounds(150, 20, 150, 20);
		
		passwordField = new JPasswordField();
		add(passwordField).setBounds(150, 70, 150, 20);
		
		confirmField = new JPasswordField();
		add(confirmField).setBounds(150, 120, 150, 20);
		
		mailField = new JTextField();
		add(mailField).setBounds(150, 270, 150, 20);
		
		//combo box
		String[] city = {"Select City", "Manila", "Pasay", "Pasig", "Makati"};
		cityField = new JComboBox(city);
		add(cityField).setBounds(150, 170, 150, 20);
		
		//radio button
		male = new JRadioButton("Male");
		add(male).setBounds(150, 220, 70, 20);
		
		female = new JRadioButton("Female");
		add(female).setBounds(220, 220, 70, 20);
		
		//button
		submit = new JButton("Submit");
		add(submit).setBounds(20, 320, 80, 20);
		submit.addActionListener(this);
		
		//bug 
		a = new JLabel();
		add(a);
		
		setVisible(true);
		setLayout(null);
		setSize(500, 400);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == submit) {
			if(nameField.getText().equals("") || passwordField.getText().equals("") || confirmField.getText().equals("") || mailField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty field.");
			} else if(cityField.getSelectedItem().equals("Select City")) {
				JOptionPane.showMessageDialog(null, "Please select a city.");
			} else if(!passwordField.getText().equals(confirmField.getText())) {
				JOptionPane.showMessageDialog(null, "Passwords do not match.");
			} else if(!male.isSelected() && !female.isSelected()) {
				JOptionPane.showMessageDialog(null, "Select gender.");
			} else if(!mailField.getText().contains("@")) {
				JOptionPane.showMessageDialog(null, "Please enter a valid E-mail.");
			} else {
				model m = new model();
				
				m.setNameField(nameField.getText());
				m.setPasswordField(passwordField.getText());
				m.setConfirmField(confirmField.getText());
				
				if(male.isSelected()) {
					m.setGender(male.getText());
				}
				if(female.isSelected()) {
					m.setGender(female.getText());
				}
				
				m.setCity(cityField.getSelectedItem().toString());
				m.setMailField(mailField.getText());
				
				//file handling
				try {
					String values = m.getNameField() + ";" + m.getPasswordField() + ";" + m.getConfirmField() + ";" + m.getGender() + ";" + m.getCity() + ";" + m.getMailField();
					
					//creates file
					File file = new File("C:/Users/DELL/Desktop/midterm.txt");
					file.createNewFile();
					
					//file writer object
					FileWriter writer = new FileWriter(file);
					
					//prints to text file
					writer.write(values);
					writer.close();
				} catch(Exception j) {
					j.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "Pawer!");
			}
		}
	}
	
}
