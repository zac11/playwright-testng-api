package playwright.restfulbooker.tests;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import playwright.restfulbooker.data.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static playwright.restfulbooker.data.BookingDatabuilder.getBookingData;
import static playwright.restfulbooker.data.BookingDatabuilder.getPartialBookingData;

public class RestFulBookerFinalTests extends BaseTest {

    private int bookingId;
    private BookingBuilder bookingData;
    private String authtoken;

    @BeforeClass
    public void setUp(){
        bookingData = BookingDatabuilder.getBookingData();
    }

    @Test
    public void createBooking(){

        /**
         * Get the booking data and create a new booking request
         *
         */
        APIResponse response = requestManager.getPostRequest("/booking", RequestOptions.create()
                .setData(bookingData));

        assertEquals(response.status(),200);
        JSONObject responseObj = new JSONObject(response.text());
        assertNotNull(responseObj.get("bookingid"));

        JSONObject bookingObj = responseObj.getJSONObject("booking");
        JSONObject bookingDates = bookingObj.getJSONObject("bookingdates");
        assertEquals(bookingData.getFirstName(), bookingObj.get("firstname"));

        assertEquals(bookingData.getDates().getCheckinDate(),bookingDates.get("checkin"));

        bookingId = responseObj.getInt("bookingid");


    }

    /**
     * Get the information of a particular booking
     *
     */

    public void getBookedData(){
        APIResponse response = requestManager.getRequest("/booking/"+bookingId);
        assertEquals(response.status(),200);

        JSONObject responseObj = new JSONObject(response.text());
        JSONObject bookingDatesObj = responseObj.getJSONObject("bookingDates");

        assertEquals(bookingData.getFirstName(), responseObj.get("firstname"));
        assertEquals(bookingData.getDates().getCheckinDate(), bookingDatesObj.get("checking"));
    }

    /**
     * Update the booking request
     */
@Test
    public void updateBooking(){
        BookingBuilder updateBooking = BookingDatabuilder.getBookingData();
        APIResponse response = requestManager.putRequest("/booking/"+bookingId,
                        RequestOptions.create().setData(updateBooking)
                        .setHeader("Cookie","token="+authtoken));

        assertEquals(response.status(),200);

        JSONObject responseObj = new JSONObject(response.text());
        JSONObject bookingDatesObject = responseObj.getJSONObject("bookingdates");


        assertEquals(updateBooking.getFirstName(), responseObj.get("firstname"));
        assertEquals(updateBooking.getDates().getCheckoutDate(), bookingDatesObject.get("checkout"));



    }

    @Test
    public void generateToken(){
        TokenCredentials tokenData = TokenBuilder.getToken();
        APIResponse response = requestManager.getPostRequest("/auth",
                RequestOptions.create().setData(tokenData));

        assertEquals(response.status(),200);

        JSONObject responseObj = new JSONObject(response.text());
        String tokenValue = responseObj.getString("token");
        assertNotNull(tokenValue);
        authtoken = tokenValue;
    }


    @Test
    public void updatePartialBooking(){
        partialDataBooking partialBooking = getPartialBookingData();

        APIResponse response = requestManager.patchRequest("/booking/"+bookingId,
                RequestOptions.create().setData(partialBooking)
                        .setHeader("Cookie", "token="+authtoken));


        assertEquals(response.status(),200);
        JSONObject responseObj = new JSONObject(response.text());
        assertEquals(partialBooking.getFirstName(), responseObj.get("firstname"));
        assertEquals(partialBooking.getTotalPrice(), responseObj.get("totalprice"));

    }


    @Test
    public void deleteBooking(){
    APIResponse response = requestManager.deleteRequest("/booking/"+bookingId,
            RequestOptions.create().setHeader("Cookie","token="+authtoken));

    assertEquals(response.status(),200);
    }


    @Test
    public void validateDeleted(){
    APIResponse response = requestManager.deleteRequest("/booking/"+bookingId);
    assertEquals(response.status(),404);
    }




}
