package neu.edu.data;

public class DeleteReplyMessageData {
	private String primaryMessageTimestamp;
	private String repliesMessageTimestamp;
	
	public DeleteReplyMessageData(String primaryMessageTimestamp, String repliesMessageTimestamp) {
		super();
		this.primaryMessageTimestamp = primaryMessageTimestamp;
		this.repliesMessageTimestamp = repliesMessageTimestamp;
	}
	public String getPrimaryMessageTimestamp() {
		return primaryMessageTimestamp;
	}
	public void setPrimaryMessageTimestamp(String primaryMessageTimestamp) {
		this.primaryMessageTimestamp = primaryMessageTimestamp;
	}
	public String getRepliesMessageTimestamp() {
		return repliesMessageTimestamp;
	}
	public void setRepliesMessageTimestamp(String repliesMessageTimestamp) {
		this.repliesMessageTimestamp = repliesMessageTimestamp;
	}
	
	

}
