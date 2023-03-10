<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
  <title>Registration</title>
  <link rel="stylesheet" href="css/header.css" >
  <link rel="stylesheet" href="css/registration.css">
  </head>
  <body>

    <ul class="dashboard">
      <li class="logo"> <img src="images/logo.png" alt="Logo" width="80" height="80" /> </li>
      <li class="websitename">Message Board</li>		

      <li><a href="index.jsp">Home</a></li>
      <li><a href="login">Login</a></li>
    </ul>

	  <!-- Add your registration form below -->
    <div class="registration-form">
      <h2 class="page-title">Registration Form</h2>
      <form action="registration" method="post" >
        <label for="firstname">First Name</label>
        <input type="text" name="firstname" class="form-control" id="firstname" pattern="^[a-zA-Z]([a-zA-Z ]){0,28}[a-zA-Z]$" title="Should contain only letters" value="Srinivas" required >

        <label for="lastname">Last Name</label>
        <input type="text" name="lastname" class="form-control" id="lastname" pattern="^[a-zA-Z]([a-zA-Z ]){0,28}[a-zA-Z]$" title="Should contain only letters" value="Akumalla" required >

        <label for="mobile">Mobile Number </label>
        <input type="text" id="mobile" name="mobile" class="form-control" pattern="^\d{10}$" title="Phone number should contain numbers only" value="1234567890" required>
        
        <label for="email">Email</label>
        <input type="text" id="email" name="email" class="form-control" pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" title="Incorrect Username" value="srinredd2022@gmail.com" required>

        <label for="username">Username</label>
        <input style="margin-bottom: 0px;" type="text" id="username" name="username" class="form-control" pattern="^[a-zA-Z0-9_-]{4,20}$" title="Invalid Username" value="srini" required>

        <p style="margin-top: 0px; color: black;">You can use 4 or more characters with a mix of letters, numbers, underscores and hyphens </p>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" class="form-control" pattern="^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*])[a-zA-Z\d!@#$%^&*]{6,}$"
          title="Invalid Password" value="pass@123" required>

        <label for="retypepassword">Retype Password </label>
        <input style="margin-bottom: 0px;" type="password" id="retypepassword" name="retypepassword" class="form-control" pattern="^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*])[a-zA-Z\d!@#$%^&*]{6,}$"
          title="Invalid Password" required>

        <p style="margin-top: 0px; color: black;">Use 6 or more characters with a mix of alphabets, numbers, symbols </p>

        <input type="submit" value="Submit" onclick="validateForm()"/>
      </form>
    </div>
    <script>
      var password = document.getElementById("password");
      var retypePassword = document.getElementById("retypepassword");

      password.onchange = validatePassword;
      retypePassword.onkeyup = validateForm;
      function validateForm() {
        validatePassword();   
      }     
      function validatePassword(){
        if (password.value != retypePassword.value) {
          retypePassword.setCustomValidity("Password and Retype Password fields do not match!");
        }else{
          retypePassword.setCustomValidity('');
        }
      }
    </script>
  </body>
</html>
