package neu.edu.data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Message {
	
	private String messageId;	
	private String username;
	private String userMessage;
	private String[] replies;
	private String primaryMessageTimestamp;
	private String repliesMessageTimestamp;
	
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
	public Message(String primaryMessageTimestamp) {
		this.primaryMessageTimestamp = primaryMessageTimestamp;
	}

	public Message(String username,String userMessage) {
		super();
		this.username = username;
		this.userMessage = userMessage;
	}	
	
	public Message(String username, String userMessage, String primaryMessageTimestamp) {
		super();
		this.username = username;
		this.userMessage = userMessage;
		this.primaryMessageTimestamp = sdf2.format(timestamp);
	}

	public Message(String messageId, String username, String userMessage, String primaryMessageTimestamp) {
		super();
		this.messageId = messageId;
		this.username = username;
		this.userMessage = userMessage;
		this.primaryMessageTimestamp = primaryMessageTimestamp;
	}

	public String getRepliesMessageTimestamp() {
		return repliesMessageTimestamp;
	}

	public void setRepliesMessageTimestamp(String repliesMessageTimestamp) {
		this.repliesMessageTimestamp = repliesMessageTimestamp;
	}

	public String getPrimaryMessageTimestamp() {
		return primaryMessageTimestamp;
	}

	public void setPrimaryMessageTimestamp(String primaryMessageTimestamp) {
		this.primaryMessageTimestamp = primaryMessageTimestamp;
	}

	public String[] getReplies() {
		return replies;
	}



	public void setReplies(String[] replies) {
		this.replies = replies;
	}

	public String getMessageId() {
		return messageId;
	}


	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
	
	
	

}
