package neu.edu.dao;

import org.bson.Document;

import com.mongodb.Cursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class GetMessageDAO {
	
	private MongoCollection<Document> mongoCollectionMessages;
	public GetMessageDAO(MongoClient mongo) {
		this.mongoCollectionMessages = mongo.getDatabase("testdb").getCollection("messages");
	}
	
	public MongoCursor<Document> getMessages() {
		try {
			 MongoCursor<Document> cursor = mongoCollectionMessages.find().iterator();
			 return cursor;
		} catch (Exception e) {
			// TODO: handle exception
			//System.out.println(e);
			System.out.println("Exception in GetMessageDAO");
		}
		return null;
	}

}
