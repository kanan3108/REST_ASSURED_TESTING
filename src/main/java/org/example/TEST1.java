package org.example;

import data_payload.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TEST1 {
    public static void main(String[] args) {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key","qaclick123").
                header("Content-Type","application/json").
                body(payload.request()).when().post("maps/api/place/add/json").
                then().log().all().assertThat().statusCode(200).
                body("scope",equalTo("APP")).
                header("Access-Control-Max-Age","3600").extract().response().asString();

        //print response
        System.out.print("----------------------------"+"\n"+response);

        JsonPath js = new JsonPath(response);// parsing json
        String extract = js.getString("place_id");
        System.out.println("\n"+"place id = "+extract);

        //  update api
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
                body(payload.update(extract)).when().put("maps/api/place/update/json").then().assertThat().statusCode(200).
                body("msg",equalTo("Address successfully updated"));



        }
}