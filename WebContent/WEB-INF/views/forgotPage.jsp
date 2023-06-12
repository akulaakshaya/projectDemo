<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <title>Forgot Password</title>
  <!-- Include Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    body {
      background-color: #f9f9f9;
      display: flex;
      align-items: center;
      justify-content: center;
      height: 100vh;
    }
    
    .container {
      max-width: 400px;
      padding: 30px;
      background-color: #ffffff;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    
    h2 {
      text-align: center;
      margin-bottom: 30px;
      color: #333333;
    }
    
    form {
      margin-bottom: 20px;
    }
    
    label {
      font-weight: bold;
      color: #333333;
    }
    
    input[type="email"] {
      padding: 10px;
      margin-bottom: 20px;
      border: 1px solid #ccc;
      border-radius: 3px;
      background-color: #f9f9f9;
      box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.1);
    }
    
    input[type="submit"] {
      display: block;
      width: 100%;
      background-color: #007bff;
      color: #fff;
      border: none;
      padding: 10px;
      border-radius: 3px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }
    
    input[type="submit"]:hover {
      background-color: #0056b3;
    }
  </style>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
    var otp1;

    function sendmail() {
      var email = $("#email").val();
      $.ajax({
        type: "POST",
        url: "otpAction",
        data: { email: email },
        success: function(response) {
          otp1 = response;
        },
        error: function() {
          alert("Error occurred. Please try again later.");
        }
      });
    }

    function validateOTP() {
      var otp2 = $("#otpTextarea").val();
      $.ajax({
        type: "POST",
        url: "validateOTP",
        data: { otp12: otp2 },
        success: function(response) {
          if (response !== "invalid") {
            var containerDiv = $("#contId");
            containerDiv.html('<form action="updatepwd" id="signup-form">' +
              '<label>Password:</label>' +
              '<input type="password" class="form-control" id="psd1" name="psd1" required>' +
              '<label>Confirm Password:</label>' +
              '<input type="password" class="form-control" id="psd2" name="psd2" required>' +
              '<div><span id="pmsg"></span></div>' +
              '<button type="button" onclick="pwd()">Update Password</button>' +
              '</form>');
          } else {
            var errorSpan = $("#sid");
            errorSpan.text("OTP Mis-Matched");
          }
        },
        error: function() {
          alert("Error occurred. Please try again later.");
        }
      });
    }

    function pwd() {
      var password = $("#psd1").val();
      var confirmPassword = $("#psd2").val();
      var passwordMessage = $("#pmsg");

      if (password !== confirmPassword) {
        passwordMessage.text("Passwords do not match");
        return false;
      } else {
        passwordMessage.text("");
        $("#signup-form").submit();
      }
    }
  </script>
</head>
<body>
  <div class="container" id="contId">
    <h2>Forgot Password</h2>
    <form>
      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" class="form-control" id="email" required>
      </div>
      <button type="button" class="btn btn-primary" onclick="sendmail()">Send OTP</button>
      <div><span id="sid"></span></div>
    </form>
    <input type="text" id="otpTextarea" class="form-control" rows="3" placeholder="Enter OTP">
    <button id="otpSubmitBtn" class="btn btn-primary mt-2" onclick="validateOTP()">Submit</button>
  </div>
 
  <!-- Include Bootstrap JS -->
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>