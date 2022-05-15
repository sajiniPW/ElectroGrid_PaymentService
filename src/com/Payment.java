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
	
	//Read payment list
		public String readPaymentDetails()
	    {
		   String output = "";
		   
		   try
		   {
			   Connection con = connect(); 
			   if (con == null) 
			   { 
				   return "Error while connecting to the database for reading."; 
			   } 
			 
		     // Prepare the html table to be displayed
		     output = "<table border='1'><tr><th>Customer ID</th><th>Customer Name</th>" +
					   "<th>Payment Type</th>" +
					   "<th>Card No</th>" +
					   "<th>Payment Amount</th>" +
					   "<th>Payment Date</th>" +
					   "<th>Bill No</th>" +
					   "<th>Update</th><th>Remove</th></tr>";
		     

			 String query = "select * from payment";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 
			 
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
				 String paymentNo = Integer.toString(rs.getInt("paymentNo"));
				 String customerID = rs.getString("customerID");
				 String customerName = rs.getString("customerName");
				 String paymentType = rs.getString("paymentType");
				 String cardNo = rs.getString("cardNo");
				 String paymentAmount = Double.toString(rs.getDouble("paymentAmount"));
				 String paymentDate = rs.getString("paymentDate");
				 String billNo = rs.getString("billNo");
				 
				 // Add into the html table
				 output += "<tr><td>" + customerID + "</td>";
				 output += "<td>" + customerName + "</td>";
				 output += "<td>" + paymentType + "</td>";
				 output += "<td>" + cardNo + "</td>";
				 output += "<td>" + paymentAmount + "</td>";
				 output += "<td>" + paymentDate + "</td>";
				 output += "<td>" + billNo + "</td>";
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' "
						 + "class='btnUpdate btn btn-secondary' data-paymentno='" + paymentNo + "'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' "
						 + "class='btnRemove btn btn-danger' data-paymentno='" + paymentNo + "'></td></tr>"; 
			 }
			 
			 con.close();
			 
			 // Complete the html table
			 output += "</table>";
		   }
		   catch (Exception e)
		   {
			 output = "Error while reading the payment details.";
			 System.err.println(e.getMessage());
		   }
		   
		   return output;
	    }
		//Update payment details
		
		public String updatePaymentDetails(String paymentNo,String customerID, String customerName, String paymentType, String cardNo, String amount, String date, String billNo)
		{
			String output = "";
			
			try
			{
				Connection con = connect(); 
				 if (con == null) 
				 { 
					 return "Error while connecting to the database for updating."; 
				 } 
				
				// create a prepared statement
				String query = "UPDATE payment SET customerID=?,customerName=?,paymentType=?,cardNo=?, paymentAmount=?, paymentDate=?, billNo=? WHERE paymentNo=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
		 
				// binding values
		
				preparedStmt.setString(1, customerID);
				preparedStmt.setString(2, customerName);
				preparedStmt.setString(3, paymentType);
				preparedStmt.setString(4, cardNo);
				preparedStmt.setDouble(5, Double.parseDouble(amount));
				preparedStmt.setString(6, date);
				preparedStmt.setString(7,(billNo));
				preparedStmt.setInt(8, Integer.parseInt(paymentNo));
				 preparedStmt.execute();
				 con.close();
				 
				 //output = "Payment detail updated successfully";
				 String newPayments = readPaymentDetails(); 
				 output = "{\"status\":\"success\", \"data\": \"" + 
				 newPayments + "\"}"; 
			}catch (Exception e)
			{
				 //output = "Error while updating the payment details.";
				output = "{\"status\":\"error\", \"data\": \"Error while updating the payment.\"}"; 

				 System.err.println(e.getMessage());
		    }
			
		   return output;
		   
		  }
		//Delete Payment Details
		public String deletePaymentDetails(String paymentNo) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for deleting."; 
		 } 
		 // create a prepared statement
		 String query = "delete from payment where paymentNo=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(paymentNo)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newPayment = readPaymentDetails(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newPayment + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
}
