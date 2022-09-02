package com.google.stepdefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.google.objectrepository.AddLocation;
import com.google.objectrepository.Location;
import com.google.resources.APIResources;
import com.google.resources.Commonutils;

public class GoogleSteps extends Commonutils{
	
	AddLocation loc;
	Commonutils c = new Commonutils();
	
	@Given("I want to add payload")
	public void i_want_to_add_payload() {
		
		//pojo
		 loc = new AddLocation();
		 
		 Location l = new Location();
		 l.setLat("-40.565");
		 l.setLng("38.798798");
		 
		 loc.setLocation(l);
		 
		 loc.setAccuracy(50);
		 
		 loc.setName("CG technologies");
		 
		 loc.setAddress("Guduvanchery");
		 
		 loc.setPhone_number("7687697979");
		 
		 List<String> li = new ArrayList<String>();
		 li.add("software");
		 li.add("training");
		 
		 loc.setTypes(li);
		 
		 loc.setWebsite("http://www.cgtech.com/");
		 
		 loc.setLanguage("tamil");
	    
	}

	Response response;
	RequestSpecification requset;
	
	@When("user submit {string} api")
	public void user_submit_api(String string) throws FileNotFoundException {
		
		c.requestSpecDif();
		
		 
		  requset = given().spec(requestDiffApproach).body(loc);
	    
	}
	
	

	@Then("user validate the status code is {int}")
	public void user_validate_the_status_code_is(Integer int1) {
		
		ResponseSpecification response2 = c.response();
		
		APIResources resource = APIResources.valueOf("addPlaceApi");
		String resource2 = resource.getResource();
		System.out.println(resource.getResource());
		
		
		response = requset.when().post(resource2)
		.then().spec(response2).extract().response();
		
		int int2 = response.getStatusCode();
		
		String s = String.valueOf(int2);
	    
		Assert.assertEquals(int1.toString(), s);
		
		
	}

}
