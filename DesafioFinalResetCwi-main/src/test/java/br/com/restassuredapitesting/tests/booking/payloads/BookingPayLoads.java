package br.com.restassuredapitesting.tests.booking.payloads;

import org.json.JSONObject;

public class BookingPayLoads {
    public static JSONObject payLoadValidBooking(String firstname, String lastname){
        JSONObject payLoad = new JSONObject();
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin","2018-01-01");
        bookingDates.put("checkout", "2019-01-01");
        payLoad.put("firstname",firstname);
        payLoad.put("lastname",lastname);
        payLoad.put("totalprice",120);
        payLoad.put("depositpaid",true);
        payLoad.put("bookingdates",bookingDates);
        payLoad.put("adicionalneeds","breackfast");

        return payLoad;

    }
    public static JSONObject addPayLoadinvalidBooking(String firstname, String lastname){
        JSONObject payLoad = new JSONObject();
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin","2018-01-01");
        bookingDates.put("checkout", "2019-01-01");
        payLoad.put("firstname",firstname);
        payLoad.put("lastne",lastname);
        payLoad.put("totalprice",120);
        payLoad.put("depositpaid",true);
        payLoad.put("bookingdates",bookingDates);
        payLoad.put("adicionalneeds","breackfast");
        payLoad.put("aditional car", true);

        return payLoad;

    }

}
