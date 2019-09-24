package com.orenan.griffith;
import java.sql.*;

import javax.security.auth.callback.TextOutputCallback;
import javax.swing.JOptionPane;
import java.awt.EventQueue;

public class MysqlCon {
	
	static String textOutput = "Blank";
	boolean usrPassMatches = false;

	public static void main(String[] args) {
		
		//giveData();
		
		  
	}
	
	//Function that connects to the db and looks for students based on their id.
	public static String giveData(String id) {
		try{  
			//Driver connecting to the db using usr and pass.
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://51.75.248.73:3306/storm","rmt_admin","V!HpE9zK");  

			//Creating statement and a result set for it.
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from student WHERE student.id = " + id);  
			
			//keep returning the result for the user until there's no next().
			while(rs.next())
				textOutput = "  " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5);  
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
			Connection con = DriverManager.getConnection("jdbc:mysql://51.75.248.73:3306/storm","rmt_admin","V!HpE9zK");
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
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://51.75.248.73/storm","rmt_admin", "V!HpE9zK");
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
