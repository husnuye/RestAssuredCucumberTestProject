package com.hotelreservation.models;

public class BookingResponse {
    private int bookingid;
    private Booking booking;

    public int getBookingid() {
        return bookingid;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
