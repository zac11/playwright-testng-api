package playwright.restfulbooker.data;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookingBuilder {

    /**
     * Builder class for creating the data for a new booking
     */
    private String firstName;
    private String lastName;
    private int totalPrice;
    private boolean depositPaid;
    private BookingDates dates;
    private String additionalNeeds;

}
