package com.orenan.griffith;
import java.sql.*;

import javax.security.auth.callback.TextOutputCallback;
import javax.swing.JOptionPane;
import java.awt.EventQueue;

public class MysqlCon {
	
	static String textOutput = "bla";

	public static void main(String[] args) {
		
		//giveData();
		
		  
	}
	
	public static String giveData(String id) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://51.75.248.73:3306/storm","rmt_admin","V!HpE9zK");  

			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from student WHERE student.id = " + id);  
			
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
	
	public static void Register(String name, String address, int mobileNumber, String nationality) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://51.75.248.73:3306/storm","rmt_admin","V!HpE9zK");
			Statement myStmt = con.createStatement();					
			
			String sql = String.format("insert into student" 
					+" (name, address, mobile_number, nationality)"
					+" values ('%s','%s' ,'%d', '%s')", name, address, mobileNumber, nationality);
			
			myStmt.executeUpdate(sql);
			
			JOptionPane.showMessageDialog(null, "New user registred!");
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
