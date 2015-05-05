package com.tengen;

import java.util.Arrays;
import java.util.Date;

import com.mongodb.BasicDBObject;

public class DocumentRepresentationTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       BasicDBObject doc = new BasicDBObject();
       doc.put("username", "jyemin");
       doc.put("birthDate", new Date(123456));
       doc.put("programmer", true);
       doc.put("language",Arrays.asList("JAVA","C++"));
       doc.put("address", new BasicDBObject("Street","32 Main")
       .append("town", "Westfield")
       .append("zip", "56789"));
       
       System.out.println(doc);
	}

}
