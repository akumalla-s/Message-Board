<%@page import="neu.edu.dao.GetMessageDAO"%>
<%@page import="com.mongodb.MongoClient"%>
<%@page import="neu.edu.data.UserSession"%>
<%@ page import="com.mongodb.client.MongoCursor"%>
<%@ page import="org.bson.Document"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Mini Project</title>
	<link rel="stylesheet" href="css/header.css">
	<link rel="stylesheet" href="css/dashboard.css">
</head>
<body>
	<ul class="dashboard">
      <li class="logo"> <img src="images/logo.png" alt="Logo" width="80" height="80" /> </li>
      <li class="websitename">Message Board</li>

      <li><a href="registration">Register</a></li>
      <li><a href="login">Login</a></li>
    </ul>
    
    <%
	    ServletContext sc = request.getServletContext();
		MongoClient mongoClient = (MongoClient) sc.getAttribute("mongodbClient");	
		GetMessageDAO getMessageDAO = new GetMessageDAO(mongoClient);
		MongoCursor<Document> cursor = getMessageDAO.getMessages();		
	%>
	<div style="color: #05a9e4">
		<h1>Guest View</h1>
	</div>
	
	<%while (cursor.hasNext()) {
			%>
			<!-- Display Message -->
			<div style="border: 1px solid black; padding: 10px; margin-bottom: 10px;">
				<%
					Document message = cursor.next();
					String buttonId = message.get("_id").toString();
				%>
				
				<!-- <h3><%=message.get("_id")%></h3> -->
				<p> <strong><%=message.get("username")%>:</strong> </p>
				<p><%=message.get("usermessage")%></p>		
				
				
			 					
					<%
						List<Document> replies = (List<Document>) message.get("replies");
						if (replies != null && replies.size() > 0) {
							
					%>
						<button class="buttonReply" id="<%= buttonId%>" onclick="showReplies('<%= buttonId%>')">Show/Hide Replies</button>	
						<!-- Display Replies-->
						<hr>
						<h3>Replies:</h3>
						<div id="replies-<%=buttonId%>" class="replies">
							<%for (Document reply : replies){
								%>
								<div  style="border: 1px solid black; padding: 10px; margin-bottom: 10px;">
									<p> <strong><%=reply.get("username")%>:</strong></p>
									<p><%=reply.get("usermessage")%></p>
								</div>
								
							<%} %>
						</div>
						
					<%}%>
			</div>		
		<%} %>
		
		<script type="text/javascript">
			function showReplies(buttonId){
				var replies = document.getElementById("replies-"+buttonId);
				//console.log(replies);
					if (replies.style.display === "none") {
				      replies.style.display = "block";
				    } else {
				      replies.style.display = "none";
					}
			}
		
		</script>

</body>
</html>