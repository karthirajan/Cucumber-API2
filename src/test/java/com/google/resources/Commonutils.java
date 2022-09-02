package com.google.resources;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Commonutils {
	
	public static RequestSpecification request;
	
	public static RequestSpecification requestDiffApproach;
	
	public RequestSpecification requestSpec() {
		
		if(request == null){
		 request =
				given().baseUri("https://rahulshettyacademy.com").
				contentType(ContentType.JSON).queryParam("key", "qaclick123");
		}
		
		
		return request;

	}
	
   public RequestSpecification requestSpecDif() throws FileNotFoundException {
	   
	   File file = new File(".//log.txt");
	   
	   PrintStream stream = new PrintStream(file);
		
		if(requestDiffApproach == null){
			requestDiffApproach =
				new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON)
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(stream))
				.addFilter(ResponseLoggingFilter.logResponseTo(stream))
				.build();
		}
		
		
		return requestDiffApproach;

	}
   
   public ResponseSpecification response(){
	   
	   ResponseSpecification  response = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	     
	    return response;
	   
   }

}
