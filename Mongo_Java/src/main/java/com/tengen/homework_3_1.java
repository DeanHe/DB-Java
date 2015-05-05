package com.tengen;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.NewBSONDecoder;

public class homework_3_1 {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		MongoClient client = new MongoClient();
        DB db = client.getDB("school");
        DBCollection collection = db.getCollection("students");
        
     //   QueryBuilder query = QueryBuilder.start("type").is("homework");
        		
DBCursor cursor = collection.find(
		       //    query.get()).sort(new BasicDBObject("student_id",1).append("score", -1)
				);
        try{

        	while(cursor.hasNext()){
        		
        		DBObject cur = cursor.next();
        		ArrayList<BasicDBObject> scores = (ArrayList<BasicDBObject>) cur.get("scores");
        //		 BasicDBObject[] scoresArray = scores.toArray(new  BasicDBObject[0]);
        		double lowestScore = 0;
        		for ( BasicDBObject temp : scores){
        			if(temp.get("type").equals("homework")){
        				if(Double.parseDouble ( temp.get("score").toString()) < lowestScore || lowestScore == 0){
        					lowestScore = Double.parseDouble ( temp.get("score").toString());
        				}
        			}
        		}
        		scores.remove(new BasicDBObject("type","homework").append("score", lowestScore));
      //  		DBObject cur2 = cur;
      //  		cur2.put("scores", scores);
      //  		System.out.println( cur2);
                collection.update(new BasicDBObject("_id", cur.get("_id")),  new BasicDBObject("$set", new BasicDBObject("scores",scores)));

        	}

        }finally{
        	cursor.close();
        }
	}

}
