package com.tengen;

import java.net.UnknownHostException;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

public class FieldSelectionTest {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		MongoClient client = new MongoClient();
		DB db = client.getDB("course");
		DBCollection collection = db.getCollection("fieldSelectionTest");
		collection.drop();
		Random rand = new Random();
		
		 // insert 10 documents with two random integers
        for (int i = 0; i < 10; i++) {
            collection.insert(
                    new BasicDBObject("x", rand.nextInt(2))
                            .append("y", rand.nextInt(100))
                            .append("z", rand.nextInt(1000)));
        }
        
        DBObject query = QueryBuilder.start("x").is(0)
                .and("y").greaterThan(10).lessThan(70).get();
        
        DBCursor cursor = collection.find(query, new BasicDBObject("z", true).append("_id", false));
        try{
        while(cursor.hasNext()){
           DBObject cur = cursor.next();
           System.out.println(cur);
        }
        }finally{
        	cursor.close();
        }
        
		
		

	}

}
