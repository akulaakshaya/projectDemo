<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard Example</title>
    <style>
      
        .dashboard {
            position: fixed;
            top: 0;
            left: -200px;
            width: 300px;
            height: 100%;
            background-color: #212121;
            padding: 20px;
            transition: left 0.3s ease;
            color: #fff;
            font-family: Arial, sans-serif;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            z-index: 1;
        }
        
        .dashboard h2 {
            margin-top: 0;
            margin-bottom: 20px;
            font-size: 22px;
            text-transform: uppercase;
        }
        
        .dashboard ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }
        
        .dashboard li {
            margin-bottom: 10px;
        }
        
        .dashboard a {
            text-decoration: none;
            color: #fff;
            transition: color 0.3s ease;
        }
        
        .dashboard a:hover {
            color: #f39c12;
        }
        
      
        .menu-icon {
            position: fixed;
            top: 20px;
            left: 20px;
            cursor: pointer;
            z-index: 2;
        }
        
        .menu-icon span {
            display: block;
            width: 25px;
            height: 3px;
            background-color: #fff;
            margin-bottom: 5px;
            transition: transform 0.3s ease;
        }
        
        .menu-icon.active span:nth-child(1) {
            transform: rotate(-45deg) translate(-5px, 5px);
        }
        
        .menu-icon.active span:nth-child(2) {
            opacity: 0;
        }
        
        .menu-icon.active span:nth-child(3) {
            transform: rotate(45deg) translate(-5px, -5px);
        }
        
   
        .content {
            margin-left: 300px;
            padding: 20px;
            font-family: Arial, sans-serif;
        }
        
        h1 {
            color: #333;
            font-size: 28px;
            margin-bottom: 20px;
        }
        
        p {
            color: #777;
            line-height: 1.6;
        }
        
      
        .data-visualization {
            margin-top: 30px;
            display: flex;
            justify-content: space-between;
        }
        
        .data-visualization .item {
            flex-basis: 30%;
            background-color: #f39c12;
            padding: 20px;
            color: #fff;
            border-radius: 5px;
            text-align: center;
            transition: transform 0.3s ease;
            cursor: pointer;
        }
        
        .data-visualization .item:hover {
            transform: translateY(-5px);
        }
        
        .data-visualization h3 {
            margin-top: 0;
            font-size: 18px;
            margin-bottom: 10px;
        }
        
        .data-visualization p {
            font-size: 14px;
        }
        
        @media (max-width: 768px) {
            .dashboard {
                left: -220px;
            }
            
            .content {
                margin-left: 0;
            }
        }
    </style>

    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    
    <script>
        // JavaScript for hamburger menu toggle
        function toggleMenu() {
            var dashboard = document.getElementById('dashboard');
            var menuIcon = document.querySelector('.menu-icon');
            
            if (dashboard.style.left === '-200px') {
                dashboard.style.left = '0';
                menuIcon.classList.add('active');
            } else {
                dashboard.style.left = '-200px';
                menuIcon.classList.remove('active');
            }
        }
    </script>
    <script>
    
    
    
    
    
 	function processOrder(orderId){
 	 $.ajax({
	      url: "processOrders",
	      method: 'GET',
	      data: { orderId: orderId },
	      success: function(response) {
	    	  $('#content').html(response);
	      },
	      error: function(xhr, status, error) {
	        console.log('AJAX Error: ' + error);
	      }
	    });
 	}
    
    function displayOrders(){
    	 $.ajax({
    	      url: "listOrders",
    	      method: 'GET',
    	      success: function(response) {
    	        $('#content').html(response); // Set the response HTML as the inner HTML of the select element
    	      },
    	      error: function(xhr, status, error) {
    	        console.log('AJAX Error: ' + error);
    	      }
    	    });
    }
    
    $(document).on('click', '.orders-link', function(event) {
	    event.preventDefault();
	   console.log("entered orders");
	    displayOrders();
    });
	 $(document).on('click', '#red-button', function(event) {
	    event.preventDefault();
		var orderId = $(this).data('order-id'); 
	    
	    console.log('Clicked button with order ID:', orderId);
	   processOrder(orderId);
	 });
	 
	 
	  $(document).ready(function() {
		    $('#dateRangeFilter').change(function() {
		      var selectVal = $("#dateRangeFilter").val();
		      console.log('filterDate: ');
		      $.ajax({
		        url: "loadOrdersByDate",
		        method: 'GET',
		        data: { selectDateFilter: selectVal },
		        success: function(response) {
		          $('#tableData').html(response);
		        },
		        error: function(xhr, status, error) {
		          console.log('AJAX Error: ' + error);
		        }
		      });
		    });
		  });
    </script>
</head>
<body>
    <div class="dashboard" id="dashboard">
        <h2>Dashboard</h2>
        <ul>
            
            <li><a href="#">Profile</a></li>
            <li><a href="#" class="orders-link">Orders</a></li>

            <li><a href="#">payments</a></li>
            <li><a href="#">Logout</a></li>
        </ul>
    </div>
    
    <div class="menu-icon" onclick="toggleMenu()">
        <span></span>
        <span></span>
        <span></span>
    </div>
    
    <div class="content" id="content">
        <h1>Welcome to the Dashboard</h1>
        <p>This is the main content area of the page. It can include various data visualizations and informative sections.</p>
        
        <div class="data-visualization">
            <div class="item">
                <h3>Orders</h3>
                <p>5 orders in progress</p>
            </div>
            <div class="item">
                <h3>Products</h3>
                <p>100 products in store</p>
            </div>
            <div class="item">
                <h3>Registered Customers</h3>
                <p>500 registered customers</p>
            </div>
        </div>
    </div>
</body>
</html>