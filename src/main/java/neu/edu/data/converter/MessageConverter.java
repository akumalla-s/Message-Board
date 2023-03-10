package neu.edu.data.converter;

import org.bson.Document;
import org.bson.types.ObjectId;

import neu.edu.data.DeleteReplyMessageData;
import neu.edu.data.Message;
import neu.edu.data.ReplyMessage;

public class MessageConverter {
	
	public static Document toDocument(Message message) {
		Document doc = new Document("username", message.getUsername())
							.append("usermessage", message.getUserMessage())
							.append("messagetimestamp", message.getPrimaryMessageTimestamp());
		if(message.getMessageId() != null) {
			doc.append("_id", new ObjectId(message.getMessageId()));
		}
		
		return doc;
	}
	
	public static Document toRepliesDocument(ReplyMessage message) {
		Document doc = new Document("replies" , new Document("username",message.getReplyUsername())
												.append("usermessage", message.getReplyMessage())
												.append("messagetimestamp", message.getMessageTimeStamp())
				);		
		return doc;
	}
	
	public static Document toDeleteDocument(Message message) {
		Document doc = new Document("messagetimestamp", message.getPrimaryMessageTimestamp());
		return doc;
	}
	
	public static Document toDeleteReplyDocument(DeleteReplyMessageData message) {
		//Document doc = new Document("messagetimestamp", message.getPrimaryMessageTimestamp())
		//				.append("replies", new Document("messagetimestamp", message.getRepliesMessageTimestamp()));
		Document doc = new Document("replies", new Document("messagetimestamp", message.getRepliesMessageTimestamp()));
		return doc;
	}

}
