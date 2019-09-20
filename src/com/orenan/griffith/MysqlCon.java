package com.orenan.griffith;
import java.sql.*;

import javax.security.auth.callback.TextOutputCallback;
import javax.swing.JOptionPane;
import java.awt.EventQueue;

public class MysqlCon {
	
	static String textOutput = "Blank";

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
}
