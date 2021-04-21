package hr.kingict.flightsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    private String originLocationCode;
    private String destinationLocationCode;
    private String departureDate;
    private String returnDate;
    private Integer departureTransfers;
    private Integer returnTransfers;
    private Integer adults;
    private String currencyCode;
    private Double price;

}
