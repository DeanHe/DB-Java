package com.tengen;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class InsertTest {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
       MongoClient client = new MongoClient();
       DB courseDB = client.getDB("course");
       DBCollection collection = courseDB.getCollection("InsertTest");
      collection.drop();
       
       DBObject doc = new BasicDBObject().append("x", 1);
       DBObject doc2 = new BasicDBObject("_id",new ObjectId()).append("x", 1);
       //System.out.println(doc);
       
       collection.insert(Arrays.asList(doc, doc2));
       
     //  System.out.println(doc);
	}

}
