package hr.kingict.flightsearch;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FlightService {

    public List<Flight> find(FlightDTO flightDTO) {

        List<Flight> result = new ArrayList<>();

        Amadeus amadeus = Amadeus
                .builder("I2YCXc7JvE5iQ9oXG3fVJVJXIZbvqnAP", "iJxIS3TKcGuCFZPZ")
                .build();

        try {
            List<FlightOfferSearch> foundFlights = Arrays.asList(
                    amadeus.shopping.flightOffersSearch.get(Params
                            .with("originLocationCode", flightDTO.getOriginLocationCode())
                            .and("destinationLocationCode", flightDTO.getDestinationLocationCode())
                            .and("adults", flightDTO.getAdults())
                            .and("departureDate", flightDTO.getDepartureDate())
                            .and("returnDate", flightDTO.getReturnDate())
                            .and("currencyCode", flightDTO.getCurrencyCode())
                    ));

            for (FlightOfferSearch foundFlight : foundFlights) {

                FlightOfferSearch.Itinerary[] itinerary = foundFlight.getItineraries();
                Integer departureTransfers = itinerary[0].getSegments().length - 1;
                Integer returnTransfers = itinerary[1].getSegments().length - 1;
                Double price = foundFlight.getPrice().getTotal();

                Flight flight = new Flight(
                        flightDTO.getOriginLocationCode(),
                        flightDTO.getDestinationLocationCode(),
                        flightDTO.getDepartureDate(),
                        flightDTO.getReturnDate(),
                        departureTransfers,
                        returnTransfers,
                        flightDTO.getAdults(),
                        flightDTO.getCurrencyCode(),
                        price
                );

                result.add(flight);

            }

        } catch (ResponseException e) {
            System.err.println("Cant't get flights");
        }

        return result;

    }
}
