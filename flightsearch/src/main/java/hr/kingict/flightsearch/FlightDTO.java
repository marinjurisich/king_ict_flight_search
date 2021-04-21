package hr.kingict.flightsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {

    private String originLocationCode;
    private String destinationLocationCode;
    private String departureDate;
    private String returnDate;
    private Integer adults;
    private String currencyCode;

}
