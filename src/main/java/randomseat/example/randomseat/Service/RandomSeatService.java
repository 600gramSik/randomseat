package randomseat.example.randomseat.Service;

import org.springframework.stereotype.Service;
import randomseat.example.randomseat.Dto.SeatRequest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RandomSeatService {

    public Map<Integer, String> assign(SeatRequest request) {
        List<String> names = new ArrayList<>(request.getNames());
        Map<String, Integer> fixed = request.getFixedSeats();

        // 1. 고정 좌석 제거 (랜덤 대상에서)
        if (fixed != null) {
            names.removeAll(fixed.keySet());
        }

        // 2. 좌석 번호 shuffle
        List<Integer> seats = IntStream.rangeClosed(1, 30).boxed().collect(Collectors.toList());
        Collections.shuffle(seats);

        // 3. 고정 좌석 먼저 배치
        Map<Integer, String> result = new HashMap<>();
        if (fixed != null) {
            for (Map.Entry<String, Integer> entry : fixed.entrySet()) {
                result.put(entry.getValue(), entry.getKey());
                seats.remove(entry.getValue()); // 중복 방지
            }
        }

        // 4. 남은 이름 → 남은 좌석에 랜덤 배정
        for (int i = 0; i < names.size(); i++) {
            result.put(seats.get(i), names.get(i));
        }

        return result;
    }
}