package neu.edu.dao;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import neu.edu.data.DeleteReplyMessageData;
import neu.edu.data.Message;
import neu.edu.data.ReplyMessage;
import neu.edu.data.converter.MessageConverter;

public class PostMessageDAO {

	private MongoCollection<Document> mongoCollectionMessages;

	public PostMessageDAO(MongoClient mongo) {
		this.mongoCollectionMessages = mongo.getDatabase("testdb").getCollection("messages");
	}

	public boolean create(Message message) {
		// TODO Auto-generated method stub
		try {
			Document messageDoc = MessageConverter.toDocument(message);
			this.mongoCollectionMessages.insertOne(messageDoc);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		return false;
	}

	public boolean reply(ReplyMessage message) {
		try {
			  Document messageDoc = MessageConverter.toRepliesDocument(message);
			  this.mongoCollectionMessages.updateOne(Filters.eq("_id", new ObjectId(message.getPrimaryMessageID())), new Document("$push", messageDoc) );			 
			  return true;
		} catch (Exception e) {
			System.out.println("PostMessageDAO: reply");
			System.out.println(e);
		}
		return false;
	}
	
	public boolean deleteMessage(Message message) {
		try {
			Document deleteDoc = MessageConverter.toDeleteDocument(message);
			this.mongoCollectionMessages.deleteOne(deleteDoc);
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	public boolean deleteReplyMessage(DeleteReplyMessageData message) {
		try {
			Document deleteDoc = MessageConverter.toDeleteReplyDocument(message);
			this.mongoCollectionMessages.updateOne(Filters.eq("messagetimestamp", message.getPrimaryMessageTimestamp()), new Document("$pull", deleteDoc));
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	public boolean updateUserName(String oldUserName, String newUserName) {
		boolean isUpdated = false;
		try {
			this.mongoCollectionMessages.updateMany(Filters.eq("replies.username", oldUserName), new Document("$set", new Document("replies.$.username", newUserName)));
			isUpdated = true;
		}catch (Exception e) {
			isUpdated = false;
			System.out.println("PostMessageDAO : updateUserName 1");
			System.out.println(e);
		}
		try {
			this.mongoCollectionMessages.updateMany(Filters.eq("username", oldUserName), new Document("$set", new Document("username", newUserName)));
			isUpdated = true;
		} catch (Exception e) {
			isUpdated = false;
			System.out.println("PostMessageDAO : updateUserName 2");
			System.out.println(e);
		}
		return isUpdated;
	}
	
	public boolean deleteUserMessagesAdmin(String username) {
		boolean isDeleted = false;
		try {
			this.mongoCollectionMessages.deleteMany(Filters.eq("username", username));
			isDeleted = true;
		} catch (Exception e) {
			isDeleted = false;
			System.out.println("PostMessageDAO :deleteUserMessagesAdmin 1 ");
			System.out.println(e);
		}
		try {
			//this.mongoCollectionMessages.updateMany(new Document(), new Document("$pull", new Document("username",username)));
			this.mongoCollectionMessages.updateMany(new Document(), Updates.pull("replies", Filters.eq("username", username)));
			isDeleted = true;
		} catch (Exception e) {
			isDeleted = false;
			System.out.println("PostMessageDAO :deleteUserMessagesAdmin 2 ");
			System.out.println(e);
		}
		return isDeleted;
	}

}
