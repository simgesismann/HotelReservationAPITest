import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
public class HotelReservationAPI {
    @Test(priority = 1)
    public void createToken(){
        String body1 = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        given().contentType("application/json").body(body1).
                when().post("https://restful-booker.herokuapp.com/auth").
                then().log().all().
                statusCode(200);
        // token = ab7fac73296bc2e
    }
    @Test(priority = 2)
    public void getBookingIds(){
        given().get("https://restful-booker.herokuapp.com/booking").then().
                log().all().statusCode(200);
    }
    @Test(priority = 3)
    public void createBooking(){
        String body2 = "{\n" +
                "    \"firstname\" : \"Simge\",\n" +
                "    \"lastname\" : \"ŞİŞMAN\",\n" +
                "    \"totalprice\" : 1000,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2022-10-01\",\n" +
                "        \"checkout\" : \"2022-10-10\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        given().contentType("application/json").
                body(body2).
                when().post("https://restful-booker.herokuapp.com/booking").
                then().log().all().
                statusCode(200);
    }
    @Test(priority = 4)
    public void partialUpdateBooking(){
        String partialBody = "{\n" +
                "    \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Brown\"\n" +
                "}";
        given().contentType("application/json").
                header("Cookie","token=ab7fac73296bc2e").
                patch("https://restful-booker.herokuapp.com/booking/127").
                then().statusCode(200);
    }
    @Test(priority = 5)
    public void updateBooking(){
        String body3 = "{\n" +
                "    \"firstname\" : \"Simge\",\n" +
                "    \"lastname\" : \"ŞİŞMAN\",\n" +
                "    \"totalprice\" : 2000,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2022-10-01\",\n" +
                "        \"checkout\" : \"2022-10-20\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        given().contentType("application/json").
                header("Cookie","token=ab7fac73296bc2e").
                patch("https://restful-booker.herokuapp.com/booking/127").
                then().statusCode(200);
    }
    @Test(priority = 6)
    public void deleteCreatedBooking(){
        given().contentType("application/json").
                header("Cookie","token=ab7fac73296bc2e").
                delete("https://restful-booker.herokuapp.com/booking/127").
                then().statusCode(201);
    }

}
