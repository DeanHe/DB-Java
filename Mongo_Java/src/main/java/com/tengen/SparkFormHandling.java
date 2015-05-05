package com.tengen;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class SparkFormHandling {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 final Configuration configuration = new Configuration();
	        configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");
	        
	        Spark.get(new Route("/") {
		         @Override
		         public Object handle(Request request, Response response) {
		        	
		        	 try{  
		        	        Template fruitPickerTemplate = configuration.getTemplate("fruitPicker.ftl");
		        	        
		        	        Map<String, Object> fruitsMap = new HashMap<String, Object>();
		        	        fruitsMap.put("fruits", Arrays.asList("apple","orange","banana","peach"));
		        	        
		        	        StringWriter writer = new StringWriter();
		        	        
		        	        fruitPickerTemplate.process(fruitsMap, writer);
		        	        return writer;
		        	        
		        	      }catch(Exception e) {
		        	    	  System.out.println(e);
		        	    	  halt(500);
		        	    	  e.printStackTrace();
		        	    	  return null;
		        	      }		   
		         }
		      });
	        
	        Spark.post(new Route("/favorite_fruit"){
	        	@Override
		         public Object handle(final Request request, final Response response) {
	        		final String fruit =  request.queryParams("fruit");
	        		if (fruit == null){
	        			return "why don't you pick one?";
	        		}
	        		else{
	        			return "your favorite fruit is " + fruit;
	        		}
	        	}
	        });
	}

}
