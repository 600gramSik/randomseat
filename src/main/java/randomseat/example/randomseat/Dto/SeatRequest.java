package randomseat.example.randomseat.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
@AllArgsConstructor
@Getter
@Setter
public class SeatRequest {
    private List<String> names;
    private Map<String, Integer> fixedSeats;

}
