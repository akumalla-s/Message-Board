<%@page import="neu.edu.data.UserSession"%>
<%@page import="neu.edu.dao.GetMessageDAO"%>
<%@page import="com.mongodb.MongoClient"%>
<%@ page import="com.mongodb.client.MongoCursor"%>
<%@ page import="org.bson.Document"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Message Board</title>
<link rel="stylesheet" href="css/header.css" >
<link rel="stylesheet" href="css/admindashboard.css" >
</head>
<body>

	<ul class="dashboard">
		<li class="logo"> <img src="images/logo.png" alt="Logo" width="80" height="80" /> </li>
    	<li class="websitename">Message Board</li>
		

		<li><a href="logout">Logout</a></li>
		<li><a href="controller?action=viewUsers">View Users</a></li>
	</ul>
	
	<div style="color: #05a9e4">
		<h1>Admin Dashboard</h1>
	</div>
	
	<%
		UserSession userSession = (UserSession)session.getAttribute("userSession");	
	%>
	<%
		ServletContext sc = request.getServletContext();
		MongoClient mongoClient = (MongoClient) sc.getAttribute("mongodbClient");	
		GetMessageDAO getMessageDAO = new GetMessageDAO(mongoClient);
		MongoCursor<Document> cursor = getMessageDAO.getMessages();		
	%>
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
				
				<div class="button-container">		
					<!-- Delete button -->					 
						<div class="button">
							<form action="controller" method="post" >
				          		<input type="hidden" name="messagestamp" value="<%=message.get("messagetimestamp")%>">
				          		<input type="hidden" name="action" value="deleteMessage" />
				          		<input type="submit" value="Delete">
				        	</form>
					 	</div>
			 	</div>
			
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
								<!-- Delete button -->
									<div class="button">
										<form action="controller" method="post" >
											<input type="hidden" name="primarymessagestamp" value="<%=message.get("messagetimestamp")%>">
							          		<input type="hidden" name="replymessagestamp" value="<%=reply.get("messagetimestamp")%>">
							          		<input type="hidden" name="action" value="deleteReplyMessage" />
							          		<input type="submit" value="Delete">
							        	</form>
								 	</div>			
								<%} %>
						</div>
						
					<%}%>
			</div>		
		<%} %>
		
	<script type="text/javascript">
		function showReplies(buttonId){
			var replies = document.getElementById("replies-"+buttonId);
				if (replies.style.display === "none") {
			      replies.style.display = "block";
			    } else {
			      replies.style.display = "none";
				}
		}
	</script>

</body>
</html>