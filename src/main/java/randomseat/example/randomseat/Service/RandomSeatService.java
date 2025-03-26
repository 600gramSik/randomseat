package randomseat.example.randomseat.Service;

import org.springframework.stereotype.Service;
import randomseat.example.randomseat.Dto.SeatRequest;

import java.util.*;

@Service
public class RandomSeatService {

    public Map<Integer, String> assign(SeatRequest request) {
        List<String> names = new ArrayList<>(request.getNames());
        Map<String, Integer> fixed = request.getFixed_seats() != null ? request.getFixed_seats() : new HashMap<>();

        int totalSeats = 30;
        List<Integer> allSeats = new ArrayList<>();
        for (int i = 1; i <= totalSeats; i++) {
            allSeats.add(i);
        }

        Set<Integer> usedSeats = new HashSet<>(fixed.values());

        List<Integer> available = new ArrayList<>();
        for (int seat : allSeats) {
            if (!usedSeats.contains(seat)) {
                available.add(seat);
            }
        }

        Map<Integer, String> assigned = new HashMap<>();
        for (Map.Entry<String, Integer> entry : fixed.entrySet()) {
            assigned.put(entry.getValue(), entry.getKey());
        }

        names.removeAll(fixed.keySet());
        Collections.shuffle(names);

        if (names.size() > available.size()) {
            throw new RuntimeException("좌석이 부족합니다.");
        }

        for (int i = 0; i < names.size(); i++) {
            assigned.put(available.get(i), names.get(i));
        }

        // 결과 정렬
        Map<Integer, String> result = new TreeMap<>();
        for (int seat : allSeats) {
            result.put(seat, assigned.getOrDefault(seat, ""));
        }

        return result;
    }
}
