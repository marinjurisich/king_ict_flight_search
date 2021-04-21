package hr.kingict.flightsearch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightController {

    private FlightService flightService;

    public FlightController(FlightService flightService){
        this.flightService = flightService;
    };

    @PostMapping("/flights")
    public ResponseEntity<List<Flight>> find(@RequestBody FlightDTO flightDTO){

        List<Flight> flights = this.flightService.find(flightDTO);

        if (flights.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(flights);
        }
    }
}
