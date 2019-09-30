package com.orenan.griffith;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.security.auth.callback.TextInputCallback;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.sql.*;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextArea;

public class Interface {
	
	//Assigning the mysql connection to a variable.
	MysqlCon connection = new MysqlCon();

	String searchContent = "None";
	private JFrame frame;
	private JTextField txtInput;
	JPanel prmPanel;
	private JPanel panel1;
	private JPanel panel3;
	private JPanel hostPanel2;
	


	/**
	 * Launch the application.
	 */
	
	//Creating cardlayout variable assigned to the layout for multiple pages.
	CardLayout cardlayout;
	
	//Creating textfields for the registration.
	private JTextField txtNationality;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtMobileNumber;
	
	//Variables to register input.
	String name;
	String address;
	int mobileNumber;
	String nationality;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	private JPanel panel2;
	private JTextArea txtOutput;
	
	//Starting the program making the interface visible.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		//Main frame draw.
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(165, 42, 42));
		frame.getContentPane().setEnabled(false);
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 822, 364);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Drawing parent cardlayout.
		prmPanel = new JPanel();
		prmPanel.setBorder(null);
		prmPanel.setBounds(150, 6, 581, 321);
		frame.getContentPane().add(prmPanel);
		prmPanel.setLayout(new CardLayout(0, 0));
		
		//Drawing child cardlayout.
		panel3 = new JPanel();
		panel3.setBorder(null);
		panel3.setBackground(Color.ORANGE);
		prmPanel.add(panel3, "panel3");
		panel3.setLayout(new CardLayout(0, 0));
		
		//Panel that holds the interface contents.
		JPanel hostPanel3 = new JPanel();
		panel3.add(hostPanel3, "name_32974287854379");
		hostPanel3.setBorder(null);
		hostPanel3.setBackground(new Color(165, 42, 42));
		hostPanel3.setLayout(null);
		
		//Simple label to the login screen
		JLabel lblLoginDb = new JLabel("LOGIN - DB ACCESS");
		lblLoginDb.setBounds(149, 35, 286, 40);
		hostPanel3.add(lblLoginDb);
		lblLoginDb.setForeground(new Color(255, 255, 255));
		lblLoginDb.setFont(new Font("Arial Black", Font.PLAIN, 26));
		
		//Box for the username input.
		txtUsername = new JTextField();
		txtUsername.setForeground(new Color(192, 192, 192));
		txtUsername.setText("USERNAME");
		txtUsername.setBounds(149, 107, 286, 34);
		hostPanel3.add(txtUsername);
		txtUsername.setColumns(10);
		
		//Button that calls the login function from mysqlcon class.
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Creating the char array from getPassword() as a String.
				String password = new String(passwordField.getPassword());
				
				//If the boolean returned from MysqlCon is true, goes to the search page.
				if(connection.Login(txtUsername.getText(), password) == true) {
					cardlayout.show(prmPanel, "panel2");
				
				//Else if the boolean is false it prints out an error.
				}else {
					System.out.println("Error!!");
				}
			}
		});
		btnLogin.setForeground(new Color(165, 42, 42));
		btnLogin.setFont(new Font("Arial", Font.PLAIN, 20));
		btnLogin.setBounds(231, 188, 113, 59);
		hostPanel3.add(btnLogin);
		
		//Creating the password box to the login.
		passwordField = new JPasswordField();
		passwordField.setText("PASSWORD");
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				passwordField.setText("");
			}
		});
		passwordField.setToolTipText("");
		passwordField.setBounds(149, 142, 286, 34);
		hostPanel3.add(passwordField);
		
		//Drawing child cardlayout.
		panel2 = new JPanel();
		panel2.setBorder(null);
		panel2.setBackground(new Color(0, 255, 255));
		prmPanel.add(panel2, "panel2");
		panel2.setLayout(new CardLayout(0, 0));
		
		//Panel that holds the interface contents.
		hostPanel2 = new JPanel();
		panel2.add(hostPanel2, "name_32533492514058");
		hostPanel2.setBorder(null);
		hostPanel2.setBackground(new Color(165, 42, 42));
		hostPanel2.setLayout(null);
		
		//Label for the program.
		JLabel lblDatabaseLookup = new JLabel("DATABASE LOOKUP");
		lblDatabaseLookup.setBounds(154, 57, 290, 37);
		hostPanel2.add(lblDatabaseLookup);
		lblDatabaseLookup.setForeground(new Color(255, 255, 255));
		lblDatabaseLookup.setBackground(new Color(178, 34, 34));
		lblDatabaseLookup.setHorizontalAlignment(SwingConstants.LEFT);
		lblDatabaseLookup.setFont(new Font("Arial Black", Font.PLAIN, 26));
		
		//Textbox for the user input.
		txtInput = new JTextField();
		txtInput.setBounds(107, 94, 290, 42);
		hostPanel2.add(txtInput);
		txtInput.setBackground(new Color(224, 255, 255));
		txtInput.setColumns(10);
		
		//JTextArea to fix the student information as output.
		txtOutput = new JTextArea();
		txtOutput.setBackground(new Color(224, 255, 255));
		txtOutput.setBounds(109, 139, 285, 73);
		hostPanel2.add(txtOutput);
		
		//Button search with the function of acquiring the data on the database based on the user input and show it in the output textbox. 
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.setBounds(397, 93, 79, 47);
		hostPanel2.add(btnSearch);
		btnSearch.setForeground(new Color(128, 0, 0));
		btnSearch.setBackground(new Color(128, 0, 0));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtOutput.setText(connection.giveData(txtInput.getText()));
			}
		});
		btnSearch.setFont(new Font("Arial Black", Font.PLAIN, 11));
		
		//Button register which leads the user to a new panel card in where it may register.
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setBounds(397, 140, 79, 72);
		hostPanel2.add(btnRegister);
		btnRegister.setForeground(new Color(128, 0, 0));
		btnRegister.setBackground(new Color(0, 0, 0));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(prmPanel, "panel1");
			}
		});
		btnRegister.setFont(new Font("Arial Black", Font.PLAIN, 11));
		
		//Requires the layout of the main panel (parent).
		cardlayout = (CardLayout)(prmPanel.getLayout());
		
		//Drawing child cardlayout.
		panel1 = new JPanel();
		panel1.setBorder(null);
		panel1.setBackground(new Color(165, 42, 42));
		prmPanel.add(panel1, "panel1");
		panel1.setLayout(new CardLayout(0, 0));
		
		//Panel that holds the interface contents.
		JPanel hostPanel1 = new JPanel();
		hostPanel1.setBorder(null);
		hostPanel1.setBackground(new Color(165, 42, 42));
		panel1.add(hostPanel1, "name_121176101314010");
		hostPanel1.setLayout(null);
		
		//Text field to input.
		txtNationality = new JTextField();
		txtNationality.setBackground(new Color(224, 255, 255));
		txtNationality.setForeground(new Color(192, 192, 192));
		txtNationality.setText("NATIONALITY");
		txtNationality.setBounds(58, 186, 439, 32);
		hostPanel1.add(txtNationality);
		txtNationality.setColumns(10);
		
		//Text field to input.
		txtName = new JTextField();
		txtName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtName.setText("");
			}
		});
		txtName.setBackground(new Color(224, 255, 255));
		txtName.setForeground(new Color(192, 192, 192));
		txtName.setText("NAME");
		txtName.setColumns(10);
		txtName.setBounds(58, 81, 439, 32);
		hostPanel1.add(txtName);
		
		//Text field to input.
		txtAddress = new JTextField();
		txtAddress.setBackground(new Color(224, 255, 255));
		txtAddress.setForeground(new Color(192, 192, 192));
		txtAddress.setText("ADDRESS");
		txtAddress.setColumns(10);
		txtAddress.setBounds(58, 116, 439, 32);
		hostPanel1.add(txtAddress);
		
		//Text field to input.
		txtMobileNumber = new JTextField();
		txtMobileNumber.setBackground(new Color(224, 255, 255));
		txtMobileNumber.setForeground(new Color(192, 192, 192));
		txtMobileNumber.setText("MOBILE NUMBER");
		txtMobileNumber.setColumns(10);
		txtMobileNumber.setBounds(58, 150, 439, 32);
		hostPanel1.add(txtMobileNumber);
		
		//Button to complete registration.
		JButton btnRegisterPage = new JButton("REGISTER");
		btnRegisterPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = txtName.getText();
				address = txtAddress.getText();
				mobileNumber = Integer.parseInt(txtMobileNumber.getText());
				nationality = txtNationality.getText();
				
				connection.Register(name, address, mobileNumber, nationality);
				
				System.out.println(name + " " + address + " " + mobileNumber + " " + nationality);
			}
		});
		btnRegisterPage.setForeground(new Color(178, 34, 34));
		btnRegisterPage.setBackground(new Color(220, 20, 60));
		btnRegisterPage.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnRegisterPage.setBounds(291, 226, 133, 67);
		hostPanel1.add(btnRegisterPage);
		
		//Button to go back to the search page.
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(prmPanel, "panel2");
			}
		});
		btnBack.setForeground(new Color(178, 34, 34));
		btnBack.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnBack.setBackground(new Color(220, 20, 60));
		btnBack.setBounds(158, 226, 133, 67);
		hostPanel1.add(btnBack);
		
		//Label to the registration layer.
		JLabel lblRegistration = new JLabel("REGISTRATION");
		lblRegistration.setBounds(158, 33, 246, 46);
		hostPanel1.add(lblRegistration);
		lblRegistration.setForeground(new Color(255, 255, 255));
		lblRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistration.setFont(new Font("Arial Black", Font.PLAIN, 26));
		
		
	}
}
