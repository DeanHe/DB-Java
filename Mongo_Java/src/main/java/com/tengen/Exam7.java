package com.tengen;

import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class Exam7 {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		
		Set<Integer> imageSet = new HashSet<Integer>();
		
		MongoClient client = new MongoClient();
        DB db = client.getDB("m101");
        DBCollection imageCollection = db.getCollection("images");
        DBCollection albumCollection = db.getCollection("albums");
        
        DBCursor albumCursor = albumCollection.find();
        try {
        	while(albumCursor.hasNext()){
        		DBObject cur = albumCursor.next();
        		List<Integer> imageDbObjects = (List<Integer>)cur.get("images");
        		for(Integer a: imageDbObjects){
        			imageSet.add(a);
        		}
        	}
			
		} finally {
			albumCursor.close();
		}
        DBCursor imageCursor = imageCollection.find();
        try {
        	while(imageCursor.hasNext()){
        		DBObject cur = imageCursor.next();
                if(!imageSet.contains(cur.get("_id"))){
                	imageCollection.remove(cur);
                }
        	}
			
		} finally {
			imageCursor.close();
		}
     System.out.println(imageCollection.count());

	}
	
}
