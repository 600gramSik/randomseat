package randomseat.example.randomseat.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import randomseat.example.randomseat.Dto.SeatRequest;
import randomseat.example.randomseat.Service.RandomSeatService;

import java.util.Map;

@RestController
@RequestMapping("/assign")
@RequiredArgsConstructor
public class RandomSeatController {
    private final RandomSeatService randomSeatService;
    @PostMapping
    public Map<Integer, String> assignSeats(@RequestBody SeatRequest request) {
        return randomSeatService.assign(request);
    }

}
