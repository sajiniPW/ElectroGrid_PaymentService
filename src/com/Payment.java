package com;

import java.sql.*;




public class Payment {
	
	//Database Connect
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/electro", "root", "hotel*123"); 
		} 
		catch (Exception e) 
		{ 
			e.printStackTrace(); 
		} 
		return con; 
	} 
	

	
}
