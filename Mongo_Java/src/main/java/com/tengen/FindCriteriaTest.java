package com.tengen;

import java.net.UnknownHostException;
import java.util.Random;

import org.bson.NewBSONDecoder;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

public class FindCriteriaTest {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		 MongoClient client = new MongoClient();
	        DB db = client.getDB("course");
	        DBCollection collection = db.getCollection("findCriteriaTest");
	        collection.drop();
	        
	     // insert 10 documents with two random integers
		for(int i = 0; i < 10; i++){
			collection.insert(
					new BasicDBObject("x", new Random().nextInt(2))
					.append("y", new Random().nextInt(100)));
		}
		
		DBObject query = new BasicDBObject("x", 0)
		                    .append("y", new BasicDBObject("$gt",20).append("$lt", 90));
		
		QueryBuilder query2 = QueryBuilder.start("x").is(0).and("y").greaterThan(20).lessThan(90);
		
		 System.out.println("\nCount:");
	        long count = collection.count(query2.get());
	        System.out.println(count);
	        
	        System.out.println("\nFind all: ");
	        DBCursor cursor = collection.find(query2.get());
	        try {
	            while (cursor.hasNext()) {
	                DBObject cur = cursor.next();
	                System.out.println(cur);
	            }
	        } finally {
	            cursor.close();
	        }
	    }    

}
