package com.orenan.griffith;
import java.sql.*;

import javax.security.auth.callback.TextOutputCallback;
import javax.swing.JOptionPane;
import java.awt.EventQueue;

public class MysqlCon {
	
	//Connection details for database (JDBC).
	private static String url = "jdbc:mysql://serverip:port/databasename";
	private static String usr = "login";
	private static String pass = "password";
	
	static String textOutput = "Blank";
	boolean usrPassMatches = false;

	public static void main(String[] args) {
		  
	}
	
	public static String showLastId(String lastId) {
		try{  
			//Driver connecting to the db using usr and pass.
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			url,usr,pass);  

			//Creating statement and a result set for it.
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("SELECT id FROM `student` ORDER BY ID DESC LIMIT 1");  
			
			/*
			 * If The id that is input is greater than the ones in the database it wont change textOutput.
			 * */
			//keep returning the result for the user until there's no next().
			while(rs.next())
				lastId = rs.getString(1);  
				System.out.println(lastId);
				con.close();
			}catch(Exception e){ 
				System.out.println(e);
			}
		return lastId;
	}
	
	//Function that connects to the db and looks for students based on their id.
	public static String giveData(String id) {
		//Setting the output as invalid if the user id input is more than the existent ones. 
		textOutput = "The specified id does not exist, the last student registered id was " + showLastId("") + ".";
		
		/*
		 * Creating a int to parse the input,
		 * then checking to see if the input is equals 
		 * or less than zero, if it is, will turn the input as invalid. 
		 * */
		int parsedInput = Integer.parseInt(id);
		if(parsedInput == 0 || parsedInput < 0) {
			textOutput = "Sorry, the user id has to be greater than 0";
		}
		try{  
			//Driver connecting to the db using usr and pass.
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			url,usr,pass);  

			//Creating statement and a result set for it.
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from student WHERE student.id = " + id);  
			
			/*
			 * If The id that is input is greater than the ones in the database it wont change textOutput.
			 * */
			//keep returning the result for the user until there's no next().
			while(rs.next())
				textOutput = "Name: " + rs.getString(2) + "\n" + "Address: " + rs.getString(3) + "\n" + "Phone: " + rs.getString(4) + "\n" + "Nationality: " + rs.getString(5);  
				System.out.println(textOutput);
				con.close();
			}catch(Exception e){ 
				System.out.println(e);
				return "Invalid Number";
			}
			return textOutput;
	}
	
	//Function to register a new student.
	public static void Register(String name, String address, int mobileNumber, String nationality) {
		try {
			//Calling the driver, Connecting to the database and creating the statement.
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,usr,pass);
			Statement myStmt = con.createStatement();					
			
			//Passing the command to the database to add the student based on the input.
			String sql = String.format("insert into student" 
					+" (name, address, mobile_number, nationality)"
					+" values ('%s','%s' ,'%d', '%s')", name, address, mobileNumber, nationality);
			
			//Executes the query.
			myStmt.executeUpdate(sql);
			
			//Confirms that the user was added as a pop-up.
			JOptionPane.showMessageDialog(null, "New user registred!");
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Method to login from screen which takes the usr and pass and compare them to the existing ones in the database.
	public boolean Login(String username, String password) {
		//Calling the interface class to use it's functions and variables.
		Interface gui = new Interface();
	
		//Creating connection variables.
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		//Setting a string for the sql script.
		String sql="select * from user where username=? and password =?";
		
		//Doing the functions to connect and passing the strings to it.
		try {
			//Calling the driver.
			Class.forName("com.mysql.jdbc.Driver");
			
			//Evoking the variable's methods.
			con = DriverManager.getConnection(url,usr, pass);
			pst = con.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			
			//If the usr and pass are matching it will return the usrPassMatches boolean as true.
			if(rs.next()) {
				usrPassMatches = true;
				System.out.println("Success");
				return usrPassMatches;
				
			//Otherwise, it's going to return false and pop-up the user with a error on matching message.	
			}else {
				JOptionPane.showMessageDialog(null, "Username or password are wrong!");
				return usrPassMatches;
			}
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return usrPassMatches;
	}
}
