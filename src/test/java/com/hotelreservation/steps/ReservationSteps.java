package com.hotelreservation.steps;

import com.hotelreservation.models.BookingResponse;
import com.hotelreservation.services.ReservationService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class ReservationSteps {

    ReservationService  reservationService;
    String authKey;
    BookingResponse bookingResponse;


    @Given("User creates a new hotel reservation")
    public void cagriBaglangici(){
        reservationService = new ReservationService();

    }
    @Given("User provides information required for hotel reservation")
    public void createAuth(){

        authKey = reservationService.generateToken();
    }
    @When("User creates hotel reservation")
    public void createReservation(){
        bookingResponse =reservationService.createBooking();
    }

    @Then("The reservation was created successfully")
    public void reservationAssertion(){
        Assertions.assertEquals("Welcome",bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Cucumber",bookingResponse.getBooking().getLastname());
        Assertions.assertEquals(1000,bookingResponse.getBooking().getTotalprice());
        Assertions.assertFalse(bookingResponse.getBooking().isDepositpaid());
        Assertions.assertEquals("Cat Bed",bookingResponse.getBooking().getAdditionalneeds());
    }
    @Then("User cancels the reservation created")
    public void cancelReservation(){
        reservationService.deleteReservation(authKey,bookingResponse.getBookingid());

    }

}
