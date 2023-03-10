<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
  <link rel="stylesheet" href="css/header.css" />
  <link rel="stylesheet" href="css/login.css" />  
</head>
<body>

	<ul class="dashboard">
		<li class="logo"> <img src="images/logo.png" alt="Logo" width="80" height="80" /> </li>
    	<li class="websitename">Message Board</li>
		

		<li><a href="index.jsp">Home</a></li>
		<li><a href="registration">Register</a></li>
	</ul>

	<!-- Add your registration form below -->
   <div class="login-form">
      <h2 class="page-title">Login</h2>    
      
      <%
      
      	if(request.getAttribute("errorMsg")!= null){
      		%>
      		
      <p class="error"><%=request.getAttribute("errorMsg")%></p>
      		
      		<% 
      	}
      %>        
      
      <form action="login" method="post">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" class="form-control" required>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" class="form-control" required>

        <input type="submit" value="Submit">
      </form>
    </div>

</body>
</html>
