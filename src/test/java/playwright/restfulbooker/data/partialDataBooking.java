package playwright.restfulbooker.data;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class partialDataBooking {

    String firstName;
    int totalPrice;


}
