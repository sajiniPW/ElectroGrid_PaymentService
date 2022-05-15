<%@page import="com.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/payments.js"></script>
<style>

	div {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px 20px 0;
}
</style>
</head>
<body> 

<div class="container"><div class="row"><div class="col-12"> 

<h1>Make Payment  </h1>
<div>
	<form id="formPayment" name="formPayment" style="margin: auto;">
		 Customer ID: 
		<input id="customerID" name="customerID" type="text" class="form-control form-control-md col-6">
		
		 <br> Customer name: 
		 <input id="customerName" name="customerName" type="text" class="form-control form-control-md col-6">
		 
		 <br> 
		 
		 <br> Payment Type:
		 <input id="paymentType" name="paymentType" type="text" class="form-control form-control-md col-6">
		
		 
		 <br> Card No: 
		 <input id="cardNo" name="cardNo" type="text" class="form-control form-control-md col-6">
		 <br>
		 
		 <br> Payment Amount: 
		 <input id="paymentAmount" name="paymentAmount" type="text" class="form-control form-control-md col-6">
		 <br>
		 
		 <br> Payment Date: 
		 <input id="paymentDate" name="paymentDate" type="text" class="form-control form-control-md col-6">
		 <br>
		 
		 <br> Connection No: 
		 <input id="billNo" name="billNo" type="text" class="form-control form-control-md col-6">
		 <br>
		 <br><br>
		 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		 
		 <input type="hidden" id="hidPaymentNoSave" name="hidPaymentNoSave" value="">
		 
		 &nbsp;&nbsp;&nbsp;&nbsp;
		 <a href="/ElectroGrid_Rich-Web-Based-Application-IT20240738/search.jsp" class="btn btn-success" >Search</a>
		 
	</form>
	</div>
	<br><br>
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>

		<br>
		<br>
		
			<div id="divPaymnetsGrid">
	
			 <%
				 Payment paymentObj = new Payment(); 
				 out.print(paymentObj.readPaymentDetails()); 
			 %>
			</div>
			<br><br>
</div> </div> </div> 
</body>
</html>