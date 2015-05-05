package com.tengen;

import java.lang.reflect.Array;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class Exam {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient();
        DB db = client.getDB("enron");
        DBCollection collection = db.getCollection("messages");
        

        DBObject projectField = new BasicDBObject("_id","$headers.From").append("To", "$headers.To");
//        DBObject unwind = new BasicDBObject("$unwind", "$headers.To");
//        DBObject addToset = new BasicDBObject("$addToSet","$headers.To");
//        DBObject groupField = new BasicDBObject("_id","$_id").append("From", "$headers.From").append("To", addToset);
//        DBObject group = new BasicDBObject("$group", groupField);
//        List< DBObject> pipeLine = Arrays.asList(unwind,group);
        DBObject project = new BasicDBObject("$project", projectField);
        AggregationOutput output = collection.aggregate(project);
        
        for (DBObject result : output.results()) {
            System.out.println(result);
        }
        
	}

}
