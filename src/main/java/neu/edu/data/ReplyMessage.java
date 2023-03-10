package neu.edu.data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;

public class ReplyMessage {
	private String primaryMessageID;
	private String replyUsername;
	private String replyMessage;
	private String messageTimeStamp;
	
	private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
	public ReplyMessage(String primaryMessageID, String replyUsername, String replyMessage) {
		super();
		this.primaryMessageID = primaryMessageID;
		this.replyUsername = replyUsername;
		this.replyMessage = replyMessage;
		this.messageTimeStamp = sdf2.format(timestamp);
	}
	


	public String getMessageTimeStamp() {
		return messageTimeStamp;
	}

	public void setMessageTimeStamp(String messageTimeStamp) {
		this.messageTimeStamp = messageTimeStamp;
	}

	public String getPrimaryMessageID() {
		return primaryMessageID;
	}
	public void setPrimaryMessageID(String primaryMessageID) {
		this.primaryMessageID = primaryMessageID;
	}
	public String getReplyUsername() {
		return replyUsername;
	}
	public void setReplyUsername(String replyUsername) {
		this.replyUsername = replyUsername;
	}
	public String getReplyMessage() {
		return replyMessage;
	}
	public void setReplyMessage(String replyMessage) {
		this.replyMessage = replyMessage;
	}
	
	
	

}
