package dev.huuns.chat.plinko;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/api/plinko")
public class PlinkoController {
    private static final Logger log = LoggerFactory.getLogger(PlinkoController.class);
    private HashMap outcomes;
    ArrayList<Double> outcomeList;

    private final ObjectMapper objectMapper;
    int TOTAL_DROPS = 16;
    static HashMap<Integer, Double> MULTIPLIERS = new HashMap<>();
    static {
        MULTIPLIERS.put(0, 16.0);
        MULTIPLIERS.put(1, 9.0);
        MULTIPLIERS.put(2, 2.0);
        MULTIPLIERS.put(3, 1.4);
        MULTIPLIERS.put(4, 1.4);
        MULTIPLIERS.put(5, 1.2);
        MULTIPLIERS.put(6, 1.1);
        MULTIPLIERS.put(7, 1.0);
        MULTIPLIERS.put(8, 0.5);
        MULTIPLIERS.put(9, 1.0);
        MULTIPLIERS.put(10, 1.1);
        MULTIPLIERS.put(11, 1.2);
        MULTIPLIERS.put(12, 1.4);
        MULTIPLIERS.put(13, 1.4);
        MULTIPLIERS.put(14, 2.0);
        MULTIPLIERS.put(15, 9.0);
        MULTIPLIERS.put(16, 16.0);
    }

    public PlinkoController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.outcomes = new HashMap<String, ArrayList<Double>>();
    }

    @PostMapping("/play")
    Plinko play(@RequestBody Object bet) {
        int outcome = 0;
        ArrayList<Character> pattern = new ArrayList<>();
        for(int i = 0; i < TOTAL_DROPS; i++) {
            if(Math.random() > 0.5) {
                pattern.add('R');
                outcome ++;
            } else {
                pattern.add('L');
            }
        }

        try (InputStream inputStream = getClass().getResourceAsStream("/data/outcomes.json")) {
            outcomes = objectMapper.readValue(inputStream, HashMap.class);
            outcomeList = (ArrayList<Double>) outcomes.get(Integer.toString(outcome));

        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON data", e);
        }

        return new Plinko(outcomeList.get((int) Math.floor(Math.random() * outcomeList.size())), MULTIPLIERS.get(outcome), pattern);
    }

}
