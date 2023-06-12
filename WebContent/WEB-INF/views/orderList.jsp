<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="eStoreProduct.model.orderModel" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order List</title>
</head>
<body>
    <h2>Order List</h2>
    <table>
        <tr>
            <th>Order ID</th>
            <th>Customer ID</th>
            <th>Bill Number</th>
            <th>Order Date</th>
            <th>Total</th>
            <th>GST</th>
            <th>Payment Reference</th>
            <th>Payment Mode</th>
            <th>Payment Status</th>
            <th>Shipping Address</th>
            <th>Shipping Pincode</th>
            <th>Shipment Status</th>
            <th>Shipment Date</th>
            <th>Processed By</th>
        </tr>
        <% 
            List<eStoreProduct.model.orderModel> orders = (List<eStoreProduct.model.orderModel>) request.getAttribute("orders");
            for (eStoreProduct.model.orderModel order : orders) {
        %>
        <tr>
            <td><%= order.getId() %></td>
            <td><%= order.getOrdr_cust_id() %></td>
            <td><%= order.getBillNumber() %></td>
            <td><%= order.getOrderDate() %></td>
            <td><%= order.getTotal() %></td>
            <td><%= order.getGst() %></td>
            <td><%= order.getPaymentReference() %></td>
            <td><%= order.getPaymentMode() %></td>
            <td><%= order.getPaymentStatus() %></td>
            <td><%= order.getShippingAddress() %></td>
            <td><%= order.getShippingPincode() %></td>
            <td><%= order.getShipmentStatus() %></td>
            <td><%= order.getShipmentDate() %></td>
            <td><%= order.getOrdr_processedby() %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>
