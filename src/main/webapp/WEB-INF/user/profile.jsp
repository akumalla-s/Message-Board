<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Message Board</title>
<link rel="stylesheet" href="css/header.css" >
<link rel="stylesheet" href="css/profile.css" >
</head>
<body>

	<ul class="dashboard">
		<li class="logo"> <img src="images/logo.png" alt="Logo" width="80" height="80" /> </li>
    	<li class="websitename">Message Board</li>
		

		<li><a href="controller?action=dashboard">Dashboard</a></li>
		<li><a href="logout">Logout</a></li>
	</ul>
	
	<!-- Add your registration form below -->
    <div class="registration-form">
      <h2 class="page-title">Update User Profile</h2>
      
      <%
      
      	if(request.getAttribute("errorMsg")!= null){
      		%>
      		
      <p class="error"><%=request.getAttribute("errorMsg")%></p>
      		
      		<% 
      	}
      %>
      
      <form action="controller" method="post">
        <label for="username">Username</label>
        <input style="margin-bottom: 0px;" type="text" id="username" name="username" class="form-control" pattern="^[a-zA-Z0-9_-]{4,20}$" title="Invalid Username" value="${requestScope.username}" required>

        <p style="margin-top: 0px; color: black;">You can use 4 or more characters with a mix of letters, numbers, underscores and hyphens </p>
		
        <label for="email">Email</label>
		<input type="text" id="email" name="email" class="form-control" pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" title="Incorrect Email address" value ="${requestScope['email']}" required>
		
    	<input type="hidden" name="page" value="editProfile"/>

        <input type="submit" value="Submit">
      </form>
    </div>
    
    <div class="registration-form">
      <h2 class="page-title">Update Password</h2>
      <form action="controller" method="post" >
      
        <label for="password">Password</label>
        <input type="password" id="password" name="password" class="form-control" pattern="^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*])[a-zA-Z\d!@#$%^&*]{6,}$"
          title="Invalid Password" required>

        <label for="retypepassword">Retype Password </label>
        <input style="margin-bottom: 0px;" type="password" id="retypepassword" name="retypepassword" class="form-control" pattern="^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*])[a-zA-Z\d!@#$%^&*]{6,}$"
          title="Invalid Password" required>

        <p style="margin-top: 0px; color: black;">Use 6 or more characters with a mix of alphabets, numbers, symbols </p>

        	<input type="hidden" name="page" value="editProfilePassword"/>
    

        <input type="submit" value="Submit" onclick="validateForm()" />
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