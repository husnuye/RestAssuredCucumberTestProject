package com.hotelreservation.services;

import com.hotelreservation.models.Auth;
import com.hotelreservation.models.Booking;
import com.hotelreservation.models.BookingDates;
import com.hotelreservation.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ReservationService extends BaseTest {


    public String generateToken(){
        Auth authBody =new Auth("admin","password123");

        Response response =given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(authBody)
                .post("/auth");
        response
                .then()
                .statusCode(200);

        return response.jsonPath().getJsonObject("token");
    }
    //token olustur

    /*curl -X POST \
    https://restful-booker.herokuapp.com/auth \
            -H 'Content-Type: application/json' \
            -d '{
            "username" : "admin",
            "password" : "password123"
}'
*/

    //rezervasyon oluştur

    public BookingResponse createBooking(){
        BookingDates bookingDates =new BookingDates("2023-04-01","2023-05-01");
        Booking booking = new Booking("Welcome","Cucumber",1000,false,bookingDates,
                "Cat Bed");

        Response response  =given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(booking)
                .post("/booking");
        response
                .then()
                .statusCode(Integer.parseInt("200")); /// burda parse integer  yaptım hata dönüyor diye

        return response.as(BookingResponse.class);
    }


     /*   curl -X POST \
        https://restful-booker.herokuapp.com/booking \
                -H 'Content-Type: application/json' \
                -d '{
                "firstname" : "Jim",
                "lastname" : "Brown",
                "totalprice" : 111,
                "depositpaid" : true,
                "bookingdates" : {
            "checkin" : "2018-01-01",
                    "checkout" : "2019-01-01"
        },
                "additionalneeds" : "Breakfast"
    }'

*/


    //rezervayon silme

    /*curl -X DELETE \
    https://restful-booker.herokuapp.com/booking/1 \
            -H 'Content-Type: application/json' \
            -H 'Cookie: token=abc123'*/

    public void deleteReservation(String token, int bookingId){
        Response response  =given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie","token" + token)
                .when()
                .delete("/booking/"+bookingId);
        response
                .then()
                .statusCode(201);
    }

}
