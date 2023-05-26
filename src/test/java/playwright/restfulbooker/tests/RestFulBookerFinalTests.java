package playwright.restfulbooker.tests;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import playwright.restfulbooker.data.BookingBuilder;
import playwright.restfulbooker.data.BookingDatabuilder;

import static playwright.restfulbooker.data.BookingDatabuilder.getBookingData;

public class RestFulBookerFinalTests extends BaseTest {

    private int bookingId;
    private BookingBuilder bookingData;
    private String authtoken;

    @BeforeClass
    public void setUp(){
        bookingData = getBookingData();
    }

    @Test
    public void createBooking(){

        /**
         * Get the booking data and create a new booking request
         *
         */
        APIResponse response = requestManager.getPostRequest("/booking", RequestOptions.create()
                .setData(bookingData));
    }

}
