
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="eStoreProduct.model.custCredModel" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>profile</title>
</head>
<style>
   /* CSS styles */
 body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
    }
          header {
      background-color: #333;
      color: #fff;
      padding: 20px;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
       nav ul {
      list-style-type: none;
      margin: 0;
      padding: 0;
    }
    
    nav ul li {
      display: inline;
    }
    
    nav ul li a {
      color: #fff;
      text-decoration: none;
      padding: 10px 20px;
    }
    
    nav ul li a:hover {
      background-color: #555;
    }
        
        h1 {
            color: #ffffff;
        }
        
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        
        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        
        button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        
        form label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }
        
        form input[type="text"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        
        form input[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
<body>
    <header>
        <!-- Display the customer name -->
        <h1>Hey! ${cust != null ? cust.custName : ""}</h1>
        <nav>
            <ul>
                <li><a href="loggedIn">Home</a></li>
                <li><a href="#">&#128142; Orders</a></li>
                <li><a href="#">&#x1F381; Cupons</a></li>
                <li><a href="#">&#128722; Cart</a></li>
                <li><a href="#">&#10084; WishList</a></li>
                <li><a href="logout">LogOut</a></li>
            </ul>
        </nav>
    </header>
    <main>
        <div id="edit">
            <h1>Customer Profile</h1>
            <table>
                <tr>
                    <th>Customer ID</th>
                    <td>${cust != null ? cust.custId : ""}</td>
                </tr>
                <tr>
                    <th>Name</th>
                    <td>${cust != null ? cust.custName : ""}</td>
                </tr>
                <tr>
                    <th>Mobile</th>
                    <td>${cust != null ? cust.custMobile : ""}</td>
                </tr>
                <tr>
                
                    <th>Location</th>
                    <td>${cust != null ? cust.custLocation : ""}</td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td>${cust != null ? cust.custEmail : ""}</td>
                </tr>
                <tr>
                    <th>Address</th>
                    <td>${cust != null ? cust.custAddress : ""}</td>
                </tr>
                <tr>
                    <th>Shipping Address</th>
                    <td>${cust != null ? cust.custSAddress : ""}</td>
                </tr>
                <tr>
                    <th>Pincode</th>
                    <td>${cust != null ? cust.custPincode : ""}</td>
                </tr>
                <tr>
                    <th>Last Login Date</th>
                    <td>${cust != null ? cust.custLastLoginDate : ""}</td>
                </tr>
            </table>
            <button onclick="editprofile()">EDIT-PROFILE</button>
        </div>
    </main> 
<script>
function editprofile() {
    var ele = document.getElementById("edit");
    ele.innerHTML = `
    <form action="updateProfile" method="post">    
        <label>CustomerId:</label>
        <input type="text" id="custId" name="custId" value="${cust != null ? cust.custId : ""}" readonly><br>

        <label for="custName">Name:</label>
        <input type="text" id="custName" name="custName" value="${cust != null ? cust.custName : ""}"><br>

        <label for="custMobile">Mobile:</label>
        <input type="text" id="custMobile" name="custMobile" value="${cust != null ? cust.custMobile : ""}"><br>

        <label for="custLocation">Location:</label>
        <input type="text" id="custLocation" name="custLocation" value="${cust != null ? cust.custLocation : ""}"><br>

        <label for="custAddress">Address:</label>
        <input type="text" id="custAddress" name="custAddress" value="${cust != null ? cust.custAddress : ""}"><br>

        <label for="custSAddress">Shipping Address:</label>
        <input type="text" id="custSAddress" name="custSAddress" value="${cust != null ? cust.custSAddress : ""}"><br>

        <label for="custPincode">Pincode:</label>
        <input type="text" id="custPincode" name="custPincode" value="${cust != null ? cust.custPincode : ""}"><br>

        <input type="submit" value="Update Profile">
    </form>`;
}
</script>
</body>
</html>