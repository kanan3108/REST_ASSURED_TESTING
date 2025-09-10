package data_payload;

import io.restassured.path.json.JsonPath;

public class parsing {
    public static JsonPath parse(String s){
        return new JsonPath(s);
    }
}
