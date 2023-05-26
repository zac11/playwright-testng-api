package playwright.restfulbooker.data;

import net.datafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class BookingDatabuilder {

    private static final Faker FAKER = new Faker();

    public static BookingBuilder getBookingData(){
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");

        return BookingBuilder.builder()
                .firstName(FAKER.name()
                        .firstName())
                .lastName(FAKER.name()
                        .lastName())
                .totalPrice(FAKER.number()
                        .numberBetween(1,5000))
                .depositPaid(true)
                .dates(BookingDates.builder()
                        .checkinDate(formatter.format(FAKER.date().past(20, TimeUnit.DAYS)))
                        .checkoutDate(formatter.format(FAKER.date().future(5, TimeUnit.DAYS)))
                        .build())
                .additionalNeeds("Breakfast")
                .build();

    }

    public static partialDataBooking getPartialBookingData() {
        return partialDataBooking.builder()
                .firstName(FAKER.name().firstName())
                .totalPrice(FAKER.number().numberBetween(100, 5000))
                .build();
    }
}


