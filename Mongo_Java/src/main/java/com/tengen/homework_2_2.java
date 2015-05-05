package com.tengen;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public class homework_2_2 {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		MongoClient client = new MongoClient();
        DB db = client.getDB("students");
        DBCollection collection = db.getCollection("grades");
        
        QueryBuilder query = QueryBuilder.start("type").is("homework");
        		
DBCursor cursor = collection.find(
		           query.get()).sort(new BasicDBObject("student_id",1).append("score", -1)
				);
        try{
        	DBObject previDoc = cursor.next();
        	while(cursor.hasNext()){
        		
        		DBObject cur = cursor.next();

        		Object curID = cur.get("student_id");
        		if( !cursor.hasNext()){
        			System.out.println(cur);
                    collection.remove(cur);
             		}
        		else if(!curID.equals(previDoc.get("student_id")) ){
        			System.out.println(previDoc);
                    collection.remove(previDoc);
        		}
        			previDoc = cur;
        	}

        }finally{
        	cursor.close();
        }
	}

}
