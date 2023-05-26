package playwright.restfulbooker.data;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookingDates {

    private String checkinDate;
    private String checkoutDate;
}
