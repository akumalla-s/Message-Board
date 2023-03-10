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
		<title>Dashboard</title>
		<link rel="stylesheet" href="css/header.css">
		<link rel="stylesheet" href="css/dashboard.css">
	</head>
	<body>
		<ul class="dashboard">
			<li class="logo"><img src="images/logo.png" alt="Logo" width="80" height="80" /></li>
			<li class="websitename">Message Board</li>
	
	
			<li><a href="controller?action=profile">Profile</a></li>
			<li><a href="logout">Logout</a></li>
		</ul>
		<%
			UserSession userSession = (UserSession) session.getAttribute("userSession");
		%>
		<!-- Post a message -->
		<div class="container">
			<h1>Message Board</h1>
			<form action="controller" method="post">
				<label for="message">Message:</label>
				<textarea id="message" name="message" required></textarea>
				<input type="hidden" id = "datetimestamp" name = "datetimestamp" value="">
				<input type="hidden" name="action" value="postMessage" /> 
				<input type="submit" value="Post Message" >
			</form>
		</div>
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
					<%if(userSession.getUsername().equals(message.getString("username"))){
						%> 
						<div class="button">
							<form action="controller" method="post" >
				          		<input type="hidden" name="messagestamp" value="<%=message.get("messagetimestamp")%>">
				          		<input type="hidden" name="action" value="deleteMessage" />
				          		<input type="submit" value="Delete">
				        	</form>
					 	</div>			
					<%}%>	
			 	</div>
			 		<!-- Post Reply to a message -->
					<div >
						<form action="controller" method="post">
							<label for="replyMessage">Reply:</label>
							<textarea id="replyMessage" name="replyMessage" required></textarea>
							<input type="hidden" id = "primaryMessageId" name = "primaryMessageId" value=<%=message.get("_id")%>>
							<input type="hidden" name="action" value="addReply" /> 
							<input type="submit" value="Post Reply" >
						</form>
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
								<%if(userSession.getUsername().equals(reply.getString("username"))){
									%> 
									<div class="button">
										<form action="controller" method="post" >
											<input type="hidden" name="primarymessagestamp" value="<%=message.get("messagetimestamp")%>">
							          		<input type="hidden" name="replymessagestamp" value="<%=reply.get("messagetimestamp")%>">
							          		<input type="hidden" name="action" value="deleteReplyMessage" />
							          		<input type="submit" value="Delete">
							        	</form>
								 	</div>			
								<%}%>
							<%} %>
						</div>
						
					<%}%>
			</div>		
		<%} %>
		
		<script type="text/javascript">
		
			//var replies = document.getElementById("buttonId");		    
			function showReplies(buttonId){
				var replies = document.getElementById("replies-"+buttonId);
				//console.log(replies);
					if (replies.style.display === "none") {
				      replies.style.display = "block";
				    } else {
				      replies.style.display = "none";
					}
			}
			
			/* var htmltimestamp = document.getElementById("datetimestamp");
			var timestamp = Date.now();
			function getTimestamp(){
				htmltimestamp.value = timestamp;
			} */
			
		</script>
		
	</body>

</html>