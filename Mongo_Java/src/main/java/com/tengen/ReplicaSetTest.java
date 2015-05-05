package com.tengen;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

import java.net.UnknownHostException;
import java.util.Arrays;

public class ReplicaSetTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws UnknownHostException, InterruptedException {
		// TODO Auto-generated method stub
		MongoClient client = new MongoClient(Arrays.asList(
				                             new ServerAddress("localhost",27017),
				                             new ServerAddress("localhost",27018),
				                             new ServerAddress("localhost",27019)));
		
		client.setWriteConcern(WriteConcern.JOURNALED);
		client.setReadPreference(ReadPreference.primary());
		
		DBCollection test = client.getDB("course").getCollection("replica.test");
		test.setWriteConcern(WriteConcern.JOURNALED);
		test.setReadPreference(ReadPreference.nearest());
		test.drop();
		
		DBCursor cursor = test.find().setReadPreference(ReadPreference.secondaryPreferred());
		
		for(int i = 0; i < Integer.MAX_VALUE; i++){
			for(int retries = 0; retries <= 2; retries ++){
				try{
				test.insert(new BasicDBObject("_id",i),WriteConcern.JOURNALED);
		        System.out.println("Inserted document: " + i);
		        break;
			} catch(MongoException.DuplicateKey e){
				System.out.println("Document already inserted: " + i);
			}
			  catch(MongoException e){
				System.out.println(e.getMessage());
				System.out.println("Retrying");
				Thread.sleep(5000);
			}
		}
		Thread.sleep(500);
	}
		
}
}
