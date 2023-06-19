<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="eStoreProduct.model.orderModel, eStoreProduct.model.orderProductsModel,eStoreProduct.DAO.OrderDAO, java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function() {
    $(".updateBtn").click(function() {
        var selectedStatus = $(this).closest("tr").find("#statusSelect").val();
        var orderId = $(this).closest("tr").find("#orderId").attr("value");
        var productId = $(this).closest("tr").find("#productId").attr("value");
        console.log(selectedStatus);
        // Perform further actions with the selected status
        updateOrderProductStatus(selectedStatus, orderId, productId);
    });

});

function updateShippedOrder(x){
	 $.ajax({
	        url: "updateShippedOrderStatus",
	        method: 'GET',
	        data: { x: x },
	        success: function(response) {
	            $('#xyz').html(response); // Set the response HTML as the inner HTML of the select element
	        },
	        error: function(xhr, status, error) {
	            console.log('AJAX Error: ' + error);
	        }
	    });
}

function updateOrderProductStatus(selectedStatus, orderId, productId){
    console.log("in Updating status Orders Products specific order id");
    $.ajax({
        url: "updateOrderProductStatus",
        method: 'POST',
        data: { shipment_status: selectedStatus, ordr_id: orderId, prod_id: productId },
        success: function(response) {
            $('#load-content').html(response); // Set the response HTML as the inner HTML of the select element
        },
        error: function(xhr, status, error) {
            console.log('AJAX Error: ' + error);
        }
    });
}

function yourJavaScriptFunction() {
    console.log("Your JavaScript function is called");
    // Your JavaScript code here
    // ...
}
</script>
</head>
<body>
<div class="container-fluid"> 
    <table class="table table-bordered table-hover">
        <thead class="thead-dark">
            <tr>
                <th>Order ID</th>
                <th>Product ID</th>
                <th>Quantity</th>
                <th>GST </th>
                <th>Price</th>
                <th>Shipment Status</th>
                <th>Change Status</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
        <% int count = 0; %>
        <% int loopCount = 0; %>
        <% int ifCount = 0; %>
        <% List<orderProductsModel> orderproducts = (List<orderProductsModel>) request.getAttribute("orderproducts"); %>
        <% for (orderProductsModel op : orderproducts) {
            loopCount++;
            if(op != null) { %>
            <tr>
                <td id="orderId" value="<%= op.getOrdr_id() %>"><%= op.getOrdr_id() %></td>
                <td id="productId" value="<%= op.getProd_id() %>"><%= op.getProd_id() %></td>
                <td><%= op.getOrpr_qty() %></td>
                <td><%= op.getOrpr_gst() %></td>
                <td><%= op.getOrpr_price() %></td>
                <td><%= op.getShipment_status() %></td>
                <td>
                    <% if(!(op.getShipment_status().equalsIgnoreCase("delivered")) && (!(op.getShipment_status().equalsIgnoreCase("cancelled")))) {
                        ifCount++;
                    %>                  
                        <select id="statusSelect">
                            <option value="Dispatched">Dispatched</option>
                            <option value="OutForDelivery">Out For Delivery</option>
                            <option value="Delivered">Delivered</option>
                        </select> 
                        <td>
                            <button class="btn btn-success updateBtn">Update</button>
                        </td>
                    <% } else { %>
                        can't edit
                    <% } %>
                </td>
            </tr>
            <% } %>
            <%
            int x=op.getOrdr_id() ;
            if(ifCount==0){ %>
       				<script>updateShippedOrder(<%=x%>);</script>
       		<%} %>
           
            
        <% }%>
        
        <tr>
        <div id="xyz">
        </div>
           <%--  <td colspan="8">
                Loop Count: <%= loopCount %><br>
                If Condition Passed Count: <%= ifCount %>
            </td> --%>
        </tr>
        
        </tbody>
    </table> 
</div>
</body>
</html>
