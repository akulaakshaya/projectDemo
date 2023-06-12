<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Details</title>
</head>
<body>
    <h2>Enter Order Details</h2>
    <form action="inserted" method="POST">
        <label for="customerId">Customer ID:</label>
        <input type="text" name="customerId" id="customerId" required><br>
        
        <label for="billNumber">Bill Number:</label>
        <input type="text" name="billNumber" id="billNumber" required><br>
        
        <label for="orderDate">Order Date:</label>
        <input type="datetime-local" name="orderDate" id="orderDate" required><br>
        
        <label for="total">Total:</label>
        <input type="text" name="total" id="total" required><br>
        
        <label for="gst">GST:</label>
        <input type="text" name="gst" id="gst" required><br>
        
        <label for="paymentReference">Payment Reference:</label>
        <input type="text" name="paymentReference" id="paymentReference" required><br>
        
        <label for="paymentMode">Payment Mode:</label>
        <input type="text" name="paymentMode" id="paymentMode" required><br>
        
        <label for="paymentStatus">Payment Status:</label>
        <input type="text" name="paymentStatus" id="paymentStatus" required><br>
        
        <label for="shippingAddress">Shipping Address:</label>
        <input type="text" name="shippingAddress" id="shippingAddress" required><br>
        
        <label for="shippingPincode">Shipping Pincode:</label>
        <input type="text" name="shippingPincode" id="shippingPincode" required><br>
        
        <label for="shipmentStatus">Shipment Status:</label>
        <input type="text" name="shipmentStatus" id="shipmentStatus" required><br>
        
        <label for="shipmentDate">Shipment Date:</label>
        <input type="datetime-local" name="shipmentDate" id="shipmentDate" required><br>
        
        <label for="processedBy">Processed By:</label>
        <input type="text" name="processedBy" id="processedBy" required><br>
        
        <input type="submit" value="Submit Order">
    </form>
</body>
</html>
