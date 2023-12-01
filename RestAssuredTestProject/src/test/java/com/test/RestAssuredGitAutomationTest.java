package com.test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
public class RestAssuredGitAutomationTest {
	@BeforeTest
	public void setUp() {
		RestAssured.baseURI="https://api.github.com";
	}
	
	
	@Test
	public void getAllRepository() {
		String token = "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";
		File schema = new File("target/schema.json");
		Response response =  RestAssured
				.given()
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + token)
				.when()
				.get("/users/LakshmiMukund2001/repos?type=owner&sort=created");
		    
			//response.then().body(matchesJsonSchema(schema));
		    System.out.println("" + response.getStatusCode());
		    Assert.assertEquals(response.getStatusCode(), 200);
		    String resString = response.prettyPrint();
		    System.out.println(" Data "+resString);
		    //JsonPath jsonPath = response.jsonPath();
		    //System.out.println("FUllName " + jsonPath.getString("node_id"));
		    //List nodes = (ArrayList)jsonPath.getString("node_id");
		    //List nodes =jsonPath.getList("node_id");
		    //System.out.println("Number Of Repos  " + nodes.size());
		    //System.out.println("Number --->  " + response.body().jsonPath().getList("").size());
		    List nodes = response.body().jsonPath().getList("");
		    System.out.println("Total Number of reopsitories --->  " + nodes.size());
		    for(int i = 0 ; i < nodes.size() ; i++) {
		    	response.body().jsonPath();
		    }
		    
		    //Assert.assertEquals(jsonPath.getString("full_name"), contains("LakshmiMukund2001/SalesForceProject"));
		  //  Assert.assertEquals(response.body().jsonPath().getString("full_name[0]") , "LakshmiMukund2001/SalesForceProject");
		   // System.out.println("default_branch" + response.body().jsonPath().getString("default_branch[0]"));
		  //  Assert.assertEquals(response.body().jsonPath().getString("default_branch[0]"),"master"); 
		  
				
	}
	
	@Test
	public void getSingleExistingRepository() {
		String token = "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";
		Response response =  RestAssured
				.given()
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + token)
				.when()
				.get("/repos/LakshmiMukund2001/TestRepo");
				
		
		    System.out.println("" + response.getStatusCode());
		    Assert.assertEquals(response.getStatusCode(), 404);
		    String resString = response.prettyPrint();
		    System.out.println(" Data "+resString);
		   JsonPath jsonPath = response.jsonPath();
		    
		    
		    Assert.assertEquals(response.body().jsonPath().getString("full_name") , "LakshmiMukund2001/SalesForceProject");
			System.out.println("default_branch" + response.body().jsonPath().getString("default_branch[0]"));
			Assert.assertEquals(response.body().jsonPath().getString("default_branch"),"master"); 
			  
		  
				
	}

	@Test
	public void getSingleNonExistingRepository() {
		String token = "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";
		Response response =  RestAssured
				.given()
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + token)
				.when()
				.get("/repos/LakshmiMukund2001/TestRepo5");
				
		
		    System.out.println("" + response.getStatusCode());
		    Assert.assertEquals(response.getStatusCode(), 404);
		    String resString = response.prettyPrint();
		    System.out.println(" Data "+resString);
		   JsonPath jsonPath = response.jsonPath();
		    System.out.println("message " + jsonPath.getString("message"));
		    Assert.assertEquals(jsonPath.getString("message") ,"Not Found");
		  
		  
				
	}
	
	

	@Test
	public void createARepository() {
		String token = "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";
		String jsonReqBody = "{\"name\":\"TestingCreateRepo\",\"description\":\"This is your first\r\n"
				+ "repo!\",\"homepage\":\"https://github.com\",\"private\":false,\"}";
		Response response =  RestAssured
				.given()
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + token)
				.body(jsonReqBody)
				.when()
				.post("LakshmiMukund2001/repos");
				
		   JsonPath jsonPath = response.jsonPath();
		    System.out.println("" + response.getStatusCode());
		    Assert.assertEquals(response.getStatusCode(),201);
		   
		    String resString = response.prettyPrint();
		    System.out.println(" Data "+resString);
		  
		  
				
	}

	@Test
	public void updateARepository() {
		String token = "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";
		String jsonReqBody = "{" 
				+ "\"name\": \"TestRepo4\",\r\n"
				+ "\"description\": \"TekArk update\",\r\n"
				+ "\"private\": \"False\"\r\n"
				+ "\r\n"
				+ "}";
		Response response =  RestAssured
				.given()
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + token)
				.body(jsonReqBody)
				.when()
				.patch("/repos/LakshmiMukund2001/TestRepo");
				
		 JsonPath jsonPath = response.jsonPath();
		    System.out.println("" + response.getStatusCode());
		    Assert.assertEquals(response.getStatusCode(),200);
		    Assert.assertEquals(jsonPath.getString("name") , "TestRepo4");
		    String resString = response.prettyPrint();
		    System.out.println(" Data "+resString);
		  
		  
				
	}
	@Test
	public void deleteNonExistingRepository() {
		String token = "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";
		
		Response response =  RestAssured
				.given()
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + token)
				
				.when()
				.delete("/repos/LakshmiMukund2001/TestRepo7");
				
		 JsonPath jsonPath = response.jsonPath();
		    System.out.println("" + response.getStatusCode());
		    Assert.assertEquals(response.getStatusCode(),404);		    
		    System.out.println("message " + jsonPath.getString("message"));
		    Assert.assertEquals(jsonPath.getString("message") ,"Not Found");
		    
		    String resString = response.prettyPrint();
		    
		   
		  
		  
				
	}
	@Test
	public void deleteExistingRepository() {
		String token = "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";
		
		Response response =  RestAssured
				.given()
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + token)
				
				.when()
				.delete("/repos/LakshmiMukund2001/TestRepo");
				
		 JsonPath jsonPath = response.jsonPath();
		    System.out.println("" + response.getStatusCode());
		    Assert.assertEquals(response.getStatusCode(),204);
		    //Assert.assertEquals(jsonPath.equals("") , false);
		    String resString = response.prettyPrint();
		    
		   
		  
		  
				
	}


	
}
