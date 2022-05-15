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
	

	//Insert payment detail 
	public String insertPaymentDetails(String customerID, String customerName, String paymentType, String cardNo, String amount, String date, String billNo)
	{
		String output = "";
		
		try
		{
			Connection con = connect(); 
			 if (con == null) 
			 { 
				 return "Error while connecting to the database for inserting."; 
			 } 
		   
			 // create a prepared statement
			 String query = " insert into payment (`paymentNo`,`customerID`,`customerName`,`paymentType`,`cardNo`,`paymentAmount`,`paymentDate`,`billNo`)"
			 + " values (?, ?, ?, ?, ?, ?, ?, ?)";
			 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, customerID);
			preparedStmt.setString(3, customerName);
			preparedStmt.setString(4, paymentType);
			preparedStmt.setString(5, cardNo);
			preparedStmt.setDouble(6, Double.parseDouble(amount));
			preparedStmt.setString(7, date);
			preparedStmt.setString(8, billNo);
			preparedStmt.execute();
			con.close();
			//output = "Payment detail inserted successfully";
			String newPayment = readPaymentDetails(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newPayment + "\"}"; 
		}catch (Exception e)
		 {
			 //output = "Error while inserting the payment detail.";
			 output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
			 System.err.println(e.getMessage());
		 }
		    return output;
	}
	
}
