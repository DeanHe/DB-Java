package com.tengen;

import spark.*;

public class HelloWorldSparkStyle {

   public static void main(String[] args) {
      
      Spark.get(new Route("/") {
         @Override
         public Object handle(Request request, Response response) {
            return "Hello Spark~!";
         }
      });

   }

}